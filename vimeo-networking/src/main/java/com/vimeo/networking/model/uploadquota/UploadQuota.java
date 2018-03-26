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
import com.vimeo.networking.Vimeo;
import com.vimeo.stag.UseStag;

import java.io.Serializable;

/**
 * Created by kylevenn on 8/19/15.
 */
@SuppressWarnings("unused")
@UseStag
public class UploadQuota implements Serializable {

    private static final long serialVersionUID = 4050488085481972886L;

    @SerializedName("space")
    protected Space mSpace;

    @SerializedName("quota")
    protected Quota mQuota;

    public Space getSpace() {
        return mSpace;
    }

    public void setSpace(Space space) {
        mSpace = space;
    }

    public Quota getQuota() {
        return mQuota;
    }

    public void setQuota(Quota quota) {
        mQuota = quota;
    }

    /**
     * The amount of free upload space left for the user.
     * Returns -1 if there is no space object on this user.
     *
     * @return the amount of upload space left, or -1 if
     * there is none left.
     */
    public long getFreeUploadSpace() {
        if (mSpace != null) {
            return mSpace.mFree;
        } else {
            return Vimeo.NOT_FOUND;
        }
    }
}
