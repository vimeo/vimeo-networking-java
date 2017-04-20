package com.vimeo.networking.model;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link UploadQuota}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class UploadQuotaTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(UploadQuota.class);
    }
}