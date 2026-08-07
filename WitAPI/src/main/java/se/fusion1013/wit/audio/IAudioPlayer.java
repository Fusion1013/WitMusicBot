package se.fusion1013.wit.audio;

import se.fusion1013.wit.domain.entity.TrackEntity;

import java.util.function.Consumer;

public interface IAudioPlayer {

    void playTrack(TrackEntity track);
    void onTrackDone(Consumer<TrackEntity> method);
    boolean isTrackPlaying();

}
