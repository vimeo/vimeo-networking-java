package com.vimeo.networking.model.appconfiguration

import com.vimeo.networking.Utils
import com.vimeo.networking.model.appconfiguration.live.LiveHeartbeatConfiguration
import org.junit.Test

/**
 * Tests for the [Live] class
 *
 * Created by zetterstromk on 8/23/17.
 */
class LiveHeartbeatConfigurationTest {

    @Test
    fun verifyTypeAdapterWasGenerated() {
        Utils.verifyTypeAdapterGeneration(LiveHeartbeatConfiguration::class.java)
    }
}