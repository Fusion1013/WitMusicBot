package se.fusion1013.wit.discord;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import dev.lavalink.youtube.YoutubeAudioSourceManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.managers.AudioManager;
import se.fusion1013.wit.audio.IAudioPlayer;
import se.fusion1013.wit.domain.dto.DiscordVoiceStatusDTO;
import se.fusion1013.wit.domain.entity.TrackEntity;

import java.util.function.Consumer;

public class PlayerManager implements IAudioPlayer {

    private static PlayerManager INSTANCE;
    private final AudioPlayerManager playerManager;
    private final AudioPlayer audioPlayer;
    private final AudioPlayerSendHandler audioPlayerSendHandler;

    private Consumer<TrackEntity> onDone;

    private Guild guild;

    private boolean isTrackPlaying = false;
    private AudioTrack currentlyPlaying;

    private TrackScheduler trackScheduler;

    private PlayerManager() {
        this.playerManager = new DefaultAudioPlayerManager();
        var ytSource = new YoutubeAudioSourceManager(true);
        playerManager.registerSourceManager(ytSource);
        AudioSourceManagers.registerLocalSource(playerManager); // important for local files!
        this.audioPlayer = playerManager.createPlayer();
        trackScheduler = new TrackScheduler(audioPlayer);
        audioPlayer.addListener(trackScheduler);
        audioPlayerSendHandler = new AudioPlayerSendHandler(audioPlayer);
    }

    public static synchronized PlayerManager getInstance() {
        if (INSTANCE == null) INSTANCE = new PlayerManager();
        return INSTANCE;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public void skipTrack() {
        trackScheduler.stop();
    }

    public void play(String filePath) {
        guild.getAudioManager().setSendingHandler(audioPlayerSendHandler);


        playerManager.loadItem(filePath, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                audioPlayer.playTrack(track);
                isTrackPlaying = true;
                currentlyPlaying = track;
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                audioPlayer.playTrack(playlist.getTracks().get(0));
            }

            @Override
            public void noMatches() {
                System.out.println("No track found for " + filePath);
                publishTrackDone();
            }

            @Override
            public void loadFailed(FriendlyException e) {
                System.out.println("Load failed: " + e.getMessage());
                publishTrackDone();
            }
        });
    }

    @Override
    public void playTrack(TrackEntity track) {
        play(track.getYoutube().getLink());
        System.out.println("Playing track " + track.getTitle() + " - " + track.getId());
    }

    @Override
    public void onTrackDone(Consumer<TrackEntity> method) {
        this.onDone = method;
    }

    @Override
    public boolean isTrackPlaying() {
        return isTrackPlaying;
    }

    public void publishTrackDone() {
        isTrackPlaying = false;
        currentlyPlaying = null;
        onDone.accept(null);
    }

    public String getCurrentlyPlayingInfo() {
        if (currentlyPlaying == null) return "Nothing currently playing";

        return currentlyPlaying.getInfo().title + " - " + currentlyPlaying.getInfo().author;
    }

    public String getAudioChannelInfo() {
        if (guild == null) return "Not connected to an audio channel";
        AudioManager audioManager = guild.getAudioManager();
        AudioChannelUnion connectedChannel = audioManager.getConnectedChannel();
        if (connectedChannel == null) return "Not connected to an audio channel";

        return "Connected to " + connectedChannel.getName();
    }

    public String[] getAudioChannelUsers() {
        if (guild == null) return new String[]{"Not connected to an audio channel"};
        AudioManager audioManager = guild.getAudioManager();
        AudioChannelUnion connectedChannel = audioManager.getConnectedChannel();
        if (connectedChannel == null) return new String[]{"Not connected to an audio channel"};
        return connectedChannel.asVoiceChannel().getMembers().stream().map(Member::getEffectiveName).toArray(String[]::new);
    }

    public DiscordVoiceStatusDTO getStatus() {
        String currentlyPlaying = getCurrentlyPlayingInfo();
        String audioChannelInfo = getAudioChannelInfo();
        String[] audioChannelUsers = getAudioChannelUsers();
        DiscordVoiceStatusDTO status = new DiscordVoiceStatusDTO(currentlyPlaying, audioChannelInfo, audioChannelUsers);
        status.setTrackDuration(audioPlayerSendHandler.getDuration());
        status.setTrackPosition(audioPlayerSendHandler.getPosition());
        return status;
    }

}
