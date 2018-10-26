package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Group of videos to share publicly or privately.
 */
@SuppressWarnings({"unused"})
@UseStag(FieldOption.SERIALIZED_NAME)
public class Album implements Serializable {

    private static final long serialVersionUID = 1587389468069117149L;

    private static final String S_GRID = "grid";
    private static final String S_PLAYER = "player";
    private static final String S_UNKNOWN = "unknown";
    private static final String S_ADDED_FIRST = "added_first";
    private static final String S_ADDED_LAST = "added_last";
    private static final String S_ALPHABETICAL = "alphabetical";
    private static final String S_ARRANGED = "arranged";
    private static final String S_COMMENTS = "comments";
    private static final String S_LIKES = "likes";
    private static final String S_NEWEST = "newest";
    private static final String S_OLDEST = "oldest";
    private static final String S_PLAYS = "plays";
    private static final String S_DARK = "dark";
    private static final String S_STANDARD = "standard";

    @UseStag
    public enum ThemeType {
        @SerializedName(S_DARK)
        DARK(S_DARK),
        @SerializedName(S_STANDARD)
        STANDARD(S_STANDARD),
        @SerializedName(S_UNKNOWN)
        UNKNOWN(S_UNKNOWN);

        @NotNull
        private final String mType;

        ThemeType(@NotNull String type) {
            mType = type;
        }

        @Override
        public String toString() {
            return mType;
        }
    }

    @UseStag
    public enum LayoutType {
        @SerializedName(S_GRID)
        GRID(S_GRID),
        @SerializedName(S_PLAYER)
        PLAYER(S_PLAYER),
        @SerializedName(S_UNKNOWN)
        UNKNOWN(S_UNKNOWN);

        @NotNull
        private final String mType;

        LayoutType(@NotNull String type) {
            mType = type;
        }

        @Override
        public String toString() {
            return mType;
        }
    }

    @UseStag
    public enum SortType {
        @SerializedName(S_ADDED_FIRST)
        ADDED_FIRST(S_ADDED_FIRST),
        @SerializedName(S_ADDED_LAST)
        ADDED_LAST(S_ADDED_LAST),
        @SerializedName(S_ALPHABETICAL)
        ALPHABETICAL(S_ALPHABETICAL),
        @SerializedName(S_ARRANGED)
        ARRANGED(S_ARRANGED),
        @SerializedName(S_COMMENTS)
        COMMENTS(S_COMMENTS),
        @SerializedName(S_LIKES)
        LIKES(S_LIKES),
        @SerializedName(S_NEWEST)
        NEWEST(S_NEWEST),
        @SerializedName(S_OLDEST)
        OLDEST(S_OLDEST),
        @SerializedName(S_PLAYS)
        PLAYS(S_PLAYS),
        @SerializedName(S_UNKNOWN)
        UNKNOWN(S_UNKNOWN);

        @NotNull
        private final String mType;

        SortType(@NotNull String type) {
            mType = type;
        }

        @Override
        public String toString() {
            return mType;
        }
    }

    @Nullable
    @SerializedName("brand_color")
    private String mBrandColor;

    @Nullable
    @SerializedName("created_time")
    private Date mCreatedTime;

    @Nullable
    @SerializedName("custom_logo")
    private PictureCollection mCustomLogo;

    @Nullable
    @SerializedName("description")
    private String mDescription;

    @Nullable
    @SerializedName("duration")
    private Long mDuration;

    @Nullable
    @SerializedName("embed")
    private AlbumEmbed mEmbed;

    @SerializedName("hide_nav")
    private boolean mHideNav;

    @Nullable
    @SerializedName("layout")
    private Album.LayoutType mLayout;

    @Nullable
    @SerializedName("link")
    private String mLink;

    @Nullable
    @SerializedName("metadata")
    protected Metadata mMetadata;

    @Nullable
    @SerializedName("modified_time")
    private Date mModifiedTime;

    @Nullable
    @SerializedName("name")
    private String mName;

    @Nullable
    @SerializedName("pictures")
    private List<PictureCollection> mPictures;

    @Nullable
    @SerializedName("privacy")
    private AlbumPrivacy mPrivacy;

    @SerializedName("review_mode")
    private boolean mIsReviewMode;

    @Nullable
    @SerializedName("sort")
    private SortType mSort;

    @Nullable
    @SerializedName("theme")
    private ThemeType mTheme;

    @Nullable
    @SerializedName("uri")
    private String mUri;

    @Nullable
    @SerializedName("user")
    private User mUser;

    /**
     * Get the hexadecimal color code for the decorative color.
     * For example, album videos use this color for player buttons.
     */
    @Nullable
    public String getBrandColor() {
        return mBrandColor;
    }

