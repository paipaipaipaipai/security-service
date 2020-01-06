package org.potholes.model;

import java.io.Serializable;
import java.util.Date;

public class UserRole implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String userId;

    private String roleId;

    private Date createDate;

    private Date updateTime;

    public UserRole() {
        super();
    }

    public UserRole(String id, String userId, String roleId, Date createDate, Date updateTime) {
        super();
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
        this.createDate = createDate;
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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

}