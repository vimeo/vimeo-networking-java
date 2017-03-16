/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vimeo
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
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Date;

/**
 * A special {@link Interaction} for use with SVOD
 * <p>
 * Created by zetterstromk on 3/15/17.
 */
@UseStag(FieldOption.SERIALIZED_NAME)
public class SvodInteraction extends Interaction {

    private static final long serialVersionUID = -3156332163901015302L;

    @Nullable
    @SerializedName("purchase_time")
    protected Date mPurchaseDate;

    @Nullable
    @SerializedName("options")
    protected ArrayList<String> mOptions;

    @Nullable
    @SerializedName("status")
    protected String mStatus;

    /**
     * @return The time when the subscription was purchased. Null if not subscribed.
     */
    @Nullable
    public Date getPurchaseDate() {
        return mPurchaseDate;
    }

    /**
     * @return An array of valid HTTP methods for this endpoint.
     */
    @Nullable
    public ArrayList<String> getOptions() {
        return mOptions;
    }

    /**
     * @return An indicator describing the purchase type. This may only be sent when this interaction
     * is contained on a User. Use {@link #getPurchaseDate()} and {@link #getExpiration()} as a better
     * indicator of the status of the description.
     */
    @Nullable
    public String getStatus() {
        return mStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        SvodInteraction that = (SvodInteraction) o;

        if (mPurchaseDate != null ? !mPurchaseDate.equals(that.mPurchaseDate) : that.mPurchaseDate != null) {
            return false;
        }
        if (mOptions != null ? !mOptions.equals(that.mOptions) : that.mOptions != null) { return false; }
        if (mStatus != null ? !mStatus.equals(that.mStatus) : that.mStatus != null) { return false; }
        if (mUri != null ? !mUri.equals(that.mUri) : that.mUri != null) { return false; }
        return mExpiration != null ? mExpiration.equals(that.mExpiration) : that.mExpiration == null;

    }

    @Override
    public int hashCode() {
        int result = mPurchaseDate != null ? mPurchaseDate.hashCode() : 0;
        result = 31 * result + (mOptions != null ? mOptions.hashCode() : 0);
        result = 31 * result + (mStatus != null ? mStatus.hashCode() : 0);
        result = 31 * result + (mUri != null ? mUri.hashCode() : 0);
        result = 31 * result + (mExpiration != null ? mExpiration.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SvodInteraction{" +
               "mPurchaseDate=" + mPurchaseDate +
               ", mOptions=" + mOptions +
               ", mStatus='" + mStatus + '\'' +
               ", mUri='" + mUri + '\'' +
               ", mExpiration=" + mExpiration +
               '}';
    }
}
