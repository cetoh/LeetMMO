package us.toh.leetmmo.configuration;


import us.toh.leetmmo.LeetMMO;

public class ExperienceConfigLoader extends ConfigLoader {

    private static String fileName = "experience.yml";

    public ExperienceConfigLoader(LeetMMO plugin) {
        super(plugin, fileName);
    }


}
