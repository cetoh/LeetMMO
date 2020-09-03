package us.toh.leetmmo.commands;

import com.google.common.collect.ImmutableList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.skills.normal.NormalSkillEnums;
import us.toh.leetmmo.utils.EnumUtilities;
import us.toh.leetmmo.utils.StringUtilities;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CommandLeetMining implements CommandExecutor {

    private Player player;
    private Map<UUID, PlayerProfile> globalPlayers;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        player = (Player) sender;

        PlayerProfile profile = globalPlayers.get(player.getUniqueId());

        //If only base command sent, display available skill points
        if (args.length == 0) {
            player.sendMessage("Available Normal Skill Points: " + ChatColor.GOLD + profile.getnSPPool().getNumPoints() + ChatColor.GOLD + " pts");

            CommandUtils.displayWholeTree(player, profile);
        } else {
            //Check if first argument is a valid Farming skill. If it is display the skill info if no other command follows.
            if (!args[0].isEmpty() && args[0] != null && !args[0].equals("")
                    && (args[1].isEmpty() || args[1] == null)) {
                if (EnumUtilities.isInEnum(args[0], NormalSkillEnums.MiningSkillNames.class)) {
                    CommandUtils.sendSkillMessage(player,
                            profile.getMiningSkillTree()
                            .getTree()
                            .get(NormalSkillEnums.MiningSkillNames.valueOf(args[0])));
                } else {
                    player.sendMessage(ChatColor.RED + "Unrecognized Skill");
                }
            }

            //Check if add skill point command was initiated
            else if (!args[0].isEmpty() && args[0] != null && !args[0].equals("")
                    && !args[1].isEmpty() && args[1] != null
                    && !args[2].isEmpty() && args[2] != null) {

                if (args[1].equals("add")) {
                    if (!EnumUtilities.isInEnum(args[0], NormalSkillEnums.MiningSkillNames.class)) {
                        player.sendMessage(ChatColor.RED + "Unrecognized Skill");
                    } else {
                        if (StringUtilities.isInteger(args[2])) {
                            //Add point to skill
                            CommandUtils.addSkillPointToSkill(player,
                                    profile,
                                    NormalSkillEnums.MiningSkillNames.valueOf(args[0]),
                                    Integer.getInteger(args[2]));
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "Must be a valid integer. (e.g. leet[skilltreename] [skillName] add INTEGER)");
                        }
                    }

                } else {
                    player.sendMessage(ChatColor.RED + "Invalid Command");
                }
            }
        }
        return true;
    }



    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return ImmutableList.of();
    }

    public void setGlobalPlayers(Map<UUID, PlayerProfile> globalPlayers) {
        this.globalPlayers = globalPlayers;
    }
}
