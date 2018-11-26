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
import com.vimeo.networking.model.Album;
import com.vimeo.networking.model.AlbumList;
import com.vimeo.networking.model.Category;
import com.vimeo.networking.model.CategoryList;
import com.vimeo.networking.model.Channel;
import com.vimeo.networking.model.ChannelList;
import com.vimeo.networking.model.Comment;
import com.vimeo.networking.model.CommentList;
import com.vimeo.networking.model.FeedList;
import com.vimeo.networking.model.RecommendationList;
import com.vimeo.networking.model.User;
import com.vimeo.networking.model.UserList;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.VideoList;
import com.vimeo.networking.model.appconfiguration.AppConfiguration;
import com.vimeo.networking.model.cinema.ProgramContentItemList;
import com.vimeo.networking.model.iap.Product;
import com.vimeo.networking.model.live.LiveStats;
import com.vimeo.networking.model.notifications.NotificationList;
import com.vimeo.networking.model.search.SearchResponse;
import com.vimeo.networking.model.tvod.SeasonList;
import com.vimeo.networking.model.tvod.TvodItem;
import com.vimeo.networking.model.tvod.TvodList;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import okhttp3.CacheControl;
import retrofit2.Call;

/**
 * This is a collection of classes that should be used for GET requests to
 * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
 * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
 * Created by rigbergh on 7/9/17.
 */
