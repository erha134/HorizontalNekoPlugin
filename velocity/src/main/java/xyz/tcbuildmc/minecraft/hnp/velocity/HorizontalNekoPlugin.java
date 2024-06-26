package xyz.tcbuildmc.minecraft.hnp.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyReloadEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.Getter;
import org.slf4j.Logger;
import xyz.tcbuildmc.minecraft.hnp.common.HorizontalNekoPluginConfigHelper;

@Getter
@Plugin(id = "horizontalnekoplugin",
        name = "HorizontalNekoPlugin",
        version = "1.0.0-SNAPSHOT",
        description = "A Minecraft server-side Mod / Plugin provides global chat for Minecraft Servers.",
        url = "https://github.com/MrCraftTeamMC/HorizontalNekoPlugin",
        authors = {
                "TCBuildTeamMC",
                "Block_limr267",
                "Mr_zmh5",
                "SnowCutieOwO"
        })
public final class HorizontalNekoPlugin {
    private final ProxyServer server;
    private final Logger logger;
    private static HorizontalNekoPluginConfigHelper config;

    @Inject
    public HorizontalNekoPlugin(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
        HorizontalNekoPlugin.config = new HorizontalNekoPluginConfigHelper(HorizontalNekoPlugin.class);
    }

    @Subscribe
    public void onProxyInit(ProxyInitializeEvent e) {
        config.load();
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent e) {
        config.close();
    }

    @Subscribe
    public void onProxyReload(ProxyReloadEvent e) {
        config.close();
        config.load();
    }
}
