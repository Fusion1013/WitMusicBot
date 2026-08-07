package se.fusion1013.wit.discord.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import se.fusion1013.wit.discord.DiscordManager;

public class MoodCommand implements ISlashCommand {
    @Override
    public String getName() {
        return "mood";
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash(getName(), "Plays tracks according to a mood").addOption(OptionType.STRING, "tags", "Tags");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping tagsOption = event.getOption("tags");
        if (tagsOption == null) {
            event.reply("Invalid tags").setEphemeral(true).queue();
            return;
        }

//        String[] tags = tagsOption.getAsString().split(",");
//        SimpleAudioMoodScene scene = new SimpleAudioMoodScene("generated", null, tags);
//        DiscordManager.getInstance().joinAudioChannel(event.getGuild().getId(), event.getMember().getVoiceState().getChannel().getId());
//        CustomAudioManager.getInstance().setAudioTrackProvider(new AudioSceneTrackProvider(scene));
//        event.reply("Created mood with **" + scene.remainingTrackCount() + "** tracks").setEphemeral(true).queue();
    }
}
