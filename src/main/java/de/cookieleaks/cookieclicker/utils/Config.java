package de.cookieleaks.cookieclicker.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import de.cookieleaks.cookieclicker.Cookieclicker;
import de.kleckzz.coresystem.bukkit.libraries.plugin.JsonAccessor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Config {

    public static JsonAccessor headLocations;
    public static JsonAccessor playerCookies;
    public static ArrayList<HeadBlock> headBlocks;

    public static void init(JavaPlugin plugin) {
        headLocations = new JsonAccessor(plugin, "heads.json");
        headLocations.saveDefaultConfig();


        playerCookies = new JsonAccessor(plugin, "cookie.json");
        playerCookies.saveDefaultConfig();


        Bukkit.getScheduler().scheduleSyncRepeatingTask(Cookieclicker.plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    playerCookies.saveConfig();
                } catch(NullPointerException ex) {}
            }
        }, 20L, 20L * 120);
    }

    public static void saveHead(HeadBlock headBlock) {
        if(headBlocks == null) {
            headBlocks = new ArrayList<>();
        }
        headBlocks.add(headBlock);
        headLocations.getConfig().put("heads", new Gson().toJson(headBlocks));
        headLocations.saveConfig();
    }

    public static void removeHead(HeadBlock headBlock) {
        headBlocks.remove(headBlock);
        headLocations.getConfig().put("heads", new Gson().toJson(headBlocks));
        headLocations.saveConfig();
    }

    public static ArrayList<HeadBlock> getHeads() {
        if(headBlocks == null) {
            headBlocks = new ArrayList<>();
            JsonArray jsonArray = new Gson().fromJson(headLocations.getConfig().get("heads").toString(), JsonArray.class);
            for(JsonElement rawHeadBlock : jsonArray) {
                headBlocks.add(new Gson().fromJson(rawHeadBlock.toString(), HeadBlock.class));
            }
        }
        return headBlocks;
    }

    public static int getCookies(String UUID) {
        if(playerCookies.getConfig().containsKey(UUID))
            return Integer.valueOf(playerCookies.getConfig().get(UUID).toString());
        return 0;
    }

    public static void setCookies(String UUID, int cookies) {
        playerCookies.getConfig().put(UUID, cookies);
    }

    public static void addCookies(String UUID, int cookies) {
        int currentCookies = getCookies(UUID);
        currentCookies += cookies;
        playerCookies.getConfig().put(UUID, currentCookies);
    }

    public static void removeCookies(String UUID, int cookies) {
        int currentCookies = getCookies(UUID);
        currentCookies += cookies;
        playerCookies.getConfig().put(UUID, currentCookies);
    }

    public static void saveCookie() {
        playerCookies.saveConfig();
    }
}
