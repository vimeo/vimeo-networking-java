package com.vimeo.networking.model.appconfiguration;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link AppConfiguration}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class AppConfigurationTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(AppConfiguration.class);
    }
}