package us.toh.leetmmo.skills.normal.farming;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import us.toh.leetmmo.LeetMMO;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.skills.SkillUtils;

import java.util.List;
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
            && (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.WOODEN_HOE)
                || event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.IRON_HOE)
                || event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.STONE_HOE)
                || event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_HOE)
                || event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.GOLDEN_HOE)
                || event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_HOE))) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFarmingSkillTree(),  BASIC_AGRICULTURE)) {
                event.setCancelled(true);
            }
        }

        /*
         * Fertilizer
         */
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK
            && event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.BONE_MEAL)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFarmingSkillTree(),  FERTILIZER)) {
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
        if (event.getBlockPlaced().getType().equals(Material.WHEAT_SEEDS) || event.getBlockPlaced().getType().equals(Material.WHEAT)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFarmingSkillTree(),  TRITICUM_CULTIVATION)) {
                event.setCancelled(true);
            }
        }

        /*
         * Daucus Cultivation
         */
        if (event.getBlockPlaced().getType().equals(Material.CARROTS) || event.getBlockPlaced().getType().equals(Material.CARROT)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFarmingSkillTree(),  DAUCUS_CULTIVATION)) {
                event.setCancelled(true);
            }
        }

        /*
         * Cucurbita Cultivation
         */
        if (event.getBlockPlaced().getType().equals(Material.PUMPKIN_SEEDS) || event.getBlockPlaced().getType().equals(Material.PUMPKIN_STEM)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFarmingSkillTree(),  CUCURBITA_CULTIVATION)) {
                event.setCancelled(true);
            }
        }

        /*
         * Tuberosem Cultivation
         */
        if (event.getBlockPlaced().getType().equals(Material.POTATOES) || event.getBlockPlaced().getType().equals(Material.POTATO)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFarmingSkillTree(),  TUBEROSEM_CULTIVATION)) {
                event.setCancelled(true);
            }
        }

        /*
         * Saccharum Cultivation
         */
        if (event.getBlockPlaced().getType().equals(Material.SUGAR_CANE)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFarmingSkillTree(),  SACCHARUM_CULTIVATION)) {
                event.setCancelled(true);
            }
        }

        /*
         * Ianatus Cultivation
         */
        if (event.getBlockPlaced().getType().equals(Material.MELON_SEEDS) || event.getBlockPlaced().getType().equals(Material.MELON_STEM)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFarmingSkillTree(),  IANATUS_CULTIVATION)) {
                event.setCancelled(true);
            }
        }

        /*
         * Cacao Cultivation
         */
        if (event.getBlockPlaced().getType().equals(Material.COCOA_BEANS) || event.getBlockPlaced().getType().equals(Material.COCOA)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFarmingSkillTree(),  CACAO_CULTIVATION)) {
                event.setCancelled(true);
            }
        }

        /*
         * Vulgaris Cultivation
         */
        if (event.getBlockPlaced().getType().equals(Material.BEETROOT) || event.getBlockPlaced().getType().equals(Material.BEETROOTS)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFarmingSkillTree(),  VULGARUS_CULTIVATION)) {
                event.setCancelled(true);
            }
        }

        /*
         * Fungal Farming
         */
        if (event.getBlockPlaced().getType().equals(Material.BROWN_MUSHROOM) || event.getBlockPlaced().getType().equals(Material.RED_MUSHROOM)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFarmingSkillTree(),  FUNGAL_FARMING)) {
                event.setCancelled(true);
            }
        }

        /*
         * Hybridization
         */
        if (event.getBlockPlaced().getType().equals(Material.NETHER_WART)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFarmingSkillTree(),  HYBRIDIZATION)) {
                event.setCancelled(true);
            }
        }

        /*
         * Hybridization
         */
        if (event.getBlockPlaced().getType().equals(Material.CHORUS_FRUIT)
                || event.getBlockPlaced().getType().equals(Material.CHORUS_FLOWER)
                || event.getBlockPlaced().getType().equals(Material.CHORUS_PLANT) ) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFarmingSkillTree(),  GMO_CROPS)) {
                event.setCancelled(true);
            }
        }
    }

    /**
     * Player Interact Event Skill Event
     * @param event
     */
    @EventHandler
    public void onHarvesting(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material blockType = block.getType();
        List<ItemStack> itemsList = (List<ItemStack>) block.getDrops();
        Location location = block.getLocation();

        /*
         * WHEAT, WHEAT_SEED double drop chances:
         *
         * - WEED_REMOVAL
         * - MECHANIZED_HARVESTING
         * - CROP_ROTATION
         */
        if (blockType == Material.WHEAT) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            int chance = 0;
            // WEED_REMOVAL
            chance += playerProfile.getFarmingSkillTree().getTree().get(WEED_REMOVAL).getSkillPoints() * 5;
            // MECHANIZED_HARVESTING
            chance +=  playerProfile.getFarmingSkillTree().getTree().get(MECHANIZED_HARVESTING).getSkillPoints() * 5;
            // CROP_ROTATION
            chance +=  playerProfile.getFarmingSkillTree().getTree().get(CROP_ROTATION).getSkillPoints() * 10;

            if (SkillUtils.chanceCheck(chance)) {
                event.setDropItems(false);
                for (ItemStack item : itemsList) {
                    ItemStack bonusTotal = new ItemStack(item.getType(), item.getAmount() * 2);
                    location.getWorld().dropItemNaturally(location, bonusTotal);
                }
            }
            return;
        }


        /*
         * RED_MUSHROOM, RED_MUSHROOM_BLOCK, BROWN_MUSHROOM, BROWN_MUSHROOM_BLOCK double drop chances:
         *
         * - INDOOR_FUNGICULTURE
         */
        if (blockType == Material.RED_MUSHROOM
                || blockType == Material.RED_MUSHROOM_BLOCK
                || blockType == Material.BROWN_MUSHROOM
                || blockType == Material.BROWN_MUSHROOM_BLOCK) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            int chance = 0;
            // INDOOR_FUNGICULTURE
            chance += playerProfile.getFarmingSkillTree().getTree().get(INDOOR_FUNGICULTURE).getSkillPoints() * 25;

            if (SkillUtils.chanceCheck(chance)) {
                event.setDropItems(false);
                for (ItemStack item : itemsList) {
                    ItemStack bonusTotal = new ItemStack(item.getType(), item.getAmount() * 2);
                    location.getWorld().dropItemNaturally(location, bonusTotal);
                }
            }
            return;
        }


        /*
         * MELON_SLICE, MELON_SEED double drop chances:
         *
         * - TRELLIS_GOURD_TECHNIQUES
         */
        if (blockType == Material.MELON || blockType == Material.MELON_STEM) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            int chance = 0;
            // TRELLIS_GOURD_TECHNIQUES
            chance += playerProfile.getFarmingSkillTree().getTree().get(TRELLIS_GOURD_TECHNIQUES).getSkillPoints() * 20;

            if (SkillUtils.chanceCheck(chance)) {
                event.setDropItems(false);
                for (ItemStack item : itemsList) {
                    ItemStack bonusTotal = new ItemStack(item.getType(), item.getAmount() * 2);
                    location.getWorld().dropItemNaturally(location, bonusTotal);
                }
            }
            return;
        }

        /*
         * SUGAR_CANE, COCOA_BEANS double drop chances:
         *
         * - PLANTATIONS
         */
        if (blockType == Material.SUGAR_CANE || blockType == Material.COCOA_BEANS) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            int chance = 0;
            // PLANTATIONS
            chance += playerProfile.getFarmingSkillTree().getTree().get(PLANTATIONS).getSkillPoints() * 25;

            if (SkillUtils.chanceCheck(chance)) {
                event.setDropItems(false);
                for (ItemStack item : itemsList) {
                    ItemStack bonusTotal = new ItemStack(item.getType(), item.getAmount() * 2);
                    location.getWorld().dropItemNaturally(location, bonusTotal);
                }
            }
            return;
        }

        /*
         * CARROT, POTATO, BEETROOT double drop chances:
         *
         * - CROP_ROTATION
         * - BLIGHT_PROTECTION
         */
        if (blockType == Material.CARROT
                || blockType == Material.POTATO
                || blockType == Material.BEETROOT) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            int chance = 0;
            // CROP_ROTATION
            chance += playerProfile.getFarmingSkillTree().getTree().get(CROP_ROTATION).getSkillPoints() * 10;
            // BLIGHT_PROTECTION
            chance +=  playerProfile.getFarmingSkillTree().getTree().get(BLIGHT_PROTECTION).getSkillPoints() * 10;

            if (SkillUtils.chanceCheck(chance)) {
                event.setDropItems(false);
                for (ItemStack item : itemsList) {
                    ItemStack bonusTotal = new ItemStack(item.getType(), item.getAmount() * 2);
                    location.getWorld().dropItemNaturally(location, bonusTotal);
                }
            }
            return;
        }

        /*
         * NETHER_WART, CHORUS_PLANT, CHORUS_FLOWER double drop chance:
         *
         * - CHEMICAL_PESTICIDES
         * - TRANSENVIRONMENTAL_CULTIVATION
         * - IMPROVED_PHOTOSYNTHESIS
         */
        if (blockType == Material.NETHER_WART
                || blockType == Material.CHORUS_PLANT
                || blockType == Material.CHORUS_FLOWER) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            int chance = 0;
            // CHEMICAL_PESTICIDES
            chance += playerProfile.getFarmingSkillTree().getTree().get(CHEMICAL_PESTICIDES).getSkillPoints() * 5;
            // TRANSENVIRONMENTAL_CULTIVATION
            chance +=  playerProfile.getFarmingSkillTree().getTree().get(TRANSENVIRONMENTAL_CULTIVATION).getSkillPoints() * 10;
            // IMPROVED_PHOTOSYNTHESIS
            chance +=  playerProfile.getFarmingSkillTree().getTree().get(IMPROVED_PHOTOSYNTHESIS).getSkillPoints() * 5;

            if (SkillUtils.chanceCheck(chance)) {
                event.setDropItems(false);
                for (ItemStack item : itemsList) {
                    ItemStack bonusTotal = new ItemStack(item.getType(), item.getAmount() * 2);
                    location.getWorld().dropItemNaturally(location, bonusTotal);
                }
            }
            return;
        }
    }
}
