package us.toh.leetmmo;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.events.Events;
import us.toh.leetmmo.database.Database;

import java.io.File;
import java.sql.*;



public final class LeetMMO extends JavaPlugin {

    public static LeetMMO plugin;

    private Events e = new Events();
    private Database db = new Database(this);

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        String path = this.getDataFolder().getAbsolutePath();
        getServer().getPluginManager().registerEvents(e, this);
        db.createNewDatabase("LeetMMO-players.db");
        System.out.println("LeetMMO Enabled");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("LeetMMO Disabled");
    }
}
