package org.potholes.api;

import java.io.Serializable;

/***
 * 分页返回参数
 * @param <T>
 */
public class Pagination<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer total;
    private T result;

    public Pagination() {
        super();
    }

    public Pagination(Integer total, T result) {
        super();
        this.total = total;
        this.setResult(result);
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}
