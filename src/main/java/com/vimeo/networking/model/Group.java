package com.vimeo.networking.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zetterstromk on 6/26/15.
 */
public class Group implements Serializable {

    private static final long serialVersionUID = -3604741570351063891L;
    public String uri;
    public Date createdTime;
    public String groupDescription;
    public String link;
    public String name;
    public PictureCollection pictureCollection;
    public Privacy privacy;
    public User user;
    public Metadata metadata;

}
