package org.potholes.api.sys;

import java.io.Serializable;
import java.util.List;

public class MenuTree implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String menuId;
    private String menuName;
    private List<MenuTree> childMenus;

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

    public List<MenuTree> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<MenuTree> childMenus) {
        this.childMenus = childMenus;
    }

}
