/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Vimeo
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

package com.vimeo.networking.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vimeo.networking.model.Category;
import com.vimeo.networking.model.Comment;
import com.vimeo.networking.model.CommentList;
import com.vimeo.networking.model.Connection;
import com.vimeo.networking.model.ConnectionCollection;
import com.vimeo.networking.model.Embed;
import com.vimeo.networking.model.Interaction;
import com.vimeo.networking.model.InteractionCollection;
import com.vimeo.networking.model.Metadata;
import com.vimeo.networking.model.Paging;
import com.vimeo.networking.model.Picture;
import com.vimeo.networking.model.PictureCollection;
import com.vimeo.networking.model.PictureResource;
import com.vimeo.networking.model.Preferences;
import com.vimeo.networking.model.Privacy;
import com.vimeo.networking.model.Quota;
import com.vimeo.networking.model.Space;
import com.vimeo.networking.model.StatsCollection;
import com.vimeo.networking.model.Tag;
import com.vimeo.networking.model.UploadQuota;
import com.vimeo.networking.model.User;
import com.vimeo.networking.model.Video;
import com.vimeo.networking.model.VideoFile;
import com.vimeo.networking.model.VideoList;
import com.vimeo.networking.model.VideosPreference;
import com.vimeo.networking.model.VimeoAccount;
import com.vimeo.networking.model.Website;
import com.vimeo.networking.model.playback.Play;
import com.vimeo.networking.model.playback.VideoLog;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A utility class that can eventually be shared across various retrofit/vimeo-api reliant libraries
 * <p/>
 * Created by kylevenn on 10/30/15.
 */
public final class VimeoNetworkUtil {

    private static Gson sGson;

    private VimeoNetworkUtil() {
    }

    /**
     * Static helper method that automatically applies the VimeoClient Gson preferences
     * </p>
     * This includes formatting for dates as well as a LOWER_CASE_WITH_UNDERSCORES field naming policy
     * Example date: "2015-05-21T14:24:03+00:00"
     * <p/>
     * This only creates the Gson instance once, then uses it, so that subsequent calls to this method do
     * not recreate the Gson object. This makes the Gson object more efficient, as it will learn what classes
     * it has TypeAdapters for.
     *
     * @return Gson object that can be passed into a {@link GsonConverterFactory} create() method
     */
    public static Gson getGson() {
        if (sGson == null) {
            sGson = getGsonBuilder().create();
        }
        return sGson;
    }

