package se.fusion1013.wit.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.fusion1013.wit.domain.entity.TrackPropertyTypeEntity;
import se.fusion1013.wit.domain.repository.TrackPropertyTypeRepository;

import java.util.List;

@RestController
@RequestMapping("/api/property")
public class TrackPropertyApi {

    private final TrackPropertyTypeRepository trackPropertyTypeRepository;

    public TrackPropertyApi(TrackPropertyTypeRepository trackPropertyTypeRepository) {
        this.trackPropertyTypeRepository = trackPropertyTypeRepository;
    }

    @GetMapping(path = "/types", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TrackPropertyTypeEntity> getAllPropertyTypes() {
        return trackPropertyTypeRepository.findAll();
    }

    @PostMapping(path = "/types")
    public TrackPropertyTypeEntity createTrackPropertyType(@RequestBody TrackPropertyTypeEntity trackPropertyType) {
        return trackPropertyTypeRepository.save(trackPropertyType);
    }
}
