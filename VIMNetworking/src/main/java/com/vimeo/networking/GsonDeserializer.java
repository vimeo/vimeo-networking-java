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
