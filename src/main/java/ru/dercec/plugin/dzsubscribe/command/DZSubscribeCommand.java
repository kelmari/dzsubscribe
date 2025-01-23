package ru.dercec.plugin.dzsubscribe.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.dercec.plugin.dzsubscribe.DZSubscribe;
import ru.dercec.plugin.dzsubscribe.data.impl.PlayerSubscribe;
import ru.dercec.plugin.dzsubscribe.expansion.Utils;

import java.time.LocalDateTime;

public class DZSubscribeCommand implements CommandExecutor {
    
    private PlayerSubscribe Data;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("give")) {
            if (args.length != 3) {
                for (String help : Utils.getStringList("messages.no-args"))
                    Utils.sendMessage(sender, help);
                return true;
            }
            if (!sender.hasPermission("dzsubscribe.give")) {
                Utils.sendMessage(sender, Utils.getString("messages.no-permission"));
                return true;
            }
            Player player = Bukkit.getPlayer(args[1]);
            if (player == null) {
                Utils.sendMessage(sender, Utils.getString("messages.null-player"));
            } else {
                String newData = args[2];
                int days = 0;
                if (!newData.equalsIgnoreCase("forever")) {
                    try {
                        days = Integer.parseInt(newData);
                        LocalDateTime time = LocalDateTime.now();
                        time = time.plusDays(days);
                        newData = time.toString().substring(0, 10);
                    } catch (NumberFormatException numberFormatException) {
                        newData = null;
                        Utils.sendMessage(sender, Utils.getString("messages.incorrect-date"));
                    }
                    if (newData == null)
                        return true;
                } else {
                    newData = "forever";
                }
                String oldData = Data.getData(player);
                if (!oldData.isEmpty()) {
                    if (newData.equalsIgnoreCase("forever")) {
                        Data.setData(player, "forever");
                    } else if (oldData.equalsIgnoreCase("forever")) {
                        Data.setData(player, String.valueOf(LocalDateTime.parse(newData + "T00:00:00")).substring(0, 10));
                    } else {
                        Data.setData(player, String.valueOf(LocalDateTime.parse(oldData + "T00:00:00").plusDays(days)).substring(0, 10));
                    }
                    DZSubscribe.getUser().save();
                    for (String commands : Utils.getStringList("subscribe.commands-on-give"))
                        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), commands.replace("%player%", player.getName()));
                } else {
                    Data.setData(player, newData);
                    DZSubscribe.getUser().save();
                    for (String commands : Utils.getStringList("subscribe.commands-on-give"))
                        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), commands.replace("%player%", player.getName()));
                }
                if (args[2].equalsIgnoreCase("forever")) {
                    Utils.sendMessage(sender, Utils.getString("messages.give-forever").replace("%player%", player.getName()));
                } else {
                    Utils.sendMessage(sender, Utils.getString("messages.give-duration").replace("%player%", player.getName()).replace("%duration%", args[2]));
                }
            }
            return true;
        }
        if (args.length > 0 && args[0].equalsIgnoreCase("take")) {
            if (args.length != 2) {
                for (String help : Utils.getStringList("messages.no-args"))
                    Utils.sendMessage(sender, help);
                return true;
            }
            if (!sender.hasPermission("dzsubscribe.take")) {
                Utils.sendMessage(sender, Utils.getString("messages.no-permission"));
                return true;
            }
            Player player = Bukkit.getPlayer(args[1]);
            if (player == null) {
                Utils.sendMessage(sender, Utils.getString("messages.null-player"));
            } else {
                String oldData = Data.getData(player);
                if (oldData.isEmpty()) {
                    Utils.sendMessage(sender, Utils.getString("messages.no-exists"));
                } else {
                    for (String commands : Utils.getStringList("subscribe.commands-on-take"))
                        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), commands.replace("%player%", player.getName()));
                    Data.removeData(player);
                    Utils.sendMessage(sender, Utils.getString("messages.take").replace("%player%", player.getName()));
                }
                return true;
            }
        } else {
            if (args.length > 0 && args[0].equalsIgnoreCase("info")) {
                if (args.length != 2) {
                    for (String help : Utils.getStringList("messages.no-args"))
                        Utils.sendMessage(sender, help);
                    return true;
                }
                if (!sender.hasPermission("dzsubscribe.info")) {
                    Utils.sendMessage(sender, Utils.getString("messages.no-permission"));
                    return true;
                }
                Player player = Bukkit.getPlayer(args[1]);
                if (player == null) {
                    Utils.sendMessage(sender, Utils.getString("messages.null-player"));
                } else {
                    String oldData = Data.getData(player);
                    if (oldData.isEmpty()) {
                        Utils.sendMessage(sender, Utils.getString("messages.no-sub"));
                    } else {
                        Utils.sendMessage(sender, Utils.getString("messages.yes-sub"));
                    }
                }
                return true;
            }
            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                if (args.length != 1) {
                    for (String help : Utils.getStringList("messages.no-args"))
                        Utils.sendMessage(sender, help);
                    return true;
                }
                if (!sender.hasPermission("dzsubscribe.reload")) {
                    Utils.sendMessage(sender, Utils.getString("messages.no-permission"));
                    return true;
                }
                DZSubscribe.getInstance().reloadConfig();
                Utils.sendMessage(sender, Utils.getString("messages.reload"));
                return true;
            }
            for (String help : Utils.getStringList("messages.no-args"))
                Utils.sendMessage(sender, help);
        }
        return false;
    }
}
