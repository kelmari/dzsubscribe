package ru.dercec.plugin.dzsubscribe.runnable;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import ru.dercec.plugin.dzsubscribe.data.impl.PlayerSubscribe;

import java.time.LocalDateTime;

public class ClearRunnable extends BukkitRunnable {

    private PlayerSubscribe Data;

    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            String oldData = Data.getData(player);
            if (!oldData.isEmpty() && !oldData.equalsIgnoreCase("forever")) {
                oldData = oldData + "T00:00:00";
                LocalDateTime time = LocalDateTime.parse(oldData);
                if (!time.isAfter(LocalDateTime.now())) {
                    Data.removeData(player);
                }
            }
        });
    }
}
