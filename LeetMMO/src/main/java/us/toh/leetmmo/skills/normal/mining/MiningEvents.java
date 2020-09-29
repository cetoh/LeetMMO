package us.toh.leetmmo.skills.normal.mining;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import us.toh.leetmmo.LeetMMO;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.skills.SkillUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static us.toh.leetmmo.skills.normal.NormalSkillEnums.FarmingSkillNames.*;
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
    public void usePickaxe(BlockBreakEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        PlayerProfile playerProfile = globalPlayers.get(uuid);

        if (player.getInventory().getItemInMainHand().getType().equals(Material.WOODEN_PICKAXE)
                || player.getInventory().getItemInMainHand().getType().equals(Material.GOLDEN_PICKAXE)) {
            //Check if player has skill
            if (!SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getMiningSkillTree(), BASIC_MINING)) {
                event.setCancelled(true);
            }
        }
        if (player.getInventory().getItemInMainHand().getType().equals(Material.STONE_PICKAXE)) {
            //Check if player has skill
            if (!SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getMiningSkillTree(), HEWER)) {
                event.setCancelled(true);
            }
        }
        if (player.getInventory().getItemInMainHand().getType().equals(Material.IRON_PICKAXE)) {
            //Check if player has skill
            if (!SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getMiningSkillTree(), PROSPECTOR)) {
                event.setCancelled(true);
            }
        }
        if (player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_PICKAXE)
                || player.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_PICKAXE)) {
            //Check if player has skill
            if (!SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getMiningSkillTree(), GEOENGINEER)) {
                event.setCancelled(true);
            }
        }
    }

    /**
     * Basic Mining Skill Event
     * @param event
     */
    @EventHandler
    public void mineOre(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material blockType = block.getType();
        List<ItemStack> itemsList = (List<ItemStack>) block.getDrops();
        Location location = block.getLocation();

        /*
         * COAL double drop chances:
         *
         * - LIGNITE_EXTRACTION
         * - BITUMINOUS_EXTRACTION
         * - ANTHRACITE_EXTRACTION
         */
        if (blockType == Material.COAL_ORE) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            int chance = 0;
            // LIGNITE_EXTRACTION
            chance += playerProfile.getMiningSkillTree().getTree().get(LIGNITE_EXTRACTION).getSkillPoints() * 5;
            // BITUMINOUS_EXTRACTION
            chance +=  playerProfile.getMiningSkillTree().getTree().get(BITUMINOUS_EXTRACTION).getSkillPoints() * 10;
            // ANTHRACITE_EXTRACTION
            chance +=  playerProfile.getMiningSkillTree().getTree().get(ANTHRACITE_EXTRACTION).getSkillPoints() * 15;

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
         * IRON_ORE double drop chances:
         *
         * - COMMINUTION
         * - GRAVITY_SEPARATION
         * - MAGNETITE_FROTH_FLOTATION
         */
        if (blockType == Material.IRON_ORE) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            int chance = 0;
            // COMMINUTION
            chance += playerProfile.getMiningSkillTree().getTree().get(COMMINUTION).getSkillPoints() * 5;
            // GRAVITY_SEPARATION
            chance +=  playerProfile.getMiningSkillTree().getTree().get(GRAVITY_SEPARATION).getSkillPoints() * 10;
            // MAGNETITE_FROTH_FLOTATION
            chance +=  playerProfile.getMiningSkillTree().getTree().get(MAGNETITE_FROTH_FLOTATION).getSkillPoints() * 15;

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
         * GOLD_ORE double drop chances:
         *
         * - HYDRAULIC_MINING
         * - LEACHING
         */
        if (blockType == Material.GOLD_ORE) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            int chance = 0;
            // HYDRAULIC_MINING
            chance += playerProfile.getMiningSkillTree().getTree().get(HYDRAULIC_MINING).getSkillPoints() * 15;
            // LEACHING
            chance +=  playerProfile.getMiningSkillTree().getTree().get(LEACHING).getSkillPoints() * 20;

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
         * REDSTONE double drop chances:
         *
         * - POWER_MINING
         * - ELECTROMAGNETIC_RADIATION
         */
        if (blockType == Material.REDSTONE_ORE) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            int chance = 0;
            // POWER_MINING
            chance += playerProfile.getMiningSkillTree().getTree().get(POWER_MINING).getSkillPoints() * 15;
            // ELECTROMAGNETIC_RADIATION
            chance +=  playerProfile.getMiningSkillTree().getTree().get(ELECROMAGNETIC_RADIATION).getSkillPoints() * 20;

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
         * DIAMOND double drop chances:
         *
         * - XRAY_FLOURESCENCE
         * - CUTTING_EFFICIENCY
         */
        if (blockType == Material.DIAMOND_ORE) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            int chance = 0;
            // XRAY_FLOURESCENCE
            chance += playerProfile.getMiningSkillTree().getTree().get(XRAY_FLOURESCENCE).getSkillPoints() * 15;
            // CUTTING_EFFICIENCY
            chance +=  playerProfile.getMiningSkillTree().getTree().get(CUTTING_EFFICIENCY).getSkillPoints() * 20;

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
         * LAPIS_LAZULI double drop chances:
         *
         * - LAZURITE_EXTRACTION
         */
        if (blockType == Material.LAPIS_ORE) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            int chance = 0;
            // LAZURITE_EXTRACTION
            chance += playerProfile.getMiningSkillTree().getTree().get(LAZURITE_EXTRACTION).getSkillPoints() * 25;

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
         * EMERALD double drop chances:
         *
         * - POSTLAPIDARY_OILING
         */
        if (blockType == Material.EMERALD_ORE) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            int chance = 0;
            // POSTLAPIDARY_OILING
            chance += playerProfile.getMiningSkillTree().getTree().get(POSTLAPIDARY_OILING).getSkillPoints() * 25;

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
         * GLOWSTONE_DUST double drop chances:
         *
         * - THERMOLUMINESCENCE_DATING
         */
        if (blockType == Material.GLOWSTONE) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            int chance = 0;
            // THERMOLUMINESCENCE_DATING
            chance += playerProfile.getMiningSkillTree().getTree().get(THERMOLUMINESCENCE_DATING).getSkillPoints() * 25;

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
         * QUARTZ double drop chances:
         *
         * - SILICONE_EXTRACTION
         */
        if (blockType == Material.NETHER_QUARTZ_ORE) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            int chance = 0;
            // SILICONE_EXTRACTION
            chance += playerProfile.getMiningSkillTree().getTree().get(SILICONE_EXTRACTION).getSkillPoints() * 25;

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
         * GOLD_ORE double drop chances:
         *
         * - FOOLS_GOLD
         */
        if (blockType == Material.NETHER_GOLD_ORE) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            int chance = 0;
            // FOOLS_GOLD
            chance += playerProfile.getMiningSkillTree().getTree().get(FOOLS_GOLD).getSkillPoints() * 25;

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
         * NETHERITE_SCRAP double drop chances:
         *
         * - MANTLE_DRILLING
         */
        if (blockType == Material.ANCIENT_DEBRIS) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            int chance = 0;
            // MANTLE_DRILLING
            chance += playerProfile.getMiningSkillTree().getTree().get(MANTLE_DRILLING).getSkillPoints() * 25;

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
         * PRISMARINE_CRYSTAL multiplier:
         *
         * - TEMPLE_DESECRATOR
         */
        if (blockType == Material.SEA_LANTERN) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);


            // TEMPLE_DESECRATOR
            int power = playerProfile.getMiningSkillTree().getTree().get(TEMPLE_DESECRATOR).getSkillPoints();

            event.setDropItems(false);
            for (ItemStack item : itemsList) {
                ItemStack bonusTotal = new ItemStack(item.getType(), item.getAmount() * (int)Math.pow(2, power));
                location.getWorld().dropItemNaturally(location, bonusTotal);
            }

            return;
        }

    }
}
