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
