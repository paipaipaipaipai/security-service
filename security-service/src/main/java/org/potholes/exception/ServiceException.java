package org.potholes.exception;

import org.potholes.api.ResponseStatus;

public class ServiceException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int status = ResponseStatus.ERROR.getStatus();

    private String message = ResponseStatus.ERROR.getMsg();

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ServiceException(ResponseStatus responseStatus) {
        this.status = responseStatus.getStatus();
        this.message = responseStatus.getMsg();
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

}