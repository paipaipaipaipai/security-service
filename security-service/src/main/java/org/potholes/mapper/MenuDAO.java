package org.potholes.mapper;

import java.util.List;

import org.potholes.api.sys.SysMenu;
import org.potholes.model.Menu;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDAO {
    int insert(Menu record);

    Menu selectByPrimaryKey(String menuId);

    int updateByPrimaryKey(Menu record);

    List<Menu> selectAllMenus(String status);

    List<SysMenu> getMenusByUser(String userId);

}