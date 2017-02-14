package com.vimeo.networking.model.vod;

import com.google.gson.annotations.SerializedName;
import com.vimeo.networking.model.Connection;
import com.vimeo.networking.model.ConnectionCollection;
import com.vimeo.networking.model.Metadata;
import com.vimeo.networking.model.User;
import com.vimeo.stag.UseStag;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * This model represents a "season" of content. Seasons are logical groupings
 * of videos set up by creators. All VODs will have a Season, even if it is
 * a feature film - the season would contain the film in this case.
 * <p>
 * Created by zetterstromk on 10/4/16.
 */
@SuppressWarnings("unused")
@UseStag
public class Season implements Serializable {

    private static final String SEASON_TYPE_MAIN = "main";
    private static final String SEASON_TYPE_EXTRAS = "extras";

    public enum SeasonType {
        MAIN,
        EXTRAS
    }

    private static final long serialVersionUID = 2770200708069413413L;

    @Nullable
    public String uri;

    @Nullable
    public String name;

    @Nullable
    public String type;

    @Nullable
    public String description;

    @Nullable
    public User user;

    public int position;

    @Nullable
    public Metadata metadata;

    @Nullable
    @SerializedName("resource_key")
    public String resourceKey;

    // -----------------------------------------------------------------------------------------------------
    // Getters
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Getters">

    @Nullable
    public String getUri() {
        return uri;
    }

    /**
     * @return the name of this season
     */
    @Nullable
    public String getName() {
        return name;
    }

    /**
     * Seasons can be either "main" or "extras", and the type
     * is always provided.
     *
     * @return the {@link SeasonType}
     */
    @NotNull
    public SeasonType getSeasonType() {
        if (SEASON_TYPE_EXTRAS.equals(type)) {
            return SeasonType.EXTRAS;
        }
        return SeasonType.MAIN;
    }

    /**
     * @return the description of this season
     */
    @Nullable
    public String getDescription() {
        return description;
    }

    /**
     * @return the Creator of this content
     */
    @Nullable
    public User getUser() {
        return user;
    }

    /**
     * Seasons are given a position that details the order in which they
     * should be shown. The lower the position, the sooner the content should
     * be made available to show to the consumer.
     *
     * @return the position of the season
     */
    public int getPosition() {
        return position;
    }

    /**
     * There will be a ConnectionCollection object holding a "videos" connection
     * for this season, as well as an InteractionCollection holding
     * possible purchase data
     *
     * @return the {@link Metadata}
     */
    @Nullable
    public Metadata getMetadata() {
        return metadata;
    }

    @Nullable
    public String getResourceKey() {
        return resourceKey;
    }

    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Helpers">

    @Nullable
    public ConnectionCollection getConnections() {
        return metadata != null ? metadata.connections : null;
    }

    @Nullable
    public Connection getVideosConnection() {
        ConnectionCollection connections = getConnections();
        return connections != null ? connections.videos : null;
    }

    @Nullable
    public String getVideosUri() {
        Connection videos = getVideosConnection();
        return videos != null ? videos.getUri() : null;
    }
    // </editor-fold>

    // -----------------------------------------------------------------------------------------------------
    // Comparison methods
    // -----------------------------------------------------------------------------------------------------
    // <editor-fold desc="Comparison methods">
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Season)) {
            return false;
        }
        Season that = (Season) obj;

        return (uri != null && that.getUri() != null) && uri.equals(that.getUri());
    }

    @Override
    public int hashCode() {
        return uri != null ? uri.hashCode() : 0;
    }
    // </editor-fold>
}
