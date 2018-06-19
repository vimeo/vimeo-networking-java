package com.vimeo.networking.model.error

import com.vimeo.networking.Utils
import com.vimeo.networking.utils.VimeoNetworkUtil
import org.assertj.core.api.Assertions.assertThat

import org.junit.Test

/**
 * Unit tests for [VimeoError].
 *
 * Created by restainoa on 4/20/17.
 */
class VimeoErrorTest {

    @Test
    fun verifyTypeAdapterWasNotGenerated() {
        Utils.verifyNoTypeAdapterGeneration(VimeoError::class.java)
    }

    @Test
    fun `error code is properly set`() {
        ErrorCode.values().forEach {
            val vimeoError = VimeoError()
            vimeoError.errorCode = it
            assertThat(vimeoError.errorCode).isEqualTo(it)
        }
    }

    @Test
    fun `raw error code is properly set`() {
        ErrorCode.values().forEach {
            val vimeoError = VimeoError()
            vimeoError.errorCode = it
            val errorCode = VimeoNetworkUtil.getGson().fromJson(vimeoError.rawErrorCode, ErrorCode::class.java)
            assertThat(errorCode).isEqualTo(it)
        }
    }
}
