package com.vimeo.networking.model.uploadquota;

import com.vimeo.networking.Utils;
import com.vimeo.networking.model.uploadquota.Space;

import org.junit.Test;

/**
 * Unit tests for {@link Space}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class SpaceTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(Space.class);
    }
}
