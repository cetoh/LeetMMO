package us.toh.leetmmo.skills;

import org.bukkit.ChatColor;
import us.toh.leetmmo.LeetMMO;
import us.toh.leetmmo.datatypes.player.PlayerProfile;

import java.util.Random;
import java.util.UUID;

import static us.toh.leetmmo.skills.normal.NormalSkillEnums.FarmingSkillNames.BASIC_AGRICULTURE;

public class SkillUtils {

    public static boolean playerHasSkill(LeetMMO plugin, PlayerProfile playerProfile, Enum skillEnum) {
        //Check if player has skill
        boolean hasSkill = true;
        Skill skill = playerProfile.getFarmingSkillTree().getTree().get(skillEnum);

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
}
