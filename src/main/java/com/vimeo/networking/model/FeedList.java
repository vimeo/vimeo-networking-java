package com.vimeo.networking.model;

/**
 * Created by zetterstromk on 6/24/15.
 */
public class FeedList extends BaseResponseList<Feed> {

    @Override
    public Class getModelClass() {
        return Feed.class;
    }
}

