package com.vimeo.networking.model.notifications;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Date;

/**
 * A representation of the {@link Subscriptions} that a user has.
 *
 * @see Subscriptions
 * <p>
 * Created by zetterstromk on 12/15/16.
 */
@SuppressWarnings("unused")
@UseStag
public class SubscriptionCollection implements Serializable {

    private static final long serialVersionUID = -6392190720319669273L;

    @Nullable
    @SerializedName("uri")
    protected String mUri;

    @Nullable
    @SerializedName("modified_time")
    protected Date mModifiedTime;

    @Nullable
    @SerializedName("subscriptions")
    protected Subscriptions mSubscriptions;

    @Nullable
    public String getUri() {
        return mUri;
    }

    @Nullable
    public Date getModifiedTime() {
        return mModifiedTime;
    }

    @Nullable
    public Subscriptions getSubscriptions() {
        return mSubscriptions;
    }
}
