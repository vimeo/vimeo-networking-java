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
    public String type;

    @Nullable
    public String festival;

    @Nullable
    public String link;

    @Nullable
    public String text;

    @Nullable
    @SerializedName("pictures")
    public PictureCollection pictureCollection;

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
        return type;
    }

    /**
     * @return The non-user-facing festival description, null if the award is not from a festival
     */
    @Nullable
    public String getFestival() {
        return festival;
    }

    /**
     * @return A link to this content - may be generic (such as "https://vimeo.com/ondemand)
     * or specific (such as "https://vimeo.com/channels/staffpicks/12345")
     */
    @Nullable
    public String getLink() {
        return link;
    }

    /**
     * @return The human-readable name of the badge such as "Staff Pick",
     * "Vimeo On Demand" or "Weekend Challenge"
     */
    @Nullable
    public String getText() {
        return text;
    }

    /**
     * @return The {@link PictureCollection} representing
     * the badge - it should be used to show badge's image
     */
    @Nullable
    public PictureCollection getPictureCollection() {
        return pictureCollection;
    }
    // </editor-fold>
}
