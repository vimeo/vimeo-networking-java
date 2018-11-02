package com.vimeo.networking2.enums

import org.junit.Assert.assertTrue
import org.junit.Test

class EnumsTest {

    private val models = listOf(
        AccountType::class.java,
        AlbumLayoutType::class.java,
        AlbumPrivacyViewValue::class.java,
        AlbumThemeType::class.java,
        ApiOptionsType::class.java,
        ApproachType::class.java,
        AttributionType::class.java,
        BillingPeriodType::class.java,
        CapabilitiesType::class.java,
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

    @Test
    fun `enum should have an UNKNOWN`() {
        models.forEach { kClass ->
            assertTrue("Enum should contain UNKNOWN type.", kClass.fields.map { it.name }.contains("UNKNOWN"))
        }
    }
}
