package org.potholes.exception;

import org.potholes.api.ResponseData;
import org.potholes.api.ResponseStatus;
import org.potholes.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    public <T> ResponseData<T> handlerError(Exception e) {
        logger.error("HandlerError error!", e);
        if (e instanceof ServiceException) {
            ServiceException se = (ServiceException) e;
            return ResponseUtils.buildResponse(se.getStatus(), se.getMessage());
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return ResponseUtils.buildResponse(ResponseStatus.METHOD_NOT_SUPPORTED);
        } else {
            return ResponseUtils.buildResponse(ResponseStatus.SYSTEM_ERROR);
        }
    }

}
