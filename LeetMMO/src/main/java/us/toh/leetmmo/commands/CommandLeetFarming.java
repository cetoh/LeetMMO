package us.toh.leetmmo.commands;

import com.google.common.collect.ImmutableList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.skills.Skill;
import us.toh.leetmmo.skills.normal.NormalSkillEnums;
import us.toh.leetmmo.skills.normal.farming.skilltree.FarmingSkillTree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static us.toh.leetmmo.skills.normal.NormalSkillEnums.FarmingSkillNames.*;

public class CommandLeetFarming implements CommandExecutor {

    private Map<UUID, PlayerProfile> globalPlayers;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        PlayerProfile profile = globalPlayers.get(player.getUniqueId());

        //If only base command sent, display available skill points
        if (args.length == 0) {
            player.sendMessage("Available Normal Skill Points: " + ChatColor.GOLD + profile.getnSPPool().getNumPoints() + ChatColor.GOLD + " pts");

            displayWholeTree(player, profile);
        } else {
            //Check if first argument is a valid Farming skill. If it is display the skill info if no other command follows.
            if (!args[0].isEmpty() && args[0] != null && !args[0].equals("")
                && (args[1].isEmpty() || args[1] == null)) {
                if (NormalSkillEnums.isInEnum(args[0], NormalSkillEnums.FarmingSkillNames.class)) {
                    sendSkillMessage(player,
                            profile.getFarmingSkillTree()
                                    .getTree()
                                    .get(NormalSkillEnums.FarmingSkillNames.valueOf(args[0])));
                } else {
                    player.sendMessage(ChatColor.RED + "Unrecognized Skill");
                }
            }

            //Check if add skill point command was initiated
            else if (!args[0].isEmpty() && args[0] != null && !args[0].equals("")
                    && !args[1].isEmpty() && args[1] != null) {
            }
        }
        return true;
    }


    private void sendSkillMessage(Player player, Skill skill) {
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

        String msg = main + name.toString() + main + ": " + main + description +
                cc + numPoints + cc + "/" + cc + numPointsReq + "\n";

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

    private void displayWholeTree(Player player, PlayerProfile playerProfile) {
        HashMap<Enum, Skill> skill = playerProfile.getFarmingSkillTree().getTree();

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

            msg += main + name.toString() + main + ": " + main + description +
                    cc + numPoints + cc + "/" + cc + numPointsReq + "\n";

        }

        player.sendMessage(msg);
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return ImmutableList.of();
    }

    public void setGlobalPlayers(Map<UUID, PlayerProfile> globalPlayers) {
        this.globalPlayers = globalPlayers;
    }
}
