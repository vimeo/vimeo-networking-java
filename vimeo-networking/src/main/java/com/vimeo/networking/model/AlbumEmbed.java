package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Embed data for a album.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
@UseStag(FieldOption.SERIALIZED_NAME)
public class AlbumEmbed implements Serializable {

    private static final long serialVersionUID = -1793340685715247655L;

    @Nullable
    @SerializedName("html")
    private String mHtml;

    /**
     * @return The responsive HTML code to embed the album on a website. This is present only
     * for non free tier accounts.
     */
    @Nullable
    public String getHtml() {
        return mHtml;
    }

    public void setHtml(@Nullable final String html) {
        mHtml = html;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || !getClass().equals(o.getClass())) { return false; }

        final AlbumEmbed that = (AlbumEmbed) o;

        return mHtml != null ? mHtml.equals(that.mHtml) : that.mHtml == null;
    }

    @Override
    public int hashCode() {
        return mHtml != null ? mHtml.hashCode() : 0;
    }
}

