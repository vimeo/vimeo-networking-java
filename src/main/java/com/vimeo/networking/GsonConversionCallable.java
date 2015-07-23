package com.vimeo.networking;

import com.google.gson.Gson;

import java.util.concurrent.Callable;

/**
 * Created by zetterstromk on 7/23/15.
 */
public class GsonConversionCallable implements Callable {

    private Gson gson;
    private Object object;
    private Class c;

    public GsonConversionCallable(Gson gson, Object object, Class c) {
        this.gson = gson;
        this.object = object;
        this.c = c;
    }

    public Object call() {
        String JSON = gson.toJson(object);
        return gson.fromJson(JSON, c);
    }
}
