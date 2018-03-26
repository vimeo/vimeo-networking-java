package com.vimeo.networking.model.uploadquota;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link Periodic}.
 * <p>
 * Created by restainoa on 3/26/18.
 */
public class PeriodicTest {

    @Test
    public void verifyThatTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(Periodic.class);
    }
}
