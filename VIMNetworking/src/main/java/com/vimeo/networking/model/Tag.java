package com.vimeo.networking.model;

import java.io.Serializable;

/**
 * Created by hanssena on 4/23/15.
 */
public class Tag implements Serializable {

    private static final long serialVersionUID = 3388947522077930006L;
    public String uri;
    public String name;
    public String tag;
    public String canonical;
    public Metadata metadata;
}
