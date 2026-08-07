package se.fusion1013.wit.domain.dto;

public class DiscordVoiceStatusDTO {

    private String currentlyPlaying;

    private String joinedAudioChannel;

    private String[] joinedUsers;

    private Long trackDuration;

    private Long trackPosition;

    public DiscordVoiceStatusDTO(String currentlyPlaying, String joinedAudioChannel, String[] joinedUsers) {
        this.currentlyPlaying = currentlyPlaying;
        this.joinedAudioChannel = joinedAudioChannel;
        this.joinedUsers = joinedUsers;
    }

    public Long getTrackDuration() {
        return trackDuration;
    }

    public void setTrackDuration(Long trackDuration) {
        this.trackDuration = trackDuration;
    }

    public Long getTrackPosition() {
        return trackPosition;
    }

    public void setTrackPosition(Long trackPosition) {
        this.trackPosition = trackPosition;
    }

    public String getCurrentlyPlaying() {
        return currentlyPlaying;
    }

    public void setCurrentlyPlaying(String currentlyPlaying) {
        this.currentlyPlaying = currentlyPlaying;
    }

    public String getJoinedAudioChannel() {
        return joinedAudioChannel;
    }

    public void setJoinedAudioChannel(String joinedAudioChannel) {
        this.joinedAudioChannel = joinedAudioChannel;
    }

    public String[] getJoinedUsers() {
        return joinedUsers;
    }

    public void setJoinedUsers(String[] joinedUsers) {
        this.joinedUsers = joinedUsers;
    }

}
