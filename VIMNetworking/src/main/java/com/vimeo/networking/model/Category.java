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

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zetterstromk on 8/20/15.
 */
public class Category implements Serializable {

    private static final long serialVersionUID = 441419347585215353L;
    public String uri;
    public String name;
    public String link;
    public boolean topLevel;
    public PictureCollection pictures;
    public ArrayList<Category> subcategories;
    public Category parent;
    public Metadata metadata;

    public int userCount() {
        if (metadata != null && metadata.connections != null && metadata.connections.users != null) {
            return metadata.connections.users.total;
        }
        return 0;
    }

    public int videoCount() {
        if (metadata != null && metadata.connections != null && metadata.connections.videos != null) {
            return metadata.connections.videos.total;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Category that = (Category) o;

        return ((this.uri != null && that.uri != null) ? this.uri.equals(that.uri) : false);
    }

    @Override
    public int hashCode() {
        return this.uri != null ? this.uri.hashCode() : 0;
    }
}
