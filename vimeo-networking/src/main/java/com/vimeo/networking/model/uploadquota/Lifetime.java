/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Vimeo
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

package com.vimeo.networking.model.uploadquota;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Represents the lifetime quota limits imposed on the user. If there are no lifetime limits imposed on the user, then
 * the fields will all be {@code null}.
 */
@UseStag
public class Lifetime implements Serializable, Quota {

    private static final long serialVersionUID = 5169142182182319211L;

    @Nullable
    @SerializedName("free")
    private Long mFree;

    @Nullable
    @SerializedName("max")
    private Long mMax;

    @Nullable
    @SerializedName("used")
    private Long mUsed;

    @Nullable
    public Long getFree() {
        return mFree;
    }

    void setFree(@Nullable Long free) {
        mFree = free;
    }

    @Nullable
    public Long getMax() {
        return mMax;
    }

    void setMax(@Nullable Long max) {
        mMax = max;
    }

    @Nullable
    public Long getUsed() {
        return mUsed;
    }

    void setUsed(@Nullable Long used) {
        mUsed = used;
    }
}
