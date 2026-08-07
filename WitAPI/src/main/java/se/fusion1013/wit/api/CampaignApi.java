package se.fusion1013.wit.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.fusion1013.wit.domain.entity.CampaignEntity;
import se.fusion1013.wit.domain.repository.CampaignRepository;

import java.util.List;

@RestController
@RequestMapping("/api/campaign")
public class CampaignApi {

    private final CampaignRepository campaignRepository;

    public CampaignApi(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CampaignEntity> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    @PostMapping
    public CampaignEntity createCampaign(@RequestBody CampaignEntity campaign) {
        return campaignRepository.save(campaign);
    }

    @PutMapping("/{id}")
    public CampaignEntity updateCampaign(@PathVariable Long id, @RequestBody CampaignEntity updateCampaign) {
        return campaignRepository.findById(id)
                .map(existing -> {
                    existing.update(updateCampaign);
                    return campaignRepository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Campaign could not be found with id " + id));
    }

    @PostMapping("/delete/{id}")
    public void deleteCampaign(@PathVariable Long id) {
        campaignRepository.deleteById(id);
    }
}
