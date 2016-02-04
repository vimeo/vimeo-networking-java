/*
 * Copyright (c) 2015 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.vimeo.networking.model.appconfiguration;

import com.google.gson.annotations.SerializedName;

/**
 * An object returned from the /configs endpoint to specify which features are enabled or disabled
 * <p/>
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
    public boolean playTrackingEnabled = true; // Default to true

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
        } else {
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
