package org.potholes.api.sys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String userId;
    private String userName;
    private String userPhone;
    private String realName;
    private String status;
    private Date createDate;
    private String createDateStr;
    private List<RoleInfo> roleInfos;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public List<RoleInfo> getRoles() {
        return roleInfos;
    }

    public void setRoles(List<RoleInfo> roleInfos) {
        this.roleInfos = roleInfos;
    }

}
