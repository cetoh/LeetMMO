package us.toh.leetmmo.commands;

import com.google.common.collect.ImmutableList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.skills.Skill;
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

        //Display available skill points
        if (args.length == 0) {
            player.sendMessage("Available Normal Skill Points: " + ChatColor.GOLD + profile.getnSPPool().getNumPoints() + ChatColor.GOLD + " pts");

            displayWholeTree(player, profile);
        } else {

        }
        return true;
    }

    private void sendSkillMessage(Player player, Skill skill) {
        Enum name = skill.getSkillName();
        String description = skill.getDescription();
        int numPoints = skill.getSkillPoints();
        int numPointsReq = skill.getSkillPointRequirement();
        ChatColor cc = null;

        if (numPoints == numPointsReq) {
            cc = ChatColor.BLUE;
        } else if (numPoints < numPointsReq) {
            cc = ChatColor.GREEN;
        }

        String msg = cc + name.toString() + cc + ": " + cc + description + "\n" +
                cc + numPoints + cc + "/" + cc + numPointsReq;

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

            if (numPoints == numPointsReq) {
                cc = ChatColor.BLUE;
            } else if (numPoints < numPointsReq) {
                cc = ChatColor.GREEN;
            }

            msg = cc + name.toString() + cc + ": " + cc + description + "\n" +
                    cc + numPoints + cc + "/" + cc + numPointsReq + "\n";

            //Check if prereqs exist
            if (!curSkill.getPrerequesiteSkills().isEmpty()) {
                for (Map.Entry<Skill, Integer> prereq : curSkill.getPrerequesiteSkills().entrySet()) {
                    msg += ChatColor.AQUA + prereq.getKey().getSkillName().toString() + " | ";
                }
                msg += "\n";
            }

            //Check if children exist
            if (!curSkill.getChildSkills().isEmpty()) {
                for (Map.Entry<Skill, Integer> child : curSkill.getPrerequesiteSkills().entrySet()) {
                    msg += "->" + ChatColor.AQUA + child.getKey().getSkillName().toString() + "\n";
                }
            }

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
