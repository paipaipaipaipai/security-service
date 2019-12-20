package org.potholes.api;

import java.io.Serializable;

/***
 * 分页请求参数
 */
public class PageHelper implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer offset;
    private Integer limit;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

}