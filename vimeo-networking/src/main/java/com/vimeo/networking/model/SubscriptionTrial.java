package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Information about the user's trial period.
 * <p>
 * Created by brentwatson on 12/3/18.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class SubscriptionTrial implements Serializable {

    private static final long serialVersionUID = -8956377218490008936L;

    private static final String TRIAL_TYPE_FREE_TRIAL = "free_trial";

    public enum TrialType {
        UNKNOWN,
        FREE_TRIAL
    }

    /**
     * The status of the user's trial.
     * If the value is "free_trial" the user is currently in a free trial.
     */
    @Nullable
    @SerializedName("status")
    private String mStatus;

    @Nullable
    public String getStatus() {
        return mStatus;
    }

    void setStatus(String status) {
        mStatus = status;
    }

    public TrialType getTrialStatus() {
        if (TRIAL_TYPE_FREE_TRIAL.equals(mStatus)) {
            return TrialType.FREE_TRIAL;
        } else {
            return TrialType.UNKNOWN;
        }
    }
}
