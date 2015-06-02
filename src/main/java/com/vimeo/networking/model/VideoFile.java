package com.vimeo.networking.model;

import java.io.Serializable;

/**
 * Created by alfredhanssen on 4/25/15.
 */
public class VideoFile implements Serializable {

    private static final long serialVersionUID = -5256416394912086020L;

    public enum QualityValue {
        HLS,
        HD,
        SD,
        MOBILE
    }

    public String expires;
    public int width;
    public int height;
    public int size;
    public String link;
    public String quality;
    public String type;
    public VideoLog log;
}
