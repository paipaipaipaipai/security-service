package org.potholes.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String userId;
    private String userName;
    private String userPhone;
    private String realName;
    private String password;
    private String status;
    private Date createDate;
    private Date updateTime;

    public User() {
        super();
    }

    public User(String userId, String userName, String userPhone, String realName, String password, String status,
            Date createDate, Date updateTime) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.realName = realName;
        this.password = password;
        this.status = status;
        this.createDate = createDate;
        this.updateTime = updateTime;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

}
