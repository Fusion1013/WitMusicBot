package se.fusion1013.wit.audio;

import se.fusion1013.wit.domain.entity.TrackEntity;

/**
 * The AudioManager handles queueing audio to the specified Audio Player. An audio player could be discord, spotify, etc.
 */
public class CustomAudioManager {

    private static IAudioTrackProvider AUDIO_TRACK_PROVIDER;
    private static IAudioPlayer AUDIO_PLAYER;

    public void setAudioTrackProvider(IAudioTrackProvider provider) {
        System.out.println("Set audio tag provider: " + provider);
        AUDIO_TRACK_PROVIDER = provider;
        if (AUDIO_TRACK_PROVIDER == null) return;
        onTrackDone(null); // Start a new track
    }
    public void setAudioPlayer(IAudioPlayer audioPlayer) {
        AUDIO_PLAYER = audioPlayer;
        AUDIO_PLAYER.onTrackDone(this::onTrackDone);
    }

    public TrackEntity getNextTrack() {
        if (AUDIO_TRACK_PROVIDER == null) return null;
        return AUDIO_TRACK_PROVIDER.getNext();
    }

    private void onTrackDone(TrackEntity track) {
        if (AUDIO_PLAYER.isTrackPlaying()) return; // Do not schedule another track if one is already playing
        TrackEntity nextTrack = getNextTrack();
        if (nextTrack == null) return;
        AUDIO_PLAYER.playTrack(nextTrack);
    }

    private CustomAudioManager() {}

    private static CustomAudioManager INSTANCE;
    public static CustomAudioManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CustomAudioManager();
        }
        return INSTANCE;
    }
}
