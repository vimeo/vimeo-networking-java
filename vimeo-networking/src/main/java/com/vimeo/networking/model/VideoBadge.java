package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * A model representing a "badge" that a video can display. The badge represents
 * an award, and is usually displayed over the thumbnail of the video, or near
 * video data (name, description, etc)
 * Created by zetterstromk on 10/4/16.
 */
@SuppressWarnings("unused")
@UseStag
public class VideoBadge implements Serializable {

    private static final long serialVersionUID = -5343389171512787927L;

    @Nullable
    @SerializedName("type")
    protected String mType;

    @Nullable
    @SerializedName("festival")
    protected String mFestival;

    @Nullable
    @SerializedName("link")
    protected String mLink;

    @Nullable
    @SerializedName("text")
    protected String mText;

    @Nullable
    @SerializedName("pictures")
    protected PictureCollection mPictureCollection;

    // -----------------------------------------------------------------------------------------------------
    // Getters
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Getters">

    /**
     * A type of the badge, such as "staffpick", "vod", or "weekendchallenge". These are
     * not meant to be user-facing. These types may change over time, which is why this is
     * a string rather than an enum.
     *
     * @return The non-user-facing name of the badge
     */
    @Nullable
    public String getType() {
        return mType;
    }

    /**
     * @return The non-user-facing festival description, null if the award is not from a festival
     */
    @Nullable
    public String getFestival() {
        return mFestival;
    }

    /**
     * @return A link to this content - may be generic (such as "https://vimeo.com/ondemand)
     * or specific (such as "https://vimeo.com/channels/staffpicks/12345")
     */
    @Nullable
    public String getLink() {
        return mLink;
    }

    /**
     * @return The human-readable name of the badge such as "Staff Pick",
     * "Vimeo On Demand" or "Weekend Challenge"
     */
    @Nullable
    public String getText() {
        return mText;
    }

    /**
     * @return The {@link PictureCollection} representing
     * the badge - it should be used to show badge's image
     */
    @Nullable
    public PictureCollection getPictureCollection() {
        return mPictureCollection;
    }
    // </editor-fold>
}
