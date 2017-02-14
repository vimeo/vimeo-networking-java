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
    protected String uri;

    @Nullable
    protected ArrayList<String> options;

    protected int total;

    @SerializedName("main_total")
    protected int mainTotal;

    @SerializedName("extra_total")
    protected int extraTotal;

    @SerializedName("viewable_total")
    protected int viewableTotal;

    @SerializedName("new_total")
    protected int newTotal;

    @SerializedName("unread_total")
    protected int unreadTotal;

    @Nullable
    public String name;

    // -----------------------------------------------------------------------------------------------------
    // Getters
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Getters">
    @Nullable
    public String getUri() {
        return uri;
    }

    @Nullable
    public ArrayList<String> getOptions() {
        return options;
    }

    public int getTotal() {
        return total;
    }

    public int getMainTotal() {
        return mainTotal;
    }

    public int getExtraTotal() {
        return extraTotal;
    }

    public int getViewableTotal() {
        return viewableTotal;
    }

    public int getNewTotal() {
        return newTotal;
    }

    public int getUnreadTotal() {
        return unreadTotal;
    }

    @Nullable
    public String getName() {
        return name;
    }
    // </editor-fold>
}
