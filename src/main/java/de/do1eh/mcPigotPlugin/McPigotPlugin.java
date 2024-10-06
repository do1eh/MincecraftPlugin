package de.do1eh.mcPigotPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;


public final class McPigotPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginManager pluginManager =this.getServer().getPluginManager();

        JoinListener joinListener = new JoinListener();
        pluginManager.registerEvents(joinListener,this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command,  String label, String[] args) {
        switch (command.getLabel()) {
            case "gutpfad": gutPfad();
            case "halloween": halloween(sender);
        }
        return true;
    }

    private void gutPfad(){
        Bukkit.broadcastMessage("Gut Pfad und ein tolles JOTA");
        this.getLogger().info("gutpfad command");
    }


    private void halloween(CommandSender sender){
        Player player=(Player) sender;
        Bukkit.broadcastMessage("Kuerbis");
        Location position =player.getLocation();
        position.setX(position.getX()+2);
        World welt=player.getWorld();
        Block block=welt.getBlockAt(position);
        block.setType(Material.PUMPKIN);
    }

}
