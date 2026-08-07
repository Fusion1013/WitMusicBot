package se.fusion1013.wit.discord.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.List;
import java.util.UUID;

public class TestCommand implements ISlashCommand {
    @Override
    public String getName() {
        return "test";
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash(getName(), "Tests some things").addOption(OptionType.STRING, "tag", "tag");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
//        Member member = event.getMember();
//        if (member == null || member.getVoiceState() == null || member.getVoiceState().getChannel() == null) {
//            event.reply("You need to be in a voice channel first!").setEphemeral(true).queue();
//            return;
//        }
//
//        AudioChannel channel = member.getVoiceState().getChannel();
//        AudioManager audioManager = event.getGuild().getAudioManager();
//
//        audioManager.openAudioConnection(channel);
//        // event.reply("Playing your local file 🎶").queue();
//
//        OptionMapping tagOption = event.getOption("tag");
//        String tag = tagOption.getAsString();
//        List<AudioTrackInfo> tracks = TrackManager.getInstance().getTracksFromMood(tag);
//        for (AudioTrackInfo info : tracks) {
//            event.getChannel().sendMessage(info.toString()).queue();
//        }
//
//
//        EmbedBuilder embed = new EmbedBuilder();
//
//        embed.setTitle("Now playing");
//        embed.setDescription("DESCRIPTION GOES HERE");
//
//        event.replyEmbeds(embed.build()).queue();
//
//
//        // Path to your MP3 file
//        String path = "./sources/2025WorldChampionshipTheme.mp3"; // or "./song.mp3" if in project directory
//        // PlayerManager.getInstance().play(event.getGuild(), path);
//
//        // PlayerManager.getInstance().play(event.getGuild(), "https://www.youtube.com/watch?v=nXU_D5JRxGQ");
//
//        PlayerManager.getInstance().setGuild(event.getGuild());
//        CustomAudioManager.getInstance().setAudioPlayer(PlayerManager.getInstance());
//        IAudioScene audioScene = new SimpleAudioMoodScene("test", UUID.fromString("6a3cc66e-4c5c-40e9-bd0e-c0c04c468467"), "travel");
//        CustomAudioManager.getInstance().setAudioTrackProvider(new AudioSceneTrackProvider(audioScene));
    }
}
