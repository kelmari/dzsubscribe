package ru.dercec.plugin.dzsubscribe;

import org.bukkit.plugin.java.JavaPlugin;
import ru.dercec.plugin.dzsubscribe.command.DZSubscribeCommand;
import ru.dercec.plugin.dzsubscribe.command.DZSubscribeTabCompleter;
import ru.dercec.plugin.dzsubscribe.data.Data;
import ru.dercec.plugin.dzsubscribe.expansion.CheckDepend;
import ru.dercec.plugin.dzsubscribe.expansion.CustomLogger;
import ru.dercec.plugin.dzsubscribe.expansion.HexColor;
import ru.dercec.plugin.dzsubscribe.runnable.ClearRunnable;

public final class DZSubscribe extends JavaPlugin {
    private static DZSubscribe instance;
    private static Data user;
    private final CustomLogger logger = new CustomLogger();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        user = new Data("users.yml");
        logger.SendWithWaterMark(HexColor.color("&#E646FFDZSubscribe v" + getDescription().getVersion() + " &nВключается!"));
        logger.SendWithWaterMark(HexColor.color("&fAuthor: &#E646FFLegitOnly_"));
        logger.SendWithWaterMark(HexColor.color("&fVersion: &#E646FFFREE"));
        CheckDepend.Check();
        ClearRunnable clear = new ClearRunnable();
        getCommand("dzsubscribe").setExecutor(new DZSubscribeCommand());
        getCommand("dzsubscribe").setTabCompleter(new DZSubscribeTabCompleter());
        clear.runTaskTimerAsynchronously(this, 0L, 60L);
    }

    @Override
    public void onDisable() {
        logger.SendWithWaterMark(HexColor.color("&#E646FFDZSubscribe v" + getDescription().getVersion() + " &nВыключается!"));
        logger.SendWithWaterMark(HexColor.color("&fAuthor: &#E646FFLegitOnly_"));
        logger.SendWithWaterMark(HexColor.color("&fVersion: &#E646FFFREE"));
    }

    public static DZSubscribe getInstance() {
        return instance;
    }
    public static Data getUser() {
        return user;
    }
}
