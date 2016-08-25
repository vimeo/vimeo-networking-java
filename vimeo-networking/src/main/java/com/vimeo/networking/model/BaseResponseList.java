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

import com.vimeo.stag.GsonAdapterKey;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kylevenn on 5/28/15.
 */
public abstract class BaseResponseList<T> implements Serializable {

    private static final long serialVersionUID = -1641146617506148394L;

    @GsonAdapterKey
    public int total;
    @GsonAdapterKey
    public int page;
    @GsonAdapterKey("per_page")
    public int perPage;
    @GsonAdapterKey
    public Paging paging;
    @GsonAdapterKey
    public ArrayList<T> data;

    // TODO: maybe don't need
    public abstract Class<T> getModelClass();
}
