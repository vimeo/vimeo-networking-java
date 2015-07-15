package com.vimeo.networking.model;

/**
 * Created by zetterstromk on 6/24/15.
 */
public class FeedList extends BaseResponseList<FeedItem> {

    @Override
    public Class getModelClass() {
        return FeedItem.class;
    }
}

