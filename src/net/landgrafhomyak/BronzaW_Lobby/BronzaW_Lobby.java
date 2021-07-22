package net.landgrafhomyak.BronzaW_Lobby;

import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Logger;

public class BronzaW_Lobby extends JavaPlugin implements Listener {
    private final Logger log = getLogger();

    @Override
    public void onEnable() {
        super.onEnable();
        log.info("Initializing lobby world...");
        World lobby_world = getServer().createWorld(new LobbyDimension());
        if (lobby_world == null)
        {
            log.severe("Can't create lobby dimension");
            log.info("Shutting down server...");
            getServer().shutdown();
            return;
        }
        else
        {
            log.info("Lobby dimension created");
        }

        if (lobby_world.setSpawnLocation(0, 201, 0))
        {
            log.info("World spawn moved to lobby dimension");
        }
        else
        {
            log.severe("Can't move world spawn point to lobby dimension");
            return;
        }
        for (World wd : getServer().getWorlds()) {
            if (wd != lobby_world) {
                getServer().unloadWorld(wd.getName(), false);
                log.info("Dimension '".concat(wd.getName().concat("' removed")));
            }
        }
        this.getServer().getPluginManager().registerEvents(new PAListener(lobby_world), this);
        log.info("Event listener registered");
        log.info("Lobby world created successful");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        log.info("Lobby world plugin disabled");
    }

    /*
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        super.onCommand(sender, command, label, args);
        return false;
    }
    */

}
