package com.vimeo.networking.model;

import java.io.Serializable;

/**
 * Created by hanssena on 4/23/15.
 */
public class ConnectionCollection implements Serializable {

    private static final long serialVersionUID = -4523270955994232839L;
    public Connection videos;
    public Connection comments;
    public Connection credits;
    public Connection likes;
    public Connection pictures;
    public Connection texttracks;
    public Connection activities;
    public Connection albums;
    public Connection channels;
    public Connection feed;
    public Connection followers;
    public Connection following;
    public Connection groups;
    public Connection portfolios;
    public Connection shared;
    public Connection replies;
    public Connection users;
}
