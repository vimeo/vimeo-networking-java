package com.vimeo.networking.adapters

import com.squareup.moshi.Moshi
import com.vimeo.networking2.TVodType
import com.vimeo.networking2.enums.*
import org.junit.Assert
import org.junit.Test

/**
 * Tests using the [ReflectiveFallbackEnumAdapterFactory] and all the serializable enum instances in the library.
 */
class ReflectiveFallbackEnumAdapterFactoryTest {

    @Test
    fun `verify enum conversion using reflective default adapter`() {
        val enums = listOf(
            AccountType::class.java,
            AlbumLayoutType::class.java,
            AlbumPrivacyViewValue::class.java,
            AlbumThemeType::class.java,
            ApiOptionsType::class.java,
            ApproachType::class.java,
            AttributionType::class.java,
            BillingPeriodType::class.java,
            CinemaType::class.java,
            CommentType::class.java,
            CommentValue::class.java,
            ContentFilterType::class.java,
            DownloadType::class.java,
            EmbedValue::class.java,
            ErrorCode::class.java,
            FollowType::class.java,
            GroupForumsPrivacyValue::class.java,
            GroupPrivacyValue::class.java,
            LicenseType::class.java,
            LiveQuotaStatus::class.java,
            LiveStatus::class.java,
            NotificationType::class.java,
            PaymentType::class.java,
            PictureType::class.java,
            PurchaseStatusType::class.java,
            RecommendationType::class.java,
            SearchType::class.java,
            SortType::class.java,
            SpatialProjectionType::class.java,
            StereoFormatType::class.java,
            StreamType::class.java,
            TextTrackType::class.java,
            TranscodeType::class.java,
            TVodType::class.java,
            UploadSpaceType::class.java,
            UploadStatusType::class.java,
            UserActivityType::class.java,
            UserBadgeType::class.java,
            VideoActionType::class.java,
            VideoBadgeType::class.java,
            VideoPlayStatus::class.java,
            VideoQualityType::class.java,
            VideoReviewTaskStatusType::class.java,
            VideoSourceType::class.java,
            VideoStatusType::class.java,
            ViewValue::class.java
        )

        val moshi = Moshi.Builder().add(ReflectiveFallbackEnumAdapterFactory()).build()

        for (enumClass in enums) {
            val unknownValue = requireNotNull(
                enumClass.enumConstants.filterIsInstance<Enum<*>>().find { it.name == "UNKNOWN" }
            )

            Assert.assertNotNull("$enumClass missing UNKNOWN", unknownValue)

            val enumAdapter = moshi.adapter<Enum<*>>(enumClass)
            Assert.assertEquals(enumAdapter.toJson(unknownValue), "\"UNKNOWN\"")
            Assert.assertEquals(enumAdapter.fromJson("\"asdfasdf\""), unknownValue)
            Assert.assertEquals(enumAdapter.fromJson("null"), unknownValue)
        }
    }
}
