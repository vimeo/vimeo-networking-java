package com.vimeo.networking.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zetterstromk on 6/5/15.
 */
public class Interaction implements Serializable {

    private static final long serialVersionUID = 2033767841952340400L;
    public boolean added;
    public Date addedDate;
    public String uri;
}
