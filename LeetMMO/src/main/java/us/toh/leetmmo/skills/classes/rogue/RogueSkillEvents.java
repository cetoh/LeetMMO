package us.toh.leetmmo.skills.classes.rogue;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;
import us.toh.leetmmo.LeetMMO;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.skills.SkillUtils;
import us.toh.leetmmo.skills.classes.ClassEnums;

import java.util.Map;
import java.util.UUID;

public class RogueSkillEvents implements Listener {

    LeetMMO plugin;
    private Map<UUID, PlayerProfile> globalPlayers;

    public void setGlobalPlayers(Map<UUID, PlayerProfile> globalPlayers) {
        this.globalPlayers = globalPlayers;
    }

    public void setPlugin(LeetMMO plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player) {
            PlayerProfile attacker = globalPlayers.get(event.getDamager().getUniqueId());

            if (attacker.getCurrentClass().equals(ClassEnums.ClassType.ROGUE)) {
                if (SkillUtils.playerHasSkill(plugin, attacker, attacker.getClassSkillTree(), ClassEnums.Rogue.BACKSTAB)) {
                    if (entityBehindEntity(event.getDamager(), event.getEntity())) {
                        event.setDamage(event.getDamage() * 1.5);
                    }
                }
            }
        }
    }

    /**
     * Checks if first entity is behind second entity
     * @param entity
     * @param entity2
     * @return true if entity is behind entity2
     */
    private static boolean entityBehindEntity(Entity entity, Entity entity2) {
        Double yaw = 2*Math.PI*entity2.getLocation().getYaw()/180;
        Vector v = entity.getLocation().toVector().subtract(entity2.getLocation().toVector());
        Vector r = new Vector(Math.sin(yaw), 0, Math.cos(yaw));
        float theta = r.angle(v);
        if (Math.PI/2 < theta && theta < 3 * Math.PI/2) {
            return true;
        }
        return false;
    }
}
