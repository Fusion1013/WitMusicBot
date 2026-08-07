package se.fusion1013.wit.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.fusion1013.wit.domain.entity.ScheduledCampaignEntity;
import se.fusion1013.wit.domain.repository.ScheduledCampaignRepository;

import java.util.List;

@RestController
@RequestMapping("/api/campaign/scheduled")
public class ScheduledCampaignApi {

    private final ScheduledCampaignRepository scheduledCampaignRepository;

    public ScheduledCampaignApi(ScheduledCampaignRepository scheduledCampaignRepository) {
        this.scheduledCampaignRepository = scheduledCampaignRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ScheduledCampaignEntity> getAllScheduledCampaigns() {
        return scheduledCampaignRepository.findAll();
    }

    @PostMapping
    public ScheduledCampaignEntity scheduleCampaign(@RequestBody ScheduledCampaignEntity scheduled) {
        return scheduledCampaignRepository.save(scheduled);
    }

    @PutMapping("/{id}")
    public ScheduledCampaignEntity updateScheduledCampaign(@PathVariable Long id, @RequestBody ScheduledCampaignEntity updateScheduledCampaign) {
        return scheduledCampaignRepository.findById(id)
                .map(existing -> {
                    existing.update(updateScheduledCampaign);
                    return scheduledCampaignRepository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Scheduled campaign could not be found with id " + id));
    }

    @PostMapping("/delete/{id}")
    public void deleteScheduledCampaign(@PathVariable Long id) {
        scheduledCampaignRepository.deleteById(id);
    }
}
