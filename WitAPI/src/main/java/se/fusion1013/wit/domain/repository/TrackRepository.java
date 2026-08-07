package se.fusion1013.wit.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.fusion1013.wit.domain.entity.TrackEntity;

import java.util.List;

public interface TrackRepository extends JpaRepository<TrackEntity, Long> {

    @Query("""
                SELECT t FROM TrackEntity t
                JOIN t.tags tag
                WHERE tag.name IN :tags
                GROUP BY t
                HAVING COUNT(DISTINCT tag.id) = :size
            """)
    List<TrackEntity> findByAllTags(
            @Param("tags") List<String> tags,
            @Param("size") long size
    );

}
