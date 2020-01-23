package org.potholes.api.sys;

import java.io.Serializable;
import java.util.List;

/***
 * 菜单
 */
public class SysMenu implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String menuId;
    private String menuName;
    private String menuIcon;
    private String menuComponent;
    private String menuRouter;
    private List<SysMenu> childMenus;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuComponent() {
        return menuComponent;
    }

    public void setMenuComponent(String menuComponent) {
        this.menuComponent = menuComponent;
    }

    public String getMenuRouter() {
        return menuRouter;
    }

    public void setMenuRouter(String menuRouter) {
        this.menuRouter = menuRouter;
    }

    public List<SysMenu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<SysMenu> childMenus) {
        this.childMenus = childMenus;
    }

}
