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
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * An object returned from the /configs endpoint containing configuration details for various API features.
 * <p>
 * Created by vennk on 5/20/15.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag(FieldOption.SERIALIZED_NAME)
public class FeaturesConfiguration implements Serializable {

    private static final long serialVersionUID = 8741925066769737513L;

    @Nullable
    @SerializedName("play_tracking")
    private String mPlayTracking;

    /**
     * @return whether play tracking logs should be enabled on the client
     */
    @Nullable
    public String getPlayTracking() {
        return mPlayTracking;
    }

    void setPlayTracking(@Nullable String playTracking) {
        mPlayTracking = playTracking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        final FeaturesConfiguration that = (FeaturesConfiguration) o;

        return mPlayTracking != null ? mPlayTracking.equals(that.mPlayTracking) : that.mPlayTracking == null;

    }

    @Override
    public int hashCode() {
        return mPlayTracking != null ? mPlayTracking.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FeaturesConfiguration{" +
               "mPlayTracking='" + mPlayTracking + '\'' +
               '}';
    }
}
