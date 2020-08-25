package us.toh.leetmmo;

import org.bukkit.plugin.java.JavaPlugin;

public final class LeetMMO extends JavaPlugin {

    public static LeetMMO plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        String path = this.getDataFolder().getAbsolutePath();
        System.out.println("LeetMMO Enabled");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("LeetMMO Disabled");
    }
}
