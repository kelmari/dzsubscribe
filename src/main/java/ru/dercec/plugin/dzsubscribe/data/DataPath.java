package ru.dercec.plugin.dzsubscribe.data;

import ru.dercec.plugin.dzsubscribe.DZSubscribe;
import ru.dercec.plugin.dzsubscribe.expansion.HexColor;

public class DataPath {
    public static String settings_placeholder_prefix_has = HexColor.color(DZSubscribe.getInstance().getConfig().getString("settings.placeholder.has.has"));
    public static String settings_placeholder_prefix_has_not = HexColor.color(DZSubscribe.getInstance().getConfig().getString("settings.placeholder.has.has-not"));
}
