/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Vimeo
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

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * The representation of the user's remaining upload space. Provides information about how much total space is
 * available, how much space has been used, and how much is free. Also provides information about whether the data shown
 * here represents the periodic or lifetime spatial limits imposed on the user, via the {@link Type} enum.
 */
@SuppressWarnings("unused")
@UseStag
public class Space implements Serializable, Quota {

    /**
     * Signifies the periodic or lifetime nature of this spatial limit.
     */
    public enum Type {
        @SerializedName("periodic")
        PERIODIC,
        @SerializedName("lifetime")
        LIFETIME
    }

    private static final long serialVersionUID = -1985382617862372889L;

    @NotNull
    @SerializedName("free")
    private Long mFree;

    @NotNull
    @SerializedName("max")
    private Long mMax;

    @NotNull
    @SerializedName("used")
    private Long mUsed;

    @Nullable
    @SerializedName("showing")
    private Type mType;

    @NotNull
    public Long getFree() {
        return mFree;
    }

    void setFree(@NotNull Long free) {
        mFree = free;
    }

    @NotNull
    public Long getMax() {
        return mMax;
    }

    void setMax(@NotNull Long max) {
        mMax = max;
    }

    @NotNull
    public Long getUsed() {
        return mUsed;
    }

    void setUsed(@NotNull Long used) {
        mUsed = used;
    }

    @Nullable
    public Type getType() {
        return mType;
    }

    void setType(@NotNull Type type) {
        mType = type;
    }
}
