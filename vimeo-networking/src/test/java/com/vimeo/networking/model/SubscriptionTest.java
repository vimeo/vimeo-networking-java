package com.vimeo.networking.model;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link Subscription}.
 * <p>
 * Created by brentwatson on 12/7/18.
 */
public class SubscriptionTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(Subscription.class);
    }
}
