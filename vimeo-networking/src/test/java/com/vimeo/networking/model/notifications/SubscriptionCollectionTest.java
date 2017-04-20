package com.vimeo.networking.model.notifications;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link SubscriptionCollection}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class SubscriptionCollectionTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(SubscriptionCollection.class);
    }
}