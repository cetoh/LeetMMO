package us.toh.leetmmo;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import us.toh.leetmmo.events.Events;
import us.toh.leetmmo.database.Database;

import java.io.File;
import java.sql.*;



public final class LeetMMO extends JavaPlugin {


    private Events e = new Events();
    private Database db = new Database(this);

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(e, this);
        db.createNewDatabase("test.db");
        System.out.println("LeetMMO Enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("LeetMMO Disabled");
    }
}
