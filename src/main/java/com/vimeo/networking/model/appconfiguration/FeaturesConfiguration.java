package com.vimeo.networking.model.appconfiguration;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vennk on 5/20/15.
 */
public class FeaturesConfiguration {

    @SerializedName("iap")
    public boolean iapEnabled;

    @SerializedName("autoupload")
    public boolean autoUploadEnabled;

    @SerializedName("comscore")
    public boolean comScoreEnabled;

    @SerializedName("play_tracking")
    public boolean playTrackingEnabled;

    @SerializedName("chromecast_app_id")
    public String chromecastReceiverAppID;
}
