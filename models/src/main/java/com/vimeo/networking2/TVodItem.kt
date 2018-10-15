package com.vimeo.networking2

/**
 * This class represents the model for a Video On Demand (VOD) container.
 */
data class TVodItem(

    /**
     * The description of this [TVodItem].
     */
    val description: String? = null,

    /**
     * This [TVodItem]'s film, if it is a film.
     */
    val film: Video? = null,

    /**
     * The link to the [TVodItem] on Vimeo.
     */
    val link: String? = null,

    /**
     * Metadata about [TVodItem].
     */
    val metadata: Metadata<TvodItemConnections, PurchaseOnDemandInteraction>? = null,

    /**
     * A descriptive title of this [TVodItem].
     */
    val name: String? = null,

    /**
     * The active poster for this [TVodItem].
     */
    val pictures: PictureCollection? = null,

    /**
     * Information on the time the [TVodItem] was published.
     */
    val published: Publish? = null,

    /**
     * Whether this [TVodItem] is for a film or a series.
     */
    val type: TVodType? = null,

    /**
     * The trailer for this [TVodItem].
     */
    val trailer: Video? = null,

    /**
     * The user who created this [TVodItem].
     */
    val user: User? = null

)
