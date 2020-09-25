package us.toh.leetmmo.skills.normal.fishing;

import org.bukkit.Material;
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
     * Basic Agriculture Skill Event
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
    public void onFishBite(PlayerFishEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        PlayerProfile playerProfile = globalPlayers.get(uuid);

        /*
         * Fishing Bait
         * Fishing Technique
         */
        if (event.getState() == PlayerFishEvent.State.FISHING) {
            FishHook hook = event.getHook();

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
         * Rod Care
         */
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getFishingSkillTree(), ROD_CARE)) {
                ItemStack rod = event.getPlayer().getInventory().getItemInMainHand();
                if (rod.getType().equals(Material.FISHING_ROD)) {
                    Damageable damaged;
                    damaged = (Damageable) rod.getItemMeta();

                    assert damaged != null;
                    damaged.setDamage(damaged.getDamage() + 1);

                }
            }
        }
    }

}
