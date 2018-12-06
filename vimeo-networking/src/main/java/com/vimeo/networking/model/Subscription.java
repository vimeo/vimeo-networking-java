package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Information about the user's subscription.
 * <p>
 * Created by brentwatson on 12/3/18.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
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
