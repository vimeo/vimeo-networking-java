package com.vimeo.networking2.enums

/**
 * Different types of approach for uploading a video.
 */
enum class ApproachType(override val value: String?) : StringValue {

    /**
     * Form-based upload approach. With this approach, your end user uploads
     * the video to Vimeo through an HTML form, which you deploy in your app or through a link.
     */
    POST("post"),

    /**
     * With the pull approach, you point us toward a video file that already exists on the internet.
     * We make a copy of the video, assign it a place on Vimeo, and otherwise treat it like any other
     * upload. We even handle any connectivity issues that might come up. All we need is the
     * absolute path to the video file.
     */
    PULL("pull"),

    /**
     * Streaming based approach for uploading videos. We recommend that you use the tus approach
     * for uploading videos. This approach will work on API versions 3.4.
     */
    STREAMING("streaming"),

    /**
     * Use the tus protocol to upload videos. With the tus approach, you can poll the progress of
     * the upload as it's occurring, and pick up a partial upload where it left off — whether
     * that's because of a lost internet connection or because you've programmed pause functionality
     * into your app.
     */
    TUS("tus"),

    /**
     * Unknown upload approach.
     */
    UNKNOWN(null)
}
