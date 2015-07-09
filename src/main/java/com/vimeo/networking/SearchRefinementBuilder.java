package com.vimeo.networking;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kylevenn on 7/9/15.
 */
public class SearchRefinementBuilder {

    private final static int FIVE_MINUTES = 60 * 5; // 60 seconds * 5 minutes = 300 seconds
    Map<String,String> parameterMap;

    public SearchRefinementBuilder() {
        parameterMap = new HashMap<>();
    }

    public SearchRefinementBuilder setSort(Vimeo.RefineSort sort) {
        parameterMap.put(Vimeo.PARAMETER_GET_SORT, sort.getText());
        return this;
    }

    public SearchRefinementBuilder setMinDuration(int duration) {
        parameterMap.put(Vimeo.PARAMETER_GET_LENGTH_MIN_DURATION, String.valueOf(duration));
        return this;
    }

    public SearchRefinementBuilder setMaxDuration(int duration) {
        parameterMap.put(Vimeo.PARAMETER_GET_LENGTH_MAX_DURATION, String.valueOf(duration));
        return this;
    }

    public SearchRefinementBuilder setDurationUnderFiveMinutes() {
        return setMaxDuration(FIVE_MINUTES);
    }

    public SearchRefinementBuilder setDurationOverFiveMinutes() {
        return setMinDuration(FIVE_MINUTES);
    }

    public SearchRefinementBuilder setUploadDateFilter(Vimeo.RefineUploadDate uploadDateFilter) {
        // Example: ?filter=upload_date&filter_upload_date=day
        parameterMap.put(Vimeo.PARAMETER_GET_FILTER, Vimeo.FILTER_UPLOAD);
        parameterMap.put(Vimeo.PARAMETER_GET_UPLOAD_DATE_FILTER, uploadDateFilter.getText());
        return this;
    }
}
