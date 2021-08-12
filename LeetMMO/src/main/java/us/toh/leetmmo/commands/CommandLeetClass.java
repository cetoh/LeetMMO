package us.toh.leetmmo.commands;

import com.google.common.collect.ImmutableList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.skills.Skill;
import us.toh.leetmmo.skills.classes.ClassEnums;
import us.toh.leetmmo.skills.normal.NormalSkillEnums;
import us.toh.leetmmo.utils.EnumUtilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CommandLeetClass implements CommandExecutor {

    private Map<UUID, PlayerProfile> globalPlayers;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        PlayerProfile profile = globalPlayers.get(player.getUniqueId());

        //If only base command sent, display available skill points
        if (args.length == 0) {
            player.sendMessage("Current Class: " + profile.getCurrentClass().toString());
            player.sendMessage("Available Class Skill Points: " + ChatColor.GOLD + profile.getcSPPool().getNumPoints() + ChatColor.GOLD + " pts");

            //CommandUtils.displayWholeTree(player, profile, skillTree);
        } else if (args.length == 1) {
            if (args[0] != null && !args[0].equals("")) {
                if (EnumUtilities.isInEnum(args[0], ClassEnums.Rogue.class)) {
                    CommandUtils.sendSkillMessage(player,
                            profile.getClassSkillTree()
                                    .getTree()
                                    .get(ClassEnums.Rogue.valueOf(args[0])));
                } else {
                    player.sendMessage(ChatColor.RED + "Unrecognized Skill");
                }
            }
        } else if (args.length == 2) {
            if (args[0] != null && args[0].equals("choose")) {
                if (EnumUtilities.isInEnum(args[1], ClassEnums.ClassType.class)) {
                    profile.setCurrentClass(ClassEnums.ClassType.valueOf(args[1]));

                    player.sendMessage(ChatColor.BLUE + "You are now a " + profile.getCurrentClass().toString() + "!");
                }
                else {
                    player.sendMessage(ChatColor.RED + "Class does not exist...");
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