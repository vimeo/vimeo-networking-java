/*
 * Copyright (c) 2015 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.vimeo.networking;

import com.google.gson.Gson;
import com.vimeo.networking.callbacks.ModelCallback;
import com.vimeo.networking.logging.ClientLogger;

import org.jetbrains.annotations.Nullable;

/**
 * To be able to keep our api calls generic, we need to handle deserialization ourselves (Retrofit calls
 * require a class to be declared up front). Since this is a Java library and we don't have access to
 * the UI thread, consumers of this library will need to provide their own implementation of deserialization
 * on a background thread and calling their callback on the platform's main/UI thread
 * <p/>
 * For Android, you can achieve this task by extending this class and calling {@link GsonDeserializer#deserializeObject}
 * on any background thread and {@link ModelCallback#success(Object)} on their UI thread.
 * <p/>
 * By default, this class will just deserialize objects and callback on the calling thread.
 * <p/>
 * Created by zetterstromk on 7/24/15.
 */
public class GsonDeserializer {

    /**
     * Deserialize the json and call a callback method.
     * <p/>
     * Override this method to perform custom deserialization. For example,
     * on Android, you may want to use a AsyncTask to do this work on
     * <p/>
     *
     * @param gson     The Gson object do do the lifting
     * @param object   The Object object deserialize
     * @param callback The callback to call (and get the object type from)
     */
    public void deserialize(Gson gson, Object object, ModelCallback<Object> callback) {
        Object result = deserializeObject(gson, object, callback);
        callback.success(result);
    }

    @Nullable
    protected Object deserializeObject(Gson gson, Object object, ModelCallback<Object> callback) {
        try {
            String JSON = gson.toJson(object);
            return gson.fromJson(JSON, callback.getObjectType());
        } catch (Exception e) {
            ClientLogger.e("Error when deserializing object!", e);
            return null;
        }
    }
}
