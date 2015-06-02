package com.vimeo.networking.model;

import java.io.Serializable;

/**
 * Created by hanssena on 4/23/15.
 */
public class Paging implements Serializable {

    private static final long serialVersionUID = -8547699448016693035L;
    public String next;
    public String previous;
    public String first;
    public String last;
}
