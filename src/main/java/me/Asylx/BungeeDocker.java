package me.Asylx;

import me.Asylx.Commands.Container;
import me.Asylx.Commands.Create;
import me.Asylx.Commands.Remove;
import me.Asylx.Commands.Start;
import me.Asylx.Utills.DockerStorage;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

import java.net.UnknownHostException;

public class BungeeDocker extends Plugin {
    private static BungeeDocker instance;

    @Override
    public void onEnable() {
        setInstance(this);

        setupStuff();

        try {
            DockerStorage.SetupMongoDB();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        System.out.println("Disabled");
    }

    private void setupStuff() {
        getProxy().getPluginManager().registerCommand(this, new Create());
        getProxy().getPluginManager().registerCommand(this, new Start());
        getProxy().getPluginManager().registerCommand(this, new Container());
        getProxy().getPluginManager().registerCommand(this, new Remove());
    }


    public static BungeeDocker getInstance() {
        return instance;
    }

    private static void setInstance(BungeeDocker instance) {
        BungeeDocker.instance = instance;
    }

}
