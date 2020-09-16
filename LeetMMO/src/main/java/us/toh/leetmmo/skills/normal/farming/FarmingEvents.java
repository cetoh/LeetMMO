package us.toh.leetmmo.skills.normal.farming;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import us.toh.leetmmo.LeetMMO;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.skills.Skill;
import us.toh.leetmmo.skills.SkillUtils;

import java.util.Map;
import java.util.UUID;

import static us.toh.leetmmo.skills.normal.NormalSkillEnums.FarmingSkillNames.*;

public class FarmingEvents implements Listener {

    LeetMMO plugin;
    private Map<UUID, PlayerProfile> globalPlayers;

    public void setGlobalPlayers(Map<UUID, PlayerProfile> globalPlayers) {
        this.globalPlayers = globalPlayers;
    }

    public void setPlugin(LeetMMO plugin) {
        this.plugin = plugin;
    }

    /**
     * Player Interact Event Skill Event
     * @param event
     */
    @EventHandler
    public void onPlayerFarmEvent(PlayerInteractEvent event) {
        /*
         * Basic Agriculture
         */
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

        /*
         * Fertilizer
         */
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK
            && event.getPlayer().getInventory().getItemInMainHand().equals(Material.BONE_MEAL)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, FERTILIZER)) {
                event.setCancelled(true);
            }
        }
    }

    /**
     * Block Place Event Skill Event
     * @param event
     */
    @EventHandler
    public void onPlanting(BlockPlaceEvent event) {
        /*
         * Triticum Cultivation
         */
        if (event.getBlockPlaced().equals(Material.WHEAT_SEEDS) || event.getBlockPlaced().equals(Material.WHEAT)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, TRITICUM_CULTIVATION)) {
                event.setCancelled(true);
            }
        }

        /*
         * Daucus Cultivation
         */
        if (event.getBlockPlaced().equals(Material.CARROTS) || event.getBlockPlaced().equals(Material.CARROT)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, DAUCUS_CULTIVATION)) {
                event.setCancelled(true);
            }
        }

        /*
         * Cucurbita Cultivation
         */
        if (event.getBlockPlaced().equals(Material.PUMPKIN_SEEDS) || event.getBlockPlaced().equals(Material.PUMPKIN_STEM)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, CUCURBITA_CULTIVATION)) {
                event.setCancelled(true);
            }
        }

        /*
         * Tuberosem Cultivation
         */
        if (event.getBlockPlaced().equals(Material.POTATOES) || event.getBlockPlaced().equals(Material.POTATO)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, TUBEROSEM_CULTIVATION)) {
                event.setCancelled(true);
            }
        }

        /*
         * Saccharum Cultivation
         */
        if (event.getBlockPlaced().equals(Material.SUGAR_CANE)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, SACCHARUM_CULTIVATION)) {
                event.setCancelled(true);
            }
        }

        /*
         * Ianatus Cultivation
         */
        if (event.getBlockPlaced().equals(Material.MELON_SEEDS) || event.getBlockPlaced().equals(Material.MELON_STEM)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, IANATUS_CULTIVATION)) {
                event.setCancelled(true);
            }
        }

        /*
         * Cacao Cultivation
         */
        if (event.getBlockPlaced().equals(Material.COCOA_BEANS) || event.getBlockPlaced().equals(Material.COCOA)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, CACAO_CULTIVATION)) {
                event.setCancelled(true);
            }
        }

        /*
         * Vulgaris Cultivation
         */
        if (event.getBlockPlaced().equals(Material.BEETROOT) || event.getBlockPlaced().equals(Material.BEETROOTS)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, VULGARUS_CULTIVATION)) {
                event.setCancelled(true);
            }
        }

        /*
         * Fungal Farming
         */
        if (event.getBlockPlaced().equals(Material.BROWN_MUSHROOM) || event.getBlockPlaced().equals(Material.RED_MUSHROOM)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, FUNGAL_FARMING)) {
                event.setCancelled(true);
            }
        }
    }

}
