package com.vimeo.networking.model.live

import com.vimeo.networking.Utils
import org.junit.Test

/**
 * Tests for the [Live] class
 *
 * Created by zetterstromk on 8/23/17.
 */
class LiveStatsViewersTest {

    @Test
    fun verifyTypeAdapterWasGenerated() {
        Utils.verifyTypeAdapterGeneration(LiveStatsViewers::class.java)
    }
}