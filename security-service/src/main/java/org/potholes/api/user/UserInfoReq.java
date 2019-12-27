package org.potholes.api.user;

import java.io.Serializable;
import java.util.List;

import org.potholes.api.sys.SysRole;

public class UserInfoReq implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String userId;
    private String userName;
    private String userPhone;
    private String realName;
    private String status;
    private List<SysRole> roles;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

}
