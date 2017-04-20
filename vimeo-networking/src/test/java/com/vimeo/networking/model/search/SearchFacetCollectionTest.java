package com.vimeo.networking.model.search;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link SearchFacetCollection}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class SearchFacetCollectionTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(SearchFacetCollection.class);
    }
}