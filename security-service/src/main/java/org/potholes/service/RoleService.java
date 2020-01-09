package org.potholes.service;

import java.util.Date;
import java.util.List;

import org.potholes.api.sys.RoleInfo;
import org.potholes.api.sys.RoleInfoReq;
import org.potholes.constants.GlobalConstants;
import org.potholes.constants.ServerConfig;
import org.potholes.enums.StatusEnum;
import org.potholes.exception.ServiceException;
import org.potholes.mapper.RoleDAO;
import org.potholes.mapper.RoleMenuDAO;
import org.potholes.mapper.UserRoleDAO;
import org.potholes.model.Role;
import org.potholes.utils.RegexUtils;
import org.potholes.utils.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private RoleMenuDAO roleMenuDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;
    @Autowired
    private ServerConfig serverConfig;

    public List<RoleInfo> getAllRoles() {
        return roleDAO.selectAllRoles();
    }

    @Transactional
    public void createRole(RoleInfoReq req) {
        if (!RegexUtils.capitalLetter(req.getRoleName())) {
            throw new ServiceException("角色英文名格式不正确");
        }
        if (!RegexUtils.commonString(req.getRoleAlias())) {
            throw new ServiceException("角色中文名格式不正确");
        }
        String roleName = GlobalConstants.ROLENAME_PREFIX + req.getRoleName().toUpperCase();
        Role r = roleDAO.selectByRoleName(roleName);
        if (r != null) {
            throw new ServiceException("角色已存在");
        }
        Date date = new Date();
        Role role = new Role(UUIDUtil.getId(), roleName, req.getRoleAlias(), StatusEnum.NORMAL.getType(), date, date);
        roleDAO.insert(role);
    }

    @Transactional
    public void saveRole(RoleInfoReq req) {
        if (serverConfig.getAdminRoleId().equals(req.getRoleId())) {
            throw new ServiceException("管理员角色不允许修改");
        }
        Role role = roleDAO.selectByPrimaryKey(req.getRoleId());
        if (role == null) {
            throw new ServiceException("角色不存在");
        }
        role.setRoleAlias(req.getRoleAlias());
        role.setUpdateTime(new Date());
        roleDAO.updateByPrimaryKey(role);
    }

    @Transactional
    public void deleteRole(RoleInfoReq req) {
        if (serverConfig.getAdminRoleId().equals(req.getRoleId())) {
            throw new ServiceException("管理员角色不允许删除");
        }
        Role role = roleDAO.selectByPrimaryKey(req.getRoleId());
        if (role == null) {
            throw new ServiceException("角色不存在");
        }
        roleDAO.deleteByRoleId(req.getRoleId());
        userRoleDAO.deleteByRoleId(req.getRoleId());
        roleMenuDAO.deleteByRoleId(req.getRoleId());
    }

}
