package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Information about the user's next renewal.
 * <p>
 * Created by brentwatson on 12/3/18.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class SubscriptionRenewal implements Serializable {

    private static final long serialVersionUID = -8336460441455505994L;

    /**
     * The date in YYYY-MM-DD format when the user's membership renews (or expires,
     * if they have disabled autorenew). For display only.
     */
    @SerializedName("display_date")
    private String mDisplayDate;

    public String getDisplayDate() {
        return mDisplayDate;
    }

    void setDisplayDate(@Nullable String displayDate) {
        mDisplayDate = displayDate;
    }

    @Override
    public String toString() {
        return "SubscriptionRenewal{" +
                "mDisplayDate='" + mDisplayDate + '\'' +
                '}';
    }
}
