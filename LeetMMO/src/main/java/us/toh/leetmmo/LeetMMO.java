package us.toh.leetmmo;

import org.bukkit.plugin.java.JavaPlugin;
import us.toh.leetmmo.commands.CommandLeetInfo;
import us.toh.leetmmo.commands.CommandLeetStats;
import us.toh.leetmmo.configuration.ExperienceConfigManager;
import us.toh.leetmmo.configuration.SkillConfigManager;
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

        //Enable Player Commands
        setupPlayerCommands();

        //Load Configurations
        ExperienceConfigManager expConfigManager = new ExperienceConfigManager(this);
        SkillConfigManager skillConfigManager = new SkillConfigManager(this);

        //Set-up Events
        evt.setDb(db);
        evt.setGlobalPlayers(globalPlayers);
        evt.setExpConfigManager(expConfigManager);
        getServer().getPluginManager().registerEvents(evt, plugin);

        System.out.println("LeetMMO Enabled");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("LeetMMO Disabled");
    }

    private void setupPlayerCommands() {
        //LeetInfo Command
        CommandLeetInfo cmdLeetInfo = new CommandLeetInfo();
        plugin.getCommand("leetinfo").setExecutor(cmdLeetInfo);

        //LeetStats Command
        CommandLeetStats cmdLeetStats =  new CommandLeetStats();
        cmdLeetStats.setGlobalPlayers(globalPlayers);
        plugin.getCommand("leetstats").setExecutor(cmdLeetStats);
    }

    public Map<UUID, PlayerProfile> getGlobalPlayers() {
        return globalPlayers;
    }

}
