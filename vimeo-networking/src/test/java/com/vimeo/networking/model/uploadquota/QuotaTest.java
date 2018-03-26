package com.vimeo.networking.model.uploadquota;

import com.vimeo.networking.Utils;
import com.vimeo.networking.model.uploadquota.Quota;

import org.junit.Test;

/**
 * Unit tests for {@link Quota}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class QuotaTest {

    @Test
    public void verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(Quota.class);
    }
}
