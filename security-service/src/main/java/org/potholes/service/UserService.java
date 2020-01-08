package org.potholes.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.potholes.api.Pagination;
import org.potholes.api.sys.RoleInfo;
import org.potholes.api.sys.UserInfo;
import org.potholes.api.sys.UserInfoReq;
import org.potholes.api.sys.UserSearchReq;
import org.potholes.constants.GlobalConstants;
import org.potholes.constants.ServerConfig;
import org.potholes.enums.StatusEnum;
import org.potholes.exception.ServiceException;
import org.potholes.mapper.UserDAO;
import org.potholes.mapper.UserRoleDAO;
import org.potholes.model.User;
import org.potholes.model.UserRole;
import org.potholes.utils.DateUtils;
import org.potholes.utils.PageUtil;
import org.potholes.utils.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;
    @Autowired
    private ServerConfig serverConfig;

    public Pagination<List<UserInfo>> getUsers(UserSearchReq req) {
        List<UserInfo> result = new ArrayList<UserInfo>();
        String adminUserId = serverConfig.getAdminUserId();
        Integer total = userDAO.countUserByKeywords(req.getKeywords(), adminUserId);
        if (total >= 0) {
            result = userDAO.selectUserByKeywords(req.getKeywords(), adminUserId,
                    PageUtil.getRowBounds(req.getPageNo(), req.getPageSize()));
            if (!CollectionUtils.isEmpty(result)) {
                for (UserInfo userInfo : result) {
                    userInfo.setStatus(StatusEnum.getNameByType(userInfo.getStatus()));
                    userInfo.setCreateDateStr(DateUtils.formatDate(userInfo.getCreateDate(), DateUtils.SDF));
                }
            }
        }
        return new Pagination<List<UserInfo>>(total, result);
    }

    @Transactional
    public void saveUser(UserInfoReq req) {
        Date date = new Date();
        if (StringUtils.isBlank(req.getUserId())) {
            String userId = UUIDUtil.getId();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = encoder.encode(GlobalConstants.DEFAULT_PASSWORD);
            User user = new User(userId, req.getUserName(), req.getUserPhone(), req.getRealName(), password,
                    req.getStatus(), date, date);
            userDAO.insert(user);
            if (!CollectionUtils.isEmpty(req.getRoles())) {
                List<UserRole> roles = new ArrayList<>();
                for (RoleInfo role : req.getRoles()) {
                    roles.add(new UserRole(UUIDUtil.getId(), userId, role.getRoleId(), date, date));
                }
                userRoleDAO.batchInsert(roles);
            }
        } else {
            User u = userDAO.selectByPrimaryKey(req.getUserId());
            if (u == null) {
                throw new ServiceException("用户不存在");
            }
            u.setRealName(req.getRealName());
            u.setUserPhone(req.getUserPhone());
            u.setStatus(req.getStatus());
            u.setUpdateTime(date);
            userDAO.updateByPrimaryKey(u);
            userRoleDAO.deleteByUserId(req.getUserId());
            if (!CollectionUtils.isEmpty(req.getRoles())) {
                List<UserRole> roles = new ArrayList<>();
                for (RoleInfo role : req.getRoles()) {
                    roles.add(new UserRole(UUIDUtil.getId(), req.getUserId(), role.getRoleId(), date, date));
                }
                userRoleDAO.batchInsert(roles);
            }
        }
    }

    @Transactional
    public void deleteUser(UserInfoReq req) {
        userDAO.deleteUserById(req.getUserId());
        userRoleDAO.deleteByUserId(req.getUserId());
    }

    @Transactional
    public void resetPassword(UserInfoReq req) {
        User user = userDAO.selectByPrimaryKey(req.getUserId());
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(GlobalConstants.DEFAULT_PASSWORD);
        user.setPassword(password);
        user.setUpdateTime(new Date());
        userDAO.updateByPrimaryKey(user);
    }

    public UserInfo getUser(UserInfoReq req) {
        User user = userDAO.selectByPrimaryKey(req.getUserId());
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        UserInfo ui = new UserInfo();
        ui.setUserId(user.getUserId());
        ui.setUserName(user.getUserName());
        ui.setRealName(user.getRealName());
        ui.setUserPhone(user.getUserPhone());
        ui.setStatus(user.getStatus());
        ui.setRoles(userDAO.selectRolesByUserId(req.getUserId()));
        return ui;
    }

}
