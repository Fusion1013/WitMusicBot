package se.fusion1013.wit.domain.dto;

import java.util.List;

public class DiscordStatusDTO {

    private DiscordVoiceStatusDTO voiceStatus;

    private List<DiscordVoiceChannelDto> voiceChannels;

    public DiscordStatusDTO() {
    }

    public DiscordVoiceStatusDTO getVoiceStatus() {
        return voiceStatus;
    }

    public void setVoiceStatus(DiscordVoiceStatusDTO discordVoiceStatus) {
        this.voiceStatus = discordVoiceStatus;
    }

    public List<DiscordVoiceChannelDto> getVoiceChannels() {
        return voiceChannels;
    }

    public void setVoiceChannels(List<DiscordVoiceChannelDto> voiceChannels) {
        this.voiceChannels = voiceChannels;
    }
}
