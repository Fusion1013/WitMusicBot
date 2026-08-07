package se.fusion1013.wit.domain.dto;

public class DiscordVoiceChannelDto {

    private String guildId;
    private String guildName;
    private String channelId;
    private String name;
    private int userCount;

    public DiscordVoiceChannelDto(String guildId, String guildName, String channelId, String name, int userCount) {
        this.guildId = guildId;
        this.guildName = guildName;
        this.channelId = channelId;
        this.name = name;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
