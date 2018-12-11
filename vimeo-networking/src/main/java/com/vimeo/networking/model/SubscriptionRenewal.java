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

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Date;

/**
 * Information about the user's next renewal.
 * <p>
 * Created by brentwatson on 12/3/18.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
@UseStag
public class SubscriptionRenewal implements Serializable {

    private static final long serialVersionUID = 5392446701403620244L;

    /**
     * The date in YYYY-MM-DD format when the user's membership renews (or expires,
     * if they have disabled autorenew). For display only.
     */
    @SerializedName("display_date")
    private String mDisplayDate;

    /**
     * The date the user's membership renews (or expires, if they have disabled autorenew).
     */
    @SerializedName("renewal_date")
    private Date mRenewalDate;

    public String getDisplayDate() {
        return mDisplayDate;
    }

    void setDisplayDate(@Nullable String displayDate) {
        mDisplayDate = displayDate;
    }

    public Date getRenewalDate() {
        return mRenewalDate;
    }

    void setRenewalDate(Date renewalDate) {
        mRenewalDate = renewalDate;
    }

    @Override
    public String toString() {
        return "SubscriptionRenewal{" +
                "mDisplayDate='" + mDisplayDate + '\'' +
                ", mRenewalDate=" + mRenewalDate +
                '}';
    }
}
