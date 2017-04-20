package com.vimeo.networking.model.vod;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link Season}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class SeasonTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(Season.class);
    }

    @Test
    public void verifyTypeAdapterWasNotGenerated_SeasonType() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(Season.SeasonType.class);
    }
}