package com.vimeo.networking.model;

import com.vimeo.networking.model.Recommendation.RecommendationType;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link Recommendation}
 * Created by zetterstromk on 8/15/16.
 */
public class RecommendationTest {

    @Test
    public void testGetRecommendationType() throws Exception {
        Recommendation recommendation = new Recommendation();

        Assert.assertNotNull(recommendation.getRecommendationType());
        Assert.assertEquals(recommendation.getRecommendationType(), RecommendationType.NONE);

        recommendation.mRecommendationType = Recommendation.TYPE_CHANNEL;
        Assert.assertEquals(recommendation.getRecommendationType(), RecommendationType.CHANNEL);

        recommendation.mRecommendationType = Recommendation.TYPE_USER;
        Assert.assertEquals(recommendation.getRecommendationType(), RecommendationType.USER);

    }
}