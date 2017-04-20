package com.vimeo.networking.model.appconfiguration;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link ApiConfiguration}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class ApiConfigurationTest {

    @Test
    public void verifyTypeAdapterWasNotGenerated() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(ApiConfiguration.class);
    }

}