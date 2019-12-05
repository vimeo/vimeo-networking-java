package com.vimeo.networking.model.connectedapp;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.model.Entity;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * A `ConnectedApp` represents a connection to a social media platform. Some activities, like simultaneously live
 * stream to multiple destinations, or publishing across platforms, require requesting specific scopes. The scopes
 * required will always be returned in the `neededScopes` array.
 * - Note: Some properties are specific to a particular platform. These cases have been noted in the documentation
 * where relevant.
 */
@SuppressWarnings({"unused"})
@UseStag(FieldOption.SERIALIZED_NAME)
public class ConnectedApp implements Serializable, Entity {

    private static final String S_FACEBOOK = "facebook";
    private static final String S_LINKEDIN = "linkedin";
    private static final String S_TWITTER = "twitter";
    private static final String S_YOUTUBE = "youtube";
    private static final long serialVersionUID = 5238428979625030071L;

    /**
     * An enumeration of the supported connected app types.
     */
    @UseStag
    public enum ConnectedAppType {
        @SerializedName(S_FACEBOOK)
        FACEBOOK(S_FACEBOOK), // Represents a connection to Facebook.
        @SerializedName(S_LINKEDIN)
        LINKED_IN(S_LINKEDIN), // Represents a connection to LinkedIn.
        @SerializedName(S_TWITTER)
        TWITTER(S_TWITTER), // Represents a connection to Twitter.
        @SerializedName(S_YOUTUBE)
        YOUTUBE(S_YOUTUBE); // Represents a connection to YouTube.

        @NotNull
        private final String mType;

        ConnectedAppType(@NotNull String type) {
            mType = type;
        }

        @Override
        public String toString() {
            return mType;
        }
    }


    @Nullable
    @SerializedName("add_date")
    private Date mDateAdded;

    @SerializedName("data_access_is_expired")
    private boolean mIsDataAccessExpired;

    @Nullable
    @SerializedName("needed_scopes")
    private ConnectedScopes mNeededScopes;

    @Nullable
    @SerializedName("pages")
    private List<PublishOptionItem> mPages;

    @Nullable
    @SerializedName("publish_categories")
    private List<PublishOptionItem> mPublishCategories;

    @Nullable
    @SerializedName("third_party_user_id")
    private String mUserId;

    @Nullable
    @SerializedName("type")
    private ConnectedAppType mType;

    @Nullable
    @SerializedName("third_party_user_display_name")
    private String mUserName;

    @Nullable
    @SerializedName("uri")
    private String mUri;

    /**
     * @return The date when the user established this connected app.
     */
    @Nullable
    public Date getDateAdded() {
        return mDateAdded == null ? null : (Date) mDateAdded.clone();
    }

    /**
     * Set the date when the user established this connected app.
     */
    void setDateAdded(@Nullable Date dateAdded) {
        mDateAdded = dateAdded == null ? null : (Date) dateAdded.clone();
    }

    /**
     * @return Whether or not the user's data access is expired (Facebook only).
     */
    public boolean isDataAccessExpired() {
        return mIsDataAccessExpired;
    }

    /**
     * Set whether or not the user's data access is expired (Facebook only).
     */
    void setDataAccessExpired(boolean dataAccessExpired) {
        mIsDataAccessExpired = dataAccessExpired;
    }

    /**
     * @return The list of remaining scopes on this connected app that the user needs for a particular feature.
     */
    @Nullable
    public ConnectedScopes getNeededScopes() {
        return mNeededScopes;
    }

    /**
     * Set the list of remaining scopes on this connected app that the user needs for a particular Vimeo feature.
     */
    void setNeededScopes(@Nullable ConnectedScopes neededScopes) {
        mNeededScopes = neededScopes;
    }

    /**
     * @return The list of third party pages that is associated with the user's account (Facebook and LinkedIn only).
     */
    @Nullable
    public List<PublishOptionItem> getPages() {
        return mPages == null ? null : Collections.unmodifiableList(mPages);
    }

