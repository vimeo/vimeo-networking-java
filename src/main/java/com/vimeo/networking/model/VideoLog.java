package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alfredhanssen on 4/25/15.
 */
public class VideoLog {

    public String load;

    public String play;

    @SerializedName("like_press")
    public String like;

    @SerializedName("watchlater_press")
    public String watchLater;
}
