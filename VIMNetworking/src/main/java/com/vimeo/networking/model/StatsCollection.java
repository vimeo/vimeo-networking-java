package com.vimeo.networking.model;

import java.io.Serializable;

import javax.annotation.Nullable;

/**
 * Created by hanssena on 4/23/15.
 */
public class StatsCollection implements Serializable {

    private static final long serialVersionUID = -348202198117360187L;
    @Nullable
    public Integer plays; // If this is null, that means the uploader has disabled play count
}
