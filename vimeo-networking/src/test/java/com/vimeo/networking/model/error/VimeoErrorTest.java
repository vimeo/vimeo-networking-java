package com.vimeo.networking.model.error;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link VimeoError}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class VimeoErrorTest {

    @Test
    public void verifyTypeAdapterWasNotGenerated() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(VimeoError.class);
    }
}