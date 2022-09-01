package de.cookieleaks.cookieclicker;

import de.cookieleaks.cookieclicker.commands.CleanupCMD;
import de.cookieleaks.cookieclicker.commands.GetHeadCMD;
import de.cookieleaks.cookieclicker.commands.RemoveHeadCMD;
import de.cookieleaks.cookieclicker.events.OnPlace;
import de.cookieleaks.cookieclicker.utils.Config;
import de.kleckzz.coresystem.bukkit.libraries.plugin.InitializeManager;
import de.kleckzz.coresystem.bukkit.libraries.plugin.JsonAccessor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Cookieclicker extends JavaPlugin {

    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        InitializeManager.registerCommand(plugin, new GetHeadCMD("gethead"));
        InitializeManager.registerCommand(plugin, new RemoveHeadCMD("removehead"));
        InitializeManager.registerCommand(plugin, new CleanupCMD("cleanup"));
        InitializeManager.registerEvent(plugin, new OnPlace());


        Bukkit.getConsoleSender().sendMessage("§l§bWillst du Kekse?");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage("§l§aDu bekommst kekse.");

        Config.init(plugin);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage("§l§cKeine Kekse für dich!");
    }
}
