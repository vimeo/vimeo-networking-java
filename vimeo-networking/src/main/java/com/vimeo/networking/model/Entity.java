package com.vimeo.networking.model;

import org.jetbrains.annotations.NotNull;

/**
 * Represents data that can be uniquely identified by an {@link Entity#identifier()}.
 * <p>
 * Implementors of this interface must provide a unique identifier.
 */
public interface Entity {

    /**
     * The identifier, as a {@link String}, that uniquely identifies this entity.
     *
     * @return a non null {@link String}.
     */
    @NotNull
    String identifier();

}
