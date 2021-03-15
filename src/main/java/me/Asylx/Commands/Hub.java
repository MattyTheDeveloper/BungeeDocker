package me.Asylx.Commands;

import me.Asylx.Utills.Utils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Hub extends Command {
    public Hub() {
        super("hub", "", "lobby");
    }

    @Override
    public void execute(CommandSender sender, String[] strings) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(ChatColor.RED+"Player only command."));
        }
        ProxiedPlayer p = (ProxiedPlayer) sender;

        ServerInfo server = ProxyServer.getInstance().getServerInfo("LOBBY"+ Utils.getLobby());
        System.out.println(Utils.getLobby());

        if (server == null) {
            Utils.send(p, "BUGy: LOBBY NOT FOUND! MESSAGE A DEVELOPER.");
            return;
        }

        p.connect(server);
        Utils.send(p, "&7Sending you to the lobby!");


    }
}
