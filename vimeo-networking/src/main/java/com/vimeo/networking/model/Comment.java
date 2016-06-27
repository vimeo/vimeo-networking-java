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
import com.vimeo.networking.Vimeo;
import com.vimeo.stag.GsonAdapterKey;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zetterstromk on 7/31/15.
 */
public class Comment implements Serializable {

    private static final long serialVersionUID = -7716027694845877155L;

    public enum CommentType {
        @SerializedName("video")
        VIDEO
        //TODO get the other comment types and put them here [KZ]
    }

    @GsonAdapterKey("uri")
    public String uri;
    @GsonAdapterKey("type")
    public CommentType type;
    @GsonAdapterKey("created_on")
    public Date createdOn;
    @GsonAdapterKey("text")
    public String text;
    @GsonAdapterKey("user")
    public User user;
    @GsonAdapterKey("metadata")
    public Metadata metadata;

    public int replyCount() {
        if ((metadata != null) && (metadata.connections != null) && (metadata.connections.replies != null)) {
            return metadata.connections.replies.total;
        }
        return 0;
    }

    public boolean canReply() {
        if ((metadata != null) && (metadata.connections != null) &&
            (metadata.connections.replies != null) &&
            (metadata.connections.replies.options != null)) {
            return metadata.connections.replies.options.contains(Vimeo.OPTIONS_POST);
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Comment that = (Comment) o;

        return ((this.uri != null && that.uri != null) ? this.uri.equals(that.uri) : false);
    }

    @Override
    public int hashCode() {
        return this.uri != null ? this.uri.hashCode() : 0;
    }

}
