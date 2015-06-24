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
        NONE //TODO show we supply a default here? [KZ]
    }

    public String uri;
    public Video clip;
    public String type;
    public Date time;
    public User user;   // from like type
    public Channel channel; // from channel type

    public AttributionType getType() {
        if(type.equalsIgnoreCase("channel")) {
            return AttributionType.CHANNEL;
        } else if(type.equalsIgnoreCase("like")) {
            return AttributionType.LIKE;
        }

        return AttributionType.NONE;
    }


}

