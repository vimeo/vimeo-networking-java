package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import java.io.Serializable;

/**
 * An object representing information on previous attempts to publish to
 * third-party social networks.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag
public class PublishJobAttempts implements Serializable {

    private static final long serialVersionUID = 932702517657008794L;

    @SerializedName("facebook")
    private boolean mFacebook;

    @SerializedName("youtube")
    private boolean mYoutube;

    @SerializedName("linkedin")
    private boolean mLinkedin;

    @SerializedName("twitter")
    private boolean mTwitter;

    /**
     * @return true or false depending on whether a previous attempt was made to publish the video
     * to Facebook. Note that if a previous attempt failed, this value will still be true.
     */
    public boolean isFacebook() {
        return mFacebook;
    }

    /**
     * @return true or false depending on whether a previous attempt was made to publish the video
     * to YouTube. Note that if a previous attempt failed, this value will still be true.
     */
    public boolean isYoutube() {
        return mYoutube;
    }

    /**
     * @return true or false depending on whether a previous attempt was made to publish the video
     * to LinkedIn. Note that if a previous attempt failed, this value will still be true.
     */
    public boolean isLinkedin() {
        return mLinkedin;
    }

    /**
     * @return true or false depending on whether a previous attempt was made to publish the
     * video to Twitter. Note that if a previous attempt failed, this value will still be true.
     */
    public boolean isTwitter() {
        return mTwitter;
    }

    public void setFacebook(boolean facebook) {
        this.mFacebook = facebook;
    }

    public void setYoutube(boolean youtube) {
        this.mYoutube = youtube;
    }

    public void setLinkedin(boolean linkedin) {
        this.mLinkedin = linkedin;
    }

    public void setTwitter(boolean twitter) {
        this.mTwitter = twitter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        PublishJobAttempts that = (PublishJobAttempts) o;

        if (isFacebook() != that.isFacebook()) { return false; }
        if (isYoutube() != that.isYoutube()) { return false; }
        if (isLinkedin() != that.isLinkedin()) { return false; }
        return isTwitter() == that.isTwitter();
    }

    @Override
    public int hashCode() {
        int result = (isFacebook() ? 1 : 0);
        result = 31 * result + (isYoutube() ? 1 : 0);
        result = 31 * result + (isLinkedin() ? 1 : 0);
        result = 31 * result + (isTwitter() ? 1 : 0);
        return result;
    }
}
