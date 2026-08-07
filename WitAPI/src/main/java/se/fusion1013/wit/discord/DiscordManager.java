package se.fusion1013.wit.discord;

import club.minnced.discord.jdave.interop.JDaveSessionFactory;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.audio.AudioModuleConfig;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import se.fusion1013.wit.discord.command.JoinCommand;
import se.fusion1013.wit.discord.command.SlashCommandListener;
import se.fusion1013.wit.discord.command.SlashCommandManager;
import se.fusion1013.wit.domain.dto.DiscordStatusDTO;
import se.fusion1013.wit.domain.dto.DiscordVoiceChannelDto;
import se.fusion1013.wit.domain.entity.TrackEntity;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

public class DiscordManager {

    private static JDA JDA;

    public static void initDiscord(String token) throws InterruptedException {
        JDA = JDABuilder.createDefault(token, EnumSet.of(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_MEMBERS))
                .addEventListeners(new SlashCommandListener(SlashCommandManager.getInstance()))
                .setAudioModuleConfig(new AudioModuleConfig().withDaveSessionFactory(new JDaveSessionFactory()))
                .build()
                .awaitReady();

        CommandListUpdateAction commands = JDA.updateCommands();
        SlashCommandManager commandManager = SlashCommandManager.getInstance();
        commandManager.register(new JoinCommand());
        commandManager.registerAllToJDA(JDA);
    }

    public static boolean joinAudioChannel(String guildId, String channelId) {
        Guild guild = JDA.getGuildById(guildId);
        if (Objects.isNull(guild)) return false;

        AudioChannel audioChannel = guild.getChannelById(AudioChannel.class, channelId);
        if (Objects.isNull(audioChannel)) return false;

        AudioManager audioManager = guild.getAudioManager();
        audioManager.openAudioConnection(audioChannel);
        PlayerManager.getInstance().setGuild(guild);
        return true;
    }

    public static void playTrack(TrackEntity track) {
        System.out.println("Play track: " + track.getTitle());
        PlayerManager.getInstance().playTrack(track);
    }

    public static DiscordStatusDTO getStatus() {
        DiscordStatusDTO status = new DiscordStatusDTO();

        status.setVoiceStatus(PlayerManager.getInstance().getStatus());
        status.setVoiceChannels(getAllConnectableVoiceChannels(JDA));

        return status;
    }

    public static List<DiscordVoiceChannelDto> getAllConnectableVoiceChannels(JDA jda) {
        List<DiscordVoiceChannelDto> result = new ArrayList<>();

        jda.getGuilds().forEach(guild -> {
            guild.getVoiceChannels().forEach(channel -> {

                if (guild.getSelfMember().hasPermission(channel, Permission.VOICE_CONNECT)) {
                    result.add(new DiscordVoiceChannelDto(guild.getId(),
                            guild.getName(),
                            channel.getId(),
                            channel.getName(),
                            channel.getMembers().size())
                    );
                }

            });
        });

        return result;
    }

}


