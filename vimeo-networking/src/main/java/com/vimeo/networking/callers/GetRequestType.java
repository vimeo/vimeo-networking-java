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
import com.vimeo.networking.VimeoService;
import com.vimeo.networking.callbacks.VimeoCallback;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import okhttp3.CacheControl;
import retrofit2.Call;

/**
 * This is a collection of classes that should be used for get requests to
 * {@link VimeoClient#getContent(String, CacheControl, VimeoCallback, String, Map, String, Caller)} or
 * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
 * Created by rigbergh on 7/9/17.
 */
public final class GetRequestType {


    public interface Caller<DataType_T> {

        @NotNull
        retrofit2.Call<DataType_T> call(@NotNull String authHeader,
                                        @NotNull String uri,
                                        @NotNull Map<String, String> queryMap,
                                        @NotNull String cacheHeader,
                                        VimeoService vimeoService);
    }


    public static final Caller<com.vimeo.networking.model.CategoryList> CATEGORY_LIST =
            new Caller<com.vimeo.networking.model.CategoryList>() {

                @NotNull
                @Override
                public Call<com.vimeo.networking.model.CategoryList> call(@NotNull String authHeader,
                                                                          @NotNull String uri,
                                                                          @NotNull Map<String, String> queryMap,
                                                                          @NotNull String cacheHeader,
                                                                          VimeoService vimeoService) {
                    return vimeoService.getCategoryList(authHeader, uri, queryMap, cacheHeader);
                }
            };

    public static final Caller<com.vimeo.networking.model.ChannelList> CHANNEL_LIST =
            new Caller<com.vimeo.networking.model.ChannelList>() {

                @NotNull
                @Override
                public Call<com.vimeo.networking.model.ChannelList> call(@NotNull String authHeader,
                                                                         @NotNull String uri,
                                                                         @NotNull Map<String, String> queryMap,
                                                                         @NotNull String cacheHeader,
                                                                         VimeoService vimeoService) {
                    return vimeoService.getChannelList(authHeader, uri, queryMap, cacheHeader);
                }
            };

    public static final Caller<com.vimeo.networking.model.CommentList> COMMENT_LIST =
            new Caller<com.vimeo.networking.model.CommentList>() {

                @NotNull
                @Override
                public Call<com.vimeo.networking.model.CommentList> call(@NotNull String authHeader,
                                                                         @NotNull String uri,
                                                                         @NotNull Map<String, String> queryMap,
                                                                         @NotNull String cacheHeader,
                                                                         VimeoService vimeoService) {
                    return vimeoService.getCommentList(authHeader, uri, queryMap, cacheHeader);
                }
            };

    public static final Caller<com.vimeo.networking.model.FeedList> FEED_LIST =
            new Caller<com.vimeo.networking.model.FeedList>() {

                @NotNull
                @Override
                public Call<com.vimeo.networking.model.FeedList> call(@NotNull String authHeader,
                                                                      @NotNull String uri,
                                                                      @NotNull Map<String, String> queryMap,
                                                                      @NotNull String cacheHeader,
                                                                      VimeoService vimeoService) {
                    return vimeoService.getFeedList(authHeader, uri, queryMap, cacheHeader);
                }
            };

    public static final Caller<com.vimeo.networking.model.RecommendationList> RECOMMENDATION_LIST =
            new Caller<com.vimeo.networking.model.RecommendationList>() {

                @NotNull
                @Override
                public Call<com.vimeo.networking.model.RecommendationList> call(@NotNull String authHeader,
                                                                                @NotNull String uri,
                                                                                @NotNull Map<String, String> queryMap,
                                                                                @NotNull String cacheHeader,
                                                                                VimeoService vimeoService) {
                    return vimeoService.getRecommendationList(authHeader, uri, queryMap, cacheHeader);
                }
            };

    public static final Caller<com.vimeo.networking.model.UserList> USER_LIST =
            new Caller<com.vimeo.networking.model.UserList>() {

                @NotNull
                @Override
                public Call<com.vimeo.networking.model.UserList> call(@NotNull String authHeader,
                                                                      @NotNull String uri,
                                                                      @NotNull Map<String, String> queryMap,
                                                                      @NotNull String cacheHeader,
                                                                      VimeoService vimeoService) {
                    return vimeoService.getUserList(authHeader, uri, queryMap, cacheHeader);
                }
            };

    public static final Caller<com.vimeo.networking.model.VideoList> VIDEO_LIST =
            new Caller<com.vimeo.networking.model.VideoList>() {

                @NotNull
                @Override
                public Call<com.vimeo.networking.model.VideoList> call(@NotNull String authHeader,
                                                                       @NotNull String uri,
                                                                       @NotNull Map<String, String> queryMap,
                                                                       @NotNull String cacheHeader,
                                                                       VimeoService vimeoService) {
                    return vimeoService.getVideoList(authHeader, uri, queryMap, cacheHeader);
                }
            };

    private GetRequestType() {

    }

}
