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

import com.google.gson.Gson;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter;
import com.vimeo.networking.Vimeo;
import com.vimeo.networking.callbacks.VimeoCallback;
import com.vimeo.networking.logging.ClientLogger;
import com.vimeo.networking2.AlbumPrivacy;
import com.vimeo.networking2.AlbumPrivacyUtils;
import com.vimeo.networking2.ApiError;
import com.vimeo.networking2.InvalidParameter;
import com.vimeo.networking2.VimeoResponse;
import com.vimeo.networking2.enums.AlbumViewPrivacyType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
import retrofit2.Response;

/**
 * A utility class that can eventually be shared across various retrofit/vimeo-api reliant libraries
 * <p>
 * Created by kylevenn on 10/30/15.
 */
@SuppressWarnings("unused")
public final class VimeoNetworkUtil {

    @NotNull
    private static final String INVALID_TOKEN_VALUE = "Bearer error=\"invalid_token\"";

    @NotNull
    private static final String AUTHENTICATE_HEADER = "WWW-Authenticate";

    @Nullable
    private static Gson sGson;

    @Nullable
    private static Moshi sMoshi;

    private VimeoNetworkUtil() {
    }

    /**
     * Static helper method that automatically applies the VimeoClient Moshi preferences
     * <p>
     * This includes formatting for dates, and any serializers used.
     *
     * @return Moshi object that can be used to serialize and deserialize JSON.
     */
    @NotNull
    public static Moshi getMoshi() {
        if (sMoshi == null) {
            sMoshi = new Moshi.Builder()
                    .add(Date.class, new Rfc3339DateJsonAdapter().nullSafe())
                    .build();
        }
        return sMoshi;
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
    @NotNull
    public static Map<String, String> getSimpleQueryMap(@NotNull String uri) {
        final Map<String, String> queryPairs = new LinkedHashMap<>();
        try {
            final String query = uri.split("\\?")[1];
            final String[] pairs = query.split("&");
            for (final String pair : pairs) {
                final int idx = pair.indexOf("=");
                queryPairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
                               URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
            return queryPairs;
        } catch (final UnsupportedEncodingException e) {
            // Just print the trace, we don't want to crash the app. If you ever get an empty query params
            // map back, then we know there was a malformed URL returned from the api (or a failure) 1/27/16 [KV]
            ClientLogger.e("Error while encoding query map", e);
        }

        return queryPairs;
    }

    /**
     * Return a builder of the given cache control because for some reason this doesn't exist already.
     * Useful for adding more attributes to an already defined {@link CacheControl}
     *
     * @param cacheControl The CacheControl to convert to a builder
     * @return A builder with the same attributes as the CacheControl passed in
     */
    @NotNull
    public static CacheControl.Builder getCacheControlBuilder(@NotNull CacheControl cacheControl) {
        final CacheControl.Builder builder = new CacheControl.Builder();
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

    /**
     * A helper which cancels an array of {@link Call} objects.
     */
    public static void cancelCalls(@NotNull final ArrayList<Call> callsToCancel) {
        final List<Call> callList = new CopyOnWriteArrayList<>(callsToCancel);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (final Call call : callList) {
                    if (call != null) {
                        call.cancel();
                    }
                }
            }
        }).start();
    }

    /**
     * Creates a local error with the provided error message.
     *
     * @param message The message explaining the error.
     */
    @NotNull
    public static VimeoResponse.Error.Exception createLocalError(@NotNull final String message) {
        return new VimeoResponse.Error.Exception(new IllegalArgumentException(message));
    }

    /**
     * Creates a local API error with the provided error message and invalid parameters list.
     *
     * @param message           The message explaining the error.
     * @param invalidParameters The parameters that were invalid.
     */
    @NotNull
    public static VimeoResponse.Error.Api createLocalApiError(@NotNull final String message,
                                                              @NotNull final InvalidParameter... invalidParameters) {
        return new VimeoResponse.Error.Api(ApiErrorFactory.createApiErrorWithInvalidParameters(message,
                                                                                               invalidParameters),
                                           VimeoResponse.HTTP_NONE);
    }

