package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * This object contains user membership information.
 * <p>
 * Created by brentwatson on 12/3/18.
 */
@SuppressWarnings("unused")
@UseStag
public class Membership implements Serializable {

    private static final long serialVersionUID = 2005778103318600521L;

    /**
     * The user's membership level
     */
    @SerializedName("display")
    private String mDisplay;

    /**
     * The user's account type.
     */
    @Nullable
    @SerializedName("type")
    private String mType;

    /**
     * Information about the user's badge.
     */
    @Nullable
    @SerializedName("badge")
    private UserBadge mBadge;

    /**
     * Information about the user's subscription.
     */
    @Nullable
    @SerializedName("subscription")
    private Subscription mSubscription;

    @Nullable
    public String getDisplay() {
        return mDisplay;
    }

    void setDisplay(@Nullable String display) {
        mDisplay = display;
    }

    @Nullable
    public String getType() {
        return mType;
    }

    void setType(@Nullable String type) {
        mType = type;
    }

    @Nullable
    public UserBadge getBadge() {
        return mBadge;
    }

    void setBadge(@Nullable UserBadge badge) {
        mBadge = badge;
    }

    @Nullable
    public Subscription getSubscription() {
        return mSubscription;
    }

    void setSubscription(@Nullable Subscription subscription) {
        mSubscription = subscription;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "mDisplay='" + mDisplay + '\'' +
                ", mType=" + mType +
                ", mBadge=" + mBadge +
                ", mSubscription=" + mSubscription +
                '}';
    }

    public UserBadge.UserBadgeType getUserBadgeType() {
        return mBadge == null ? UserBadge.UserBadgeType.NONE : mBadge.getBadgeType();
    }
}
