package se.fusion1013.wit.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.fusion1013.wit.audio.CustomAudioManager;
import se.fusion1013.wit.audio.SceneTrackProvider;
import se.fusion1013.wit.discord.DiscordManager;
import se.fusion1013.wit.discord.PlayerManager;
import se.fusion1013.wit.domain.entity.SceneEntity;
import se.fusion1013.wit.domain.entity.TagEntity;
import se.fusion1013.wit.domain.entity.TagSelectionMethod;
import se.fusion1013.wit.domain.entity.TrackEntity;
import se.fusion1013.wit.domain.repository.SceneRepository;
import se.fusion1013.wit.domain.repository.TrackRepository;

import java.util.List;

@RestController
@RequestMapping("/api/audio")
public class AudioApi {

    private final TrackRepository trackRepository;
    private final SceneRepository sceneRepository;

    public AudioApi(TrackRepository trackRepository, SceneRepository sceneRepository) {
        this.trackRepository = trackRepository;
        this.sceneRepository = sceneRepository;
    }

    @PostMapping("/track/{id}")
    public void playTrack(@PathVariable Long id) {
        TrackEntity track = trackRepository.findById(id).orElse(null);
        if (track == null) return;

        CustomAudioManager.getInstance().setAudioTrackProvider(null);
        DiscordManager.playTrack(track);
    }

    @PostMapping("/scene/{id}")
    public void playScene(@PathVariable Long id) {
        SceneEntity scene = sceneRepository.findById(id).orElse(null);
        System.out.println("Scene: " + scene);
        if (scene == null) return;

        List<String> sceneTagNames = scene.getTags().stream().map(TagEntity::getName).toList();

        List<TrackEntity> byAllTags = trackRepository
                .findByAllTags(sceneTagNames, sceneTagNames.size())
                .stream()
                .filter(track -> canSelect(track.getTags(), sceneTagNames))
                .toList();

        SceneTrackProvider sceneTrackProvider = new SceneTrackProvider(scene.getIntroTrack(), byAllTags);

        CustomAudioManager.getInstance().setAudioTrackProvider(sceneTrackProvider);
    }

    private boolean canSelect(List<TagEntity> trackTags, List<String> selectedTags) {

        List<TagEntity> inTrackNotSpecified = trackTags.stream().filter(trackTag -> selectedTags.contains(trackTag.getName())).toList();

        if (inTrackNotSpecified.stream().anyMatch(tag -> tag.getSelectionMethod() == TagSelectionMethod.OnlyIfSelfSpecified)) {
            return false;
        }

        return true;
    }

    @PostMapping("skip")
    public void skipTrack() {
        PlayerManager.getInstance().skipTrack();
    }

}
