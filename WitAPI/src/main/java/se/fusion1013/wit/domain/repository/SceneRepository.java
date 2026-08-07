package se.fusion1013.wit.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.fusion1013.wit.domain.entity.SceneEntity;

public interface SceneRepository extends JpaRepository<SceneEntity, Long> {
}
