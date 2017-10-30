package com.vimeo.networking.model;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link TextTrack}.
 * <p>
 * Created by Mohit Sarveiya on 4/20/17.
 */
public class TextTrackTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(TextTrack.class);
    }
}
