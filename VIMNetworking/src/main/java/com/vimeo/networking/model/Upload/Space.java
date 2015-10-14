package com.vimeo.networking.model.Upload;

import java.io.Serializable;

/**
 * Created by kylevenn on 8/19/15.
 */
public class Space implements Serializable {

    private static final long serialVersionUID = -1985382617862372889L;

    public long free;
    public long max;
    public long used;
}
