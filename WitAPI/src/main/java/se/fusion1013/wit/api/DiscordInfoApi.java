package se.fusion1013.wit.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.fusion1013.wit.discord.DiscordManager;
import se.fusion1013.wit.domain.dto.DiscordStatusDTO;

@RestController
@RequestMapping("/api/discord")
public class DiscordInfoApi {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public DiscordStatusDTO getInfo() {
        return DiscordManager.getStatus();
    }

    @PutMapping("/guild/{guildId}/channel/{channelId}")
    public void joinChannel(@PathVariable String guildId, @PathVariable String channelId) {
        DiscordManager.joinAudioChannel(guildId, channelId);
    }

}
