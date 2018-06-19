package com.vimeo.networking.model.error

import com.vimeo.networking.Utils
import org.assertj.core.api.Assertions.assertThat

import org.junit.Test

/**
 * Unit tests for [VimeoError].
 *
 *
 * Created by restainoa on 4/20/17.
 */
class VimeoErrorTest {

    @Test
    @Throws(Exception::class)
    fun verifyTypeAdapterWasNotGenerated() {
        Utils.verifyNoTypeAdapterGeneration(VimeoError::class.java)
    }

    @Test
    fun verifyErrorCodeSerializationWorks() {
        ErrorCode.values().forEach {
            val vimeoError = VimeoError()
            vimeoError.errorCode = it
            assertThat(vimeoError.errorCode).isEqualTo(it)
        }
    }
}
