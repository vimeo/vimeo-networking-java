package com.vimeo.networking.model;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link BaseResponseList}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class BaseResponseListTest {

    @Test
    public void verifyTypeAdapterWasNotGenerated() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(BaseResponseList.class);
    }
}