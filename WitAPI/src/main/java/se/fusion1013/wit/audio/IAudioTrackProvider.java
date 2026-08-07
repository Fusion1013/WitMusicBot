package se.fusion1013.wit.audio;

import se.fusion1013.wit.domain.entity.TrackEntity;

/***
 * Provides an audio track upon request.
 */
public interface IAudioTrackProvider {
    TrackEntity getNext();
    void reset();
}
