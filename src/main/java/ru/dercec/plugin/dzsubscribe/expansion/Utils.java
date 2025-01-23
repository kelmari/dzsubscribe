package ru.dercec.plugin.dzsubscribe.expansion;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import ru.dercec.plugin.dzsubscribe.DZSubscribe;

import java.util.List;

public class Utils {
    public static void sendMessage(CommandSender player, String message) {
        player.sendMessage(HexColor.color(message));
    }
    @NotNull
    public static String getString(String path) {
        return HexColor.color(DZSubscribe.getInstance().getConfig().getString(path));
    }
    @NotNull
    public static List<String> getStringList(String path) {
        return DZSubscribe.getInstance().getConfig().getStringList(path);
    }
}
