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

import java.util.Date;

/**
 * Created by zetterstromk on 6/24/15.
 */
public class FeedItem extends BaseVideo {

    public enum AttributionType {
        UPLOAD,
        LIKE,
        CHANNEL,
        GROUP,
        TAG,
        CREDIT,
        SHARE,
        NONE
    }

    public String uri;
    public Video clip;
    public String type;
    public Date time;
    public User user;   // from like type
    public Channel channel; // from channel type
    public Tag tag;
    public Group group;
    public Metadata metadata;

    // TODO: this can use serializedname [basevid]
    public AttributionType getType() {
        if (type.equalsIgnoreCase("channel")) {
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

        return AttributionType.NONE;
    }

    @Override
    public Video getVideo() {
        return clip;
    }

    @Override
    public String getRelatedUri() {
        if ((metadata == null) || (metadata.connections == null) ||
            (metadata.connections.related == null)) {
            return null;
        }
        return metadata.connections.related.uri;
    }
}

