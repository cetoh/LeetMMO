package us.toh.leetmmo.skills.normal.hunting;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import us.toh.leetmmo.LeetMMO;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.skills.SkillUtils;

import java.util.Map;
import java.util.UUID;

import static us.toh.leetmmo.skills.normal.NormalSkillEnums.FarmingSkillNames.BASIC_AGRICULTURE;
import static us.toh.leetmmo.skills.normal.NormalSkillEnums.FarmingSkillNames.TRITICUM_CULTIVATION;

public class HuntingEvents implements Listener {

    LeetMMO plugin;
    private Map<UUID, PlayerProfile> globalPlayers;

    public void setGlobalPlayers(Map<UUID, PlayerProfile> globalPlayers) {
        this.globalPlayers = globalPlayers;
    }

    public void setPlugin(LeetMMO plugin) {
        this.plugin = plugin;
    }

    /**
     * Basic Agriculture Skill Event
     * @param event
     */
    @EventHandler
    public void onTillSoil(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK
            && (event.getPlayer().getInventory().getItemInMainHand().equals(Material.WOODEN_HOE)
                || event.getPlayer().getInventory().getItemInMainHand().equals(Material.IRON_HOE)
                || event.getPlayer().getInventory().getItemInMainHand().equals(Material.STONE_HOE)
                || event.getPlayer().getInventory().getItemInMainHand().equals(Material.DIAMOND_HOE)
                || event.getPlayer().getInventory().getItemInMainHand().equals(Material.GOLDEN_HOE)
                || event.getPlayer().getInventory().getItemInMainHand().equals(Material.NETHERITE_HOE))) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, BASIC_AGRICULTURE)) {
                event.setCancelled(true);
            }
        }
    }

    /**
     * Triticum Cultivation Skill Event
     * @param event
     */
    @EventHandler
    public void onWheatPlant(BlockPlaceEvent event) {
        if (event.getBlockPlaced().equals(Material.WHEAT_SEEDS) || event.getBlockPlaced().equals(Material.WHEAT)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, TRITICUM_CULTIVATION)) {
                event.setCancelled(true);
            }
        }
    }

    /**
     * Daucus Cultivation Skill Event
     * @param event
     */
    @EventHandler
    public void onCarrotPlant(BlockPlaceEvent event) {

    }
}