    /**
     * Set the list of third party pages that is associated with the user's account (Facebook and LinkedIn only).
     */
    void setPages(@Nullable List<PublishOptionItem> pages) {
        mPages = pages == null ? null : Collections.unmodifiableList(pages);
    }

    /**
     * @return The list of third party categories that can be selected for a publish to social
     * job (Facebook and YouTube only).
     */
    @Nullable
    public List<PublishOptionItem> getPublishCategories() {
        return mPublishCategories == null ? null : Collections.unmodifiableList(mPublishCategories);
    }

    /**
     * Set the list of third party categories that can be selected for a publish to social
     * job (Facebook and YouTube only).
     */
    void setPublishCategories(@Nullable List<PublishOptionItem> publishCategories) {
        mPublishCategories = publishCategories == null ? null : Collections.unmodifiableList(publishCategories);
    }

    /**
     * @return The unique identifier for the user on this connected app.
     */
    @Nullable
    public String getUserId() {
        return mUserId;
    }

    /**
     * Set the unique identifier for the user on this connected app.
     */
    void setUserId(@Nullable String userId) {
        mUserId = userId;
    }

    /**
     * @return The {@link ConnectedAppType} of the connected app.
     */
    @Nullable
    public ConnectedAppType getType() {
        return mType;
    }

    /**
     * Set the {@link ConnectedAppType} of the connected app.
     */
    void setType(@Nullable ConnectedAppType type) {
        mType = type;
    }

    /**
     * @return The user's display name on the connected app.
     */
    @Nullable
    public String getUserName() {
        return mUserName;
    }

    /**
     * Set the user's display name on the connected app.
     */
    void setUserName(@Nullable String userName) {
        mUserName = userName;
    }

    /**
     * @return The API URI of this connected app.
     */
    @Nullable
    public String getUri() {
        return mUri;
    }

    /**
     * Set the API URI of this connected app.
     */
    void setUri(@Nullable String uri) {
        mUri = uri;
    }

    @SuppressWarnings("EqualsReplaceableByObjectsCall")
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || !o.getClass().equals(getClass())) { return false; }

        final ConnectedApp that = (ConnectedApp) o;

        if (mIsDataAccessExpired != that.mIsDataAccessExpired) { return false; }
        if (mDateAdded != null ? !mDateAdded.equals(that.mDateAdded) : that.mDateAdded != null) { return false; }
        if (mNeededScopes != null ? !mNeededScopes.equals(that.mNeededScopes) : that.mNeededScopes != null) {
            return false;
        }
        if (mPages != null ? !mPages.equals(that.mPages) : that.mPages != null) { return false; }
        if (mPublishCategories != null ?
                !mPublishCategories.equals(that.mPublishCategories) : that.mPublishCategories != null) {
            return false;
        }
        if (mUserId != null ? !mUserId.equals(that.mUserId) : that.mUserId != null) { return false; }
        if (mType != that.mType) { return false; }
        if (mUserName != null ? !mUserName.equals(that.mUserName) : that.mUserName != null) { return false; }
        return mUri != null ? mUri.equals(that.mUri) : that.mUri == null;
    }

    @Override
    public int hashCode() {
        int result = mDateAdded != null ? mDateAdded.hashCode() : 0;
        result = 31 * result + (mIsDataAccessExpired ? 1 : 0);
        result = 31 * result + (mNeededScopes != null ? mNeededScopes.hashCode() : 0);
        result = 31 * result + (mPages != null ? mPages.hashCode() : 0);
        result = 31 * result + (mPublishCategories != null ? mPublishCategories.hashCode() : 0);
        result = 31 * result + (mUserId != null ? mUserId.hashCode() : 0);
        result = 31 * result + (mType != null ? mType.hashCode() : 0);
        result = 31 * result + (mUserName != null ? mUserName.hashCode() : 0);
        result = 31 * result + (mUri != null ? mUri.hashCode() : 0);
        return result;
    }

    @Nullable
    @Override
    public String getIdentifier() {
        return mUri;
    }
}
