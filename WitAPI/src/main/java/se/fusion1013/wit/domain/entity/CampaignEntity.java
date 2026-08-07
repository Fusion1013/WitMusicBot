package se.fusion1013.wit.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "campaign")
public class CampaignEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "intro_track_id")
    private TrackEntity introTrack;

    public void update(CampaignEntity updateCampaign) {
        setName(updateCampaign.getName());
        setIntroTrack(updateCampaign.getIntroTrack());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TrackEntity getIntroTrack() {
        return introTrack;
    }

    public void setIntroTrack(TrackEntity introTrack) {
        this.introTrack = introTrack;
    }
}
