package com.vimeo.networking.model.iap;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.model.Metadata;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Represents a Product that can be purchased, such as a Vimeo subscription.
 * <p>
 * Created by brentwatson on 10/30/17.
 */
@SuppressWarnings("unused")
@UseStag(FieldOption.SERIALIZED_NAME)
public class Product implements Serializable {

    private static final long serialVersionUID = -8874937816078232655L;

    @Nullable
    @SerializedName("product_id")
    private String mProductId;

    @Nullable
    @SerializedName("name")
    private String mName;

    @Nullable
    @SerializedName("description")
    private String mDescription;

    @Nullable
    @SerializedName("billing_period")
    private String mBillingPeriod;

    @Nullable
    @SerializedName("uri")
    private String mUri;

    @Nullable
    @SerializedName("metadata")
    private Metadata mMetadata;

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

    /**
     * @return ID for the product (aka product SKU).  This is a unique value for the product.
     */
    @Nullable
    public String getProductId() {
        return mProductId;
    }

    void setProductId(@Nullable String productId) {
        mProductId = productId;
    }

    /**
     * @return Product name.
     */
    @Nullable
    public String getName() {
        return mName;
    }

    void setName(@Nullable String name) {
        mName = name;
    }

    /**
     * @return Product description.
     */
    @Nullable
    public String getDescription() {
        return mDescription;
    }

    void setDescription(@Nullable String description) {
        mDescription = description;
    }

    @Nullable
    public String getBillingPeriod() {
        return mBillingPeriod;
    }

    void setBillingPeriod(@Nullable String billingPeriod) {
        mBillingPeriod = billingPeriod;
    }

    /**
     * @return relative URI that can be used to request product details.
     */
    @Nullable
    public String getUri() {
        return mUri;
    }

    void setUri(@Nullable String uri) {
        mUri = uri;
    }

    /**
     * @return {@link #mMetadata}.
     */
    @Nullable
    public Metadata getMetadata() {
        return mMetadata;
    }

    void setMetadata(@Nullable Metadata metadata) {
        mMetadata = metadata;
    }

    @Override
    public String toString() {
        return "Product{" +
               "mProductId='" + mProductId + '\'' +
               ", mName='" + mName + '\'' +
               ", mDescription='" + mDescription + '\'' +
               ", mBillingPeriod='" + mBillingPeriod + '\'' +
               ", mUri='" + mUri + '\'' +
               ", mMetadata='" + mMetadata + '\'' +
               '}';
    }
}
