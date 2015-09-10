package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by alfredhanssen on 4/25/15.
 */
public class VideoLog implements Serializable {

    private static final long serialVersionUID = -4646869969374079276L;
    // URL for the "load" event
    public String load;

    // URL for the "play" event
    public String play;

    // URL for the "like_press" event
    @SerializedName("like_press")
    public String like;

    // URL for the "watchlater_press" event
    @SerializedName("watchlater_press")
    public String watchLater;

    public boolean isLoadEmpty() {
        return load == null || load.isEmpty();
    }

    public boolean isPlayEmpty() {
        return play == null || play.isEmpty();
    }

    public boolean isLikeEmpty() {
        return like == null || like.isEmpty();
    }

    public boolean isWatchLaterEmpty() {
        return watchLater == null || watchLater.isEmpty();
    }
}