package com.vimeo.networking.model;

import java.util.ArrayList;

/**
 * Created by alfredhanssen on 4/12/15.
 */
public class VideoList extends BaseResponseList<Video> {

    @Override
    public Class getModelClass() {
        return Video.class;
    }
}
