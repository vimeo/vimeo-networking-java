package com.vimeo.networking.model.vod;

import com.vimeo.networking.Utils;

import org.junit.Test;

/**
 * Unit tests for {@link VodList}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class VodListTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(VodList.class);
    }
}