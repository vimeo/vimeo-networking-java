package com.vimeo.networking.model;

/**
 * Created by alfredhanssen on 4/25/15.
 */
public class VideoFile {

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