    /**
     * Set the hexadecimal color code for the decorative color.
     * For example, album videos use this color for player buttons.
     */
    public void setBrandColor(@Nullable final String brandColor) {
        mBrandColor = brandColor;
    }

    /**
     * Get the time in ISO 8601 format that the album was created.
     */
    @Nullable
    public Date getCreatedTime() {
        return mCreatedTime == null ? null : new Date(mCreatedTime.getTime());
    }

    /**
     * Set the time in ISO 8601 format that the album was created.
     */
    public void setCreatedTime(@Nullable final Date createdTime) {
        mCreatedTime = createdTime == null ? null : new Date(createdTime.getTime());
    }

    /**
     * Get a custom logo for the album.
     */
    @Nullable
    public PictureCollection getCustomLogo() {
        return mCustomLogo;
    }

    /**
     * Set a custom logo for the album.
     */
    public void setCustomLogo(@Nullable final PictureCollection customLogo) {
        mCustomLogo = customLogo;
    }

    /**
     * Get a description of the album's content.
     */
    @Nullable
    public String getDescription() {
        return mDescription;
    }

    /**
     * Set a description of the album's content.
     */
    public void setDescription(@Nullable final String description) {
        mDescription = description;
    }

    /**
     * Get the total duration in seconds of all the videos in the album.
     */
    @Nullable
    public Long getDuration() {
        return mDuration;
    }

    /**
     * Set the total duration in seconds of all the videos in the album.
     */
    public void setDuration(@Nullable final Long duration) {
        mDuration = duration;
    }

    /**
     * Get the embed data for the album.
     */
    @Nullable
    public AlbumEmbed getEmbed() {
        return mEmbed;
    }

    /**
     * Set the embed data for the album.
     */
    public void setEmbed(@Nullable final AlbumEmbed embed) {
        mEmbed = embed;
    }

    /**
     * Get whether to hide the Vimeo navigation when viewing the album.
     */
    public boolean isHideNav() {
        return mHideNav;
    }

    /**
     * Set whether to hide the Vimeo navigation when viewing the album.
     */
    public void setHideNav(boolean hideNav) {
        mHideNav = hideNav;
    }

    /**
     * Get the album's layout preference.
     */
    @Nullable
    public LayoutType getLayout() {
        return mLayout;
    }

    /**
     * Set the album's layout preference.
     */
    public void setLayout(@Nullable final LayoutType layout) {
        mLayout = layout;
    }

    /**
     * Get the URL to access the album on vimeo's website.
     */
    @Nullable
    public String getLink() {
        return mLink;
    }

    /**
     * Set the URL to access the album on vimeo's website.
     */
    public void setLink(@Nullable final String link) {
        mLink = link;
    }

    /**
     * Get metadata about the album.
     */
    @Nullable
    public Metadata getMetadata() {
        return mMetadata;
    }

    /**
     * Set metadata about the album.
     */
    public void setMetadata(@Nullable final Metadata metadata) {
        mMetadata = metadata;
    }

    /**
     * Get the time in ISO 8601 format that the album was last modified.
     */
    @Nullable
    public Date getModifiedTime() {
        return mModifiedTime == null ? null : new Date(mModifiedTime.getTime());
    }

    /**
     * Set the time in ISO 8601 format that the album was last modified.
     */
    public void setModifiedTime(@Nullable final Date modifiedTime) {
        mModifiedTime = modifiedTime == null ? null : new Date(modifiedTime.getTime());
    }

    /**
     * Set the album's display name.
     */
    @Nullable
    public String getName() {
        return mName;
    }

    /**
     * Get the album's display name.
     */
    public void setName(@Nullable final String name) {
        mName = name;
    }

    /**
     * Get a list of the thumbnails of the first three videos in the album
     */
    @Nullable
    public List<PictureCollection> getPictures() {
        return mPictures == null ? null : new ArrayList<>(mPictures);
    }

    /**
     * Set a list of the thumbnails of the first three videos in the album
     */
    public void setPictures(@Nullable final List<PictureCollection> pictures) {
        mPictures = pictures == null ? null : new ArrayList<>(pictures);
    }

    /**
     * Get the privacy settings of the album.
     */
    @Nullable
    public AlbumPrivacy getPrivacy() {
        return mPrivacy;
    }

    /**
     * Set the privacy settings of the album.
     */
    public void setPrivacy(@Nullable final AlbumPrivacy privacy) {
        mPrivacy = privacy;
    }

    /**
     * Whether album videos should use the review mode URL.
     */
    public boolean isReviewMode() {
        return mIsReviewMode;
    }

