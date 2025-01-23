package ru.dercec.plugin.dzsubscribe.expansion;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import ru.dercec.plugin.dzsubscribe.DZSubscribe;
import ru.dercec.plugin.dzsubscribe.hook.PlaceholderAPIHook;

import java.util.List;

public class CheckDepend {

    private static CustomLogger logger = new CustomLogger();

    public static void Check(){
        List<String> depend = DZSubscribe.getInstance().getDescription().getDepend();
        logger.SendWithWaterMark("&fПроверка дополнений к плагину..");
        for(Plugin plugins : Bukkit.getPluginManager().getPlugins()) {
            for (String pl : depend) {
                if (plugins.getName().equalsIgnoreCase(pl)){
                    if(plugins.isEnabled()){
                        logger.SendWithWaterMark("&f" + pl + " &#E646FF✓");
                        switch (pl){
                            case "PlaceholderAPI":
                                PlaceholderAPIHook.registerHook();
                                break;
                        }
                    }  else{
                        logger.SendWithWaterMark("&f" + pl + " &#E646FF✘");
                    }
                }
            }
        }
    }
}
