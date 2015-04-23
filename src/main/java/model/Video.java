package model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by alfredhanssen on 4/12/15.
 */
public class Video
{
    public String uri;
    public String name;
    public String description;
    public String link;
    public int duration;
    public int width;
    public int height;
    public Embed embed;
    public String language;
    public String createdTime;
    public String modifiedTime;
    public ArrayList<String> contentRating;
    public String license;
    public Privacy privacy;
    public PictureCollection pictures;
    public ArrayList<Tag> tags;
    public StatsCollection stats;
    public Metadata metadata;
    public User user;
    public String status;
}
