package com.vimeo.networking.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zetterstromk on 6/24/15.
 */
public class Feed implements Serializable {

    private static final long serialVersionUID = -8744477085158366576L;

    //TODO I have only seen like/channel types, we need more types [KZ]
    public enum AttributionType {
        UPLOAD,
        LIKE,
        CHANNEL,
        GROUP,
        TAG,
        CREDIT,
        NONE
    }

    public String uri;
    public Video clip;
    public String type;
    public Date time;
    public User user;   // from like type
    public Channel channel; // from channel type
    public Tag tag;
    public Group group;

    public AttributionType getType() {
        if (type.equalsIgnoreCase("channel")) {
            return AttributionType.CHANNEL;
        } else if (type.equalsIgnoreCase("like")) {
            return AttributionType.LIKE;
        } else if (type.equalsIgnoreCase("upload")) {
            return AttributionType.UPLOAD;
        } else if (type.equalsIgnoreCase("tag")) {
            return AttributionType.TAG;
        } else if (type.equalsIgnoreCase("group")) {
            return AttributionType.GROUP;
        } else if (type.equalsIgnoreCase("appearance")) {
            return AttributionType.CREDIT;
        }

        return AttributionType.NONE;
    }


}

