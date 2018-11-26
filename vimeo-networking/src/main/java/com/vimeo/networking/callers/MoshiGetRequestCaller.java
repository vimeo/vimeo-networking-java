/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Vimeo
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

package com.vimeo.networking.callers;

import com.vimeo.networking.VimeoClient;
import com.vimeo.networking.VimeoClient.Caller;
import com.vimeo.networking.VimeoService;
import com.vimeo.networking.callbacks.VimeoCallback;
import com.vimeo.networking2.AppConfiguration;
import com.vimeo.networking2.FeedList;
import com.vimeo.networking2.UserList;
import com.vimeo.networking2.VideoList;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import okhttp3.CacheControl;
import retrofit2.Call;

/**
 * This is a collection of classes that should be used for GET requests to
 * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
 * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
 * <p>
 * These classes are for specifically getting data using the new models.
 */
public final class MoshiGetRequestCaller {

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link FeedList} response from an API endpoint.
     */
    public static final Caller<FeedList> FEED_LIST = new Caller<FeedList>() {

        @NotNull
        @Override
        public Call<FeedList> call(@NotNull String authHeader, @NotNull String uri,
                                   @NotNull Map<String, String> queryMap, @NotNull String cacheHeader,
                                   @NotNull VimeoService vimeoService) {
            return vimeoService.getFeedListMoshi(authHeader, uri, queryMap, cacheHeader);
        }
    };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link UserList} response from an API endpoint.
     */
    public static final Caller<UserList> USER_LIST = new Caller<UserList>() {

        @NotNull
        @Override
        public Call<UserList> call(@NotNull String authHeader, @NotNull String uri,
                                   @NotNull Map<String, String> queryMap, @NotNull String cacheHeader,
                                   @NotNull VimeoService vimeoService) {
            return vimeoService.getUserListMoshi(authHeader, uri, queryMap, cacheHeader);
        }
    };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link VideoList} response from an API endpoint.
     */
    public static final Caller<com.vimeo.networking2.VideoList> VIDEO_LIST =
            new Caller<com.vimeo.networking2.VideoList>() {

                @NotNull
                @Override
                public Call<com.vimeo.networking2.VideoList> call(@NotNull String authHeader,
                                                                  @NotNull String uri,
                                                                  @NotNull Map<String, String> queryMap,
                                                                  @NotNull String cacheHeader,
                                                                  @NotNull VimeoService vimeoService) {
                    return vimeoService.getVideoListMoshi(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get an {@link AppConfiguration} response from an API endpoint. This caller will call through to
     * {@link VimeoService#getAppConfigurationMoshi(String, String, Map, String)} (String, String, Map, String)}
     */
    public static final Caller<AppConfiguration> APP_CONFIGURATION =
            new Caller<AppConfiguration>() {
                @NotNull
                @Override
                public Call<AppConfiguration> call(@NotNull String authHeader,
                                                   @NotNull String uri,
                                                   @NotNull Map<String, String> queryMap,
                                                   @NotNull String cacheHeader,
                                                   @NotNull VimeoService vimeoService) {
                    return vimeoService.getAppConfigurationMoshi(authHeader, uri, queryMap, cacheHeader);
                }
            };

    private MoshiGetRequestCaller() {
    }

}
