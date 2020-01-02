package org.potholes.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.potholes.api.Pagination;
import org.potholes.api.user.UserInfo;
import org.potholes.api.user.UserInfoReq;
import org.potholes.api.user.UserSearchReq;
import org.potholes.constants.GlobalConstants;
import org.potholes.enums.StatusEnum;
import org.potholes.exception.ServiceException;
import org.potholes.mapper.UserDAO;
import org.potholes.model.User;
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

    public Pagination<List<UserInfo>> getUsers(UserSearchReq req) {
        List<UserInfo> result = new ArrayList<UserInfo>();
        Integer total = userDAO.countUserByKeywords(req.getKeywords());
        if (total >= 0) {
            result = userDAO.selectUserByKeywords(req.getKeywords(),
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
        String userId = UUIDUtil.getId();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(GlobalConstants.DEFAULT_PASSWORD);
        User user = new User();
        user.setId(userId);
        user.setUserName(req.getUserName());
        user.setRealName(req.getRealName());
        user.setUserPhone(req.getUserPhone());
        user.setPassword(password);
        user.setStatus(req.getStatus());
        user.setCreateDate(date);
        user.setUpdateTime(date);
        userDAO.insert(user);
    }

    @Transactional
    public void deleteUser(UserInfoReq req) {
        userDAO.deleteUserById(req.getUserId());
    }

    public UserInfo getUser(UserInfoReq req) {
        User user = userDAO.selectByPrimaryKey(req.getUserId());
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        UserInfo ui = new UserInfo();
        ui.setUserId(user.getId());
        ui.setUserName(user.getUserName());
        ui.setRealName(user.getRealName());
        ui.setUserPhone(user.getUserPhone());
        ui.setStatus(user.getStatus());
        ui.setCreateDateStr(DateUtils.formatDate(user.getCreateDate(), DateUtils.SDF));
        ui.setRoles(userDAO.selectRolesByUserId(req.getUserId()));
        return ui;
    }

}
