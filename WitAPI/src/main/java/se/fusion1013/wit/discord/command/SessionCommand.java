package se.fusion1013.wit.discord.command;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SessionCommand implements ISlashCommand {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public String getName() {
        return "session";
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash(getName(), "Session command").addSubcommands(
                new SubcommandData("schedule", "Schedules a new session")
                        .addOption(OptionType.STRING, "campaign", "Name of the campaign", true, true)
                        .addOption(OptionType.STRING, "date", "When to start the session", true, false)
        );
    }

    @Override
    public List<String> getCommandSuggestions(CommandAutoCompleteInteractionEvent event) {
        if (event.getSubcommandName() == null) return List.of();

        if (event.getSubcommandName().equalsIgnoreCase("schedule") && event.getFocusedOption().getName().equalsIgnoreCase("campaign")) {
//            return CampaignManager.getInstance().getCampaignNames();
            return null;
        }

        return List.of();
    }

    private void executeSchedule(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        if (member == null || member.getVoiceState() == null || member.getVoiceState().getChannel() == null) {
            event.reply("You need to be in a voice channel first!").setEphemeral(true).queue();
            return;
        }

        OptionMapping campaignNameOption = event.getOption("campaign");
        String campaignName = campaignNameOption.getAsString();

//        ICampaign campaign = CampaignManager.getInstance().getCampaign(campaignName);
//        if (campaign == null) {
//            event.reply("Could not find campaign").setEphemeral(true).queue();
//            return;
//        }
//
//        OptionMapping datetimeOption = event.getOption("date");
//        String datetimeInput = datetimeOption.getAsString();
//        LocalDateTime targetTime = LocalDateTime.parse(datetimeInput, DATE_TIME_FORMATTER);
//        ZonedDateTime zonedTarget = targetTime.atZone(ZoneId.systemDefault());
//
//        Guild guild = event.getGuild();
//        AudioChannel audioChannel = member.getVoiceState().getChannel();
//
//        SessionManager.getInstance().create(campaign.getId(), guild.getId(), audioChannel.getId(), zonedTarget);
//        event.reply("Starting new session at **" + datetimeInput + "**").setEphemeral(false).queue();
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if (event.getSubcommandName() == null) return;
        switch (event.getSubcommandName()) {
            case "schedule" -> executeSchedule(event);
        }
    }
}
