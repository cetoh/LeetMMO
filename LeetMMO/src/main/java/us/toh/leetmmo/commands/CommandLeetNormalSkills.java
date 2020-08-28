package us.toh.leetmmo.commands;

import com.google.common.collect.ImmutableList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.gui.advancements.NormalSkillTreeGUI;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CommandLeetNormalSkills implements CommandExecutor {

    private Map<UUID, PlayerProfile> globalPlayers;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        PlayerProfile profile = globalPlayers.get(player.getUniqueId());

        NormalSkillTreeGUI normalSkillTreeGUI = new NormalSkillTreeGUI();
        normalSkillTreeGUI.openInventory(player);
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return ImmutableList.of();
    }

    public void setGlobalPlayers(Map<UUID, PlayerProfile> globalPlayers) {
        this.globalPlayers = globalPlayers;
    }
}