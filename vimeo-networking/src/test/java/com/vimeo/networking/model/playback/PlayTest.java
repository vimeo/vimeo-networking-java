package com.vimeo.networking.model.playback;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link Play}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class PlayTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(Play.class);
    }

    @Test
    public void verifyTypeAdapterWasGenerated_Status() throws Exception {
        Utils.verifyTypeAdapterGeneration(Play.Status.class);
    }
}