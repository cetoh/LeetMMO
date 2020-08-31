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
        player.sendMessage("Available Normal Skill Points: " + ChatColor.GOLD + profile.getnSPPool().getNumPoints() + ChatColor.GOLD + " pts");

        FarmingSkillTree skillTree = profile.getFarmingSkillTree();
        Skill rootSkill = skillTree.getTree().get(BASIC_AGRICULTURE);

        sendSkillMessage(player, rootSkill);



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

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return ImmutableList.of();
    }

    public void setGlobalPlayers(Map<UUID, PlayerProfile> globalPlayers) {
        this.globalPlayers = globalPlayers;
    }
}
