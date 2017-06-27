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

package com.vimeo.networking.logging;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vimeo.networking.Configuration;
import com.vimeo.networking.Vimeo.LogLevel;
import com.vimeo.networking.VimeoClient;
import com.vimeo.networking.utils.VimeoNetworkUtil;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * An interceptor for logging the requests and responses for each individual call. This class relies on the
 * {@link LogLevel} passed into the {@link Configuration} which initialized {@link VimeoClient}
 * <p>
 * Created by zetterstromk on 10/23/15.
 */
public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (ClientLogger.getLogLevel().ordinal() <= LogLevel.DEBUG.ordinal()) {
            Request request = chain.request();

            HttpUrl httpUrl = request.url();

            long t1 = System.nanoTime();
            ClientLogger.d("--------- REQUEST ---------");
            ClientLogger.d("METHOD: " + request.method());
            ClientLogger.d("ENDPOINT: " + httpUrl.encodedPath());
            try {
                if (request.body() != null && shouldLogVerbose()) {
                    ClientLogger.v("QUERY: " + httpUrl.query());
                    ClientLogger.v("--------- REQUEST BODY ---------");
                    Request copy = request.newBuilder().build();
                    Buffer requestBuffer = new Buffer();
                    copy.body().writeTo(requestBuffer);
                    verboseLogLongJson(requestBuffer.readUtf8());
                    ClientLogger.v("--------- REQUEST BODY END ---------");
                }
            } catch (Exception e) {
                ClientLogger.e("Exception in LoggingInterceptor", e);
            }
            ClientLogger.d("--------- REQUEST END ---------");

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();

            ClientLogger.d("--------- RESPONSE ---------");
            ClientLogger.d("ENDPOINT: " + httpUrl.encodedPath());
            ClientLogger.d("STATUS CODE: " + response.code());
            ClientLogger.d(String.format("REQUEST TIME: %.1fms", (t2 - t1) / 1e6d));

            String responseBodyString = response.body().string();
            if (shouldLogVerbose()) {
                ClientLogger.v("--------- RESPONSE BODY ---------");
                verboseLogLongJson(responseBodyString);
                ClientLogger.v("--------- RESPONSE BODY END ---------");
            }
            ClientLogger.d("--------- RESPONSE END ---------");

            // You need to reconstruct the body because it can only be read once 1/27/16 [KV]
            return response.newBuilder()
                    .body(ResponseBody.create(response.body().contentType(), responseBodyString))
                    .build();
        } else {
            return chain.proceed(chain.request());
        }
    }

    private boolean shouldLogVerbose() {
        return ClientLogger.getLogLevel().ordinal() <= LogLevel.VERBOSE.ordinal();
    }

    private void verboseLogLongJson(String jsonString) {
        int maxLogSize = 1000;
        String veryLongString = toPrettyFormat(jsonString);
        for (int i = 0; i <= veryLongString.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i + 1) * maxLogSize;
            end = end > veryLongString.length() ? veryLongString.length() : end;
            ClientLogger.v(veryLongString.substring(start, end));
        }
    }

    /**
     * Convert a JSON string to pretty print version
     *
     * @param jsonString string to make pretty
     */
    private String toPrettyFormat(String jsonString) {
        String prettyString = "";
        if (jsonString != null && !jsonString.isEmpty()) {
            try {
                JsonParser parser = new JsonParser();
                JsonObject json = parser.parse(jsonString).getAsJsonObject();

                Gson gson = VimeoNetworkUtil.getGsonBuilder().setPrettyPrinting().create();
                prettyString = gson.toJson(json);
            } catch (Exception e) {
                ClientLogger.e("Error making body pretty response/request");
            }
        }
        return prettyString;
    }

}
