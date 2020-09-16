package us.toh.leetmmo.skills.normal.mining;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import us.toh.leetmmo.LeetMMO;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.skills.SkillUtils;

import java.util.Map;
import java.util.UUID;

import static us.toh.leetmmo.skills.normal.NormalSkillEnums.MiningSkillNames.*;

public class MiningEvents implements Listener {

    LeetMMO plugin;
    private Map<UUID, PlayerProfile> globalPlayers;

    public void setGlobalPlayers(Map<UUID, PlayerProfile> globalPlayers) {
        this.globalPlayers = globalPlayers;
    }

    public void setPlugin(LeetMMO plugin) {
        this.plugin = plugin;
    }

    /**
     * Basic Mining Skill Event
     * @param event
     */
    @EventHandler
    public void usePickaxe(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            if (player.getInventory().getItemInMainHand().equals(Material.WOODEN_PICKAXE)
                || player.getInventory().getItemInMainHand().equals(Material.GOLDEN_PICKAXE)) {
                //Check if player has skill
                UUID uuid = player.getUniqueId();
                PlayerProfile playerProfile = globalPlayers.get(uuid);

                if (!SkillUtils.playerHasSkill(plugin, playerProfile, BASIC_MINING)) {
                    event.setCancelled(true);
                    player.sendMessage("Player does not have BASIC_MINING skill!");
                }
            }
            if (player.getInventory().getItemInMainHand().equals(Material.STONE_PICKAXE)) {
                //Check if player has skill
                UUID uuid = player.getUniqueId();
                PlayerProfile playerProfile = globalPlayers.get(uuid);

                if (!SkillUtils.playerHasSkill(plugin, playerProfile, HEWER)) {
                    event.setCancelled(true);
                    player.sendMessage("Player does not have HEWER skill!");
                }
            }
            if (player.getInventory().getItemInMainHand().equals(Material.IRON_PICKAXE)) {
                //Check if player has skill
                UUID uuid = player.getUniqueId();
                PlayerProfile playerProfile = globalPlayers.get(uuid);

                if (!SkillUtils.playerHasSkill(plugin, playerProfile, PROSPECTOR)) {
                    event.setCancelled(true);
                    player.sendMessage("Player does not have PROSPECTOR skill!");
                }
            }
            if (player.getInventory().getItemInMainHand().equals(Material.DIAMOND_PICKAXE)
                || player.getInventory().getItemInMainHand().equals(Material.NETHERITE_PICKAXE)) {
                //Check if player has skill
                UUID uuid = player.getUniqueId();
                PlayerProfile playerProfile = globalPlayers.get(uuid);

                if (!SkillUtils.playerHasSkill(plugin, playerProfile, GEOENGINEER)) {
                    event.setCancelled(true);
                    player.sendMessage("Player does not have GEOENGINEER skill!");
                }
            }
        }
    }
}
