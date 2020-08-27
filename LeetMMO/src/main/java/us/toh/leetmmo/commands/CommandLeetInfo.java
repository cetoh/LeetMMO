package us.toh.leetmmo.commands;

import com.google.common.collect.ImmutableList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.toh.leetmmo.datatypes.player.PlayerProfile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CommandLeetInfo implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        player.sendMessage(createMessage());
        return true;
    }

    private String createMessage() {
        String msg = ChatColor.GOLD  + "This is LeetMMO a new take on Minecraft MMO.\n" +
                ChatColor.GOLD + "This is a project made by two brothers in their free time.\n" +
                ChatColor.GOLD + "We hope you have fun and we hope to continue adding cool features.";

        return msg;
    }


    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return ImmutableList.of();
    }

}
