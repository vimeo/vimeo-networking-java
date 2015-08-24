package com.vimeo.networking.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hanssena on 4/23/15.
 */
public class Connection implements Serializable {

    private static final long serialVersionUID = -840088720891343176L;
    public String uri;
    public ArrayList<String> options;
    public int total;
}
