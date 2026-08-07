package se.fusion1013.wit.discord.command;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.List;

public class TrackCommand implements ISlashCommand {
    @Override
    public String getName() {
        return "track";
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash(getName(), "Track command").addSubcommands(
                new SubcommandData("add", "Add new track")
                        .addOption(OptionType.STRING, "title", "Name of the track", true)
                        .addOption(OptionType.STRING, "link", "Youtube link to the track", true)
                        .addOption(OptionType.STRING, "tags", "Tags for the track. Separate by comma (,)", true),
                new SubcommandData("play", "Play a track")
                        .addOption(OptionType.STRING, "track", "Name of the track", true, true),
                new SubcommandData("fadeskip", "Fadeskips")
        );
    }

    @Override
    public List<String> getCommandSuggestions(CommandAutoCompleteInteractionEvent event) {
        if (event.getSubcommandName() == null) return List.of();

        if (event.getSubcommandName().equalsIgnoreCase("play")) {
            return null;
//            return TrackManager.getInstance().getTrackNames();
        }

        return List.of();
    }

    private void executePlay(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        if (member == null || member.getVoiceState() == null || member.getVoiceState().getChannel() == null) {
            event.reply("You need to be in a voice channel first!").setEphemeral(true).queue();
            return;
        }

        AudioChannel channel = member.getVoiceState().getChannel();
        AudioManager audioManager = event.getGuild().getAudioManager();

        audioManager.openAudioConnection(channel);

//        String trackName = event.getOption("track").getAsString();
//        AudioTrackInfo trackInfo = TrackManager.getInstance().getTrack(trackName);
//
//        PlayerManager.getInstance().setGuild(event.getGuild());
//        PlayerManager.getInstance().play(trackInfo.getYoutubeLink());
//        event.reply("Playing track **" + trackName + "**").setEphemeral(true).queue();
    }

    private void executeAdd(SlashCommandInteractionEvent event) {
        OptionMapping titleOption = event.getOption("title");
        if (titleOption == null) {
            event.reply("Title invalid").setEphemeral(true).queue();
            return;
        }
        String title = titleOption.getAsString();

        OptionMapping linkOption = event.getOption("link");
        if (linkOption == null) {
            event.reply("Link invalid").setEphemeral(true).queue();
            return;
        }
        String link = linkOption.getAsString();

        OptionMapping tagsOption = event.getOption("tags");
        if (tagsOption == null) {
            event.reply("Tags invalid").setEphemeral(true).queue();
            return;
        }
        String[] tags = tagsOption.getAsString().split(",");

//        TrackManager.getInstance().createTrack(title, link, tags);

        event.reply("Created new track").setEphemeral(true).queue();
    }

    private void executeFadeSkip(SlashCommandInteractionEvent event) {
//        PlayerManager.getInstance().fadeOutAndPlayNext(5000, 5000);
        event.reply("Fading track").setEphemeral(true).queue();
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if (event.getSubcommandName() == null) return;
        switch (event.getSubcommandName()) {
            case "add" -> executeAdd(event);
            case "play" -> executePlay(event);
            case "fadeskip" -> executeFadeSkip(event);
        }
    }
}
