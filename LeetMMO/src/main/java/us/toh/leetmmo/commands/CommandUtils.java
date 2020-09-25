package us.toh.leetmmo.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.skills.Skill;

import java.util.HashMap;
import java.util.Map;

public class CommandUtils {

    public static void addSkillPointToSkill(Player player, PlayerProfile playerProfile, Enum skillEnum, int ptsToAdd, HashMap<Enum, Skill> tree) {

        //Check that player has skill points available
        if (playerProfile.getnSPPool().getNumPoints() >= ptsToAdd) {

            Skill skill = tree.get(skillEnum);

            //Add skill points but only to cap
            int ptsToMax = skill.getSkillPointRequirement() - skill.getSkillPoints();
            int remainder = 0;
            if (ptsToAdd > ptsToMax) {
                remainder = ptsToAdd - ptsToMax;
                skill.setSkillPoints(skill.getSkillPointRequirement());
            } else {
                skill.setSkillPoints(skill.getSkillPoints() + ptsToAdd);
            }

            //Update tree
            tree.put(skillEnum, skill);

            //Remove used skill points from pool
            playerProfile.getnSPPool().subtractNumPoints(ptsToAdd - remainder);

            //Notify user of action
            String notif = (ptsToAdd - remainder) + " points were added to " + skillEnum.toString() + ". "
                    + remainder + " points could not be added.";

            player.sendMessage(ChatColor.YELLOW + notif);
        } else {
            player.sendMessage(ChatColor.RED + "Insufficient Normal Skill Points!");
        }
    }

    public static void sendSkillMessage(Player player, Skill skill) {
        Enum name = skill.getSkillName();
        String description = skill.getDescription();
        int numPoints = skill.getSkillPoints();
        int numPointsReq = skill.getSkillPointRequirement();
        ChatColor cc = null;
        ChatColor main = ChatColor.DARK_AQUA;

        if (numPoints == numPointsReq) {
            cc = ChatColor.BLUE;
        } else if (numPoints < numPointsReq) {
            cc = ChatColor.GREEN;
        }

        String msg = main + name.toString() + ": " + description +
                cc + " || " + numPoints + "/" + numPointsReq + "\n";

        //Check if prereqs exist
        if (!skill.getPrerequesiteSkills().isEmpty()) {
            msg += ChatColor.YELLOW + "Prerequisite Skills: ";
            for (Map.Entry<Skill, Integer> prereq : skill.getPrerequesiteSkills().entrySet()) {
                msg += ChatColor.AQUA + prereq.getKey().getSkillName().toString() + " | ";
            }
            msg += "\n";
        }

        //Check if children exist
        if (!skill.getChildSkills().isEmpty()) {
            msg += ChatColor.LIGHT_PURPLE + "Necessary to Unlock:\n";
            for (Map.Entry<Skill, Integer> child : skill.getPrerequesiteSkills().entrySet()) {
                msg += ChatColor.DARK_PURPLE + "->" + ChatColor.DARK_PURPLE + child.getKey().getSkillName().toString() + "\n";
            }
        }

        player.sendMessage(msg);
    }

    public static void displayWholeTree(Player player, PlayerProfile playerProfile, HashMap<Enum, Skill> skill) {

        String msg = "";
        for (Map.Entry<Enum, Skill> entry : skill.entrySet()) {

            Skill curSkill = entry.getValue();
            Enum name = curSkill.getSkillName();
            String description = curSkill.getDescription();
            int numPoints = curSkill.getSkillPoints();
            int numPointsReq = curSkill.getSkillPointRequirement();
            ChatColor cc = null;
            ChatColor main = ChatColor.DARK_AQUA;

            if (numPoints == numPointsReq) {
                cc = ChatColor.BLUE;
            } else if (numPoints < numPointsReq) {
                cc = ChatColor.GREEN;
            }

            msg += main + name.toString() + ": " + description +
                    cc + " || " + numPoints + "/" + numPointsReq + "\n";

        }

        player.sendMessage(msg);
    }
}
