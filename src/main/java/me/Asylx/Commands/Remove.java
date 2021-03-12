package me.Asylx.Commands;

import com.github.dockerjava.api.command.CreateContainerResponse;
import me.Asylx.Utills.Docker;
import me.Asylx.Utills.DockerStorage;
import me.Asylx.Utills.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Remove extends Command {
    public Remove() {
        super("remove");
    }

    CreateContainerResponse container;

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer) sender;

        if (p.hasPermission("Deniable.Admin.Manage")) {
            if (args.length == 2) { // create LOBBY 1 25565
                String id = DockerStorage.getData(args[0]).getString("ID");
                if (args[0].equalsIgnoreCase("server".toLowerCase())) {
                    Utils.send(p, "&eShutting down server");


                    Docker.stopContainer(id);
                    Docker.deleteContainer(id);

                    Docker.removeServer(args[0]);

                    Utils.send(p, "&eServer removed");
                } else if (args[0].equalsIgnoreCase("image")) {
                    Utils.send(p, "WIP");
                } else {
                    Utils.send(p, "&cIncorrect Usage: /remove (server/image) (Name)");
                }

            } else {
                Utils.send(p, "&cIncorrect Usage: /remove (server/image) (Name)");
            }
        } else {
            Utils.send(p, "&cInsufficient Permissions!");
            return;
        }
    }
}