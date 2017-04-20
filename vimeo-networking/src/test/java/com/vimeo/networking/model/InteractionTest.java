package com.vimeo.networking.model;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link Interaction}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class InteractionTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(Interaction.class);
    }

    @Test
    public void verifyTypeAdapterWasGenerated_Stream() throws Exception {
        Utils.verifyTypeAdapterGeneration(Interaction.Stream.class);
    }
}