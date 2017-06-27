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
 * An object returned from the /configs endpoint to specify which features are enabled or disabled.
 * <p>
 * Created by vennk on 5/20/15.
 */
@SuppressWarnings("unused")
public class FeaturesConfiguration {

    @SerializedName("iap")
    protected boolean mIapEnabled;

    @SerializedName("autoupload")
    protected boolean mAutoUploadEnabled;

    @SerializedName("comscore")
    protected boolean mComScoreEnabled;

    @SerializedName("play_tracking")
    protected boolean mPlayTrackingEnabled = true; // Default to true

    @SerializedName("chromecast_app_id")
    protected String mChromecastReceiverAppID;

    public boolean isIapEnabled() {
        return mIapEnabled;
    }

    public boolean isAutoUploadEnabled() {
        return mAutoUploadEnabled;
    }

    public boolean isComScoreEnabled() {
        return mComScoreEnabled;
    }

    public boolean isPlayTrackingEnabled() {
        return mPlayTrackingEnabled;
    }

    public String getChromecastReceiverAppID() {
        return mChromecastReceiverAppID;
    }

    public void setIapEnabled(boolean iapEnabled) {
        mIapEnabled = iapEnabled;
    }

    public void setAutoUploadEnabled(boolean autoUploadEnabled) {
        mAutoUploadEnabled = autoUploadEnabled;
    }

    public void setComScoreEnabled(boolean comScoreEnabled) {
        mComScoreEnabled = comScoreEnabled;
    }

    public void setPlayTrackingEnabled(boolean playTrackingEnabled) {
        mPlayTrackingEnabled = playTrackingEnabled;
    }

    public void setChromecastReceiverAppID(String chromecastReceiverAppID) {
        mChromecastReceiverAppID = chromecastReceiverAppID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FeaturesConfiguration that = (FeaturesConfiguration) o;

        if (mIapEnabled != that.mIapEnabled) {
            return false;
        }
        if (mAutoUploadEnabled != that.mAutoUploadEnabled) {
            return false;
        }
        if (mComScoreEnabled != that.mComScoreEnabled) {
            return false;
        }
        if (mPlayTrackingEnabled != that.mPlayTrackingEnabled) {
            return false;
        }
        if (mChromecastReceiverAppID != null && that.mChromecastReceiverAppID != null) {
            return mChromecastReceiverAppID.equals(that.mChromecastReceiverAppID);
        } else {
            return mChromecastReceiverAppID == null && that.mChromecastReceiverAppID == null;
        }
    }

    @Override
    public int hashCode() {
        int result = (mIapEnabled ? 1 : 0);
        result = 31 * result + (mAutoUploadEnabled ? 1 : 0);
        result = 31 * result + (mComScoreEnabled ? 1 : 0);
        result = 31 * result + (mPlayTrackingEnabled ? 1 : 0);
        result = 31 * result + (mChromecastReceiverAppID != null ? mChromecastReceiverAppID.hashCode() : 0);
        return result;
    }
}