    /**
     * Whether album videos should use the review mode URL.
     */
    public void setReviewMode(boolean reviewMode) {
        mIsReviewMode = reviewMode;
    }

    /**
     * Get the sort type of the album list.
     */
    @Nullable
    public SortType getSort() {
        return mSort;
    }

    /**
     * Set the sort type of the album list.
     */
    public void setSort(@Nullable final SortType sort) {
        mSort = sort;
    }

    /**
     * Get the display theme for the album.
     */
    @Nullable
    public ThemeType getTheme() {
        return mTheme;
    }

    /**
     * Set the display theme for the album.
     */
    public void setTheme(@Nullable final ThemeType theme) {
        mTheme = theme;
    }

    /**
     * Get the album's uri to be used via the Vimeo api.
     */
    @Nullable
    public String getUri() {
        return mUri;
    }

    /**
     * Set the album's uri.
     */
    public void setUri(@Nullable final String uri) {
        mUri = uri;
    }

    /**
     * Get the owner of the album.
     */
    @Nullable
    public User getUser() {
        return mUser;
    }

    /**
     * Set the owner of the album.
     */
    public void setUser(@Nullable final User user) {
        mUser = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || !getClass().equals(o.getClass())) { return false; }

        final Album album = (Album) o;

        if (mHideNav != album.mHideNav) { return false; }
        if (mIsReviewMode != album.mIsReviewMode) { return false; }
        if (mBrandColor != null ? !mBrandColor.equals(album.mBrandColor) : album.mBrandColor != null) { return false; }
        if (mCreatedTime != null ? !mCreatedTime.equals(album.mCreatedTime) : album.mCreatedTime != null) {
            return false;
        }
        if (mCustomLogo != null ? !mCustomLogo.equals(album.mCustomLogo) : album.mCustomLogo != null) { return false; }
        if (mDescription != null ? !mDescription.equals(album.mDescription) : album.mDescription != null) {
            return false;
        }
        if (mDuration != null ? !mDuration.equals(album.mDuration) : album.mDuration != null) { return false; }
        if (mEmbed != null ? !mEmbed.equals(album.mEmbed) : album.mEmbed != null) { return false; }
        if (mLayout != album.mLayout) { return false; }
        if (mLink != null ? !mLink.equals(album.mLink) : album.mLink != null) { return false; }
        if (mMetadata != null ? !mMetadata.equals(album.mMetadata) : album.mMetadata != null) { return false; }
        if (mModifiedTime != null ? !mModifiedTime.equals(album.mModifiedTime) : album.mModifiedTime != null) {
            return false;
        }
        if (mName != null ? !mName.equals(album.mName) : album.mName != null) { return false; }
        if (mPictures != null ? !mPictures.equals(album.mPictures) : album.mPictures != null) { return false; }
        if (mPrivacy != null ? !mPrivacy.equals(album.mPrivacy) : album.mPrivacy != null) { return false; }
        if (mSort != album.mSort) { return false; }
        if (mTheme != album.mTheme) { return false; }
        if (mUri != null ? !mUri.equals(album.mUri) : album.mUri != null) { return false; }
        return mUser != null ? mUser.equals(album.mUser) : album.mUser == null;
    }

    @Override
    public int hashCode() {
        int result = mBrandColor != null ? mBrandColor.hashCode() : 0;
        result = 31 * result + (mCreatedTime != null ? mCreatedTime.hashCode() : 0);
        result = 31 * result + (mCustomLogo != null ? mCustomLogo.hashCode() : 0);
        result = 31 * result + (mDescription != null ? mDescription.hashCode() : 0);
        result = 31 * result + (mDuration != null ? mDuration.hashCode() : 0);
        result = 31 * result + (mEmbed != null ? mEmbed.hashCode() : 0);
        result = 31 * result + (mHideNav ? 1 : 0);
        result = 31 * result + (mLayout != null ? mLayout.hashCode() : 0);
        result = 31 * result + (mLink != null ? mLink.hashCode() : 0);
        result = 31 * result + (mMetadata != null ? mMetadata.hashCode() : 0);
        result = 31 * result + (mModifiedTime != null ? mModifiedTime.hashCode() : 0);
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mPictures != null ? mPictures.hashCode() : 0);
        result = 31 * result + (mPrivacy != null ? mPrivacy.hashCode() : 0);
        result = 31 * result + (mIsReviewMode ? 1 : 0);
        result = 31 * result + (mSort != null ? mSort.hashCode() : 0);
        result = 31 * result + (mTheme != null ? mTheme.hashCode() : 0);
        result = 31 * result + (mUri != null ? mUri.hashCode() : 0);
        result = 31 * result + (mUser != null ? mUser.hashCode() : 0);
        return result;
    }
}
