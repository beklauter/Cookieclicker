package de.cookieleaks.cookieclicker.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CleanupCMD extends BukkitCommand {

    public CleanupCMD(String name) {
        super(name);
        this.setAliases(Arrays.asList("cleanup", "cls", "c"));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Player p = (Player) sender;
        if (!p.hasPermission("cookieclicker.*")) {
            return false;
        }


        p.getInventory().clear();
        p.sendMessage("§aDein Inventar wurde komplett rasiert!");
        return false;
    }
}
