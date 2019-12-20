package org.potholes.service;

import java.util.List;

import org.potholes.api.sys.SysMenu;
import org.potholes.mapper.MenuDAO;
import org.potholes.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuDAO menuDAO;

    public List<SysMenu> getMenusByUser() {
        return menuDAO.getMenusByUser(UserUtil.getCurrentUser().getUserId());
    }

}
