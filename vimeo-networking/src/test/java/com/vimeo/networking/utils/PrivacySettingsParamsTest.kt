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

    private val privacySettingsParams = PrivacySettingsParams()

    @Test
    fun `Check privacy settings are set`() {
        privacySettingsParams
                .comments(Privacy.CommentValue.ANYBODY)
                .embed(Privacy.EmbedValue.PRIVATE)
                .view(Privacy.ViewValue.ANYBODY)
                .addToCollections(true)
                .download(true)

        privacySettingsParams.params.let {
            assertThat(it[Vimeo.PARAMETER_VIDEO_COMMENTS]).isEqualTo(Privacy.CommentValue.ANYBODY)
            assertThat(it[Vimeo.PARAMETER_VIDEO_EMBED]).isEqualTo(Privacy.EmbedValue.PRIVATE)
            assertThat(it[Vimeo.PARAMETER_VIDEO_VIEW]).isEqualTo(Privacy.ViewValue.ANYBODY)
            assertThat(it[Vimeo.PARAMETER_VIDEO_ADD]).isEqualTo(true)
            assertThat(it[Vimeo.PARAMETER_VIDEO_DOWNLOAD]).isEqualTo(true)
        }
    }

    @Test
    fun `Check empty privacy settings are null`() {
        privacySettingsParams.params.let {
            assertThat(it[Vimeo.PARAMETER_VIDEO_COMMENTS]).isNull()
            assertThat(it[Vimeo.PARAMETER_VIDEO_EMBED]).isNull()
            assertThat(it[Vimeo.PARAMETER_VIDEO_VIEW]).isNull()
            assertThat(it[Vimeo.PARAMETER_VIDEO_ADD]).isNull()
            assertThat(it[Vimeo.PARAMETER_VIDEO_DOWNLOAD]).isNull()
        }
    }

}
