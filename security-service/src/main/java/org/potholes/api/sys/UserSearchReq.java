package org.potholes.api.sys;

import java.io.Serializable;

import org.potholes.api.PageHelper;

public class UserSearchReq extends PageHelper implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String keywords;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

}
