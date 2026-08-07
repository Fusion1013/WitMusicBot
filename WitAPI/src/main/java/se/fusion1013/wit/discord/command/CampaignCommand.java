package se.fusion1013.wit.discord.command;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.managers.AudioManager;
import se.fusion1013.wit.discord.PlayerManager;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CampaignCommand implements ISlashCommand {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Override
    public String getName() {
        return "campaign";
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash(getName(), "Session command").addSubcommands(
                new SubcommandData("list", "Lists all campaigns"),
                new SubcommandData("create", "Creates a new campaign")
                        .addOption(OptionType.STRING, "name", "Name of the campaign"),
                new SubcommandData("option", "Edits a campaign option"),
                new SubcommandData("schedule", "Schedules a new session for the campaign")
                        .addOptions(new OptionData(OptionType.STRING, "name", "The name of the campaign", true, true))
                        .addOptions(new OptionData(OptionType.STRING, "date", "The date the session will take place", true, false))
        );
    }

    @Override
    public List<String> getCommandSuggestions(CommandAutoCompleteInteractionEvent event) {
        if (event.getSubcommandName() == null) return new ArrayList<>();
        switch (event.getSubcommandName()) {
            case "schedule" -> {
                return getCommandSuggestionsSchedule(event);
            }
        }
        return new ArrayList<>();
    }

    private List<String> getCommandSuggestionsSchedule(CommandAutoCompleteInteractionEvent event) {
//        return CampaignManager.getInstance().getCampaignNames();
        return null;
    }

    private void executeCreate(SlashCommandInteractionEvent event) {
        OptionMapping nameOption = event.getOption("name");
        String campaignName = nameOption.getAsString();
//        CampaignManager.getInstance().create(campaignName);
        event.reply("✅ Created new campaign **" + campaignName + "**").queue();
    }

    private void executeSchedule(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        if (member == null || member.getVoiceState() == null || member.getVoiceState().getChannel() == null) {
            event.reply("You need to be in a voice channel first!").setEphemeral(true).queue();
            return;
        }

        AudioChannel channel = member.getVoiceState().getChannel();
        AudioManager audioManager = event.getGuild().getAudioManager();

        audioManager.openAudioConnection(channel);

        OptionMapping datetimeOption = event.getOption("date");
        String datetimeInput = datetimeOption.getAsString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            LocalDateTime targetTime = LocalDateTime.parse(datetimeInput, formatter);
            ZonedDateTime zonedTarget = targetTime.atZone(ZoneId.systemDefault());

            long delayMillis = Duration.between(Instant.now(), zonedTarget.toInstant()).toMillis();

            event.reply("✅ Starting new session at **" + targetTime + "**").queue();


            scheduler.schedule(() -> {
                event.getChannel()
                        .sendMessage("⏰ Session started!")
                        .queue();
                String path = "./data/tracks/2025WorldChampionshipTheme.mp3";
                PlayerManager.getInstance().setGuild(event.getGuild());
                PlayerManager.getInstance().play(path);
            }, delayMillis, TimeUnit.MILLISECONDS);
        } catch (DateTimeParseException e) {
            event.reply("❌ Invalid datetime format! Please use `YYYY-MM-DD HH:MM`, e.g. `2025-10-09 15:30`.").setEphemeral(true).queue();
        }

        event.getGuild().createScheduledEvent("Session test", channel, OffsetDateTime.now().plusMinutes(10))
                .setDescription("Session description")
                .queue(scheduledEvent -> {
                    System.out.println("Event created: " + event.getName() + " (" + event.getId() + ")");
                });
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if (event.getSubcommandName() == null) return;
        switch (event.getSubcommandName()) {
            case "schedule" -> executeSchedule(event);
            case "create" -> executeCreate(event);
        }
    }
}
