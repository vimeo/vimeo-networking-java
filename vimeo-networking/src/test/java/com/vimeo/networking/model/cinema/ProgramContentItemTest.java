package com.vimeo.networking.model.cinema;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link ProgramContentItem}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class ProgramContentItemTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(ProgramContentItem.class);
    }

    @Test
    public void verifyTypeAdapterWasGenerated_Type() throws Exception {
        Utils.verifyTypeAdapterGeneration(ProgramContentItem.Type.class);
    }
}