    /**
     * This utility method takes a Retrofit response and extracts a {@link ApiError} object out of it if
     * applicable. It will return null in the case where there has been a successful response.
     *
     * @param response A non-null response from the Vimeo API
     * @return a {@link VimeoResponse.Error} object extracted from the response or null
     */
    @NotNull
    public static <ResponseType_T> VimeoResponse.Error getErrorFromResponse(@NotNull final Response<ResponseType_T> response) {
        if (response.isSuccessful()) {
            throw new IllegalStateException("Cannot get error from a successful response");
        }
        final VimeoResponse.Error vimeoError;
        String errorBody = null;
        final boolean isUnauthorizedError = INVALID_TOKEN_VALUE.equals(response.headers().get(AUTHENTICATE_HEADER));
        if (response.errorBody() != null) {
            try {
                errorBody = response.errorBody().string();
                final ApiError apiError = getMoshi().adapter(ApiError.class).fromJson(errorBody);
                if (isUnauthorizedError) {
                    return new VimeoResponse.Error.InvalidToken(apiError, response.code());
                } else if (apiError != null) {
                    return new VimeoResponse.Error.Api(apiError, response.code());
                } else {
                    return new VimeoResponse.Error.Unknown(errorBody, response.code());
                }
            } catch (final Exception e) {
                ClientLogger.e("Error while attempting to convert response body to VimeoError", e);
                if (errorBody == null) {
                    errorBody = "Unable to read error body";
                }
                if (isUnauthorizedError) {
                    return new VimeoResponse.Error.InvalidToken(null, response.code());
                } else {
                    return new VimeoResponse.Error.Unknown(errorBody, response.code());
                }
            }
        } else if (isUnauthorizedError) {
            return new VimeoResponse.Error.InvalidToken(null, response.code());
        } else {
            return new VimeoResponse.Error.Unknown("Null error body", response.code());
        }
    }

    /**
     * Call this method to validate a string that is expected to not be blank. In the event that the string is
     * null or blank, the callback.failure will be invoked and false will be returned.
     *
     * @param input        The string to be tested.
     * @param errorMessage An error message to be relayed to the callback in the event of failure.
     * @param callback     A callback to be invoked when the string is null or empty.
     * @return false if the string is null or blank, true otherwise
     */
    public static boolean validateString(@Nullable final String input,
                                         @NotNull final String errorMessage,
                                         @NotNull final VimeoCallback callback) {
        if (isStringEmpty(input)) {
            callback.failure(new VimeoResponse.Error.Exception(new IllegalArgumentException(errorMessage)));
            return false;
        }
        return true;
    }

    /**
     * @return true if the passed string is null or empty, false otherwise
     */
    public static boolean isStringEmpty(@Nullable final String input) {
        return input == null || input.isEmpty();
    }

    /**
     * Prepare album parameters for making a network request to edit or add an album. This method will combine
     * known parameters with the optional extended parameters and perform validation on the known parameters.
     * In the event of invalid parameters, the callback.failure will be invoked and the method will return null.
     *
     * @param name         A non-blank string that will be the new name of the album.
     * @param albumPrivacy A representation of the viewing privacy for the album. The expectation is that if the
     *                     album is password protected that this object will also contain the required password.
     * @param description  A nullable description for the album.
     * @param parameters   Additional less common parameters that modify the album.
     * @param callback     A callback that will receive the results of the network request.
     * @return A prepared map of parameters or null in the event of an error.
     */
    @Nullable
    public static Map<String, Object> prepareAlbumEditParameters(@NotNull final String name,
                                                                 @NotNull final AlbumPrivacy albumPrivacy,
                                                                 @Nullable final String description,
                                                                 @Nullable final Map<String, Object> parameters,
                                                                 @NotNull final VimeoCallback callback) {
        Map<String, Object> retVal = new HashMap<>();
        if (parameters != null) {
            retVal = new HashMap<>(parameters);
        }
        retVal.put(Vimeo.PARAMETER_ALBUM_NAME, name);

        final String viewingPermissions = AlbumPrivacyUtils.getViewPrivacyType(albumPrivacy).getValue();

        if (!validateString(viewingPermissions, "ViewingPermissions can't be empty in album edit.", callback)) {
            return null;
        }

        retVal.put(Vimeo.PARAMETER_ALBUM_PRIVACY, viewingPermissions);

        if (description != null) {
            retVal.put(Vimeo.PARAMETER_ALBUM_DESCRIPTION, description);
        }

        if (AlbumPrivacyUtils.getViewPrivacyType(albumPrivacy) == AlbumViewPrivacyType.PASSWORD) {
            if (!validateString(albumPrivacy.getPassword(),
                                "Password can't be empty in password protected album edit.",
                                callback)) {
                return null;
            }
            retVal.put(Vimeo.PARAMETER_ALBUM_PASSWORD, albumPrivacy.getPassword());
        }

        return retVal;
    }

}
