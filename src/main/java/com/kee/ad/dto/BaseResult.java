package com.kee.ad.dto;

/**
 * 异步请求结果
 * Created by Kee on 2016/10/23.
 */
public class BaseResult<T> {

    private boolean success;
    private T data;
    private String error;

    public BaseResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public BaseResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
