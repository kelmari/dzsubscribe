package ru.dercec.plugin.dzsubscribe.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DZSubscribeTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1)
            return Arrays.asList(new String[] { "give", "take", "info", "reload" });
        if (args[0].equalsIgnoreCase("give")) {
            if (args.length == 2) {
                Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
                ArrayList<String> onlinePlayerName = new ArrayList<>();
                for (Player onlinePlayer : onlinePlayers) {
                    String name = onlinePlayer.getName();
                    onlinePlayerName.add(name);
                }
                return onlinePlayerName;
            }
            if (args.length == 3)
                return Arrays.asList( "10", "20", "30", "40", "50", "forever" );
        } else if (args[0].equalsIgnoreCase("take")) {
            if (args.length == 2) {
                Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
                ArrayList<String> onlinePlayerName = new ArrayList<>();
                for (Player onlinePlayer : onlinePlayers) {
                    String name = onlinePlayer.getName();
                    onlinePlayerName.add(name);
                }
                return onlinePlayerName;
            }
        } else if (args[0].equalsIgnoreCase("info") &&
                args.length == 2) {
            Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
            ArrayList<String> onlinePlayerName = new ArrayList<>();
            for (Player onlinePlayer : onlinePlayers) {
                String name = onlinePlayer.getName();
                onlinePlayerName.add(name);
            }
            return onlinePlayerName;
        }
        return new ArrayList<>();
    }
}
