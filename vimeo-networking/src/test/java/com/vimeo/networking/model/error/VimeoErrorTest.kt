package com.vimeo.networking.model.error

import com.google.gson.annotations.SerializedName
import com.vimeo.networking.Utils
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
        Utils.verifyTypeAdapterGeneration(VimeoError::class.java)
    }

    /**
     * Extract the [SerializedName] annotation from the [ErrorCode] enum value.
     */
    private fun ErrorCode.getAnnotation(): SerializedName? =
        ErrorCode::class.java.getField(this.name).getAnnotation(SerializedName::class.java)

    @Test
    fun `error code is properly set`() {
        ErrorCode.values().forEach {
            val vimeoError = VimeoError()
            vimeoError.rawErrorCode = it.getAnnotation()?.value
            assertThat(vimeoError.errorCode).isEqualTo(it)
        }
    }

    @Test
    fun `error code defaults to DEFAULT`() {
        with(VimeoError()) {
            assertThat(errorCode).isEqualTo(ErrorCode.DEFAULT)
            assertThat(rawErrorCode).isNull()
        }
    }

}
