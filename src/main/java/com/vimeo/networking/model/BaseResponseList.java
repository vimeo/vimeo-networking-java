package com.vimeo.networking.model;

import java.util.ArrayList;

/**
 * Created by kylevenn on 5/28/15.
 */
public abstract class BaseResponseList<T> {

    public int total;
    public int page;
    public int perPage;
    public Paging paging;
    public ArrayList<T> data;

    // TODO: maybe don't need
    public abstract Class getModelClass();
}
