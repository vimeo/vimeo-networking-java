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
    protected Category category;

    @Nullable
    @SerializedName("type")
    protected String recommendationType;

    /**
     * Null if type is not user
     */
    @Nullable
    protected User user;

    /**
     * Null if type is not channel
     */
    @Nullable
    protected Channel channel;

    @Nullable
    protected String description;

    @Nullable
    @SerializedName("resource_key")
    public String resourceKey;

    // -----------------------------------------------------------------------------------------------------
    // Getters
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Getters">
    @Nullable
    public Category getCategory() {
        return category;
    }

    @Nullable
    public User getUser() {
        return user;
    }

    @Nullable
    public Channel getChannel() {
        return channel;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Nullable
    public String getResourceKey() {
        return resourceKey;
    }

    @NotNull
    public RecommendationType getRecommendationType() {
        if (TYPE_CHANNEL.equals(recommendationType)) {
            return RecommendationType.CHANNEL;
        } else if (TYPE_USER.equals(recommendationType)) {
            return RecommendationType.USER;
        } else {
            return RecommendationType.NONE;
        }
    }
    // </editor-fold>

}
