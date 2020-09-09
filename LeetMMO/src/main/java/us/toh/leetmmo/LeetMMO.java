package us.toh.leetmmo;

import org.bukkit.plugin.java.JavaPlugin;
import us.toh.leetmmo.commands.*;
import us.toh.leetmmo.configuration.ExperienceConfigLoader;
import us.toh.leetmmo.datatypes.experience.ExperienceEvents;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.events.Events;
import us.toh.leetmmo.database.Database;
import us.toh.leetmmo.gui.advancements.NormalSkillTreeGUI;
import us.toh.leetmmo.skills.normal.farming.FarmingEvents;
import us.toh.leetmmo.skills.normal.mining.MiningEvents;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public final class LeetMMO extends JavaPlugin {

    public static LeetMMO plugin;

    private Map<UUID,PlayerProfile> globalPlayers = new HashMap<UUID,PlayerProfile>();

    private Events evt = new Events();
    private ExperienceEvents experienceEvents = new ExperienceEvents();
    private FarmingEvents farmingEvents = new FarmingEvents();
    private MiningEvents miningEvents = new MiningEvents();

    private NormalSkillTreeGUI normalSkillTreeGUI = new NormalSkillTreeGUI();

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

        //Set-up Events

        //Core Startup Events
        evt.setDb(db);
        evt.setGlobalPlayers(globalPlayers);
        getServer().getPluginManager().registerEvents(evt, plugin);

        //Experience Events
        experienceEvents.setGlobalPlayers(globalPlayers);
        experienceEvents.setExpConfigManager(expConfigLoader);
        getServer().getPluginManager().registerEvents(experienceEvents, plugin);

        //Farming Events
        farmingEvents.setGlobalPlayers(globalPlayers);
        farmingEvents.setPlugin(this);
        getServer().getPluginManager().registerEvents(farmingEvents, plugin);

        //Farming Events
        miningEvents.setGlobalPlayers(globalPlayers);
        getServer().getPluginManager().registerEvents(miningEvents, plugin);

        getServer().getPluginManager().registerEvents(normalSkillTreeGUI, plugin);

        System.out.println("LeetMMO Enabled");


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        // Save player data
        for (Map.Entry<UUID, PlayerProfile> profile : globalPlayers.entrySet()) {
            PlayerProfile pp = profile.getValue();

            //See if player exists in database. If player exists update profile. Else insert new profile
            if (!db.checkIfPlayerExists(pp, "player")
                    || !db.checkIfPlayerExists(pp, "farming")
                    || !db.checkIfPlayerExists(pp, "mining")) {
                db.insertNewPlayerProfile(pp);
            }
            db.updatePlayerProfile(pp);
        }


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

        //LeetNormalSkills Command
        CommandLeetNormalSkills cmdNormalSkills =  new CommandLeetNormalSkills();
        cmdNormalSkills.setGlobalPlayers(globalPlayers);
        plugin.getCommand("leetnskills").setExecutor(cmdNormalSkills);

        //LeetNormalSkills Command
        CommandLeetFarming cmdFarming =  new CommandLeetFarming();
        cmdFarming.setGlobalPlayers(globalPlayers);
        plugin.getCommand("leetfarming").setExecutor(cmdFarming);

        CommandLeetMining cmdMining =  new CommandLeetMining();
        cmdMining.setGlobalPlayers(globalPlayers);
        plugin.getCommand("leetmining").setExecutor(cmdMining);
    }

    public Map<UUID, PlayerProfile> getGlobalPlayers() {
        return globalPlayers;
    }

}
