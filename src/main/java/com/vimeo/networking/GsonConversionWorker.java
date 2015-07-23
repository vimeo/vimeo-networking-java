package com.vimeo.networking;

import com.google.gson.Gson;
import com.vimeo.networking.model.error.VimeoError;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by zetterstromk on 7/23/15.
 */
public class GsonConversionWorker {

    private Gson gson;
    private Object object;
    private Object result;
    private ModelCallback callback;
    private VimeoResponse vimeoResponse;
    private final FutureTask<Object> future;
    private Thread thread;
    private String failure;

    public GsonConversionWorker(Gson gson, Object object, ModelCallback callback,
                                VimeoResponse vimeoResponse) {
        this.gson = gson;
        this.object = object;
        this.callback = callback;
        this.vimeoResponse = vimeoResponse;
        Callable<Object> callable = new Callable<Object>() {
            public Object call() throws Exception {
                return doInBackground();
            }
        };

        future = new FutureTask<Object>(callable) {
            @Override
            protected void done() {
                onPostExecute();
            }
        };
    }

    public void execute() {
        thread = new Thread(future);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            callback.failure(new VimeoError(ex.getLocalizedMessage()));
        }
        if (result != null) {
            callback.success(result, vimeoResponse);
        } else {
            if (failure == null) {
                failure = "Unknown failure";
            }
            callback.failure(new VimeoError(failure));
        }
    }

    private Object doInBackground() {
        String JSON = gson.toJson(object);
        return gson.fromJson(JSON, callback.getObjectType());
    }

    private void onPostExecute() {
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
            failure = ex.getLocalizedMessage();
        }
    }
}
