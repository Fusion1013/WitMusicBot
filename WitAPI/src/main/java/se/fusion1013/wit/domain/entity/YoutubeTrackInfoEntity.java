package se.fusion1013.wit.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "track_youtube")
public class YoutubeTrackInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String link;

    private Long lengthMilliseconds;

    private Integer loadFailures;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getLengthMilliseconds() {
        return lengthMilliseconds;
    }

    public void setLengthMilliseconds(Long lengthMilliseconds) {
        this.lengthMilliseconds = lengthMilliseconds;
    }

    public Integer getLoadFailures() {
        return loadFailures;
    }

    public void setLoadFailures(Integer loadFailures) {
        this.loadFailures = loadFailures;
    }
}
