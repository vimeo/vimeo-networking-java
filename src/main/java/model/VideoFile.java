package model;

/**
 * Created by alfredhanssen on 4/25/15.
 */
public class VideoFile
{
    public enum QualityValue
    {
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
