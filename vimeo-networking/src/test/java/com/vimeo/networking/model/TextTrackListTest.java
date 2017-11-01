package com.vimeo.networking.model;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link TextTrackList}.
 * <p>
 * Created by Mohit Sarveiya on 4/20/17.
 */
public class TextTrackListTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(TextTrackList.class);
    }
}
