package com.vimeo.networking;

import com.google.gson.Gson;

/**
 * Created by zetterstromk on 7/24/15.
 */
public class GsonDeserializer {

    public GsonDeserializer() {
    }

    /**
     * Deserialize the json and call a callback method.
     * <p/>
     * Override this method to perform custom deserialization. For example,
     *  on Android, you may want to use a AsyncTask to do this work on
     * <p/>
     *
     * @param gson The Gson object do do the lifting
     * @param object The Object object deserialize
     * @param callback The callback to call (and get the object type from)
     * @param response The VimeoResponse object
     */
    public void deserialize(Gson gson, Object object, ModelCallback callback, VimeoResponse response) {
        Object result = deserializeObject(gson, object, callback);
        callback.success(result, response);
    }

    public Object deserializeObject(Gson gson, Object object, ModelCallback callback) {
        String JSON = gson.toJson(object);
        return gson.fromJson(JSON, callback.getObjectType());
    }
}
