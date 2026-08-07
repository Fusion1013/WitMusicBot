package se.fusion1013.wit.discord.command;

import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.ArrayList;
import java.util.List;

public interface ISlashCommand {
    String getName();
    CommandData getCommandData();
    void execute(SlashCommandInteractionEvent event);
    default List<String> getCommandSuggestions(CommandAutoCompleteInteractionEvent event) { return new ArrayList<>(); }
}
