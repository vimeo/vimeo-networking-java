package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * A model representing info about the file transfer page.
 */
@SuppressWarnings("unused")
@UseStag
public class FileTransferPage implements Serializable {

    private static final long serialVersionUID = 4370717113633153800L;

    @Nullable
    @SerializedName("link")
    private String mLink;

    @Nullable
    public String getLink() {
        return mLink;
    }

    public void setLink(@Nullable String link) {
        mLink = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || !getClass().equals(o.getClass())) { return false; }
        final FileTransferPage that = (FileTransferPage) o;
        return mLink == null || mLink.equals(that.mLink);
    }

    @Override
    public int hashCode() {
        return mLink != null ? mLink.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FileTransferPage{" +
               "mLink='" + mLink + '\'' +
               '}';
    }
}
