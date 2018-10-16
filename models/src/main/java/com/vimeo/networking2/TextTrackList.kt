package com.vimeo.networking2

/**
 * List of text tracks for a video.
 */
data class TextTrackList(
    override val total: Int? = null,
    override val page: Int? = null,
    override val perPage: Int? = null,
    override val paging: Paging? = null,
    override val data: List<TextTrack>? = null
) : Pageable<TextTrack>
