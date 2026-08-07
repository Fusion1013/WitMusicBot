package se.fusion1013.wit.discord.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlashCommandManager {

    private final Map<String, ISlashCommand> COMMANDS = new HashMap<>();

    public void register(ISlashCommand command) {
        COMMANDS.put(command.getName(), command);
    }

    public void registerAllToJDA(JDA jda) {
        jda.updateCommands()
                .addCommands(COMMANDS.values().stream()
                        .map(ISlashCommand::getCommandData)
                        .toList())
                .queue();
    }

    public void handle(SlashCommandInteractionEvent event) {
        ISlashCommand command = COMMANDS.get(event.getName());
        if (command != null) {
            command.execute(event);
        } else {
            event.reply("Unknown command!").setEphemeral(true).queue();
        }
    }

    public void handleAutoCompletion(@NotNull CommandAutoCompleteInteractionEvent event) {
        ISlashCommand command = COMMANDS.get(event.getName());
        if (command != null) {
            List<String> suggestions = command.getCommandSuggestions(event);
            event.replyChoices(suggestions.stream()
                    .map(s -> new Command.Choice(s, s))
                    .toList()
            ).queue();
        }
    }

    public SlashCommandManager() {
    }
    private static SlashCommandManager INSTANCE;

    public static SlashCommandManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SlashCommandManager();
        }
        return INSTANCE;
    }
}
