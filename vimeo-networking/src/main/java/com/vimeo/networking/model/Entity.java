package com.vimeo.networking.model;

import org.jetbrains.annotations.Nullable;

/**
 * Represents data that can be uniquely identified by an {@link Entity#getIdentifier()}.
 * <p>
 * Implementors of this interface must provide a unique identifier.
 */
public interface Entity {

    /**
     * The identifier, as a {@link String}, that uniquely identifies this entity.
     *
     * @return a {@link String}, or null if the entity does not have an identifier.
     */
    @Nullable
    String getIdentifier();

}
