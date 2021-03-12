package me.Asylx.Utills;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Utils {

    public static void send(ProxiedPlayer p, String message) {
        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
    }

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
