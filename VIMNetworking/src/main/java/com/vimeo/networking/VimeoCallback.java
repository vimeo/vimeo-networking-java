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

import com.vimeo.networking.model.error.VimeoError;

import java.lang.annotation.Annotation;

import javax.annotation.Nullable;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by zetterstromk on 5/27/15.
 * <p/>
 * This class allows intercepting and analyzing of the responses for errors and invalid tokens
 */
public abstract class VimeoCallback<T> implements Callback<T> {

    public abstract void success(T t);

    public abstract void failure(VimeoError error);

    @Nullable
    private Call call;

    public void setCall(Call call) {
        this.call = call;
    }

    @Override
    public void onResponse(Response<T> response) {
        // response.isSuccess() is true if the response code is 2xx
        if (response.isSuccess()) {
            T t = response.body();
            success(t);
        } else {
            VimeoError vimeoError = null;
            if (response.errorBody() != null) {
                try {
                    Converter<ResponseBody, VimeoError> errorConverter = VimeoClient.getInstance()
                            .getRetrofit()
                            .responseBodyConverter(VimeoError.class, new Annotation[0]);
                    vimeoError = errorConverter.convert(response.errorBody());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (vimeoError == null) {
                vimeoError = new VimeoError();
            }
            vimeoError.setResponse(response);
            failure(vimeoError);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        /* handle execution failures
         * Failures may include:
         *      No Internet
         *      Socket issues
         *      Cancelled request (cancelling may also lead to socket issues if request has been made)
         *      Retrofit deserialization errors in which Retrofit cannot determine the response
         */
        t.printStackTrace();
        VimeoError vimeoError = new VimeoError();
        vimeoError.setDeveloperMessage(t.getMessage());
        boolean wasCancelled = call != null && call.isCanceled();
        vimeoError.setIsCanceledError(wasCancelled);
        vimeoError.setIsNetworkError(!wasCancelled);
        failure(vimeoError);
    }
}
