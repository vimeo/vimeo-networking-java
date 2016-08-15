package com.vimeo.networking.model;

/**
 * A {@link BaseResponseList} for {@link Recommendation} objects
 * Created by zetterstromk on 8/15/16.
 */
public class RecommendationList extends BaseResponseList<Recommendation> {

    private static final long serialVersionUID = -1488717279892501485L;

    @Override
    public Class<Recommendation> getModelClass() {
        return Recommendation.class;
    }
}
