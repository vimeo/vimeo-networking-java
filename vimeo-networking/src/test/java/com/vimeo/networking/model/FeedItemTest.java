package com.vimeo.networking.model;

import com.vimeo.networking.Utils;
import com.vimeo.networking.model.FeedItem.AttributionType;

import org.junit.Test;

/**
 * Unit tests for {@link FeedItem}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class FeedItemTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(FeedItem.class);
    }

    @Test
    public void verifyTypeAdapterWasNotGenerated_AttributionType() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(AttributionType.class);
    }
}