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

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kylevenn on 5/28/15.
 */
@SuppressWarnings("unused")
@UseStag
public abstract class BaseResponseList<T> implements Serializable {

    private static final long serialVersionUID = -1641146617506148394L;

    @SerializedName("total")
    private int mTotal;

    @SerializedName("page")
    private int mPage;

    @SerializedName("per_page")
    private int mPerPage;

    @SerializedName("paging")
    private Paging mPaging;

    @SerializedName("data")
    private ArrayList<T> mData;

    public int getTotal() {
        return mTotal;
    }

    public void setTotal(int total) {
        mTotal = total;
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public int getPerPage() {
        return mPerPage;
    }

    public void setPerPage(int perPage) {
        mPerPage = perPage;
    }

    public Paging getPaging() {
        return mPaging;
    }

    public void setPaging(Paging paging) {
        mPaging = paging;
    }

    public ArrayList<T> getData() {
        return mData;
    }

    public void setData(ArrayList<T> data) {
        mData = data;
    }

    public abstract Class<T> getModelClass();
}
