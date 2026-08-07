package se.fusion1013.wit.discord.command;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.managers.AudioManager;
import se.fusion1013.wit.discord.PlayerManager;

public class JoinCommand implements ISlashCommand {

    @Override
    public String getName() {
        return "join";
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash(getName(), "Joins an audio channel");
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        boolean joined = joinVoiceChannel(event);
        if (joined) {
            PlayerManager.getInstance().setGuild(event.getGuild());
            event.reply("Joined voice channel").setEphemeral(true).queue();
        }
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
}
