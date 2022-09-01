package de.cookieleaks.cookieclicker.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class HeadBlock {

    private final String world;
    private final double x;
    private final double y;
    private final double z;

    public HeadBlock(String world, double x, double y, double z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public HeadBlock(Location location) {
        this.world = location.getWorld().toString();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(this.world), this.x, this.y, this.z);
    }

    public String getXYZ() {
        return x + ";" + y + ";" + z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
