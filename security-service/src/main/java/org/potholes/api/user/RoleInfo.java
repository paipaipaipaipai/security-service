package org.potholes.api.user;

import java.io.Serializable;

public class RoleInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String roleId;
    private String roleName;
    private String roleAlias;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleAlias() {
        return roleAlias;
    }

    public void setRoleAlias(String roleAlias) {
        this.roleAlias = roleAlias;
    }

}