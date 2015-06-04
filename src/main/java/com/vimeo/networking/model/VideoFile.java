package com.vimeo.networking.model;

import java.io.Serializable;

/**
 * Created by alfredhanssen on 4/25/15.
 */
public class VideoFile implements Serializable {

    private static final long serialVersionUID = -5256416394912086020L;

    public static final String HLS = "hls";
    public static final String HD = "hd";
    public static final String SD = "sd";
    public static final String MOBILE = "mobile";

    public String expires;
    public int width;
    public int height;
    public int size;
    public String link;
    public String quality;
    public String type;
    public VideoLog log;

}
