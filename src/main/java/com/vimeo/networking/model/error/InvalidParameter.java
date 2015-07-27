package com.vimeo.networking.model.error;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kylevenn on 7/15/15.
 */
public class InvalidParameter {

    @SerializedName("field")
    private String field;
    @SerializedName("error_code")
    private ErrorCode errorCode;
    @SerializedName("user_message")
    private String userMessage;
    @SerializedName("developer_message")
    private String developerMessage;

    public InvalidParameter(String field, ErrorCode errorCode, String developerMessage) {
        this.field = field;
        this.errorCode = errorCode;
        this.developerMessage = developerMessage;
    }

    public String getField() {
        return field;
    }

    public ErrorCode getErrorCode() {
        return errorCode == null ? ErrorCode.DEFAULT : errorCode;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }
}
