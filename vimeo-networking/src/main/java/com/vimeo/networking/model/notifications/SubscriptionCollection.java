/*
 * Copyright (c) 2017 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
