package se.fusion1013.wit.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "scene")
public class SceneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "track_id")
    private TrackEntity introTrack;

    @ManyToMany
    @JoinTable(
            name = "scene_tag_map",
            joinColumns = @JoinColumn(name = "scene_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<TagEntity> tags;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TrackEntity getIntroTrack() {
        return introTrack;
    }

    public void setIntroTrack(TrackEntity introTrack) {
        this.introTrack = introTrack;
    }

    public List<TagEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
    }
}
