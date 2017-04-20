package com.vimeo.networking.model;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link Video}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class VideoTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(Video.class);
    }

    @Test
    public void verifyTypeAdapterWasGenerated_Status() throws Exception {
        Utils.verifyTypeAdapterGeneration(Video.Status.class);
    }

    @Test
    public void verifyTypeAdapterWasNotGenerated_ContentRating() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(Video.ContentRating.class);
    }

    @Test
    public void verifyTypeAdapterWasNotGenerated_LicenseValue() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(Video.LicenseValue.class);
    }

    @Test
    public void verifyTypeAdapterWasNotGenerated_VodVideoType() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(Video.VodVideoType.class);
    }
}