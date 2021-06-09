package me.aleksilassila.teams.commands.team.subcommands;

import me.aleksilassila.teams.Config;
import me.aleksilassila.teams.Team;
import me.aleksilassila.teams.commands.Subcommand;
import me.aleksilassila.teams.utils.Messages;
import me.aleksilassila.teams.utils.Permissions;
import org.bukkit.entity.Player;

public class LeaveSubcommand extends Subcommand {
    @Override
    public void onCommand(Player player, String[] args) {
        Team team = Config.getPlayerTeam(player);
        if (team == null) {
            Messages.send(player, "MEMBERSHIP_REQUIRED");
            return;
        }

        team.remove(player);

        if (team.leader.equals(player.getUniqueId()))
            team.updateLeader();

        Messages.send(player, "TEAM_LEFT");
    }

    @Override
    public String getName() {
        return "leave";
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public String getPermission() {
        return Permissions.join;
    }
}
