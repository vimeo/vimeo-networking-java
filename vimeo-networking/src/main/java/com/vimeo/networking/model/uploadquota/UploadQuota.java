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
 * The representation of the user's upload quota. It consists of three fields that provided details about the user's
 * quota.
 * <p>
 * "space": The amount of space the user has left before they will be stopped from uploading. Use this field to
 * determine if a file the user is about to upload will breach their quota. The quota returned in this field may be
 * either the users periodic or lifetime quota depending on their account type.
 * <p>
 * "periodic": The quota that resets after a certain period of time. For example, basic users are only allowed to upload
 * ~500 MB per week, but their total storage space is much larger. If the user does not have the restriction of a
 * periodic quota, the field will still be populated, but object will be internally empty.
 * <p>
 * "lifetime": The total amount of space available to the current user. If the user does not have the restriction of a
 * lifetime quota, the field will still non-null, but the object will be internally empty.
 */
@SuppressWarnings("unused")
@UseStag
public class UploadQuota implements Serializable {

    private static final long serialVersionUID = -3916253914179265714L;

    @NotNull
    @SerializedName("space")
    private Space mSpace;

    @Nullable
    @SerializedName("periodic")
    private Periodic mPeriodic;

    @Nullable
    @SerializedName("lifetime")
    private Lifetime mLifetime;

    @NotNull
    public Space getSpace() {
        return mSpace;
    }

    void setSpace(@NotNull Space space) {
        mSpace = space;
    }

    @Nullable
    public Periodic getPeriodic() {
        return mPeriodic;
    }

    void setPeriodic(@Nullable Periodic periodic) {
        mPeriodic = periodic;
    }

    @Nullable
    public Lifetime getLifetime() {
        return mLifetime;
    }

    void setLifetime(@Nullable Lifetime lifetime) {
        mLifetime = lifetime;
    }

    /**
     * The amount of free upload space left for the user.
     *
     * @return the amount of upload space left.
     */
    public long getFreeUploadSpace() {
        return mSpace.getFree();
    }
}
