package org.potholes.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1356617050597158265L;

    private String id;
    private String userName;
    private String nickName;
    private String password;
    private String status;
    private Date createDate;
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [id=").append(id).append(", userName=").append(userName).append(", nickName=")
                .append(nickName).append(", password=").append(password).append(", status=").append(status)
                .append(", createDate=").append(createDate).append(", updateTime=").append(updateTime).append("]");
        return builder.toString();
    }

}
