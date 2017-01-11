package com.vimeo.networking.model.notifications;

import com.vimeo.stag.GsonAdapterKey;

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
public class SubscriptionCollection implements Serializable {

    private static final long serialVersionUID = -6392190720319669273L;

    @Nullable
    @GsonAdapterKey("uri")
    String mUri;

    @Nullable
    @GsonAdapterKey("modified_time")
    Date mModifiedTime;

    @Nullable
    @GsonAdapterKey("subscriptions")
    Subscriptions mSubscriptions;

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
