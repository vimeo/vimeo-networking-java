package com.vimeo.networking.model;

import com.vimeo.networking.Utils;
import com.vimeo.networking.model.Recommendation.RecommendationType;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link Recommendation}.
 * <p>
 * Created by zetterstromk on 8/15/16.
 */
public class RecommendationTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(Recommendation.class);
    }

    @Test
    public void verifyTypeAdapterWasNotGenerated_RecommendationType() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(Recommendation.RecommendationType.class);
    }

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