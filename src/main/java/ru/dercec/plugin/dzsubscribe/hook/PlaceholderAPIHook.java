package ru.dercec.plugin.dzsubscribe.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dercec.plugin.dzsubscribe.DZSubscribe;
import ru.dercec.plugin.dzsubscribe.data.impl.PlayerSubscribe;
import ru.dercec.plugin.dzsubscribe.expansion.HexColor;
import ru.dercec.plugin.dzsubscribe.expansion.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class PlaceholderAPIHook extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "dzsubscribe";
    }

    @Override
    public @NotNull String getAuthor() {
        return "LegitOnly_";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.1";
    }
    @Override
    public @Nullable String onRequest(OfflinePlayer offlinePlayer, @NotNull String params){
        if (offlinePlayer != null && offlinePlayer.isOnline()) {
            if(params.equalsIgnoreCase("has")){
                if (PlayerSubscribe.SubscribeIsValid(offlinePlayer.getPlayer()))
                    return HexColor.color(DZSubscribe.getInstance().getConfig().getString("settings.placeholder.has.has-sub"));
                else return HexColor.color(DZSubscribe.getInstance().getConfig().getString("settings.placeholder.has.has-not-sub"));
            } else if (params.equalsIgnoreCase("time")) {
                if (PlayerSubscribe.SubscribeIsValid(offlinePlayer.getPlayer())) {
                    String oldData = PlayerSubscribe.getData(offlinePlayer.getPlayer());
                    if (oldData.equalsIgnoreCase("forever"))
                        return Utils.getString("settings.placeholder.time.forever");
                    LocalDate futureData = LocalDate.parse(oldData, DateTimeFormatter.ISO_DATE);
                    LocalDate currentDate = LocalDate.now();
                    long daysUntil = ChronoUnit.DAYS.between(currentDate, futureData);
                    return Utils.getString("settings.placeholder.time.remainder").replace("%days%", String.valueOf(daysUntil));
                } else return Utils.getString("settings.placeholder.time.no-sub");
            } else if(params.equalsIgnoreCase("boolean")){
                if(PlayerSubscribe.SubscribeIsValid(offlinePlayer.getPlayer()))
                    return DZSubscribe.getInstance().getConfig().getString("settings.placeholder.boolean.yes-sub");
                else return DZSubscribe.getInstance().getConfig().getString("settings.placeholder.boolean.no-sub");
            }
        }
        return null;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        return super.onPlaceholderRequest(player, params);
    }


    public static void registerHook() {
        new PlaceholderAPIHook().register();
    }
}
