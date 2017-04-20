package com.vimeo.networking.model;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link InteractionCollection}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class InteractionCollectionTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(InteractionCollection.class);
    }
}