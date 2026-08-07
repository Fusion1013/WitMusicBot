package se.fusion1013.wit.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.fusion1013.wit.domain.entity.TagEntity;
import se.fusion1013.wit.domain.repository.TagRepository;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagApi {

    private final TagRepository tagRepository;

    public TagApi(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TagEntity> getAllTags() {
        return tagRepository.findAll();
    }

    @PostMapping()
    public TagEntity createTag(@RequestBody TagEntity tag) {
        return tagRepository.save(tag);
    }

    @PutMapping("/update/{id}")
    public TagEntity updateTag(@PathVariable Long id, @RequestBody TagEntity tag) {
        return tagRepository.findById(id)
                .map(existing -> {
                    existing.update(tag);
                    return tagRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Tag not found with id " + id));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TagEntity getTag(@PathVariable Long id) {
        return tagRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTag(@PathVariable Long id) {
        tagRepository.deleteById(id);
    }


}
