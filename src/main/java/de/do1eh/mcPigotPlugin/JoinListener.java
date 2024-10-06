package de.do1eh.mcPigotPlugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onEvent(PlayerJoinEvent event) {
        Player spieler=event.getPlayer();
        spieler.sendMessage("Gut Pfad "+spieler.getName()+"! Willkommen beim JOTA 2024");
    }
}
