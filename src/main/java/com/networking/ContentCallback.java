package com.networking;

import retrofit.client.Response;

/**
 * Created by alfredhanssen on 4/12/15.
 */
public interface ContentCallback<T>
{
    void success(T t);
    void failure(Error error);
}
