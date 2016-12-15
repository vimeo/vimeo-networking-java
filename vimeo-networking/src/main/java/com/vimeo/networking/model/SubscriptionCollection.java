package com.vimeo.networking.model;

import com.vimeo.stag.GsonAdapterKey;

import java.io.Serializable;
import java.util.Date;

/**
 * A representation of the subscriptions that a user has.
 *
 * @see Subscriptions
 * <p>
 * Created by zetterstromk on 12/15/16.
 */
public class SubscriptionCollection implements Serializable {

    private static final long serialVersionUID = -6392190720319669273L;

    @GsonAdapterKey("uri")
    String mUri;

    @GsonAdapterKey("modified_time")
    Date mModifiedTime;

    @GsonAdapterKey("subscriptions")
    Subscriptions mSubscriptions;

    public String getUri() {
        return mUri;
    }

    public Date getModifiedTime() {
        return mModifiedTime;
    }

    public Subscriptions getSubscriptions() {
        return mSubscriptions;
    }
}
