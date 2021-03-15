package me.Asylx.Commands;

import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectVolumeResponse;
import me.Asylx.Utills.Docker;
import me.Asylx.Utills.DockerStorage;
import me.Asylx.Utills.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Start extends Command {
    public Start() {
        super("start");
    }

    CreateContainerResponse container;

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer) sender;

        if (p.hasPermission("Deniable.Admin.Manage")) {
            if (args.length == 1) { // create LOBBY 1 25565
                Utils.send(p, "&eThe requested server has been started.");

                String id = DockerStorage.getData(args[0]).getString("ID");
                int port = DockerStorage.getData(args[0]).getInteger("Port");
                Docker.startContainer(id);

                Docker.addServer(args[0],port);

                Utils.send(p, "&eContainer started.");

            } else {
                Utils.send(p, "&cIncorrect Usage: /start (Name)");
            }
        } else {
            Utils.send(p, "&cInsufficient Permissions!");
            return;
        }
    }
}