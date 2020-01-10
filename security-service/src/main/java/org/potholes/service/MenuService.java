package org.potholes.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.potholes.api.sys.AuthorityReq;
import org.potholes.api.sys.MenuTree;
import org.potholes.api.sys.SysMenu;
import org.potholes.enums.StatusEnum;
import org.potholes.exception.ServiceException;
import org.potholes.mapper.MenuDAO;
import org.potholes.mapper.RoleDAO;
import org.potholes.mapper.RoleMenuDAO;
import org.potholes.model.Role;
import org.potholes.model.RoleMenu;
import org.potholes.utils.UUIDUtil;
import org.potholes.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class MenuService {

    @Autowired
    private MenuDAO menuDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private RoleMenuDAO roleMenuDAO;

    public List<SysMenu> getMenusByUser() {
        return menuDAO.getMenusByUser(UserUtil.getCurrentUser().getUserId(), StatusEnum.NORMAL.getType());
    }

    public List<MenuTree> menuTree() {
        return menuDAO.getMenuTree(StatusEnum.NORMAL.getType());
    }

    public List<String> roleMenus(AuthorityReq req) {
        return menuDAO.selectMenuIdsByRoleId(req.getRoleId());
    }

    @Transactional
    public void saveAuthority(AuthorityReq req) {
        Role role = roleDAO.selectByPrimaryKey(req.getRoleId());
        if (role == null) {
            throw new ServiceException("角色不存在");
        }
        if (CollectionUtils.isEmpty(req.getCheckedKeys())) {
            roleMenuDAO.deleteByRoleId(req.getRoleId());
        } else {
            List<RoleMenu> roleMenus = new ArrayList<RoleMenu>();
            Date date = new Date();
            for (String s : req.getCheckedKeys()) {
                RoleMenu rm = new RoleMenu();
                rm.setId(UUIDUtil.getId());
                rm.setRoleId(req.getRoleId());
                rm.setMenuId(s);
                rm.setCreateDate(date);
                rm.setUpdateTime(date);
                roleMenus.add(rm);
            }
            roleMenuDAO.deleteByRoleId(req.getRoleId());
            roleMenuDAO.batchInsert(roleMenus);
        }
    }

}
