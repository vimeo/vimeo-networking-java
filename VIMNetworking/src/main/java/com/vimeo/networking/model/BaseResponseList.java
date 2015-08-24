package com.vimeo.networking.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kylevenn on 5/28/15.
 */
public abstract class BaseResponseList<T> implements Serializable {

    private static final long serialVersionUID = -1641146617506148394L;
    public int total;
    public int page;
    public int perPage;
    public Paging paging;
    public ArrayList<T> data;

    // TODO: maybe don't need
    public abstract Class getModelClass();
}
