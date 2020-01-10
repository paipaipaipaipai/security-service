package org.potholes.api.sys;

import java.io.Serializable;
import java.util.List;

public class Authority implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private List<String> checkedKeys;
    private List<MenuTree> menuTree;

    public List<String> getCheckedKeys() {
        return checkedKeys;
    }

    public void setCheckedKeys(List<String> checkedKeys) {
        this.checkedKeys = checkedKeys;
    }

    public List<MenuTree> getMenuTree() {
        return menuTree;
    }

    public void setMenuTree(List<MenuTree> menuTree) {
        this.menuTree = menuTree;
    }

}
