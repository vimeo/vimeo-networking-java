/*
 * Copyright (c) 2015 Vimeo (https://vimeo.com)
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

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This model object represents a Connection.
 * Created by hanssena on 4/23/15.
 */
@SuppressWarnings("unused")
@UseStag
public class Connection implements Serializable {

    private static final long serialVersionUID = -840088720891343176L;

    @Nullable
    @SerializedName("uri")
    protected String mUri;

    @Nullable
    @SerializedName("options")
    protected ArrayList<String> mOptions;

    @SerializedName("total")
    protected int mTotal;

    @SerializedName("main_total")
    protected int mMainTotal;

    @SerializedName("extra_total")
    protected int mExtraTotal;

    @SerializedName("viewable_total")
    protected int mViewableTotal;

    @SerializedName("new_total")
    protected int mNewTotal;

    @SerializedName("unread_total")
    protected int mUnreadTotal;

    @Nullable
    @SerializedName("name")
    protected String mName;

    // -----------------------------------------------------------------------------------------------------
    // Getters
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Getters">
    @Nullable
    public String getUri() {
        return mUri;
    }

    @Nullable
    public ArrayList<String> getOptions() {
        return mOptions;
    }

    public int getTotal() {
        return mTotal;
    }

    public int getMainTotal() {
        return mMainTotal;
    }

    public int getExtraTotal() {
        return mExtraTotal;
    }

    public int getViewableTotal() {
        return mViewableTotal;
    }

    public int getNewTotal() {
        return mNewTotal;
    }

    public int getUnreadTotal() {
        return mUnreadTotal;
    }

    @Nullable
    public String getName() {
        return mName;
    }
    // </editor-fold>
}
