package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * An object that is used to patch video addition updates to an Album.
 */
public class AddVideoToAlbum implements Serializable {

    private static final long serialVersionUID = -855083653273120166L;

    public AddVideoToAlbum(@NotNull String uri, int position) {
        mUri = uri;
        mPosition = position;
    }

    @NotNull
    @SerializedName("uri")
    public String mUri;

    @SerializedName("position")
    public int mPosition;

    public String getUri() {
        return mUri;
    }

    public int getPosition() {
        return mPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || !getClass().equals(o.getClass())) { return false; }

        final AddVideoToAlbum that = (AddVideoToAlbum) o;

        if (mPosition != that.mPosition) { return false; }
        return mUri.equals(that.mUri);
    }

    @Override
    public int hashCode() {
        int result = mUri.hashCode();
        result = 31 * result + mPosition;
        return result;
    }
}
