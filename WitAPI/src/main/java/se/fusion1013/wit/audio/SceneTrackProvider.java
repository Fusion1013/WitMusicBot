package se.fusion1013.wit.audio;

import se.fusion1013.wit.domain.entity.TrackEntity;

import java.util.*;

public class SceneTrackProvider implements IAudioTrackProvider {

    private final TrackEntity introTrack;
    private final List<TrackEntity> tracks;
    private final Queue<TrackEntity> trackQueue = new LinkedList<>();
    private boolean hasIntroPlayed = false;

    public SceneTrackProvider(TrackEntity introTrack, List<TrackEntity> tracks) {
        this.introTrack = introTrack;
        this.tracks = new ArrayList<>(tracks);
        Collections.shuffle(this.tracks);
        this.trackQueue.addAll(this.tracks);
    }

    @Override
    public TrackEntity getNext() {
        if (!hasIntroPlayed && introTrack != null) {
            hasIntroPlayed = true;
            return introTrack;
        }
        if (trackQueue.isEmpty()) {
            List<TrackEntity> tracksFromMood = new ArrayList<>(tracks);
            Collections.shuffle(tracksFromMood);
            trackQueue.addAll(tracksFromMood);
        }
        return trackQueue.remove();
    }

    @Override
    public void reset() {
        hasIntroPlayed = false;
    }
}
