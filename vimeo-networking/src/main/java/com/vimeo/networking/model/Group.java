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
import java.util.Date;

/**
 * Created by zetterstromk on 6/26/15.
 */
public class Group implements Serializable {

    private static final long serialVersionUID = -3604741570351063891L;
    @GsonAdapterKey("uri")
    public String uri;
    @GsonAdapterKey("created_time")
    public Date createdTime;
    @GsonAdapterKey("group_description")
    public String groupDescription;
    @GsonAdapterKey("link")
    public String link;
    @GsonAdapterKey("name")
    public String name;
    @GsonAdapterKey("picture_collection")
    public PictureCollection pictureCollection;
    @GsonAdapterKey("privacy")
    public Privacy privacy;
    @GsonAdapterKey("user")
    public User user;
    @GsonAdapterKey("metadata")
    public Metadata metadata;

}
