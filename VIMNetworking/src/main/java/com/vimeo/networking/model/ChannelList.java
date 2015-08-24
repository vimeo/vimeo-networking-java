package com.vimeo.networking.model;

/**
 * Created by zetterstromk on 6/11/15.
 */
public class ChannelList extends BaseResponseList<Channel> {

    @Override
    public Class getModelClass() {
        return Channel.class;
    }
}
