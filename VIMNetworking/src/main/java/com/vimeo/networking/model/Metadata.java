package com.vimeo.networking.model;

import java.io.Serializable;

/**
 * Created by hanssena on 4/23/15.
 */
public class Metadata implements Serializable {

    private static final long serialVersionUID = 6626539965452151962L;
    public ConnectionCollection connections;
    public InteractionCollection interactions;
}
