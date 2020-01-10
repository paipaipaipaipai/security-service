package org.potholes.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.potholes.api.sys.AuthorityReq;
import org.potholes.api.sys.MenuTree;
import org.potholes.api.sys.SysMenu;
import org.potholes.model.Menu;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDAO {
    int insert(Menu record);

    Menu selectByPrimaryKey(String menuId);

    int updateByPrimaryKey(Menu record);

    List<Menu> selectAllMenus(String status);

    List<SysMenu> getMenusByUser(@Param("userId") String userId, @Param("status") String status);

    void menuTree(AuthorityReq req);

    List<String> selectMenuIdsByRoleId(String roleId);

    List<MenuTree> getMenuTree(String status);

}