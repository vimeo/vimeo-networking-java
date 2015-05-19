package com.vimeo.networking;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by hanssena on 4/27/15.
 */
public abstract class ModelCallback<T> implements Callback<T> {

    private Class objectType;

    public ModelCallback(Class objectType) {
        this.objectType = objectType;
    }

    public Class getObjectType() {
        return this.objectType;
    }
}
