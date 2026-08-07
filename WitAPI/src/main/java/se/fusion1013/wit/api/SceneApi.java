package se.fusion1013.wit.api;

import org.springframework.web.bind.annotation.*;
import se.fusion1013.wit.domain.entity.SceneEntity;
import se.fusion1013.wit.domain.repository.SceneRepository;
import se.fusion1013.wit.domain.repository.TrackRepository;

import java.util.List;

@RestController
@RequestMapping("/api/scene")
public class SceneApi {

    private final SceneRepository sceneRepository;

    public SceneApi(SceneRepository sceneRepository, TrackRepository trackRepository) {
        this.sceneRepository = sceneRepository;
    }

    @GetMapping(produces = "application/json")
    public List<SceneEntity> getAllScenes() {
        return sceneRepository.findAll();
    }

    @PostMapping()
    public SceneEntity createScene(@RequestBody SceneEntity scene) {
        return sceneRepository.save(scene);
    }

    @PutMapping("/{id}")
    public SceneEntity updateScene(@PathVariable Long id, @RequestBody SceneEntity updateScene) {
        return sceneRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updateScene.getTitle());
                    existing.setIntroTrack(updateScene.getIntroTrack());
                    existing.setTags(updateScene.getTags());
                    return sceneRepository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Scene not found with id " + id));
    }

    @PostMapping("/delete/{id}")
    public void deleteScene(@PathVariable Long id) {
        sceneRepository.deleteById(id);
    }

}
