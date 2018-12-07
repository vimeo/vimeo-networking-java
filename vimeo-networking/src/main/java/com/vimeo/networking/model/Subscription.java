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

/**
 * Information about the user's subscription.
 * <p>
 * Created by brentwatson on 12/3/18.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
@UseStag
public class Subscription implements Serializable {

    private static final long serialVersionUID = -4419561580922152031L;

    /**
     * Information about the user's next renewal.
     */
    @SerializedName("renewal")
    private SubscriptionRenewal mRenewal;

    /**
     * Information about the user's trial period.
     */
    @SerializedName("trial")
    private SubscriptionTrial mTrial;

    public SubscriptionRenewal getRenewal() {
        return mRenewal;
    }

    void setRenewal(@Nullable SubscriptionRenewal renewal) {
        mRenewal = renewal;
    }

    public SubscriptionTrial getTrial() {
        return mTrial;
    }

    void setTrial(@Nullable SubscriptionTrial trial) {
        mTrial = trial;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "mRenewal=" + mRenewal +
                ", mTrial=" + mTrial +
                '}';
    }
}
