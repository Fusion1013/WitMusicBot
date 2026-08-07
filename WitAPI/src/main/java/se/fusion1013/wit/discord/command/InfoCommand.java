package se.fusion1013.wit.discord.command;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

import java.util.List;

public class InfoCommand implements ISlashCommand {
    @Override
    public String getName() {
        return "info";
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash(getName(), "Info about various things").addSubcommands(
                new SubcommandData("tracks_with_tags", "Lists tracks with given tags")
                        .addOption(OptionType.STRING, "tags", "Tags", true)
        );
    }

    private void executeTrackWithTags(SlashCommandInteractionEvent event) {
//        OptionMapping tagsOption = event.getOption("tags");
//        if (tagsOption == null) {
//            event.reply("Invalid tags").setEphemeral(true).queue();
//            return;
//        }
//        String[] tags = tagsOption.getAsString().split(",");
//        List<Track> tracks = TrackManager.getInstance().getTracksFromMood(tags);
//
//        List<String> namesList = tracks.stream()
//                .map(t -> "**" + t.getTitle() + "** - " + String.join(", ", t.getTags()))
//                .toList();
//        String[] indexedNames = new String[namesList.size()];
//        for (int i = 0; i < indexedNames.length; i++) {
//            indexedNames[i] = "[" + (i + 1) + "] " + namesList.get(i);
//        }
//
//        String names = String.join("\n", indexedNames);
//
//        EmbedBuilder embed = new EmbedBuilder()
//                .setTitle("Track List [" + tracks.size() + "] - " + String.join(", ", tags))
//                .setDescription(names.isEmpty() ? "No objects found." : names)
//                .setColor(0x00ADEF);
//
//        event.replyEmbeds(embed.build()).queue();
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if (event.getSubcommandName() == null) return;
        switch (event.getSubcommandName()) {
            case "tracks_with_tags" -> executeTrackWithTags(event);
        }
    }
}
