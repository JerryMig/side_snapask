package che.per.sidesnapask.model;

import java.io.Serializable;

/**
 * Created by Jerry on 2018/1/3.
 */

public class HttpResponse<T> implements Serializable {

    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_ERROR = "error";

    private String status;
    private T data;
    protected Object response;
    protected String error_code;

    private Object mExtraInfo;

    public boolean isSuccess() {
        return STATUS_SUCCESS.equals(status);
    }

    public T getData() {
        return data;
    }

    public String getResponse() {
        return (String) response;
    }

    public String getErrorCode() {
        return error_code;
    }

    public void setExtraInfo(Object extraInfo) {
        mExtraInfo = extraInfo;
    }

    public Object getExtraInfo() {
        return mExtraInfo;
    }

}
