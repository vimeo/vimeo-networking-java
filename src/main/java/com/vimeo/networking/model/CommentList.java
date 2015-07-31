package com.vimeo.networking.model;

/**
 * Created by zetterstromk on 7/31/15.
 */
public class CommentList extends BaseResponseList<Comment> {

    @Override
    public Class getModelClass() {
        return Comment.class;
    }
}
