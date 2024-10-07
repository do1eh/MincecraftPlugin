package de.do1eh.mcPigotPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.type.Door;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;



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

        String parameter1="6";

        if (args.length>0) {
            parameter1=args[0];
        }


        switch (command.getLabel()) {

            case "gutpfad": gutPfad();
            case "halloween": halloween(sender);
            case "jurte" :jurte(sender,parameter1);

        }
        return true;
    }

    private void gutPfad(){
        Bukkit.broadcastMessage("Gut Pfad und ein tolles JOTA");
        this.getLogger().info("gutpfad command");
    }


    private void halloween(CommandSender sender){
        Player player=(Player) sender;
        Bukkit.broadcastMessage("Happy Halloween");
        Location position =player.getLocation();
        position.setX(position.getX()+2);
        World welt=player.getWorld();
        Block block=welt.getBlockAt(position);
        block.setType(Material.BELL);
    }


    /**
     * Jurte bauen
     * @param sender
     */
    private void jurte(CommandSender sender, String radius){
      if (null==radius){
            radius="6";
        }
        //Jurte
        int r= Integer.parseInt(radius);
        kreis(sender,r,0,Material.BLACK_WOOL,true);
        kreis(sender,r,1,Material.BLACK_WOOL,true);
        int hoehe=2;
        for(int i=r-1;i>0;i--) {
            kreis(sender,i,hoehe,Material.BLACK_WOOL,true);
            if ((i%3==0)){hoehe++;}
        }

        Player player=(Player) sender;
        World welt=player.getWorld();
        Location position=player.getLocation();

        //Feuer
        position.setZ(position.getZ()+3);
        Block block=welt.getBlockAt(position);
        block.setType(Material.CAMPFIRE);

        //Tür
        position.setZ(position.getZ()+r);
        Block tuerUnterBlock = welt.getBlockAt(position);
        tuerUnterBlock.setType(Material.DARK_OAK_DOOR);

        Door tuerUnten=(Door) tuerUnterBlock.getBlockData();
        tuerUnten.setHalf(Bisected.Half.BOTTOM);
        tuerUnten.setFacing(BlockFace.SOUTH);
        tuerUnten.setOpen(false);
        tuerUnterBlock.setBlockData(tuerUnten);

        Block tuerObenBlock = tuerUnterBlock.getRelative(BlockFace.UP);
        tuerObenBlock.setType(Material.DARK_OAK_DOOR,false);
        Door tuerOben=(Door) tuerObenBlock.getBlockData();
        tuerOben.setHalf(Bisected.Half.TOP);
        tuerOben.setFacing(BlockFace.SOUTH);
        tuerOben.setOpen(false);
        tuerObenBlock.setBlockData(tuerOben);
    }




    /**
     * Kreis aus Blöcken setzen
     * Die runde Form wird nach dem Midpoint Algorithmus
     *
     * @param sender
     * @param radius
     * @param hoeheueberGrund
     */
    private void kreis(CommandSender sender, int radius, int hoeheueberGrund, Material material, boolean pyramide) {
        Player player=(Player) sender;
        World welt=player.getWorld();
        Location position=player.getLocation();

        double xStart =position.getX();
        double zStart =position.getZ();
        zStart+=3;
        position.setY(position.getY()+hoeheueberGrund);

        int d=-radius;
        int x=radius;
        int z = 0;

        while (z <= x) {
            position.setZ(zStart+z);
            position.setX(xStart+x);
            welt.getBlockAt(position).setType(material);

            position.setZ(zStart-z);
            position.setX(xStart-x);
            welt.getBlockAt(position).setType(material);

            position.setZ(zStart+z);
            position.setX(xStart-x);
            welt.getBlockAt(position).setType(material);

            position.setZ(zStart-z);
            position.setX(xStart+x);
            welt.getBlockAt(position).setType(material);

            position.setZ(zStart+x);
            position.setX(xStart+z);
            welt.getBlockAt(position).setType(material);

            position.setZ(zStart-x);
            position.setX(xStart-z);
            welt.getBlockAt(position).setType(material);

            position.setZ(zStart+x);
            position.setX(xStart-z);
            welt.getBlockAt(position).setType(material);

            position.setZ(zStart-x);
            position.setX(xStart+z);
            welt.getBlockAt(position).setType(material);

            d=d+2*z+1;
            z++;

           if (!pyramide) {

               if (d > 0) {
                   d = d - 2 * x + 2;
                   x--;
               }
           } else {
               x--;
           }
        }
 }
}
