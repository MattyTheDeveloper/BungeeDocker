package me.Asylx.Commands;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DockerClientBuilder;
import me.Asylx.Utills.Docker;
import me.Asylx.Utills.DockerStorage;
import me.Asylx.Utills.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.List;

public class Create extends Command {
    public Create() {
        super("Create");
    }

    CreateContainerResponse container;

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer) sender;

        if (p.hasPermission("Deniable.Admin.Manage")) {
            if (args.length == 3) { // create LOBBY 1 25565
                if (args[0].equalsIgnoreCase("lobby".toLowerCase())) {
                    if (DockerStorage.getData(args[1]) == null) {
                        Utils.send(p, "&eCreating construction of Lobby.");
                        int port = Integer.parseInt(args[2]);

                        Docker.createContainter(args[1].toLowerCase(), "lobby", port);
                        Utils.send(p, "&eReconstruction successfully created.");
                    } else {
                        Utils.send(p, "&eServer already running!");
                    }
                }
            } else {
                Utils.send(p, "&cIncorrect Usage: /create (type) (name) (port)");
            }
        } else {
            Utils.send(p, "&cInsufficient Permissions!");
            return;
        }
    }
}
