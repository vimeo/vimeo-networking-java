/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vimeo
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

package com.vimeo.networking.model;

import com.google.gson.annotations.SerializedName;
import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Representation of a DASH Video stream/playback file.
 * <p>
 * Created by zetterstromk on 8/3/17.
 */
@SuppressWarnings("unused")
@UseStag(FieldOption.SERIALIZED_NAME)
public class DashVideoFile extends VideoFile {

    private static final long serialVersionUID = -5721104078876753376L;

    @Nullable
    @SerializedName("token")
    protected String mToken;

    @Nullable
    @SerializedName("license_link")
    protected String mLicenseLink;

    @Nullable
    public String getToken() {
        return mToken;
    }

    @Nullable
    public String getLicenseLink() {
        return mLicenseLink;
    }

    @NotNull
    @Override
    public VideoQuality getQuality() {
        return VideoQuality.DASH;
    }

}
