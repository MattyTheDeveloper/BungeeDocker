package me.Asylx.Commands;

import com.github.dockerjava.api.command.CreateContainerResponse;
import me.Asylx.Utills.Docker;
import me.Asylx.Utills.DockerStorage;
import me.Asylx.Utills.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.bson.Document;

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
                if (args[0].equalsIgnoreCase("server".toLowerCase())) {
                    Document id = DockerStorage.getData(args[1]);
                    if (id != null) {
                        Docker.stopContainer(id.getString("ID"));
                        Docker.deleteContainer(id.getString("ID"));
                        Docker.removeServer(args[0]);

                        DockerStorage.deleteDocument(id.getString("ID"));

                        Utils.send(p, "&eServer removed");
                    }
                    Utils.send(p, "&cServer not found!");
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