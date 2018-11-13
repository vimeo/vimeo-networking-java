package com.vimeo.networking2.common

/**
 * Represents data that can be uniquely identified by an [Entity.identifier].
 *
 * Implementors of this interface must provide a unique identifier.
 */
interface Entity {

    /**
     * The identifier, as a [String], that uniquely identifies this entity.
     *
     * @return a [String], or null if the entity does not have an identifier.
     */
    val identifier: String?

}
