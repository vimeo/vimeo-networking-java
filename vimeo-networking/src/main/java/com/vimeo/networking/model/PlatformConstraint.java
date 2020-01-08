package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import java.io.Serializable;

@SuppressWarnings({"unused"})
@UseStag
public class PlatformConstraint implements Serializable {

    private static final long serialVersionUID = -3004640141349461475L;

    @SerializedName("duration")
    private int mDuration;

    @SerializedName("size")
    private Long mSize;

    PlatformConstraint() {
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        this.mDuration = duration;
    }

    public Long getSize() {
        return mSize;
    }

    public void setSize(Long size) {
        this.mSize = size;
    }
}
