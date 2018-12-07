package com.vimeo.networking.model;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link SubscriptionRenewal}.
 * <p>
 * Created by brentwatson on 12/7/18.
 */
public class SubscriptionRenewalTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(SubscriptionRenewal.class);
    }
}
