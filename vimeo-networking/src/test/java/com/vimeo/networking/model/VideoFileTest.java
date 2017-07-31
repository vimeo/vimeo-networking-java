package com.vimeo.networking.model;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link VideoFile}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class VideoFileTest {

    @Test
    public void verifyTypeAdapterWasNotGenerated() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(VideoFile.class);
    }

    @Test
    public void verifyTypeAdapterWasGenerated_MimeType() throws Exception {
        Utils.verifyTypeAdapterGeneration(VideoFile.MimeType.class);
    }
}