package com.vimeo.networking.model.error;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by zetterstromk on 6/3/15.
 */
public class VimeoErrorBody {

    @SerializedName("error")
    public String errorMessage;
    @SerializedName("link")
    public String link;
    @SerializedName("developer_message")
    public String developerMessage;
    @SerializedName("error_code")
    public String errorCode;
    @SerializedName("invalid_parameters")
    public JsonObject invalidParameters;

    public VimeoErrorBody() {

    }

    public VimeoErrorBody(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
