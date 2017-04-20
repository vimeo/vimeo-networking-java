package com.vimeo.networking.model;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link Spatial}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class SpatialTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(Spatial.class);
    }

    @Test
    public void verifyTypeAdapterWasNotGenerated_Projection() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(Spatial.Projection.class);
    }

    @Test
    public void verifyTypeAdapterWasNotGenerated_Format() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(Spatial.Format.class);
    }
}