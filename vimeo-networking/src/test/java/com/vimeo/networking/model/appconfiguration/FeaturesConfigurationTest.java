package com.vimeo.networking.model.appconfiguration;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link FeaturesConfiguration}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class FeaturesConfigurationTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(FeaturesConfiguration.class);
    }
}