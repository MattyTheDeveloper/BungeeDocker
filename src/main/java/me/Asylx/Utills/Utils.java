package me.Asylx.Utills;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Random;

public class Utils {

    public static int num;

    public static void send(ProxiedPlayer p, String message) {
        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
    }

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }


    public static int getLobby() {
        Random rand = new Random();

        int rangeMin = 0;

        for (ServerInfo server : ProxyServer.getInstance().getServers().values()) {
            if (server.getName().toLowerCase().contains("lobby")) {
                String number = server.getName().toLowerCase().replace("lobby", "");
                num = Integer.parseInt(number);
            }
        }
        int result = rand.nextInt( (num - rangeMin) +1) + rangeMin;

        System.out.println("RESULT "+result+" SERVER NUMBER "+num);

        return result;
    }

}
