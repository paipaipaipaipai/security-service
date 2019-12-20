package org.potholes.api;

import java.io.Serializable;

public class ResponseData<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int status;
    private String message;
    private T data;

    public ResponseData() {
        super();
    }

    public ResponseData(int status, String message, T data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
