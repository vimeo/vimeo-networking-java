package com.vimeo.networking.model.error;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link ErrorCode}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class ErrorCodeTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(ErrorCode.class);
    }
}