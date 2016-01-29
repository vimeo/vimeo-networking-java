package com.vimeo.example.vimeonetworking;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.vimeo.networking.GsonDeserializer;
import com.vimeo.networking.callbacks.ModelCallback;

/**
 * Created by zetterstromk on 7/24/15.
 */
public class AndroidGsonDeserializer extends GsonDeserializer {

    @Override
    public void deserialize(Gson gson, Object object, ModelCallback callback) {
        new DeserializeTask(gson, object, callback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private class DeserializeTask extends AsyncTask<Void, Void, Object> {

        private Gson gson;
        private Object object;
        private ModelCallback callback;

        public DeserializeTask(Gson gson, Object object, ModelCallback callback) {
            this.gson = gson;
            this.object = object;
            this.callback = callback;
        }

        @Override
        protected Object doInBackground(Void... params) {
            return deserializeObject(gson, object, callback);
        }

        @Override
        protected void onPostExecute(Object result) {
            callback.success(result);
        }
    }
}
