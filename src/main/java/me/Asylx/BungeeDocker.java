package me.Asylx;

import me.Asylx.Commands.*;
import me.Asylx.Player.Join;
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
        getProxy().getPluginManager().registerCommand(this, new Hub()); // /MESSAGE

        getProxy().getPluginManager().registerListener(this, new Join());
    }


    public static BungeeDocker getInstance() {
        return instance;
    }

    private static void setInstance(BungeeDocker instance) {
        BungeeDocker.instance = instance;
    }

}
