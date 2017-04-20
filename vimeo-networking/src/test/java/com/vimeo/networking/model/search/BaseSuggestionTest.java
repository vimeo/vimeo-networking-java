package com.vimeo.networking.model.search;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link BaseSuggestion}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class BaseSuggestionTest {

    @Test
    public void verifyTypeAdapterWasNotGenerated() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(BaseSuggestion.class);
    }
}