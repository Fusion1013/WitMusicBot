package se.fusion1013.wit.api;

import org.springframework.web.bind.annotation.*;
import se.fusion1013.wit.domain.entity.TrackEntity;
import se.fusion1013.wit.domain.repository.TagRepository;
import se.fusion1013.wit.domain.repository.TrackRepository;

import java.util.List;

@RestController
@RequestMapping("/api/track")
public class TrackApi {

    private final TrackRepository trackRepository;
    private final TagRepository tagRepository;

    public TrackApi(TrackRepository trackRepository, TagRepository tagRepository) {
        this.trackRepository = trackRepository;
        this.tagRepository = tagRepository;
    }

    @GetMapping(produces = "application/json")
    public List<TrackEntity> getAllTracks(
            @RequestParam(required = false) List<String> tags
    ) {
        if (tags == null || tags.isEmpty()) {
            return trackRepository.findAll();
        }
        return trackRepository.findByAllTags(tags, tags.size());
    }

    @PostMapping()
    public TrackEntity createTrack(@RequestBody TrackEntity track) {
        return trackRepository.save(track);
    }

    @PutMapping("/update/{id}")
    public TrackEntity updateTrack(@PathVariable Long id, @RequestBody TrackEntity updatedTrack) {
        return trackRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedTrack.getTitle());
                    existing.setArtist(updatedTrack.getArtist());
                    existing.setTags(updatedTrack.getTags());

                    // Handle nested YouTube object
                    if (updatedTrack.getYoutube() != null) {
                        if (existing.getYoutube() == null) {
                            existing.setYoutube(updatedTrack.getYoutube());
                        } else {
                            existing.getYoutube().setLink(updatedTrack.getYoutube().getLink());
                            existing.getYoutube().setLengthMilliseconds(
                                    updatedTrack.getYoutube().getLengthMilliseconds()
                            );
                        }
                    }

                    return trackRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Track not found with id " + id));
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public TrackEntity getTrack(@PathVariable Long id) {
        return trackRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTrack(@PathVariable Long id) {
        trackRepository.deleteById(id);
    }

}
