package com.vimeo.networking.model;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link PictureCollection}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class PictureCollectionTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(PictureCollection.class);
    }

    @Test
    public void verifyTypeAdapterWasNotGenerated_PictureType() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(PictureCollection.PictureType.class);
    }
}