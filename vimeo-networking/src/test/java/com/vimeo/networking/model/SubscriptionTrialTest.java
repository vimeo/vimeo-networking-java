package com.vimeo.networking.model;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link SubscriptionTrial}.
 * <p>
 * Created by brentwatson on 12/7/18.
 */
public class SubscriptionTrialTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(SubscriptionTrial.class);
    }

    @Test
    public void verifyTypeAdapterWasNotGenerated_TrialType() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(SubscriptionTrial.TrialType.class);
    }
}
