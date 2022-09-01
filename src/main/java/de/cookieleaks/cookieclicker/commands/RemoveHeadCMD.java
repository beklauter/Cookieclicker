package de.cookieleaks.cookieclicker.commands;

import de.cookieleaks.cookieclicker.utils.Config;
import de.cookieleaks.cookieclicker.utils.HeadBlock;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RemoveHeadCMD extends BukkitCommand {

    public RemoveHeadCMD(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        Player p = (Player) sender;

        if (!p.hasPermission("cookieclicker.*")) {
            return false;
        }

        Block block = p.getTargetBlock(10);

        Location location = block.getLocation();
        for(HeadBlock headBlock : Config.getHeads()) {
            if(headBlock.getXYZ().equals(location.getX() + ";" + location.getY() + ";" + location.getZ())) {
                location.getBlock().setType(Material.AIR);
                Config.removeHead(headBlock);
                p.sendMessage("§aDer Kopf wurde erfolgreich entfernt!");
                return false;
            }
        }
        p.sendMessage("§cSchau den god damn Keks an! " + location.getX() + ";" + location.getY() + ";" + location.getZ());

        return false;
    }
}
