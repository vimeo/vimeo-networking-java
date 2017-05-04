package com.vimeo.networking.model;

import com.vimeo.networking.Utils;
import com.vimeo.networking.model.Video.TvodVideoType;
import com.vimeo.networking.model.playback.Play;
import com.vimeo.networking.model.playback.Play.Status;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link Video}.
 * <p>
 * Created by restainoa on 4/20/17.
 */
public class VideoTest {

    @Test
    public void test_verifyTypeAdapterWasGenerated() throws Exception {
        Utils.verifyTypeAdapterGeneration(Video.class);
    }

    @Test
    public void test_verifyTypeAdapterWasGenerated_Status() throws Exception {
        Utils.verifyTypeAdapterGeneration(Video.Status.class);
    }

    @Test
    public void test_verifyTypeAdapterWasNotGenerated_ContentRating() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(Video.ContentRating.class);
    }

    @Test
    public void test_verifyTypeAdapterWasNotGenerated_LicenseValue() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(Video.LicenseValue.class);
    }

    @Test
    public void test_verifyTypeAdapterWasNotGenerated_TvodVideoType() throws Exception {
        Utils.verifyNoTypeAdapterGeneration(TvodVideoType.class);
    }

    @NotNull
    private static Video createVideoWithConnectionCollection(@Nullable ConnectionCollection connectionCollection) {
        Metadata metadata = new Metadata();
        metadata.mConnections = connectionCollection;
        Video video = new Video();
        video.mMetadata = metadata;

        return video;
    }

    @Test
    public void test_isTvod_Tvod() throws Exception {
        ConnectionCollection connectionCollection = new ConnectionCollection();
        connectionCollection.mTvod = new Connection();
        Video tvod = createVideoWithConnectionCollection(connectionCollection);

        Assert.assertTrue(tvod.isTvod());
    }

    @Test
    public void test_isTvod_NotTvod() throws Exception {
        // Null tvod field
        ConnectionCollection connectionCollection = new ConnectionCollection();
        connectionCollection.mTvod = null;
        Video nonTvod1 = createVideoWithConnectionCollection(connectionCollection);

        Assert.assertFalse(nonTvod1.isTvod());

        // Null metadata field
        Video nonTvod2 = new Video();

        Assert.assertFalse(nonTvod2.isTvod());

        // Null connection collection field
        Video nonTvod3 = new Video();
        nonTvod3.mMetadata = new Metadata();

        Assert.assertFalse(nonTvod3.isTvod());
    }

    @NotNull
    private static Video createVideoWithSvodInteraction(@Nullable SvodInteraction interaction) {
        InteractionCollection interactions = new InteractionCollection();
        interactions.mSvod = interaction;
        Metadata metadata = new Metadata();
        metadata.mInteractions = interactions;
        Video video = new Video();
        video.mMetadata = metadata;

        return video;
    }

    @Test
    public void test_isSvod_Svod() throws Exception {
        Video video = createVideoWithSvodInteraction(new SvodInteraction());

        Assert.assertTrue(video.isSvod());
    }

    @Test
    public void test_isSvod_NotSvod() throws Exception {
        // Null svod interaction
        Video video1 = createVideoWithSvodInteraction(null);

        Assert.assertFalse(video1.isSvod());

        // Null interaction collection
        Video video2 = new Video();
        video2.mMetadata = new Metadata();
        video2.mMetadata.mInteractions = null;

        Assert.assertFalse(video2.isSvod());

        // Null metadata
        Video video3 = new Video();

        Assert.assertFalse(video3.isSvod());

    }

    @Test
    public void test_isTrailer_SvodNonTrailer() throws Exception {
        Video video = createVideoWithSvodInteraction(new SvodInteraction());
        Assert.assertTrue(video.isSvod());

        video.mMetadata.mConnections = new ConnectionCollection();
        video.mMetadata.mConnections.mTrailer = new Connection();

        Assert.assertFalse(video.isTrailer());
    }

    @Test
    public void test_isTrailer_SvodTrailer() throws Exception {
        Video video = createVideoWithSvodInteraction(new SvodInteraction());
        Assert.assertTrue(video.isSvod());

        video.mMetadata.mConnections = new ConnectionCollection();
        video.mMetadata.mConnections.mTrailer = null;

        Assert.assertTrue(video.isTrailer());
    }

    @Test
    public void test_isTrailer_TvodNonTrailer() throws Exception {
        ConnectionCollection connectionCollection = new ConnectionCollection();
        connectionCollection.mTvod = new Connection();
        Video tvod = createVideoWithConnectionCollection(connectionCollection);
        Assert.assertTrue(tvod.isTvod());

        tvod.mMetadata.mConnections.mTrailer = new Connection();

        Assert.assertFalse(tvod.isTrailer());
    }

    @Test
    public void test_isTrailer_TvodTrailer() throws Exception {
        ConnectionCollection connectionCollection = new ConnectionCollection();
        connectionCollection.mTvod = new Connection();
        Video tvod = createVideoWithConnectionCollection(connectionCollection);
        Assert.assertTrue(tvod.isTvod());

        tvod.mMetadata.mConnections.mTrailer = null;
        Assert.assertTrue(tvod.isTrailer());
    }

    @Test
    public void test_isTrailer_NonVodNonTrailer() throws Exception {
        Video video = new Video();

        Assert.assertFalse(video.isTrailer());

        video.mMetadata = new Metadata();
        video.mMetadata.mConnections = new ConnectionCollection();
        video.mMetadata.mConnections.mTrailer = null;

        Assert.assertFalse(video.isTrailer());
    }

    @Test
    public void test_isPlayable_Playable() throws Exception {
        Video video = new Video();
        video.mPlay = new Play();

        video.mPlay.setStatus(Status.PLAYABLE);
        Assert.assertTrue(video.isPlayable());
    }

    @Test
    public void test_isPlayable_NotPlayable() throws Exception {
        Video video = new Video();
        video.mPlay = new Play();

        video.mPlay.setStatus(Status.PURCHASE_REQUIRED);
        Assert.assertFalse(video.isPlayable());

        video.mPlay.setStatus(Status.RESTRICTED);
        Assert.assertFalse(video.isPlayable());

        video.mPlay.setStatus(Status.UNAVAILABLE);
        Assert.assertFalse(video.isPlayable());

        video.mPlay.setStatus(null);
        Assert.assertFalse(video.isPlayable());

        video.mPlay = null;
        Assert.assertFalse(video.isPlayable());
    }
}