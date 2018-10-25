package com.vimeo.networking2

/**
 * This model represents a "season" of content. Seasons are logical groupings
 * of videos set up by creators. All TVODs will have a Season, even if it is
 * a feature film - the season would contain the film in this case.
 */
data class Season(

    /**
     * The description for this season.
     */
    val description: String? = null,

    /**
     * Season metadata.
     */
    val metadata: Metadata<SeasonConnections, SeasonInteractions>? = null,

    /**
     * The descriptive name of the season.
     */
    val name: String? = null,

    /**
     * The position of the season relative to other seasons in the series.
     */
    val position: Int? = null,

    /**
     * The unique identifier for this On Demand season.
     */
    val resourceKey: String? = null,

    /**
     * The type of season.
     */
    val type: String? = null,

    /**
     * The season container''s relative URI.
     */
    val uri: String? = null,

    /**
     * The creator of this On Demand page.
     */
    val user: User? = null

) : Entity {

    override val identifier: String? = resourceKey

}
