package ru.dercec.plugin.dzsubscribe.expansion;

import org.bukkit.Bukkit;
import ru.dercec.plugin.dzsubscribe.DZSubscribe;

public class CustomLogger {

    public void SendWithWaterMark(String message){
        Bukkit.getConsoleSender().sendMessage("[" + DZSubscribe.getInstance().getDescription().getName() + "] " + HexColor.color(message));
    }
    public void SendWithoutWaterMark(String message){
        Bukkit.getConsoleSender().sendMessage(HexColor.color(message));
    }
}
