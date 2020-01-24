package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * An object that represents the blockers keeping a video from being published to each platform.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@UseStag
public class PublishJobBlockers implements Serializable {

    private static final String S_SIZE = "size";
    private static final String S_DURATION = "duration";
    private static final String S_FB_NO_PAGES = "fb_no_pages";
    private static final String S_LI_NO_ORGANIZATIONS = "li_no_organizations";
    private static final String S_YT_NO_CHANNEL = "yt_no_channel";
    private static final long serialVersionUID = -112432222471334867L;

    /**
     * an enumeration of the possible blockers for a video being posted.
     * NOTE: some are of these are platform specific and will be documented as such.
     */
    @UseStag
    public enum Blocker {
        @SerializedName(S_SIZE)
        SIZE(S_SIZE), // The video size is too large.
        @SerializedName(S_DURATION)
        DURATION(S_DURATION), // The video is too long.
        @SerializedName(S_FB_NO_PAGES)
        FB_NO_PAGES(S_FB_NO_PAGES), // The user has no pages connected to their account. (Facebook only)
        @SerializedName(S_LI_NO_ORGANIZATIONS)
        LI_NO_ORGANIZATIONS(S_LI_NO_ORGANIZATIONS), // The user has no organization pages connected to their account. (LinkedIn only)
        @SerializedName(S_YT_NO_CHANNEL)
        YT_NO_CHANNEL(S_YT_NO_CHANNEL); // The user has no YouTube channel associated with their connected google account.


        @NotNull
        private final String mType;

        Blocker(@NotNull String type) {
            mType = type;
        }

        @Override
        public String toString() {
            return mType;
        }
    }

    @SerializedName("facebook")
    private List<Blocker> mFacebook;

    @SerializedName("youtube")
    private List<Blocker> mYoutube;

    @SerializedName("linkedin")
    private List<Blocker> mLinkedin;

    @SerializedName("twitter")
    private List<Blocker> mTwitter;

    /**
     * @return the list of blockers keeping this video from being uploaded to Facebook.
     */
    public List<Blocker> getFacebook() {
        return mFacebook == null ? null : Collections.unmodifiableList(mFacebook);
    }

    /**
     * @return the list of blockers keeping this video from being uploaded to YouTube.
     */
    public List<Blocker> getYoutube() {
        return mYoutube == null ? null : Collections.unmodifiableList(mYoutube);
    }

    /**
     * @return the list of blockers keeping this video from being uploaded to LinkedIn.
     */
    public List<Blocker> getLinkedin() {
        return mLinkedin == null ? null : Collections.unmodifiableList(mLinkedin);
    }

    /**
     * @return the list of blockers keeping this video from being uploaded to Twitter.
     */
    public List<Blocker> getTwitter() {
        return mTwitter == null ? null : Collections.unmodifiableList(mTwitter);
    }

    public void setFacebook(List<Blocker> facebook) {
        mFacebook = facebook == null ? null : Collections.unmodifiableList(facebook);
    }

    public void setYoutube(List<Blocker> youtube) {
        mYoutube = youtube == null ? null : Collections.unmodifiableList(youtube);
    }

    public void setLinkedin(List<Blocker> linkedin) {
        mLinkedin = linkedin == null ? null : Collections.unmodifiableList(linkedin);
    }

    public void setTwitter(List<Blocker> twitter) {
        mTwitter = twitter == null ? null : Collections.unmodifiableList(twitter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        PublishJobBlockers that = (PublishJobBlockers) o;

        if (getFacebook() != null ? !getFacebook().equals(that.getFacebook()) : that.getFacebook() != null) {
            return false;
        }
        if (getYoutube() != null ? !getYoutube().equals(that.getYoutube()) : that.getYoutube() != null) {
            return false;
        }
        if (getLinkedin() != null ? !getLinkedin().equals(that.getLinkedin()) : that.getLinkedin() != null) {
            return false;
        }
        return getTwitter() != null ? getTwitter().equals(that.getTwitter()) : that.getTwitter() == null;
    }

    @Override
    public int hashCode() {
        int result = getFacebook() != null ? getFacebook().hashCode() : 0;
        result = 31 * result + (getYoutube() != null ? getYoutube().hashCode() : 0);
        result = 31 * result + (getLinkedin() != null ? getLinkedin().hashCode() : 0);
        result = 31 * result + (getTwitter() != null ? getTwitter().hashCode() : 0);
        return result;
    }
}

