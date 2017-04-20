package com.vimeo.networking.model.playback;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link PlayProgress}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class PlayProgressTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(PlayProgress.class);
    }
}