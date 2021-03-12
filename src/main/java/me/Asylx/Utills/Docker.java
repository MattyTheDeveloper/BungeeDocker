package me.Asylx.Utills;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectVolumeResponse;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DockerClientBuilder;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.List;

public class Docker {

    public static DockerClient dockerClient = DockerClientBuilder.getInstance().build();

    public static void createContainter(String name, String type, int port) {
        if (type.equals("lobby")) {
            ExposedPort tcp25565 = ExposedPort.tcp(port);
            Ports portBindings = new Ports();
            portBindings.bind(tcp25565, Ports.Binding.bindPort(port));

            CreateContainerResponse container
                    = dockerClient.createContainerCmd("lobby:latest")
                    .withEnv("PORT="+port)
                    .withExposedPorts(tcp25565)
                    .withPortBindings(portBindings)
                    .withName(name).exec();

            String id = container.getId();
            DockerStorage.addContainerData(id,name, port);
        }
    }

    public static void deleteContainer(String id) {
        dockerClient.removeContainerCmd(id).exec();

    }


    public static void startContainer(String id) {
        dockerClient.startContainerCmd(id).exec();
    }

    public static List listContainers() {
        List<Container> containers = dockerClient.listContainersCmd().exec();
        return containers;
    }

    public static void stopContainer(String id) {
        dockerClient.stopContainerCmd(id).exec();
    }

    public static void CreateImage(String name, String type) {
        String image = dockerClient.buildImageCmd()
                .withDockerfile(new File(ProxyServer.getInstance().getPluginsFolder()+"/Docker/Images/Paper16.5", "Dockerfile"))
                .withPull(true)
                .withNoCache(true)
                .withTag(name+":latest")
                .exec(new BuildImageResultCallback())
                .awaitImageId();

    }

    public static void deleteImage(String value) {

    }


    public static void addServer(String name, int port) {
        ServerInfo info = ProxyServer.getInstance().constructServerInfo(name, new InetSocketAddress("localhost",port), "Docker", false);
        ProxyServer.getInstance().getServers().put(name, info);
    }
    public static void removeServer(String name) {
        for (ProxiedPlayer p : ProxyServer.getInstance().getServerInfo(name).getPlayers()) {
            p.disconnect(new TextComponent("Server has been force closed."));
        }
        ProxyServer.getInstance().getServers().remove(name);
    }

    public static DockerClient getDockerClient() {
        return dockerClient;
    }
}
