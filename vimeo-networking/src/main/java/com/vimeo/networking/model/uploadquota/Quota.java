package com.vimeo.networking.model.uploadquota;

import org.jetbrains.annotations.Nullable;

/**
 * The common interface implemented by various quotas, like {@link Space}, {@link Periodic}, and {@link Lifetime}.
 * <p>
 * Created by restainoa on 3/27/18.
 */
public interface Quota {

    /**
     * @return the amount of free space in bytes left in the quota. May be null.
     */
    @Nullable
    Long getFree();

    /**
     * @return the amount of space in bytes used in the quota. May be null.
     */
    @Nullable
    Long getUsed();

    /**
     * @return the total amount of space in bytes in the quota. May be null.
     */
    @Nullable
    Long getMax();

}
