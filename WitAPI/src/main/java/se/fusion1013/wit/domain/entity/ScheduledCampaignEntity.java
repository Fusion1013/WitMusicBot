package se.fusion1013.wit.domain.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "scheduled_campaign")
public class ScheduledCampaignEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private CampaignEntity campaign;

    private Date date;

    public void update(ScheduledCampaignEntity updateScheduledCampaign) {
        setCampaign(updateScheduledCampaign.getCampaign());
        setDate(updateScheduledCampaign.getDate());
    }

    public Long getId() {
        return id;
    }

    public CampaignEntity getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignEntity campaign) {
        this.campaign = campaign;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
