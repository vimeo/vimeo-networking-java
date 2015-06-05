package com.vimeo.networking.model;

import java.io.Serializable;

/**
 * Created by alfredhanssen on 4/25/15.
 */
public class VideoFile implements Serializable {

    private static final long serialVersionUID = -5256416394912086020L;

    public static final String MIMETYPE_MP4 = "video/mp4";
    public static final String MIMETYPE_WEBM = "video/webm"; // Flash
    public static final String MIMETYPE_VP6 = "vp6/x-video"; // Flash

    public static final String QUALITY_HLS = "hls";
    public static final String QUALITY_HD = "hd";
    public static final String QUALITY_SD = "sd";
    public static final String QUALITY_MOBILE = "mobile";

    public String expires;
    public int width;
    public int height;
    public int size;
    public String link;
    public String quality;
    public String type;
    public VideoLog log;

}
