package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by alfredhanssen on 4/25/15.
 */
public class VideoLog implements Serializable {

    private static final long serialVersionUID = -4646869969374079276L;
    public String load;

    public String play;

    @SerializedName("like_press")
    public String like;

    @SerializedName("watchlater_press")
    public String watchLater;
}
