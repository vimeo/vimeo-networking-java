package com.vimeo.networking.model.search;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link VideoSuggestion}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class VideoSuggestionTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(VideoSuggestion.class);
    }
}