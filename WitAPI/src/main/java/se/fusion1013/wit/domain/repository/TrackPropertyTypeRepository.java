package se.fusion1013.wit.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.fusion1013.wit.domain.entity.TrackPropertyTypeEntity;

public interface TrackPropertyTypeRepository extends JpaRepository<TrackPropertyTypeEntity, Long> {
}
