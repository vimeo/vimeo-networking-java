package com.vimeo.networking.model;

import com.vimeo.stag.UseStag;
import com.vimeo.stag.UseStag.FieldOption;

/**
 * A {@link BaseResponseList} for {@link Recommendation} objects
 * Created by zetterstromk on 8/15/16.
 */
@UseStag(FieldOption.NONE)
public class RecommendationList extends BaseResponseList<Recommendation> {

    private static final long serialVersionUID = -1488717279892501485L;

    @Override
    public Class<Recommendation> getModelClass() {
        return Recommendation.class;
    }
}
