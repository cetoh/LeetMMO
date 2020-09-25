package us.toh.leetmmo.skills;

import org.bukkit.ChatColor;
import us.toh.leetmmo.LeetMMO;
import us.toh.leetmmo.datatypes.player.PlayerProfile;

import java.util.HashMap;
import java.util.Random;

public class SkillUtils {

    public static boolean playerHasSkill(LeetMMO plugin, PlayerProfile playerProfile, SkillTree skillTree, Enum skillEnum) {
        //Check if player has skill
        boolean hasSkill = true;
        Skill skill = skillTree.getTree().get(skillEnum);

        if (skill.getSkillPoints() != skill.getSkillPointRequirement()) {
            plugin.getServer().getPlayer(playerProfile.getUuid()).sendMessage(ChatColor.RED + "You do not have the necessary skill: " + skillEnum);
            hasSkill = false;
        }

        return hasSkill;
    }

    public static boolean chanceCheck(int percentage) {
        return chanceCheck(percentage, 101);
    }

    public static boolean chanceCheck(int percentage, int max) {
        boolean isRolled = false;

        Random random = new Random();
        int rand = 0;
        while (true) {
            rand = random.nextInt(max);
            if (rand != 0) break;
        }

        if (rand <= percentage) isRolled = true;

        return isRolled;
    }

    public static boolean hasPrerequesiteSkill(LeetMMO plugin, PlayerProfile playerProfile, SkillTree skillTree, Enum skillEnum) {
        boolean hasPrereq = true;
        Skill currentSkill = skillTree.getTree().get(skillEnum);

        HashMap<Skill, Integer> prereqSkills = currentSkill.getPrerequesiteSkills();

        for (Skill skill : prereqSkills.keySet()) {
            if (skill.getSkillPoints() < skill.getSkillPointRequirement()) {
                plugin.getServer().getPlayer(playerProfile.getUuid()).sendMessage(
                        "Missing Following Pre-requisite: " + skill.getSkillName().toString());
                hasPrereq = false;
            }
        }

        return hasPrereq;
    }
}
