package org.potholes.model;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String roleId;

    private String roleName;

    private String roleAlias;

    private String status;

    private Date createDate;

    private Date updateTime;

    public Role() {
        super();
    }

    public Role(String roleId, String roleName, String roleAlias, String status, Date createDate, Date updateTime) {
        super();
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleAlias = roleAlias;
        this.status = status;
        this.createDate = createDate;
        this.updateTime = updateTime;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Role [roleId=" + roleId + ", roleName=" + roleName + ", roleAlias=" + roleAlias + ", status=" + status
                + ", createDate=" + createDate + ", updateTime=" + updateTime + "]";
    }

}