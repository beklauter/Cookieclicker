package de.cookieleaks.cookieclicker.events;

import de.cookieleaks.cookieclicker.Cookieclicker;
import de.cookieleaks.cookieclicker.utils.Config;
import de.cookieleaks.cookieclicker.utils.HeadBlock;
import de.kleckzz.coresystem.bukkit.libraries.plugin.chanel.player.chat.HexText;
import de.kleckzz.coresystem.bukkit.libraries.plugin.chanel.player.inventory.InventoryBuilder;
import de.kleckzz.coresystem.bukkit.libraries.plugin.chanel.player.item.ItemBuilder;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class OnPlace implements Listener {

    public static HashMap<Player, Integer> clicker = new HashMap<Player, Integer>();
    public static ArrayList<Player> cooldown = new ArrayList<>();


    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        ItemStack item = e.getItemInHand();
        if(item == null && e.getBlockPlaced().getType() == Material.PLAYER_HEAD)
            return;
        String name = item.getItemMeta().getDisplayName();
        if(name == null)
            return;
        if (!e.getPlayer().hasPermission("cookieclicker.*")) {
            e.setCancelled(true);
            return;
        }
        if(name.equals("Knochen :)")) {
            Config.saveHead(new HeadBlock(e.getBlockPlaced().getLocation()));
            e.getPlayer().sendMessage("§aKlicker platzier auf Koordinaten: " + e.getBlockPlaced().getX() + ";" + e.getBlockPlaced().getY() + ";" + e.getBlockPlaced().getZ());
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Location location = e.getBlock().getLocation();
        for(HeadBlock headBlock : Config.getHeads()) {
            if(headBlock.getXYZ().equals(location.getX() + ";" + location.getY() + ";" + location.getZ())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Block block = e.getPlayer().getTargetBlock(10);

        Location location = block.getLocation();
        for(HeadBlock headBlock : Config.getHeads()) {
            if(headBlock.getXYZ().equals(location.getX() + ";" + location.getY() + ";" + location.getZ())) {
                if(!cooldown.contains(e.getPlayer())) {
                    float clicked = Config.getCookies(e.getPlayer().getUniqueId().toString());

                    if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        // Wir zählen hier hoch
                        Config.addCookies(e.getPlayer().getUniqueId().toString(), 1);

                        e.getPlayer().sendActionBar(new HexText("&#fbbd1dD&#f4c221u &#edc825h&#e6cd2aa&#dfd22es&#d8d832t " + clicked + " &#d1dd36C&#c9e23ao&#c2e83eo&#bbed43k&#b4f247i&#adf84be&#a6fd4fs").translateColorCodes().parseHex().toString());
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_PLAYER_BURP, 100 , 50);
                    }

                    if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
                        // Wir öffnen das inventar
                        Inventory inventory = new InventoryBuilder().setFillMaterial(Material.BLACK_STAINED_GLASS_PANE).setTitle("§l§6Cookie übericht").setSize(9*3).build();
                        inventory.setItem(13, new ItemBuilder(Material.COOKIE).setName("§6Cookie §eübersicht").setLore("§aDu hast §e" + clicked + " §6Cookies").toItemStack());
                        e.getPlayer().openInventory(inventory);

                        Config.saveCookie();
                    }

                    cooldown.add(e.getPlayer());
                    autoRemove(e.getPlayer());
                }
                e.setCancelled(true);
            }
        }
    }

    private void autoRemove(Player player) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Cookieclicker.plugin, new Runnable() {
            @Override
            public void run() {
                cooldown.remove(player);
            }
        }, 1L);
    }

    @EventHandler
    public void onClickEvent(InventoryClickEvent event)
    {
        if(event.getView().getTitle().equals("§l§6Cookie übericht")){
            event.getWhoClicked().sendMessage("§cNein nein nein! Nur anschauen");
            event.setCancelled(true);
        }
    }
}
