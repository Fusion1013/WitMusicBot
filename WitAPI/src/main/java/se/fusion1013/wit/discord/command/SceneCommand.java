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
import se.fusion1013.wit.discord.PlayerManager;

import java.util.List;

public class SceneCommand implements ISlashCommand {
    @Override
    public String getName() {
        return "scene";
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash(getName(), "Scene command").addSubcommands(
                new SubcommandData("schedule", "Schedules an audio scene to play")
                        .addOption(OptionType.STRING, "scene", "Name of the scene", true, true)
        );
    }

    @Override
    public List<String> getCommandSuggestions(CommandAutoCompleteInteractionEvent event) {
//        if (event.getSubcommandName() == null) return List.of();
//        System.out.println("Subcommand: " + event.getSubcommandName());
//        System.out.println("Scene names:");
//        AudioSceneManager.getSceneNames().forEach(s -> System.out.println(" - " + s));
//        switch (event.getSubcommandName()) {
//            case "schedule" -> {
//                return AudioSceneManager.getSceneNames();
//            }
//        }
        return List.of();
    }

    private void executeSchedule(SlashCommandInteractionEvent event) {
        if (!joinVoiceChannel(event)) return;

        PlayerManager.getInstance().setGuild(event.getGuild());

//        OptionMapping sceneOption = event.getOption("scene");
//        if (sceneOption == null) {
//            event.reply("No scene option").setEphemeral(true).queue();
//            return;
//        }
//
//        String sceneName = sceneOption.getAsString();
//        IAudioTrackProvider trackProvider = AudioSceneManager.getTrackProvider(sceneName);
//        if (trackProvider == null) {
//            event.reply("Scene not found").setEphemeral(true).queue();
//            return;
//        }
//
//        PlayerManager.getInstance().setGuild(event.getGuild());
//        CustomAudioManager.getInstance().setAudioTrackProvider(trackProvider);
//        event.reply("Scheduled scene " + sceneName).setEphemeral(true).queue();
    }

    private boolean joinVoiceChannel(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        if (member == null || member.getVoiceState() == null || member.getVoiceState().getChannel() == null || event.getGuild() == null) {
            event.reply("You need to be in a voice channel first!").setEphemeral(true).queue();
            return false;
        }
        AudioChannel channel = member.getVoiceState().getChannel();
        AudioManager audioManager = event.getGuild().getAudioManager();
        audioManager.openAudioConnection(channel);
        return true;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        System.out.println("Schedule command");
        if (event.getSubcommandName() == null) return;
        switch (event.getSubcommandName()) {
            case "schedule" -> executeSchedule(event);
        }
    }
}
