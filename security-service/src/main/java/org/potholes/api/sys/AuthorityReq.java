package org.potholes.api.sys;

import java.io.Serializable;
import java.util.List;

public class AuthorityReq implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String roleId;
    private List<String> checkedKeys;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getCheckedKeys() {
        return checkedKeys;
    }

    public void setCheckedKeys(List<String> checkedKeys) {
        this.checkedKeys = checkedKeys;
    }

}
