package us.toh.leetmmo;

import org.bukkit.plugin.java.JavaPlugin;
import us.toh.leetmmo.commands.CommandLeetInfo;
import us.toh.leetmmo.commands.CommandLeetStats;
import us.toh.leetmmo.configuration.ExperienceConfigLoader;
import us.toh.leetmmo.configuration.SkillConfigLoader;
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
        ExperienceConfigLoader expConfigLoader = new ExperienceConfigLoader(this);
        SkillConfigLoader skillConfigLoader = new SkillConfigLoader(this);

        //Set-up Events
        evt.setDb(db);
        evt.setGlobalPlayers(globalPlayers);
        evt.setExpConfigManager(expConfigLoader);
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
