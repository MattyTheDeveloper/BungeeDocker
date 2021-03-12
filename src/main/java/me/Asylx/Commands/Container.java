package me.Asylx.Commands;

import me.Asylx.Utills.Docker;
import me.Asylx.Utills.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.List;

public class Container extends Command {
    public Container() {
        super("container");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer) sender;

        if (p.hasPermission("Deniable.Admin.Manage")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("list")) {

                    List<Container> list = Docker.listContainers();


                    Utils.send(p, list.toString());

                    Utils.send(p, "&eList completed.");

                } else {
                    Utils.send(p, "&cIncorrect Usage: /container (list)");
                    return;
                }


            } else {
                Utils.send(p, "&cIncorrect Usage: /container (list)");
                return;
            }
        } else {
            Utils.send(p, "&cInsufficient Permissions!");
            return;

        }
    }
}
