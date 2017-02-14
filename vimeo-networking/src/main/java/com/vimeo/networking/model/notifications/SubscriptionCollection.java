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
    protected String uri;

    @Nullable
    @SerializedName("modified_time")
    protected Date modifiedTime;

    @Nullable
    protected Subscriptions subscriptions;

    @Nullable
    public String getUri() {
        return uri;
    }

    @Nullable
    public Date getModifiedTime() {
        return modifiedTime;
    }

    @Nullable
    public Subscriptions getSubscriptions() {
        return subscriptions;
    }
}
