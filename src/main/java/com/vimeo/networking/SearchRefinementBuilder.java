package com.vimeo.networking;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kylevenn on 7/9/15.
 */
public class SearchRefinementBuilder {

    private final static int FIVE_MINUTES = 60 * 5; // 60 seconds * 5 minutes = 300 seconds
    Map<String, String> parameterMap;

    public SearchRefinementBuilder() {
        parameterMap = new HashMap<>();
    }

    public SearchRefinementBuilder(Vimeo.RefineSort sort) {
        parameterMap = new HashMap<>();
        this.setSort(sort);
    }

    public SearchRefinementBuilder setSort(Vimeo.RefineSort sort) {
        switch (sort) {
            case AZ:
            case ZA:
                parameterMap.put(Vimeo.PARAMETER_GET_SORT, Vimeo.SORT_ALPHABETICAL);
                parameterMap.put(Vimeo.PARAMETER_GET_DIRECTION, sort.getText());
                break;
            default:
                parameterMap.put(Vimeo.PARAMETER_GET_SORT, sort.getText());
                break;
        }
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

    // Example: ?filter=upload_date&filter_upload_date=day
    public SearchRefinementBuilder setUploadDateFilter(Vimeo.RefineUploadDate uploadDateFilter) {
        // Only include in refinement parameters if it's not ANYTIME
        if (uploadDateFilter != Vimeo.RefineUploadDate.ANYTIME) {
            parameterMap.put(Vimeo.PARAMETER_GET_FILTER, Vimeo.FILTER_UPLOAD);
            parameterMap.put(Vimeo.PARAMETER_GET_UPLOAD_DATE_FILTER, uploadDateFilter.getText());
        }
        return this;
    }

    public Map<String, String> build() {
        return parameterMap;
    }
}
