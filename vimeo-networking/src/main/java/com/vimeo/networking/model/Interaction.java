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

package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This model object represents an Interaction.
 * Created by zetterstromk on 6/5/15.
 */
@SuppressWarnings("unused")
@UseStag
public class Interaction implements Serializable {

    private static final long serialVersionUID = -5159299959634106837L;

    private static final String PURCHASED = "purchased";
    private static final String RESTRICTED = "restricted";
    private static final String AVAILABLE = "available";
    private static final String UNAVAILABLE = "unavailable";

    @UseStag
    public enum Stream {
        @SerializedName(Interaction.PURCHASED)
        PURCHASED(Interaction.PURCHASED), // you have purchased it (without using a promo code)
        @SerializedName(Interaction.RESTRICTED)
        RESTRICTED(Interaction.RESTRICTED), // you don't have it, can't purchase it in this country
        @SerializedName(Interaction.AVAILABLE)
        AVAILABLE(Interaction.AVAILABLE), // you don't have it, can purchase it
        @SerializedName(Interaction.UNAVAILABLE)
        UNAVAILABLE(Interaction.UNAVAILABLE); // you don't have it, can't purchase it

        @NotNull
        private final String mName;

        Stream(@NotNull String name) {
            mName = name;
        }

        @Override
        public String toString() {
            return mName;
        }
    }

    @UseStag
    public enum IapStatus {
        @SerializedName(Interaction.PURCHASED)
        PURCHASED(Interaction.PURCHASED),
        @SerializedName(Interaction.AVAILABLE)
        AVAILABLE(Interaction.AVAILABLE),
        @SerializedName(Interaction.UNAVAILABLE)
        UNAVAILABLE(Interaction.UNAVAILABLE);

        @NotNull
        private final String mName;

        IapStatus(@NotNull String name) {
            mName = name;
        }

        @Override
        public String toString() {
            return mName;
        }
    }

    @SerializedName("added")
    protected boolean mAdded;

    @Nullable
    @SerializedName("added_time")
    protected Date mAddedTime;

    @Nullable
    @SerializedName("uri")
    protected String mUri;

    @Nullable
    @SerializedName("stream")
    protected Stream mStream;

    @Nullable
    @SerializedName("status")
    protected IapStatus mStatus;

    @Nullable
    @SerializedName("expires_time")
    protected Date mExpiration;

    @Nullable
    @SerializedName("reason")
    protected ArrayList<String> mReportingReasons;

    public boolean isAdded() {
        return mAdded;
    }

    @Nullable
    public Date getAddedTime() {
        return mAddedTime;
    }

    @Nullable
    public String getUri() {
        return mUri;
    }

    @Nullable
    public Stream getStream() {
        return mStream;
    }

    @Nullable
    public Date getExpiration() {
        return mExpiration;
    }

    public void setIsAdded(boolean added) {
        mAdded = added;
    }

    /**
     * @return a {@code List<String>} containing the allowable reasons that can be sent to the API
     * when POSTing a report of a terms of service violation. This method will return null in the event
     * that it is not a "report" {@link Interaction}
     */
    @Nullable
    public List<String> getReportingReasons() {
        return mReportingReasons;
    }
}
