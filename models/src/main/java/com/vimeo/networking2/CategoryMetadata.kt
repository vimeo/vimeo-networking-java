package com.vimeo.networking2

data class CategoryMetadata(

    /**
     * A collection of information that is connected to this resource.
     */
    val connections: CategoryConnections?,

    /**
     * The permissible actions related to the category.
     */
    val interactions: CategoryInteractions?

)
