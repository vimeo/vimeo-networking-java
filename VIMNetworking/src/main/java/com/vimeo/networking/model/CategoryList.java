package com.vimeo.networking.model;

/**
 * Created by zetterstromk on 8/20/15.
 */
public class CategoryList extends BaseResponseList<Category> {

    @Override
    public Class getModelClass() {
        return Category.class;
    }
}
