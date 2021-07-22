package net.landgrafhomyak.BronzaW_Lobby;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.util.Vector;

import java.util.Objects;

import static org.bukkit.Bukkit.getConsoleSender;

public class PAListener implements Listener {
    private final World lobby_world;
    private final Location spawn;

    PAListener(World world) {
        this.lobby_world = world;
        this.spawn = new Location(this.lobby_world, 0.5, 201, 0.5);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getName().equals("LandgrafHomyak")) {
            return;
        }
//        if (event.getMaterial() == Material.CHORUS_FRUIT && event.getAction() == )
        event.setCancelled(true);
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.SPAWNER_EGG) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageEvent event) throws InterruptedException {
        if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
            event.setCancelled(true);
            if (event.getEntity().getLocation().getY() < 0) {
                event.getEntity().getServer().getLogger().severe("'" + event.getEntity().getName() + "' gets event horizon");
            }
            event.getEntity().teleport(this.spawn);
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.getFrom().getY() <= 0 || event.getFrom().getWorld() != this.lobby_world) {
            event.getPlayer().teleport(this.spawn);
            event.getPlayer().sendMessage("Постарайся не падать больше");

        }
    }


    @EventHandler
    public void onTeleport(EntityTeleportEvent event) {
        if (event.getFrom().getWorld() == this.lobby_world && (event.getTo() == null || event.getTo().getWorld() == this.lobby_world)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (event.getTo() != null && event.getTo().getY() < 0)
        {
            event.setCancelled(true);
            event.getPlayer().teleport(this.spawn);
        }
        if (event.getFrom().getWorld() == this.lobby_world && (event.getTo() == null || event.getTo().getWorld() == this.lobby_world) && event.getCause() != PlayerTeleportEvent.TeleportCause.ENDER_PEARL && event.getCause() != PlayerTeleportEvent.TeleportCause.PLUGIN && event.getCause() != PlayerTeleportEvent.TeleportCause.SPECTATE && !event.getPlayer().getName().toLowerCase().equals("landgrafhomyak") && !event.getPlayer().getName().toLowerCase().equals("bronzaw")) {

            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer().getWorld() != this.lobby_world) {
            event.getPlayer().teleport(this.spawn);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerSpawn(PlayerRespawnEvent event) {
        event.getPlayer().getServer().getLogger().severe(
                "Player '" + event.getPlayer().getName() + "' was respawned!"
        );
        /*
        if (event.getRespawnLocation().getWorld() != this.lobby_world) {
            event.getPlayer().teleport(new Location(this.lobby_world, -1.5, 201, -1.5)); *//* todo move world (+2;+2) *//*
        }
        */
    }

}