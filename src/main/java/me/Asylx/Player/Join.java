package me.Asylx.Player;

import me.Asylx.Utills.Utils;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Random;

public class Join implements Listener {

    public int num;

    @EventHandler
    public void onJoin(PostLoginEvent e) {
        ProxiedPlayer p = e.getPlayer();

        ServerInfo lobby = ProxyServer.getInstance().getServerInfo("lobby"+ Utils.getLobby());

        p.connect(lobby);
    }
}
