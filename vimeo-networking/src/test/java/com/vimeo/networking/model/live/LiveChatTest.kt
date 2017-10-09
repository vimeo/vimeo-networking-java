package com.vimeo.networking.model.live

import com.vimeo.networking.Utils
import org.junit.Test

/**
 * Tests for the [Live] class
 *
 * Created by zetterstromk on 8/23/17.
 */
class LiveChatTest {

    @Test
    fun verifyTypeAdapterWasGenerated() {
        Utils.verifyTypeAdapterGeneration(LiveChat::class.java)
    }
}