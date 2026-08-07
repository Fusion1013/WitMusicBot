package se.fusion1013.wit.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import se.fusion1013.wit.discord.DiscordProperties;

@Configuration
@EnableConfigurationProperties(DiscordProperties.class)
public class Config {
}
