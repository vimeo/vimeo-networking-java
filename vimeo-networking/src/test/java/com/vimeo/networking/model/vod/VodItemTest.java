package com.vimeo.networking.model.vod;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link VodItem}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class VodItemTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(VodItem.class);
    }

    @Test
    public void verifyTypeAdapterWasGenerated_VodType() throws Exception {
        Utils.verifyTypeAdapterGeneration(VodItem.VodType.class);
    }

    @Test
    public void verifyTypeAdapterWasGenerated_Publish() throws Exception {
        Utils.verifyTypeAdapterGeneration(VodItem.Publish.class);
    }
}