    /**
     * Static helper method that automatically applies the VimeoClient Gson preferences
     * </p>
     * This includes formatting for dates as well as a LOWER_CASE_WITH_UNDERSCORES field naming policy
     *
     * @return GsonBuilder that can be built upon and then created
     */
    public static GsonBuilder getGsonBuilder() {
        // Example date: "2015-05-21T14:24:03+00:00"
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, ISO8601.getDateSerializer())
                .registerTypeAdapter(Date.class, ISO8601.getDateDeserializer())
                .registerTypeAdapter(Paging.class, new Paging.PagingTypeAdapter())
                .registerTypeAdapter(Privacy.class, new Privacy.PrivacyTypeAdapter())
                .registerTypeAdapter(StatsCollection.class, new StatsCollection.StatsCollectionTypeAdapter())
                .registerTypeAdapter(Embed.class, new Embed.EmbedTypeAdapter())
                .registerTypeAdapter(VideoLog.class, new VideoLog.VideoLogTypeAdapter())
                .registerTypeAdapterFactory(new VideoFile.Factory())
                .registerTypeAdapter(PictureResource.class, new PictureResource.PictureResourceTypeAdapter())
                .registerTypeAdapter(Interaction.class, new Interaction.InteractionTypeAdapter())
                .registerTypeAdapterFactory(new InteractionCollection.Factory())
                .registerTypeAdapter(Connection.class, new Connection.ConnectionTypeAdapter())
                .registerTypeAdapterFactory(new ConnectionCollection.Factory())
                .registerTypeAdapter(Picture.class, new Picture.PictureTypeAdapter())
                .registerTypeAdapterFactory(new PictureCollection.Factory())
                .registerTypeAdapterFactory(new Metadata.Factory())
                .registerTypeAdapter(VideosPreference.class,
                                     new VideosPreference.VideosPreferenceTypeAdapter())
                .registerTypeAdapterFactory(new Preferences.Factory())
                .registerTypeAdapter(Website.class, new Website.WebsiteTypeAdapter())
                .registerTypeAdapter(Quota.class, new Quota.QuotaTypeAdapter())
                .registerTypeAdapter(Space.class, new Space.SpaceTypeAdapter())
                .registerTypeAdapterFactory(new UploadQuota.Factory())
                .registerTypeAdapterFactory(new User.Factory())
                .registerTypeAdapterFactory(new Comment.Factory())
                .registerTypeAdapterFactory(new VimeoAccount.Factory())
                .registerTypeAdapterFactory(new Tag.Factory())
                .registerTypeAdapterFactory(new Category.Factory())
                .registerTypeAdapterFactory(new Play.Factory())
                .registerTypeAdapterFactory(new Video.Factory())
                .registerTypeAdapterFactory(new VideoList.Factory())
                .registerTypeAdapterFactory(new CommentList.Factory());
        /** Refer to {@link ISO8601} for explanation of deserialization */
        // .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
    }

    /**
     * Returns a simple query map from a provided uri. The simple map enforces there is exactly one value for
     * every name (multiple values for the same name are regularly allowed in a set of parameters)
     *
     * @param uri a uri, optionally with a query
     * @return a query map with a one to one mapping of names to values or empty {@link HashMap}
     * if no parameters are found on the uri
     * @see <a href="http://stackoverflow.com/a/13592567/1759443">StackOverflow</a>
     */
    public static Map<String, String> getSimpleQueryMap(String uri) {
        final Map<String, String> query_pairs = new LinkedHashMap<>();
        try {
            String query = uri.split("\\?")[1];
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
                                URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
            return query_pairs;
        } catch (UnsupportedEncodingException e) {
            // Just print the trace, we don't want to crash the app. If you ever get an empty query params
            // map back, then we know there was a malformed URL returned from the api (or a failure) 1/27/16 [KV]
            e.printStackTrace();
        }

        return query_pairs;
    }

    /**
     * Return a builder of the given cache control because for some reason this doesn't exist already.
     * Useful for adding more attributes to an already defined {@link CacheControl}
     *
     * @param cacheControl The CacheControl to convert to a builder
     * @return A builder with the same attributes as the CacheControl passed in
     */

    public static CacheControl.Builder getCacheControlBuilder(CacheControl cacheControl) {
        CacheControl.Builder builder = new CacheControl.Builder();
        if (cacheControl.maxAgeSeconds() > -1) {
            builder.maxAge(cacheControl.maxAgeSeconds(), TimeUnit.SECONDS);
        }
        if (cacheControl.maxStaleSeconds() > -1) {
            builder.maxStale(cacheControl.maxStaleSeconds(), TimeUnit.SECONDS);
        }
        if (cacheControl.minFreshSeconds() > -1) {
            builder.minFresh(cacheControl.minFreshSeconds(), TimeUnit.SECONDS);
        }

        if (cacheControl.noCache()) {
            builder.noCache();
        }
        if (cacheControl.noStore()) {
            builder.noStore();
        }
        if (cacheControl.noTransform()) {
            builder.noTransform();
        }
        if (cacheControl.onlyIfCached()) {
            builder.onlyIfCached();
        }
        return builder;
    }

    /** A helper which cancels an array of {@link Call} objects. */
    public static void cancelCalls(final ArrayList<Call> callsToCancel) {
        final List<Call> callList = new CopyOnWriteArrayList<>(callsToCancel);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Call call : callList) {
                    if (call != null) {
                        call.cancel();
                    }
                }
            }
        }).start();
    }
}
