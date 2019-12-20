package org.potholes.api.sys;

import java.io.Serializable;

public class SysRole implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3578689042833276674L;

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

    @Override
    public String toString() {
        return "SysRole [roleId=" + roleId + ", roleName=" + roleName + ", roleAlias=" + roleAlias + "]";
    }

}
