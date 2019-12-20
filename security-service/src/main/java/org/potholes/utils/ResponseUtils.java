package org.potholes.utils;

import org.potholes.api.ResponseData;
import org.potholes.api.ResponseStatus;

public class ResponseUtils {

    /***
     * buildSuccessResponse
     * @param <T>
     * @param data
     * @return
     */
    public static <T> ResponseData<T> buildSuccessResponse() {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setStatus(ResponseStatus.SUCCESS.getStatus());
        responseData.setMessage(ResponseStatus.SUCCESS.getMsg());
        return responseData;
    }

    /***
     * buildSuccessResponse
     * @param <T>
     * @param data
     * @return
     */
    public static <T> ResponseData<T> buildSuccessResponse(T data) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setStatus(ResponseStatus.SUCCESS.getStatus());
        responseData.setMessage(ResponseStatus.SUCCESS.getMsg());
        responseData.setData(data);
        return responseData;
    }

    /***
     * buildErrorResponse
     * @param <T>
     * @param data
     * @return
     */
    public static <T> ResponseData<T> buildErrorResponse(String message) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setStatus(ResponseStatus.ERROR.getStatus());
        responseData.setMessage(message);
        return responseData;
    }

    /***
     * buildResponse
     * @param status
     * @param msg
     * @return
     */
    public static <T> ResponseData<T> buildResponse(Integer status, String msg) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setStatus(status);
        responseData.setMessage(msg);
        return responseData;
    }

    /***
     * buildResponse
     * @param responseStatus
     * @return
     */
    public static <T> ResponseData<T> buildResponse(ResponseStatus responseStatus) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setStatus(responseStatus.getStatus());
        responseData.setMessage(responseStatus.getMsg());
        return responseData;
    }

    /***
     * buildResponse
     * @param responseStatus
     * @param obj
     * @return
     */
    public static <T> ResponseData<T> buildResponse(ResponseStatus responseStatus, T obj) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setStatus(responseStatus.getStatus());
        responseData.setMessage(responseStatus.getMsg());
        responseData.setData(obj);
        return responseData;
    }

}
