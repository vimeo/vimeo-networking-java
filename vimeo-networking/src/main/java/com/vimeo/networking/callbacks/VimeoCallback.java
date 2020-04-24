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

package com.vimeo.networking.callbacks;

import com.vimeo.networking.utils.VimeoNetworkUtil;
import com.vimeo.networking2.VimeoResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class allows intercepting and analyzing of the responses for errors and invalid tokens
 * <p>
 * Created by zetterstromk on 5/27/15.
 */
public abstract class VimeoCallback<T> implements Callback<T> {

    public abstract void success(T t);

    public abstract void failure(@NotNull VimeoResponse.Error error);

    public VimeoCallback() {
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        // response.isSuccess() is true if the response code is 2xx
        if (response.isSuccessful()) {
            final T t = response.body();
            success(t);
        } else {
            VimeoResponse.Error vimeoError = VimeoNetworkUtil.getErrorFromResponse(response);
            if (vimeoError == null) {
                vimeoError = new VimeoResponse.Error.Exception(new IllegalStateException("Unable to parse error"),
                                                               response.code());
            }
            failure(vimeoError);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        /* handle execution failures
         * Failures may include:
         *      No Internet
         *      Socket issues
         *      Cancelled request (cancelling may also lead to socket issues if request has been made)
         *      Retrofit deserialization errors in which Retrofit cannot determine the response
         */
        if (call == null || !call.isCanceled()) {
            final VimeoResponse.Error vimeoError = new VimeoResponse.Error.Exception(t, -1);
            failure(vimeoError);
        }
    }
}
