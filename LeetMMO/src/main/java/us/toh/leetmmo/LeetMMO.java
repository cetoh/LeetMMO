package us.toh.leetmmo;

import org.bukkit.plugin.java.JavaPlugin;
import us.toh.leetmmo.commands.CommandLeetStats;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.events.Events;
import us.toh.leetmmo.database.Database;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public final class LeetMMO extends JavaPlugin {

    public static LeetMMO plugin;

    private Map<UUID,PlayerProfile> globalPlayers = new HashMap<UUID,PlayerProfile>();

    private Events evt = new Events();
    private Database db = new Database(this);

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        //Start-up database
        db.createNewDatabase("LeetMMO-players.db");

        //Set-up Events
        evt.setDb(db);
        evt.setGlobalPlayers(globalPlayers);
        getServer().getPluginManager().registerEvents(evt, plugin);

        CommandLeetStats cmdLeetStats =  new CommandLeetStats();
        cmdLeetStats.setGlobalPlayers(globalPlayers);
        plugin.getCommand("leetstats").setExecutor(cmdLeetStats);
        System.out.println("LeetMMO Enabled");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("LeetMMO Disabled");
    }

    public Map<UUID, PlayerProfile> getGlobalPlayers() {
        return globalPlayers;
    }

}
