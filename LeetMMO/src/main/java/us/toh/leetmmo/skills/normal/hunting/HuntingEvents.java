package us.toh.leetmmo.skills.normal.hunting;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import us.toh.leetmmo.LeetMMO;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.skills.SkillUtils;

import java.util.Map;
import java.util.UUID;

import static us.toh.leetmmo.skills.normal.NormalSkillEnums.HuntingSkillNames.*;

public class HuntingEvents implements Listener {

    LeetMMO plugin;
    private Map<UUID, PlayerProfile> globalPlayers;

    public void setGlobalPlayers(Map<UUID, PlayerProfile> globalPlayers) {
        this.globalPlayers = globalPlayers;
    }

    public void setPlugin(LeetMMO plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerKillMob(EntityDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) {
            Player player = event.getEntity().getKiller().getPlayer();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            //Chicken Catcher
            if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), CHICKEN_CATCHER)) {
                if (event.getEntity().getType().equals(EntityType.CHICKEN)) {
                    event.getDrops().add(new ItemStack(Material.FEATHER, 1));
                }
            }
        }

    }

}
