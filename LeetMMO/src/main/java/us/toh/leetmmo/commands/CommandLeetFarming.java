package us.toh.leetmmo.commands;

import com.google.common.collect.ImmutableList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import us.toh.leetmmo.LeetMMO;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.skills.Skill;
import us.toh.leetmmo.skills.SkillUtils;
import us.toh.leetmmo.skills.normal.NormalSkillEnums;
import us.toh.leetmmo.utils.EnumUtilities;
import us.toh.leetmmo.utils.StringUtilities;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandLeetFarming implements CommandExecutor {

    private Player player;
    private Map<UUID, PlayerProfile> globalPlayers;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        player = (Player) sender;

        PlayerProfile profile = globalPlayers.get(player.getUniqueId());
        HashMap<Enum, Skill> skillTree = profile.getFarmingSkillTree().getTree();

        //If only base command sent, display available skill points
        if (args.length == 0) {
            player.sendMessage("Available Normal Skill Points: " + ChatColor.GOLD + profile.getnSPPool().getNumPoints() + ChatColor.GOLD + " pts");

            CommandUtils.displayWholeTree(player, profile, skillTree);
        } else {
            //Check if first argument is a valid Farming skill. If it is display the skill info if no other command follows.
            if (args.length == 1 && args[0] != null && !args[0].equals("")) {
                if (EnumUtilities.isInEnum(args[0], NormalSkillEnums.FarmingSkillNames.class)) {
                    CommandUtils.sendSkillMessage(player,
                            profile.getFarmingSkillTree()
                                    .getTree()
                                    .get(NormalSkillEnums.FarmingSkillNames.valueOf(args[0])));
                } else {
                    player.sendMessage(ChatColor.RED + "Unrecognized Skill");
                }
            }
            //Check if add skill point command was initiated
            else if (args.length == 3 && args[0] != null && !args[0].equals("")
                    && !args[1].isEmpty() && !args[2].isEmpty() ) {

                if (args[1].equals("add")) {
                    if (!EnumUtilities.isInEnum(args[0], NormalSkillEnums.FarmingSkillNames.class)) {
                        player.sendMessage(ChatColor.RED + "Unrecognized Skill");
                    } else {
                        if (StringUtilities.isInteger(args[2])) {
                            if (SkillUtils.hasPrerequesiteSkill(
                                    LeetMMO.plugin,
                                    profile,
                                    profile.getFarmingSkillTree(),
                                    NormalSkillEnums.isFarmingSkillEnum(args[0].toUpperCase().replace(' ', '_'))
                            )) {
                                //Add point(s) to skill
                                CommandUtils.addSkillPointToSkill(player,
                                        profile,
                                        NormalSkillEnums.isFarmingSkillEnum(args[0].toUpperCase().replace(' ', '_')),
                                        Integer.parseInt(args[2]),
                                        skillTree);
                            }
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "Must be a valid integer. (e.g. leet[skilltreename] [skillName] add INTEGER)");
                        }
                    }
                }
                else if (args[1].equals("remove")) {
                    if (!EnumUtilities.isInEnum(args[0], NormalSkillEnums.FarmingSkillNames.class)) {
                        player.sendMessage(ChatColor.RED + "Unrecognized Skill");
                    } else {
                        if (StringUtilities.isInteger(args[2])) {

                            if (SkillUtils.playerHasSkill(
                                    LeetMMO.plugin,
                                    profile,
                                    profile.getFarmingSkillTree(),
                                    NormalSkillEnums.isFarmingSkillEnum(args[0].toUpperCase().replace(' ', '_'))
                            )) {
                                //Remove point(s) from skill
                                CommandUtils.removeSkillPointToSkill(player,
                                        profile,
                                        NormalSkillEnums.isFarmingSkillEnum(args[0].toUpperCase().replace(' ', '_')),
                                        Integer.parseInt(args[2]),
                                        skillTree);
                            } else {
                                player.sendMessage(ChatColor.RED + "Player has no points in this skill!");
                            }

                        }
                        else {
                            player.sendMessage(ChatColor.RED + "Must be a valid integer. (e.g. leet[skilltreename] [skillName] remove INTEGER)");
                        }
                    }
                }
                else {
                    player.sendMessage(ChatColor.RED + "Invalid Command");
                }
            }
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        switch(args.length) {
            case 1:
                //create a new string list for tab completion
                completions.addAll(Stream
                        .of(NormalSkillEnums.FarmingSkillNames.values())
                        .map(NormalSkillEnums.FarmingSkillNames::name).collect(Collectors.toList()));
                return StringUtil.copyPartialMatches(args[0], completions, new ArrayList<>());
            case 2:
                completions.add("add");
                completions.add("remove");
                return StringUtil.copyPartialMatches(args[1], completions, new ArrayList<>());
            case 3:
                completions.add("1");
                completions.add("2");
                completions.add("3");
                completions.add("4");
                completions.add("5");
                return  StringUtil.copyPartialMatches(args[2], completions, new ArrayList<>());
        }

        return ImmutableList.of();
    }

    public void setGlobalPlayers(Map<UUID, PlayerProfile> globalPlayers) {
        this.globalPlayers = globalPlayers;
    }
}
