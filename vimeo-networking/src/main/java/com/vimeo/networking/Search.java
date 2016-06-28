/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Vimeo
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

import com.vimeo.networking.callbacks.ModelCallback;
import com.vimeo.networking.model.search.SearchResponse;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * Class to handle searching. To use this class, your app must be whitelisted. If you are not an approved app,
 * you can still use the existing search mechanism {@link VimeoClient#search(String, String, ModelCallback, Map, String)}.
 * <p/>
 * Created by zetterstromk on 6/27/16.
 */
public final class Search {

    private Search() {
    }

    public enum FilterType {
        VIDEO("clip"),
        VOD("ondemand"),
        USER("people"),
        CHANNEL("channel"),
        GROUP("group");

        private final String mText;

        FilterType(String text) {
            this.mText = text;
        }

        public String getText() {
            return this.mText;
        }

        public static FilterType fromString(String text) {
            if (text != null) {
                for (FilterType b : FilterType.values()) {
                    if (text.equalsIgnoreCase(b.mText)) {
                        return b;
                    }
                }
            }
            throw new IllegalArgumentException("No constant with text " + text + " found");
        }
    }

    public enum FilterUpload {
        TODAY("today"),
        THIS_WEEK("this-week"),
        THIS_MONTH("this-month"),
        THIS_YEAR("this-year");

        private final String mText;

        FilterUpload(String text) {
            this.mText = text;
        }

        public String getText() {
            return this.mText;
        }

        public static FilterUpload fromString(String text) {
            if (text != null) {
                for (FilterUpload b : FilterUpload.values()) {
                    if (text.equalsIgnoreCase(b.mText)) {
                        return b;
                    }
                }
            }
            throw new IllegalArgumentException("No constant with text " + text + " found");
        }
    }

    public enum Sort {
        RELEVANCE("relevance"),
        LATEST("latest"),
        POPULARITY("popularity"),
        DURATION("duration"),
        JOIN_DATE("join_date"),
        ALPHABETICAL("alphabetical");

        private final String mText;

        Sort(String text) {
            this.mText = text;
        }

        public String getText() {
            return this.mText;
        }

        public static Sort fromString(String text) {
            if (text != null) {
                for (Sort b : Sort.values()) {
                    if (text.equalsIgnoreCase(b.mText)) {
                        return b;
                    }
                }
            }
            throw new IllegalArgumentException("No constant with text " + text + " found");
        }
    }

    public enum FilterDuration {
        SHORT("short"),
        MEDIUM("medium"),
        LONG("long");

        private final String mText;

        FilterDuration(String text) {
            this.mText = text;
        }

        public String getText() {
            return this.mText;
        }

        public static FilterDuration fromString(String text) {
            if (text != null) {
                for (FilterDuration b : FilterDuration.values()) {
                    if (text.equalsIgnoreCase(b.mText)) {
                        return b;
                    }
                }
            }
            throw new IllegalArgumentException("No constant with text " + text + " found");
        }
    }

    public enum FilterCategory {
        ANIMATION("animation"),
        ART("art"),
        CAMERA_TECHNIQUES("cameratechniques"),
        COMEDY("comedy"),
        DOCUMENTARY("documentary"),
        EXPERIMENTAL("experimental"),
        FASHION("fashion"),
        FOOD("food"),
        INSTRUCTIONALS("instructionals"),
        JOURNALISM("journalism"),
        MUSIC("music"),
        NARRATIVE("narrative"),
        PERSONAL("personal"),
        SPORTS("sports"),
        TALKS("talks"),
        TRAVEL("travel");

        private final String mText;

        FilterCategory(String text) {
            this.mText = text;
        }

        public String getText() {
            return this.mText;
        }

        public static FilterCategory fromString(String text) {
            if (text != null) {
                for (FilterCategory b : FilterCategory.values()) {
                    if (text.equalsIgnoreCase(b.mText)) {
                        return b;
                    }
                }
            }
            throw new IllegalArgumentException("No constant with text " + text + " found");
        }
    }

    static final String FILTER_CATEGORY = "filter_category";
    static final String FILTER_UPLOADED = "filter_uploaded";
    static final String FILTER_DURATION = "filter_duration";
    static final String FILTER_TYPE = "filter_type";
    static final String FILTER_FEATURED_COUNT = "featured_clip_count";

    public static Call<SearchResponse> search(@NotNull String query, @NotNull FilterType type,
                                              @NotNull ModelCallback<SearchResponse> callback,
                                              @Nullable Map<String, String> refinementMap,
                                              @Nullable String fieldFilter) {
        if (refinementMap == null) {
            refinementMap = new HashMap<>();
        }
        refinementMap.put(FILTER_TYPE, type.getText());
        Map<String, String> queryMap =
                VimeoClient.getInstance().createQueryMap(query, refinementMap, fieldFilter);
        // VimeoClient is the end-all interactor with the retrofit service
        return VimeoClient.getInstance().search(queryMap, callback);
    }

    public static class QueryParameterProvider {

        private final Map<String, String> mQueryParameters;

        public QueryParameterProvider() {
            mQueryParameters = new HashMap<>();
        }

        public QueryParameterProvider setSort(@Nullable Sort sort) {
            if (sort != null) {
                mQueryParameters.put(Vimeo.PARAMETER_GET_SORT, sort.getText());
            } else {
                mQueryParameters.remove(Vimeo.PARAMETER_GET_SORT);
            }
            return this;
        }

        public QueryParameterProvider setDirection(@Nullable String direction) {
            if (Vimeo.SORT_DIRECTION_ASCENDING.equals(direction) ||
                Vimeo.SORT_DIRECTION_DESCENDING.equals(direction)) {
                mQueryParameters.put(Vimeo.PARAMETER_GET_DIRECTION, direction);
            } else {
                mQueryParameters.remove(Vimeo.PARAMETER_GET_DIRECTION);
            }
            return this;
        }

        public QueryParameterProvider setUploadDate(@Nullable FilterUpload filterUpload) {
            if (filterUpload != null) {
                mQueryParameters.put(FILTER_UPLOADED, filterUpload.getText());
            } else {
                mQueryParameters.remove(FILTER_UPLOADED);
            }
            return this;
        }

        public QueryParameterProvider setDuration(@Nullable FilterDuration filterDuration) {
            if (filterDuration != null) {
                mQueryParameters.put(FILTER_DURATION, filterDuration.getText());
            } else {
                mQueryParameters.remove(FILTER_DURATION);
            }
            return this;
        }

        public QueryParameterProvider setCategory(@Nullable FilterCategory filterCategory) {
            if (filterCategory != null) {
                mQueryParameters.put(FILTER_CATEGORY, filterCategory.getText());
            } else {
                mQueryParameters.remove(FILTER_CATEGORY);
            }
            return this;
        }

        public QueryParameterProvider setFeaturedVideoCount(int count) {
            mQueryParameters.put(FILTER_FEATURED_COUNT, String.valueOf(count));
            return this;
        }

        public Map<String, String> getQueryParameters() {
            return mQueryParameters;
        }
    }
}
