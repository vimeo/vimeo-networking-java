package com.vimeo.networking.model.appconfiguration;

import java.util.Arrays;

/**
 * Created by vennk on 5/20/15.
 */
public class FacebookConfiguration {

    public String[] requiredScopes;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FacebookConfiguration that = (FacebookConfiguration) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.deepEquals(requiredScopes, that.requiredScopes);
    }

    @Override
    public int hashCode() {
        return requiredScopes != null ? Arrays.hashCode(requiredScopes) : 0;
    }
}
