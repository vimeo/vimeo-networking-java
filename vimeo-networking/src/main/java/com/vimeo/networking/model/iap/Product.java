package com.vimeo.networking.model.iap;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.model.Metadata;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Represents a Product that can be purchased, such as a Vimeo subscription.
 *
 * Created by watsonbr on 10/30/17.
 */
@SuppressWarnings("unused")
@UseStag
public class Product implements Serializable {

    private static final long serialVersionUID = 27901675080110352L;

    @Nullable
    @SerializedName("product_id")
    protected String mProductId;

    @Nullable
    @SerializedName("name")
    protected String mName;

    @Nullable
    @SerializedName("description")
    protected String mDescription;

    @Nullable
    @SerializedName("uri")
    protected String mUri;

    @Nullable
    @SerializedName("metadata")
    protected Metadata mMetadata;

    /**
     * Equals and hashCode use {@link #mProductId} to check for equality.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof Product)) { return false; }

        Product product = (Product) o;
        return mProductId != null ? mProductId.equals(product.mProductId) : product.mProductId == null;
    }

    @Override
    public int hashCode() {
        return mProductId != null ? mProductId.hashCode() : 0;
    }

    public String getProductId() {
        return mProductId;
    }

    public void setProductId(String productId) {
        mProductId = productId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getUri() {
        return mUri;
    }

    public void setUri(String uri) {
        mUri = uri;
    }

    @Nullable
    public Metadata getMetadata() {
        return mMetadata;
    }

    public void setMetadata(@Nullable Metadata metadata) {
        mMetadata = metadata;
    }

    @Override
    public String toString() {
        return "Product{" +
               "mProductId='" + mProductId + '\'' +
               ", mName='" + mName + '\'' +
               ", mDescription='" + mDescription + '\'' +
               ", mUri='" + mUri + '\'' +
               ", mMetadata='" + mMetadata + '\'' +
               '}';
    }
}
