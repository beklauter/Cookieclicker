package de.cookieleaks.cookieclicker.commands;

import de.kleckzz.coresystem.bukkit.libraries.plugin.chanel.player.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class GetHeadCMD extends BukkitCommand {

    public GetHeadCMD(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Player p = (Player) sender;
        if (!p.hasPermission("cookieclicker.*")) {
            return false;
        }

        ItemStack item = new ItemBuilder(Material.PLAYER_HEAD).setName("Knochen :)").setOwningPlayer(Bukkit.getOfflinePlayer("QuadratCookie")).toItemStack();
        p.getInventory().addItem(item);
        p.sendMessage("§aYou got the §fCookie§dclicker §ahead!");

        return false;
    }
}
