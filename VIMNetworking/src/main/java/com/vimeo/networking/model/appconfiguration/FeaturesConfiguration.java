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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FeaturesConfiguration that = (FeaturesConfiguration) o;

        if (iapEnabled != that.iapEnabled) {
            return false;
        }
        if (autoUploadEnabled != that.autoUploadEnabled) {
            return false;
        }
        if (comScoreEnabled != that.comScoreEnabled) {
            return false;
        }
        if (playTrackingEnabled != that.playTrackingEnabled) {
            return false;
        }
        if (chromecastReceiverAppID != null && that.chromecastReceiverAppID != null) {
            return chromecastReceiverAppID.equals(that.chromecastReceiverAppID);
        }
        else
        {
            return chromecastReceiverAppID == null && that.chromecastReceiverAppID == null;
        }
    }

    @Override
    public int hashCode() {
        int result = (iapEnabled ? 1 : 0);
        result = 31 * result + (autoUploadEnabled ? 1 : 0);
        result = 31 * result + (comScoreEnabled ? 1 : 0);
        result = 31 * result + (playTrackingEnabled ? 1 : 0);
        result = 31 * result + (chromecastReceiverAppID != null ? chromecastReceiverAppID.hashCode() : 0);
        return result;
    }
}
