package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * The privacy set for an album.
 */
@SuppressWarnings({"unused"})
@UseStag(FieldOption.SERIALIZED_NAME)
public class AlbumPrivacy implements Serializable {

    private static final String S_ANYBODY = "anybody";
    private static final String S_EMBED_ONLY = "embed_only";
    private static final String S_PASSWORD = "password";
    private static final String S_UNKNOWN = "unknown";
    private static final long serialVersionUID = -4715743289843427780L;

    @UseStag
    public enum AlbumPrivacyViewValue {
        @SerializedName(S_ANYBODY)
        ANYBODY(S_ANYBODY),
        @SerializedName(S_EMBED_ONLY)
        EMBED_ONLY(S_EMBED_ONLY),
        @SerializedName(S_PASSWORD)
        PASSWORD(S_PASSWORD),
        @SerializedName(S_UNKNOWN)
        UNKNOWN(S_UNKNOWN);

        @NotNull
        private final String mType;

        AlbumPrivacyViewValue(@NotNull String type) {
            mType = type;
        }

        @Override
        public String toString() {
            return mType;
        }
    }

    @Nullable
    @SerializedName("password")
    private String mPassword;

    @Nullable
    @SerializedName("view")
    private AlbumPrivacyViewValue mViewingPermissions;

    /**
     * Get the privacy-enabled password to see this album. Present only when privacy.view is password and
     * when the album belongs to the authenticated user.
     */
    @Nullable
    public String getPassword() {
        return mPassword;
    }

    /**
     * Set the privacy-enabled password to see this album.
     */
    public void setPassword(@Nullable final String password) {
        mPassword = password;
    }

    /**
     * Get who can view the album.
     */
    @Nullable
    public AlbumPrivacyViewValue getViewingPermissions() {
        return mViewingPermissions;
    }

    /**
     * Set who can view the album.
     */
    public void setViewingPermissions(@Nullable final AlbumPrivacyViewValue viewingPermissions) {
        mViewingPermissions = viewingPermissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || !getClass().equals(o.getClass())) { return false; }

        final AlbumPrivacy that = (AlbumPrivacy) o;

        if (mPassword != null ? !mPassword.equals(that.mPassword) : that.mPassword != null) { return false; }
        return mViewingPermissions == that.mViewingPermissions;
    }

    @Override
    public int hashCode() {
        int result = mPassword != null ? mPassword.hashCode() : 0;
        result = 31 * result + (mViewingPermissions != null ? mViewingPermissions.hashCode() : 0);
        return result;
    }
}
