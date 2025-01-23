package ru.dercec.plugin.dzsubscribe.data.impl;

import org.bukkit.entity.Player;
import ru.dercec.plugin.dzsubscribe.DZSubscribe;

public class PlayerSubscribe {

    public static boolean SubscribeIsValid(Player player){
        if(DZSubscribe.getUser().getConfig().contains("data." + player.getName().toLowerCase() + ".date")){
            return true;
        }
        return false;
    }
    public static void setData(Player player, String date) {
        DZSubscribe.getUser().getConfig().set("data." + player.getName().toLowerCase() + ".date", date);
        DZSubscribe.getUser().save();
    }

    public static void removeData(Player player) {
        DZSubscribe.getUser().getConfig().set("data." + player.getName().toLowerCase(), null);
        DZSubscribe.getUser().save();
    }

    public static String getData(Player player) {
        Object object = DZSubscribe.getUser().getConfig().get("data." + player.getName().toLowerCase() + ".date");
        String result = "";
        if (object != null)
            result = object.toString();
        return result;
    }
}
