package us.toh.leetmmo.skills.normal.fishing;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import us.toh.leetmmo.LeetMMO;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.skills.SkillUtils;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static us.toh.leetmmo.skills.normal.NormalSkillEnums.FishingSkillNames.*;

public class FishingEvents implements Listener {

    LeetMMO plugin;
    private Map<UUID, PlayerProfile> globalPlayers;

    public void setGlobalPlayers(Map<UUID, PlayerProfile> globalPlayers) {
        this.globalPlayers = globalPlayers;
    }

    public void setPlugin(LeetMMO plugin) {
        this.plugin = plugin;
    }

    /**
     * Use Rod Event
     * @param event
     */
    @EventHandler
    public void onUseRod(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        PlayerProfile playerProfile = globalPlayers.get(uuid);

        if (player.getInventory().getItemInMainHand().getType().equals(Material.FISHING_ROD)) {
            //Check if player has skill
            if (!SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFishingSkillTree(), BASIC_FISHING)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEatFish(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        PlayerProfile playerProfile = globalPlayers.get(uuid);



    }
    @EventHandler
    public void onEatFish(EntityRegainHealthEvent event) {
        if (event.getEntity().getType().equals(EntityType.PLAYER)) {
            Player player = (Player) event.getEntity();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            if (event.getRegainReason().equals(EntityRegainHealthEvent.RegainReason.EATING)){
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFishingSkillTree(), FISHERMAN_DIET)) {

                    //TODO
                }
            }
        }

    }

    @EventHandler
    public void onFishing(PlayerFishEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        PlayerProfile playerProfile = globalPlayers.get(uuid);

        /*
         * Still Fishing
         */
        if (event.getState() == PlayerFishEvent.State.FISHING) {
            FishHook hook = event.getHook();

            /*
             * Fishing Bait
             * Fishing Technique
             */
            double timeDecrease = 0.0;
            if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFishingSkillTree(), FISHING_BAIT)) {
                timeDecrease += 0.05;
            }

            if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFishingSkillTree(), FISHING_TECHNIQUE)) {
                timeDecrease += 0.05;
            }

            hook.setBiteChance(hook.getBiteChance() - timeDecrease);
        }

        /*
         * Fish Caught
         */
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            /*
             * Rod Care
             */
            if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFishingSkillTree(), ROD_CARE)) {
                ItemStack rod = event.getPlayer().getInventory().getItemInMainHand();
                if (rod.getType().equals(Material.FISHING_ROD)) {
                    Damageable damaged;
                    damaged = (Damageable) rod.getItemMeta();

                    assert damaged != null;
                    damaged.setDamage(damaged.getDamage() + 1);

                }
            }

            /*
             * Current Watcher
             */
            if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFishingSkillTree(), CURRENT_WATCHER)) {
                if (SkillUtils.chanceCheck(5)
                        && Objects.requireNonNull(event.getCaught()).getType().equals(EntityType.COD)) {
                    Objects.requireNonNull(event.getCaught()).getWorld()
                            .dropItemNaturally(event.getCaught().getLocation(), new ItemStack(Material.COD))
                            .setVelocity(event.getCaught().getVelocity());
                }
            }

            /*
             * Grizzly Instincts
             */
            if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFishingSkillTree(), GRIZZLY_INSTINCTS)) {
                if (SkillUtils.chanceCheck(5)
                        && Objects.requireNonNull(event.getCaught()).getType().equals(EntityType.SALMON)) {
                    Objects.requireNonNull(event.getCaught()).getWorld()
                            .dropItemNaturally(event.getCaught().getLocation(), new ItemStack(Material.SALMON))
                            .setVelocity(event.getCaught().getVelocity());
                }
            }

            /*
             * Oceanography
             */
            if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFishingSkillTree(), OCEANOGRAPHY)) {
                if (SkillUtils.chanceCheck(5)
                        && Objects.requireNonNull(event.getCaught()).getType().equals(EntityType.TROPICAL_FISH)) {
                    Objects.requireNonNull(event.getCaught()).getWorld()
                            .dropItemNaturally(event.getCaught().getLocation(), new ItemStack(Material.TROPICAL_FISH))
                            .setVelocity(event.getCaught().getVelocity());
                }
            }

            /*
             * Deadliest Catch
             */
            if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFishingSkillTree(), DEADLIEST_CATCH)) {
                if (SkillUtils.chanceCheck(5)
                        && Objects.requireNonNull(event.getCaught()).getType().equals(EntityType.PUFFERFISH)) {
                    Objects.requireNonNull(event.getCaught()).getWorld()
                            .dropItemNaturally(event.getCaught().getLocation(), new ItemStack(Material.PUFFERFISH))
                            .setVelocity(event.getCaught().getVelocity());
                }
            }

            /*
             * Pirate Lore
             */
            if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFishingSkillTree(), PIRATE_LORE)) {
                if (SkillUtils.chanceCheck(1)) {
                    Objects.requireNonNull(event.getCaught()).getWorld()
                            .dropItemNaturally(event.getCaught().getLocation(), new ItemStack(Material.GOLD_INGOT))
                            .setVelocity(event.getCaught().getVelocity());
                }
            }

            /*
             * Pirate Lore
             */
            if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFishingSkillTree(), FISHERMAN_LUCK)) {
                if (SkillUtils.chanceCheck(5)) {
                    Objects.requireNonNull(event.getCaught()).getWorld()
                            .dropItemNaturally(event.getCaught().getLocation(), new ItemStack(Material.IRON_INGOT))
                            .setVelocity(event.getCaught().getVelocity());
                }
            }

            /*
             * Fisherman
             */
            if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFishingSkillTree(), FISHERMAN)) {
                if (SkillUtils.chanceCheck(1)) {
                    Objects.requireNonNull(event.getCaught()).getWorld()
                            .dropItemNaturally(event.getCaught().getLocation(), new ItemStack(Material.GOLD_NUGGET))
                            .setVelocity(event.getCaught().getVelocity());
                }
            }
        }
    }

}
