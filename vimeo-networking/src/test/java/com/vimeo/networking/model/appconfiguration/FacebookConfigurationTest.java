package com.vimeo.networking.model.appconfiguration;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link FacebookConfiguration}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class FacebookConfigurationTest {

    @Test
    public void verifyTypeAdapterWasNotGenerated() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(FacebookConfiguration.class);
    }
}