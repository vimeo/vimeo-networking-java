package com.vimeo.android.networking.example.kotlin.vimeonetworking

import android.os.AsyncTask
import com.google.gson.Gson
import com.vimeo.networking.GsonDeserializer
import com.vimeo.networking.callbacks.ModelCallback

/**
 * Created by zetterstromk on 7/24/15.
 */
class AndroidGsonDeserializer : GsonDeserializer() {


    override fun deserialize(gson: Gson, any: Any, callback: ModelCallback<Any>) {
        DeserializeTask(gson, any, callback).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }

    private inner class DeserializeTask(private val gson: Gson,
                                        private val any: Any,
                                        private val callback: ModelCallback<Any>) : AsyncTask<Void, Void, Any>() {

        override fun doInBackground(vararg params: Void): Any? {
            return deserializeObject(gson, any, callback)
        }

        override fun onPostExecute(result: Any) {
            callback.success(result)
        }
    }
}
