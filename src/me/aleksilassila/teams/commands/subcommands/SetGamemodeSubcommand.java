package me.aleksilassila.teams.commands.subcommands;

import me.aleksilassila.teams.Config;
import me.aleksilassila.teams.PlayerScoreboard;
import me.aleksilassila.teams.Team;
import me.aleksilassila.teams.Teams;
import me.aleksilassila.teams.commands.Subcommand;
import me.aleksilassila.teams.utils.Messages;
import me.aleksilassila.teams.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class SetGamemodeSubcommand extends Subcommand {
    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length == 0) {
            Messages.send(player, "GAMEMODE_ARGUMENT_REQUIRED");
            return;
        }

        if (Messages.get("SIDEBAR_CURRENT_GAMEMODE_VALUE", String.join(" ", args)).length() > 40) {
            Messages.send(player, "CHARACTER_LIMIT", 40);
            return;
        }

        Teams.gamemode = String.join(" ", args);

        for (Player p : Bukkit.getOnlinePlayers())
            PlayerScoreboard.update(p);
    }

    @Override
    public List<String> onTabComplete(Player player, String[] args) {
        return null;
    }

    @Override
    public String getName() {
        return "gamemode";
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
