package se.fusion1013.wit.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tracks")
public class TrackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String artist;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "youtube_id")
    private YoutubeTrackInfoEntity youtube;

    @ManyToMany
    @JoinTable(
            name = "track_tag_map",
            joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<TagEntity> tags;

    @ManyToMany
    private List<TrackPropertyValueEntity> propertyValues;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public YoutubeTrackInfoEntity getYoutube() {
        return youtube;
    }

    public void setYoutube(YoutubeTrackInfoEntity youtube) {
        this.youtube = youtube;
    }

    public List<TagEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
    }

    public List<TrackPropertyValueEntity> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(List<TrackPropertyValueEntity> propertyValues) {
        this.propertyValues = propertyValues;
    }
}
