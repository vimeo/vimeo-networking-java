package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

@SuppressWarnings({"unused"})
@UseStag
public class PlatformConstraint {

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
