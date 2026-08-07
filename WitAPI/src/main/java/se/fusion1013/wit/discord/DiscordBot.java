package se.fusion1013.wit.discord;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import se.fusion1013.wit.audio.CustomAudioManager;

@Component
public class DiscordBot {

    private final DiscordProperties properties;

    public DiscordBot(DiscordProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void start() throws Exception {
        DiscordManager.initDiscord(properties.getToken());
        CustomAudioManager.getInstance().setAudioPlayer(PlayerManager.getInstance());
    }

}
