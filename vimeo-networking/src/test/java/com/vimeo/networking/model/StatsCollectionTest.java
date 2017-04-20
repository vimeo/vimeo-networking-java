package com.vimeo.networking.model;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link StatsCollection}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class StatsCollectionTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(StatsCollection.class);
    }
}