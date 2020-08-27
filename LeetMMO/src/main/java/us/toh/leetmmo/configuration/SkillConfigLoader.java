package us.toh.leetmmo.configuration;

import us.toh.leetmmo.LeetMMO;

public class SkillConfigLoader extends ConfigLoader{

    private static String fileName = "skills.yml";

    public SkillConfigLoader(LeetMMO plugin) {
        super(plugin, fileName);
    }

}
