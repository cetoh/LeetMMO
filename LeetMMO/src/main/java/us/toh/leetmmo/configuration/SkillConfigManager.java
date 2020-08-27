package us.toh.leetmmo.configuration;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import us.toh.leetmmo.LeetMMO;

import java.io.File;
import java.io.IOException;

public class SkillConfigManager {

    private static LeetMMO plugin;
    private File customConfigFile;
    private FileConfiguration customConfig;
    private static String fileName = "skills.yml";

    public SkillConfigManager(LeetMMO plugin) {
        this.plugin = plugin;
        createCustomConfig();
    }

    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

    private void createCustomConfig() {
        customConfigFile = new File(plugin.getDataFolder(), fileName);
        if (!customConfigFile.exists()) {
            System.out.println("Creating LeetMMO " + fileName + " File...");

            try {
                plugin.saveResource(fileName, false); // Normal files
            }
            catch (IllegalArgumentException ex) {
                plugin.saveResource(customConfigFile.getParentFile().getName() + File.separator + fileName, false); // Mod files
            }
        }
        else {
            System.out.println("Loading LeetMMO " + fileName + " File...");
        }

        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);

    }
}
