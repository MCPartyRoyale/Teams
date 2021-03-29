package me.aleksilassila.teams.commands.subcommands;

import me.aleksilassila.teams.Config;
import me.aleksilassila.teams.Team;
import me.aleksilassila.teams.commands.Subcommand;
import me.aleksilassila.teams.utils.Messages;
import me.aleksilassila.teams.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class InfoSubcommand extends Subcommand {
    @Override
    public void onCommand(Player player, String[] args, Team team, Player target) {
        if (args.length == 0) {
            Messages.send(player, "TEAM_NAME_REQUIRED");
            return;
        }

        Team targetTeam = Config.teams.get(args[0]);

        Messages.send(player, "TEAM_MEMBERS");
        for (UUID member : targetTeam.members) {
            String name = Bukkit.getOfflinePlayer(member).getName();

            if (name != null) {
                if (targetTeam.leader == member)
                    Messages.send(player, "TEAM_LEADER", name);
                else Messages.send(player, "TEAM_MEMBER", name);
            }
        }
    }

    @Override
    public boolean hasTargetPlayer() {
        return false;
    }

    @Override
    public List<String> onTabComplete(Player player, String[] args) {
        return null;
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public String getPermission() {
        return Permissions.moderate;
    }
}
