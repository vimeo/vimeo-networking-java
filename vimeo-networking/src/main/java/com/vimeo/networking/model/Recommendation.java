package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * A model that contains recommendations for a User to follow.
 * A recommendation can either be a {@link User} or {@link Channel}
 * <p/>
 * Created by zetterstromk on 8/15/16.
 */
@SuppressWarnings("unused")
@UseStag
public class Recommendation implements Serializable {

    private static final long serialVersionUID = -1451431453348153582L;

    static final String TYPE_CHANNEL = "channel";
    static final String TYPE_USER = "user";

    public enum RecommendationType {
        NONE,
        CHANNEL,
        USER
    }

    /**
     * Null if not recommended by a category
     */
    @Nullable
    @SerializedName("category")
    protected Category mCategory;

    @Nullable
    @SerializedName("type")
    protected String mRecommendationType;

    /**
     * Null if type is not user
     */
    @Nullable
    @SerializedName("user")
    protected User mUser;

    /**
     * Null if type is not channel
     */
    @Nullable
    @SerializedName("channel")
    protected Channel mChannel;

    @Nullable
    @SerializedName("description")
    protected String mDescription;

    @Nullable
    @SerializedName("resource_key")
    protected String mResourceKey;

    // -----------------------------------------------------------------------------------------------------
    // Getters
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Getters">
    @Nullable
    public Category getCategory() {
        return mCategory;
    }

    @Nullable
    public User getUser() {
        return mUser;
    }

    @Nullable
    public Channel getChannel() {
        return mChannel;
    }

    @Nullable
    public String getDescription() {
        return mDescription;
    }

    @Nullable
    public String getResourceKey() {
        return mResourceKey;
    }

    @NotNull
    public RecommendationType getRecommendationType() {
        if (TYPE_CHANNEL.equals(mRecommendationType)) {
            return RecommendationType.CHANNEL;
        } else if (TYPE_USER.equals(mRecommendationType)) {
            return RecommendationType.USER;
        } else {
            return RecommendationType.NONE;
        }
    }
    // </editor-fold>

}
