package com.vimeo.networking.utils

import com.vimeo.networking.Vimeo
import com.vimeo.networking.model.Privacy
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * Tests for {@link PrivacySettingsParams}.
 *
 * Created by Mohit Sarveiya on 10/30/15.
 */
class PrivacySettingsParamsTest {

    @Test
    fun `Check privacy settings are set`() {
        val privacySettingsParams = PrivacySettingsParams()
        privacySettingsParams
                .comments(Privacy.CommentValue.ANYBODY)
                .embed(Privacy.EmbedValue.PRIVATE)
                .view(Privacy.ViewValue.ANYBODY)
                .addToCollections(true)
                .download(true)

        with(privacySettingsParams.params) {
            assertThat(this[Vimeo.PARAMETER_VIDEO_COMMENTS]).isEqualTo(Privacy.CommentValue.ANYBODY)
            assertThat(this[Vimeo.PARAMETER_VIDEO_EMBED]).isEqualTo(Privacy.EmbedValue.PRIVATE)
            assertThat(this[Vimeo.PARAMETER_VIDEO_VIEW]).isEqualTo(Privacy.ViewValue.ANYBODY)
            assertThat(this[Vimeo.PARAMETER_VIDEO_ADD]).isEqualTo(true)
            assertThat(this[Vimeo.PARAMETER_VIDEO_DOWNLOAD]).isEqualTo(true)
        }
    }

    @Test
    fun `Check empty privacy settings are null`() {
        with(PrivacySettingsParams().params) {
            assertThat(this[Vimeo.PARAMETER_VIDEO_COMMENTS]).isNull()
            assertThat(this[Vimeo.PARAMETER_VIDEO_EMBED]).isNull()
            assertThat(this[Vimeo.PARAMETER_VIDEO_VIEW]).isNull()
            assertThat(this[Vimeo.PARAMETER_VIDEO_ADD]).isNull()
            assertThat(this[Vimeo.PARAMETER_VIDEO_DOWNLOAD]).isNull()
            assertThat(this).isEmpty()
        }
    }

}
