package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * An object that is used to patch video addition updates to an Album.
 */
public class RemoveVideoFromAlbum implements Serializable {

    private static final long serialVersionUID = 5352714439194578175L;

    public RemoveVideoFromAlbum(@NotNull final String uri) {
        mUri = uri;
    }

    @NotNull
    @SerializedName("uri")
    public String mUri;

    public String getUri() {
        return mUri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || !getClass().equals(o.getClass())) { return false; }

        final RemoveVideoFromAlbum that = (RemoveVideoFromAlbum) o;

        return mUri.equals(that.mUri);
    }

    @Override
    public int hashCode() {
        return mUri.hashCode();
    }

}
