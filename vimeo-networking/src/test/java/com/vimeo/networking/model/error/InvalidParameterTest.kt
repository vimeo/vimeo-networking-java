package com.vimeo.networking.model.error

import com.vimeo.networking.Utils
import com.vimeo.networking.utils.VimeoNetworkUtil
import org.assertj.core.api.Assertions

import org.junit.Test

/**
 * Unit tests for [InvalidParameter].
 *
 * Created by restainoa on 4/20/17.
 */
class InvalidParameterTest {

    @Test
    fun verifyTypeAdapterWasGenerated() {
        Utils.verifyTypeAdapterGeneration(InvalidParameter::class.java)
    }

    @Test
    fun `error code is properly set`() {
        ErrorCode.values().forEach {
            val invalidParameter = InvalidParameter("", it, "")
            Assertions.assertThat(invalidParameter.errorCode).isEqualTo(it)
        }
    }

    @Test
    fun `raw error code is properly set`() {
        ErrorCode.values().forEach {
            val invalidParameter = InvalidParameter("", it, "")
            val errorCode = VimeoNetworkUtil.getGson().fromJson(invalidParameter.rawErrorCode, ErrorCode::class.java)
            Assertions.assertThat(errorCode).isEqualTo(it)
        }
    }
}
