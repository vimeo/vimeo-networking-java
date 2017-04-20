package com.vimeo.networking.model;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link Privacy}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class PrivacyTest {

    @Test
    public void verifyTypeAdapterWasNotGenerated() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(Privacy.class);
    }

    @Test
    public void verifyTypeAdapterWasGenerated_PrivacyValue() throws Exception {
        Utils.verifyTypeAdapterGeneration(Privacy.PrivacyValue.class);
    }
}