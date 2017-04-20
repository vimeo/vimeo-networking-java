package com.vimeo.networking.model.error;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link InvalidParameter}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class InvalidParameterTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(InvalidParameter.class);
    }
}