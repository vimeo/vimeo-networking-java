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
 * Created by zetterstromk on 6/24/15.
 */
public class FeedItem implements Serializable {

    private static final long serialVersionUID = -8744477085158366576L;

    public enum AttributionType {
        UPLOAD,
        LIKE,
        CATEGORY,
        CHANNEL,
        GROUP,
        TAG,
        CREDIT,
        SHARE,
        NONE
    }

    @GsonAdapterKey("uri")
    public String uri;
    @GsonAdapterKey("clip")
    public Video clip;
    @GsonAdapterKey("type")
    public String type;
    @GsonAdapterKey("time")
    public Date time;
    @GsonAdapterKey("user")
    public User user;   // from like type
    @GsonAdapterKey("channel")
    public Channel channel; // from channel type
    @GsonAdapterKey("category")
    public Category category;
    @GsonAdapterKey("tag")
    public Tag tag;
    @GsonAdapterKey("group")
    public Group group;
    @GsonAdapterKey("metadata")
    public Metadata metadata;

    public AttributionType getType() {
        if (type != null) {
            if (type.equalsIgnoreCase("category")) {
                return AttributionType.CATEGORY;
            } else if (type.equalsIgnoreCase("channel")) {
                return AttributionType.CHANNEL;
            } else if (type.equalsIgnoreCase("like")) {
                return AttributionType.LIKE;
            } else if (type.equalsIgnoreCase("upload")) {
                return AttributionType.UPLOAD;
            } else if (type.equalsIgnoreCase("tag")) {
                return AttributionType.TAG;
            } else if (type.equalsIgnoreCase("group")) {
                return AttributionType.GROUP;
            } else if (type.equalsIgnoreCase("appearance")) {
                return AttributionType.CREDIT;
            } else if (type.equalsIgnoreCase("share")) {
                return AttributionType.SHARE;
            }
        }

        return AttributionType.NONE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FeedItem that = (FeedItem) o;

        return ((this.clip != null && that.clip != null) &&
                (this.clip.getResourceKey() != null && that.clip.getResourceKey() != null) &&
                this.clip.getResourceKey().equals(that.clip.getResourceKey()));
    }

    @Override
    public int hashCode() {
        return this.clip.getResourceKey() != null ? this.clip.getResourceKey().hashCode() : 0;
    }

}

