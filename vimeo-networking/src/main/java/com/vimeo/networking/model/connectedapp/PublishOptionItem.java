package com.vimeo.networking.model.connectedapp;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.model.Entity;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * A page or category that can be sent when publishing to a social media platform.
 */
@SuppressWarnings({"unused"})
@UseStag(FieldOption.SERIALIZED_NAME)
public class PublishOptionItem implements Serializable, Entity {

    private static final long serialVersionUID = -4124113112184284361L;

    @Nullable
    @SerializedName("id")
    private String mId;

    @Nullable
    @SerializedName("name")
    private String mName;

    /**
     * @return The ID of the publish item.
     */
    @Nullable
    public String getId() {
        return mId;
    }

    /**
     * Set the ID of the publish item.
     */
    public void setId(@Nullable String id) {
        mId = id;
    }

    /**
     * @return The name or display name of the publish item, i.e.: "art", "family", "vacation" etc.
     */
    @Nullable
    public String getName() {
        return mName;
    }

    /**
     * Set the name or display name of the publich item, i.e.: "art", "family", "vacation" etc.
     */
    public void setName(@Nullable String name) {
        mName = name;
    }

    @SuppressWarnings("EqualsReplaceableByObjectsCall")
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || !o.getClass().equals(getClass())) { return false; }

        final PublishOptionItem that = (PublishOptionItem) o;

        if (mId != null ? !mId.equals(that.mId) : that.mId != null) { return false; }
        return mName != null ? mName.equals(that.mName) : that.mName == null;
    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        return result;
    }

    @Nullable
    @Override
    public String getIdentifier() {
        return mId;
    }
}