@SuppressWarnings("unused")
public final class GetRequestCaller {

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get an {@link AppConfiguration} response from an API endpoint. This caller will call through to
     * {@link VimeoService#getAppConfiguration(String, String, Map, String)}
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
                    return vimeoService.getAppConfiguration(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get an {@link Category} response from an API endpoint. This caller will call through to
     * {@link VimeoService#getCategory(String, String, Map, String)}
     */
    public static final Caller<Category> CATEGORY =
            new Caller<Category>() {

                @NotNull
                @Override
                public Call<Category> call(@NotNull String authHeader,
                                           @NotNull String uri,
                                           @NotNull Map<String, String> queryMap,
                                           @NotNull String cacheHeader,
                                           @NotNull VimeoService vimeoService) {
                    return vimeoService.getCategory(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link Channel} response from an API endpoint.
     */
    public static final Caller<Channel> CHANNEL =
            new Caller<Channel>() {

                @NotNull
                @Override
                public Call<Channel> call(@NotNull String authHeader,
                                          @NotNull String uri,
                                          @NotNull Map<String, String> queryMap,
                                          @NotNull String cacheHeader,
                                          @NotNull VimeoService vimeoService) {
                    return vimeoService.getChannel(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link CategoryList} response from an API endpoint.
     */
    public static final Caller<CategoryList> CATEGORY_LIST =
            new Caller<CategoryList>() {

                @NotNull
                @Override
                public Call<CategoryList> call(@NotNull String authHeader,
                                               @NotNull String uri,
                                               @NotNull Map<String, String> queryMap,
                                               @NotNull String cacheHeader,
                                               @NotNull VimeoService vimeoService) {
                    return vimeoService.getCategoryList(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link ChannelList} response from an API endpoint.
     */
    public static final Caller<ChannelList> CHANNEL_LIST =
            new Caller<ChannelList>() {

                @NotNull
                @Override
                public Call<ChannelList> call(@NotNull String authHeader,
                                              @NotNull String uri,
                                              @NotNull Map<String, String> queryMap,
                                              @NotNull String cacheHeader,
                                              @NotNull VimeoService vimeoService) {
                    return vimeoService.getChannelList(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link Comment} response from an API endpoint.
     */
    public static final Caller<Comment> COMMENT =
            new Caller<Comment>() {

                @NotNull
                @Override
                public Call<Comment> call(@NotNull String authHeader,
                                          @NotNull String uri,
                                          @NotNull Map<String, String> queryMap,
                                          @NotNull String cacheHeader,
                                          @NotNull VimeoService vimeoService) {
                    return vimeoService.getComment(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link CommentList} response from an API endpoint.
     */
    public static final Caller<CommentList> COMMENT_LIST =
            new Caller<CommentList>() {

                @NotNull
                @Override
                public Call<CommentList> call(@NotNull String authHeader,
                                              @NotNull String uri,
                                              @NotNull Map<String, String> queryMap,
                                              @NotNull String cacheHeader,
                                              @NotNull VimeoService vimeoService) {
                    return vimeoService.getCommentList(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link FeedList} response from an API endpoint.
     */
    public static final Caller<FeedList> FEED_LIST =
            new Caller<FeedList>() {

                @NotNull
                @Override
                public Call<FeedList> call(@NotNull String authHeader,
                                           @NotNull String uri,
                                           @NotNull Map<String, String> queryMap,
                                           @NotNull String cacheHeader,
                                           @NotNull VimeoService vimeoService) {
                    return vimeoService.getFeedList(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link LiveStats} response from an API endpoint.
     */
    public static final Caller<LiveStats> LIVE_STATS =
            new Caller<LiveStats>() {

                @NotNull
                @Override
                public Call<LiveStats> call(@NotNull String authHeader,
                                            @NotNull String uri,
                                            @NotNull Map<String, String> queryMap,
                                            @NotNull String cacheHeader,
                                            @NotNull VimeoService vimeoService) {
                    return vimeoService.getLiveStats(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link NotificationList} response from an API endpoint.
     */
    public static final Caller<NotificationList> NOTIFICATION_LIST =
            new Caller<NotificationList>() {

                @NotNull
                @Override
                public Call<NotificationList> call(
                        @NotNull String authHeader,
                        @NotNull String uri,
                        @NotNull Map<String, String> queryMap,
                        @NotNull String cacheHeader,
                        @NotNull VimeoService vimeoService) {
                    return vimeoService.getNotificationList(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link ProgramContentItemList} response from an API endpoint.
     */
    public static final Caller<ProgramContentItemList> PROGRAM_CONTENT_ITEM_LIST =
            new Caller<ProgramContentItemList>() {

                @NotNull
                @Override
                public Call<ProgramContentItemList> call(
                        @NotNull String authHeader,
                        @NotNull String uri,
                        @NotNull Map<String, String> queryMap,
                        @NotNull String cacheHeader,
                        @NotNull VimeoService vimeoService) {
                    return vimeoService.getProgramContentItemList(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link RecommendationList} response from an API endpoint.
     */
    public static final Caller<RecommendationList> RECOMMENDATION_LIST =
            new Caller<RecommendationList>() {

                @NotNull
                @Override
                public Call<RecommendationList> call(@NotNull String authHeader,
                                                     @NotNull String uri,
                                                     @NotNull Map<String, String> queryMap,
                                                     @NotNull String cacheHeader,
                                                     @NotNull VimeoService vimeoService) {
                    return vimeoService.getRecommendationList(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link SearchResponse} response from an API endpoint.
     */
    public static final Caller<SearchResponse> SEARCH_RESPONSE_LIST =
            new Caller<SearchResponse>() {

                @NotNull
                @Override
                public Call<SearchResponse> call(@NotNull String authHeader,
                                                 @NotNull String uri,
                                                 @NotNull Map<String, String> queryMap,
                                                 @NotNull String cacheHeader,
                                                 @NotNull VimeoService vimeoService) {
                    return vimeoService.getSearchResponse(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link SeasonList} response from an API endpoint.
     */
    public static final Caller<SeasonList> SEASON_LIST =
            new Caller<SeasonList>() {

                @NotNull
                @Override
                public Call<SeasonList> call(@NotNull String authHeader,
                                             @NotNull String uri,
                                             @NotNull Map<String, String> queryMap,
                                             @NotNull String cacheHeader,
                                             @NotNull VimeoService vimeoService) {
                    return vimeoService.getSeasonList(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link TvodItem} response from an API endpoint.
     */
    public static final Caller<TvodItem> TVOD_ITEM =
            new Caller<TvodItem>() {

                @NotNull
                @Override
                public Call<TvodItem> call(@NotNull String authHeader,
                                           @NotNull String uri,
                                           @NotNull Map<String, String> queryMap,
                                           @NotNull String cacheHeader,
                                           @NotNull VimeoService vimeoService) {
                    return vimeoService.getTvodItem(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link TvodList} response from an API endpoint.
     */
    public static final Caller<TvodList> TVOD_LIST =
            new Caller<TvodList>() {

                @NotNull
                @Override
                public Call<TvodList> call(@NotNull String authHeader,
                                           @NotNull String uri,
                                           @NotNull Map<String, String> queryMap,
                                           @NotNull String cacheHeader,
                                           @NotNull VimeoService vimeoService) {
                    return vimeoService.getTvodList(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link User} response from an API endpoint.
     */
    public static final Caller<User> USER =
            new Caller<User>() {

                @NotNull
                @Override
                public Call<User> call(@NotNull String authHeader,
                                       @NotNull String uri,
                                       @NotNull Map<String, String> queryMap,
                                       @NotNull String cacheHeader,
                                       @NotNull VimeoService vimeoService) {
                    return vimeoService.getUser(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link Video} response from an API endpoint.
     */
    public static final Caller<Video> VIDEO =
            new Caller<Video>() {

                @NotNull
                @Override
                public Call<Video> call(@NotNull String authHeader,
                                        @NotNull String uri,
                                        @NotNull Map<String, String> queryMap,
                                        @NotNull String cacheHeader,
                                        @NotNull VimeoService vimeoService) {
                    return vimeoService.getVideo(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link Album} response from an API endpoint.
     */
    public static final Caller<Album> ALBUM =
            new Caller<Album>() {

                @NotNull
                @Override
                public Call<Album> call(@NotNull String authHeader,
                                        @NotNull String uri,
                                        @NotNull Map<String, String> queryMap,
                                        @NotNull String cacheHeader,
                                        @NotNull VimeoService vimeoService) {
                    return vimeoService.getAlbum(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link Void} response from an API endpoint.
     */
    public static final Caller<Void> VOID =
            new Caller<Void>() {

                @NotNull
                @Override
                public Call<Void> call(@NotNull String authHeader,
                                       @NotNull String uri,
                                       @NotNull Map<String, String> queryMap,
                                       @NotNull String cacheHeader,
                                       @NotNull VimeoService vimeoService) {
                    return vimeoService.getVoid(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link UserList} response from an API endpoint.
     */
    public static final Caller<UserList> USER_LIST =
            new Caller<UserList>() {

                @NotNull
                @Override
                public Call<UserList> call(@NotNull String authHeader,
                                           @NotNull String uri,
                                           @NotNull Map<String, String> queryMap,
                                           @NotNull String cacheHeader,
                                           @NotNull VimeoService vimeoService) {
                    return vimeoService.getUserList(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link VideoList} response from an API endpoint.
     */
    public static final Caller<VideoList> VIDEO_LIST =
            new Caller<VideoList>() {

                @NotNull
                @Override
                public Call<VideoList> call(@NotNull String authHeader,
                                            @NotNull String uri,
                                            @NotNull Map<String, String> queryMap,
                                            @NotNull String cacheHeader,
                                            @NotNull VimeoService vimeoService) {
                    return vimeoService.getVideoList(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link AlbumList} response from an API endpoint.
     */
    public static final Caller<AlbumList> ALBUM_LIST =
            new Caller<AlbumList>() {

                @NotNull
                @Override
                public Call<AlbumList> call(@NotNull String authHeader,
                                            @NotNull String uri,
                                            @NotNull Map<String, String> queryMap,
                                            @NotNull String cacheHeader,
                                            @NotNull VimeoService vimeoService) {
                    return vimeoService.getAlbumList(authHeader, uri, queryMap, cacheHeader);
                }
            };

    /**
     * Used in association with
     * {@link VimeoClient#getContent(String, CacheControl, Caller, String, Map, String, VimeoCallback)} or
     * {@link VimeoClient#getContentSync(String, CacheControl, String, Map, String, Caller)}
     * to get a {@link Product} response from an API endpoint.
     */
    public static final Caller<Product> PRODUCT =
            new Caller<Product>() {

                @NotNull
                @Override
                public Call<Product> call(@NotNull String authHeader,
                                          @NotNull String uri,
                                          @NotNull Map<String, String> queryMap,
                                          @NotNull String cacheHeader,
                                          @NotNull VimeoService vimeoService) {
                    return vimeoService.getProduct(authHeader, uri, queryMap, cacheHeader);
                }
            };

    private GetRequestCaller() {}

}
