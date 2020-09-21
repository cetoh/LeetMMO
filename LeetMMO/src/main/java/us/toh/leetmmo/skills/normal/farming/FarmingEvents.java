package us.toh.leetmmo.skills.normal.farming;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import us.toh.leetmmo.LeetMMO;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.skills.Skill;
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
        if (event.getBlockPlaced().getType().equals(Material.WHEAT_SEEDS) || event.getBlockPlaced().getType().equals(Material.WHEAT)) {
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
        if (event.getBlockPlaced().getType().equals(Material.CARROTS) || event.getBlockPlaced().getType().equals(Material.CARROT)) {
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
        if (event.getBlockPlaced().getType().equals(Material.PUMPKIN_SEEDS) || event.getBlockPlaced().getType().equals(Material.PUMPKIN_STEM)) {
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
        if (event.getBlockPlaced().getType().equals(Material.POTATOES) || event.getBlockPlaced().getType().equals(Material.POTATO)) {
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
        if (event.getBlockPlaced().getType().equals(Material.SUGAR_CANE)) {
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
        if (event.getBlockPlaced().getType().equals(Material.MELON_SEEDS) || event.getBlockPlaced().getType().equals(Material.MELON_STEM)) {
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
        if (event.getBlockPlaced().getType().equals(Material.COCOA_BEANS) || event.getBlockPlaced().getType().equals(Material.COCOA)) {
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
        if (event.getBlockPlaced().getType().equals(Material.BEETROOT) || event.getBlockPlaced().getType().equals(Material.BEETROOTS)) {
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
        if (event.getBlockPlaced().getType().equals(Material.BROWN_MUSHROOM) || event.getBlockPlaced().getType().equals(Material.RED_MUSHROOM)) {
            //Check if player has skill
            UUID uuid = event.getPlayer().getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, FUNGAL_FARMING)) {
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

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, HYBRIDIZATION)) {
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

            if (!SkillUtils.playerHasSkill(plugin, playerProfile, GMO_CROPS)) {
                event.setCancelled(true);
            }
        }
    }

    /**
     * Player Interact Event Skill Event
     * @param event
     */
    @EventHandler
    public void onHarvesting(PlayerHarvestBlockEvent event) {
        List<ItemStack> items = event.getItemsHarvested();

        /*
         * Weed Removal
         * Mechanized Harvesting
         */
        for (ItemStack itemStack : items) {
            if (itemStack.getType().equals(Material.WHEAT)) {
                //Check if player has skill
                Player player = event.getPlayer();
                UUID uuid = player.getUniqueId();

                PlayerProfile playerProfile = globalPlayers.get(uuid);

                //Weed Removal
                if (SkillUtils.playerHasSkill(plugin, playerProfile, WEED_REMOVAL)) {
                    if (SkillUtils.chanceCheck(playerProfile.getFarmingSkillTree().getTree().get(WEED_REMOVAL).getSkillPoints() * 5)) {
                        ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.WHEAT));
                        assert item != null;
                        item.setAmount(item.getAmount() + 1);
                    }
                }

                //Mechanized Harvesting
                if (SkillUtils.playerHasSkill(plugin, playerProfile, MECHANIZED_HARVESTING)) {
                    if (SkillUtils.chanceCheck(playerProfile.getFarmingSkillTree().getTree().get(MECHANIZED_HARVESTING).getSkillPoints() * 5)) {
                        ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.WHEAT));
                        assert item != null;
                        item.setAmount(item.getAmount() + 1);
                    }
                }
                return;
            }
        }



        /*
         * Indoor Fungiculture
         */
        for (ItemStack itemStack : items) {
            if (itemStack.getType().equals(Material.RED_MUSHROOM) || itemStack.getType().equals(Material.BROWN_MUSHROOM)) {

                //Check if player has skill
                Player player = event.getPlayer();
                UUID uuid = player.getUniqueId();

                PlayerProfile playerProfile = globalPlayers.get(uuid);

                if (SkillUtils.playerHasSkill(plugin, playerProfile, INDOOR_FUNGICULTURE)) {
                    if (SkillUtils.chanceCheck(playerProfile.getFarmingSkillTree().getTree().get(INDOOR_FUNGICULTURE).getSkillPoints() * 25)) {
                        if (itemStack.getType().equals(Material.RED_MUSHROOM)) {
                            ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.RED_MUSHROOM));
                            assert item != null;
                            item.setAmount(item.getAmount() + 1);
                        }
                        if (itemStack.getType().equals(Material.BROWN_MUSHROOM)) {
                            ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.BROWN_MUSHROOM));
                            assert item != null;
                            item.setAmount(item.getAmount() + 1);
                        }
                    }
                }
            }
        }

        /*
         * Trellis Gourd Techniques
         */
        for (ItemStack itemStack : items) {
            if (itemStack.getType().equals(Material.MELON_SLICE)) {

                //Check if player has skill
                Player player = event.getPlayer();
                UUID uuid = player.getUniqueId();

                PlayerProfile playerProfile = globalPlayers.get(uuid);

                if (SkillUtils.playerHasSkill(plugin, playerProfile, TRELLIS_GOURD_TECHNIQUES)) {
                    if (SkillUtils.chanceCheck(playerProfile.getFarmingSkillTree().getTree().get(TRELLIS_GOURD_TECHNIQUES).getSkillPoints() * 20)) {
                        ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.MELON_SLICE));
                        assert item != null;
                        item.setAmount(item.getAmount() + 1);
                    }
                }
            }
        }

        /*
         * Plantations
         */
        for (ItemStack itemStack : items) {
            if (itemStack.getType().equals(Material.SUGAR_CANE) || itemStack.getType().equals(Material.COCOA_BEANS)) {

                //Check if player has skill
                Player player = event.getPlayer();
                UUID uuid = player.getUniqueId();

                PlayerProfile playerProfile = globalPlayers.get(uuid);

                if (SkillUtils.playerHasSkill(plugin, playerProfile, PLANTATIONS)) {
                    if (SkillUtils.chanceCheck(playerProfile.getFarmingSkillTree().getTree().get(PLANTATIONS).getSkillPoints() * 25)) {
                        if (itemStack.getType().equals(Material.SUGAR_CANE)) {
                            ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.SUGAR_CANE));
                            assert item != null;
                            item.setAmount(item.getAmount() + 1);
                        }
                        if (itemStack.getType().equals(Material.COCOA_BEANS)) {
                            ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.COCOA_BEANS));
                            assert item != null;
                            item.setAmount(item.getAmount() + 1);
                        }
                    }
                }
            }
        }

        /*
         * Crop Rotation
         * Blight Protection
         */
        for (ItemStack itemStack : items) {
            if (itemStack.getType().equals(Material.WHEAT)
                    || itemStack.getType().equals(Material.CARROT)
                    || itemStack.getType().equals(Material.POTATO)
                    || itemStack.getType().equals(Material.BEETROOT)) {

                //Check if player has skill
                Player player = event.getPlayer();
                UUID uuid = player.getUniqueId();

                PlayerProfile playerProfile = globalPlayers.get(uuid);

                if (SkillUtils.playerHasSkill(plugin, playerProfile, CROP_ROTATION)) {
                    if (SkillUtils.chanceCheck(playerProfile.getFarmingSkillTree().getTree().get(CROP_ROTATION).getSkillPoints() * 10)) {
                        if (itemStack.getType().equals(Material.WHEAT)) {
                            ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.WHEAT));
                            assert item != null;
                            item.setAmount(item.getAmount() + 1);
                        }
                        if (itemStack.getType().equals(Material.CARROT)) {
                            ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.CARROT));
                            assert item != null;
                            item.setAmount(item.getAmount() + 1);
                        }
                        if (itemStack.getType().equals(Material.POTATO)) {
                            ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.POTATO));
                            assert item != null;
                            item.setAmount(item.getAmount() + 1);
                        }
                        if (itemStack.getType().equals(Material.BEETROOT)) {
                            ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.BEETROOT));
                            assert item != null;
                            item.setAmount(item.getAmount() + 1);
                        }
                    }
                }

                if (SkillUtils.playerHasSkill(plugin, playerProfile, BLIGHT_PROTECTION)) {
                    if (SkillUtils.chanceCheck(playerProfile.getFarmingSkillTree().getTree().get(BLIGHT_PROTECTION).getSkillPoints() * 10)) {
                        if (itemStack.getType().equals(Material.CARROT)) {
                            ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.CARROT));
                            assert item != null;
                            item.setAmount(item.getAmount() + 1);
                        }
                        if (itemStack.getType().equals(Material.POTATO)) {
                            ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.POTATO));
                            assert item != null;
                            item.setAmount(item.getAmount() + 1);
                        }
                        if (itemStack.getType().equals(Material.BEETROOT)) {
                            ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.BEETROOT));
                            assert item != null;
                            item.setAmount(item.getAmount() + 1);
                        }
                    }
                }
            }
        }

        /*
         * Chemical Pesticides
         */
        for (ItemStack itemStack : items) {
            if (itemStack.getType().equals(Material.NETHER_WART) || itemStack.getType().equals(Material.CHORUS_FRUIT)) {

                //Check if player has skill
                Player player = event.getPlayer();
                UUID uuid = player.getUniqueId();

                PlayerProfile playerProfile = globalPlayers.get(uuid);

                if (SkillUtils.playerHasSkill(plugin, playerProfile, CHEMICAL_PESTICIDES)) {
                    if (SkillUtils.chanceCheck(playerProfile.getFarmingSkillTree().getTree().get(CHEMICAL_PESTICIDES).getSkillPoints() * 5)) {
                        if (itemStack.getType().equals(Material.NETHER_WART)) {
                            ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.NETHER_WART));
                            assert item != null;
                            item.setAmount(item.getAmount() + 1);
                        }
                        if (itemStack.getType().equals(Material.CHORUS_FRUIT)) {
                            ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.CHORUS_FRUIT));
                            assert item != null;
                            item.setAmount(item.getAmount() + 1);
                        }
                    }
                }

                if (SkillUtils.playerHasSkill(plugin, playerProfile, TRANSENVIRONMENTAL_CULTIVATION)) {
                    if (SkillUtils.chanceCheck(playerProfile.getFarmingSkillTree().getTree().get(TRANSENVIRONMENTAL_CULTIVATION).getSkillPoints() * 10)) {
                        if (itemStack.getType().equals(Material.NETHER_WART)) {
                            ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.NETHER_WART));
                            assert item != null;
                            item.setAmount(item.getAmount() + 1);
                        }
                        if (itemStack.getType().equals(Material.CHORUS_FRUIT)) {
                            ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.CHORUS_FRUIT));
                            assert item != null;
                            item.setAmount(item.getAmount() + 1);
                        }
                    }
                }

                if (SkillUtils.playerHasSkill(plugin, playerProfile, IMPROVED_PHOTOSYNTHESIS)) {
                    if (SkillUtils.chanceCheck(playerProfile.getFarmingSkillTree().getTree().get(IMPROVED_PHOTOSYNTHESIS).getSkillPoints() * 5)) {
                        if (itemStack.getType().equals(Material.NETHER_WART)) {
                            ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.NETHER_WART));
                            assert item != null;
                            item.setAmount(item.getAmount() + 1);
                        }
                        if (itemStack.getType().equals(Material.CHORUS_FRUIT)) {
                            ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.CHORUS_FRUIT));
                            assert item != null;
                            item.setAmount(item.getAmount() + 1);
                        }
                    }
                }
            }
        }
    }
}
