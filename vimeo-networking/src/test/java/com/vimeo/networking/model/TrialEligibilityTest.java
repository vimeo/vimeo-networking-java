package com.vimeo.networking.model;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link TrialEligibility}.
 * <p>
 * Created by brentwatson on 12/7/18.
 */
public class TrialEligibilityTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(TrialEligibility.class);
    }
}
