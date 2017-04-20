package com.vimeo.networking.model.search;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link SearchFacet}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class SearchFacetTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(SearchFacet.class);
    }
}