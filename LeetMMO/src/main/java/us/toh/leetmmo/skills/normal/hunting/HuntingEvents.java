package us.toh.leetmmo.skills.normal.hunting;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
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

            //Bone Drop Chances
            int boneDrop = 0;
            if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), WILDERNESS_RESOURCEFULNESS)) {
                boneDrop += playerProfile.getHuntingSkillTree().getTree().get(WILDERNESS_RESOURCEFULNESS).getSkillPoints() * 5;
            }
            if (SkillUtils.chanceCheck(boneDrop)) {
                event.getDrops().add(new ItemStack(Material.BONE, 1));
            }

            //Chicken Drop Chances
            if (event.getEntity().getType().equals(EntityType.CHICKEN)) {

                int featherDrop = 0;
                int chickenDrop = 0;
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), CHICKEN_CATCHER)) {
                    featherDrop += playerProfile.getHuntingSkillTree().getTree().get(CHICKEN_CATCHER).getSkillPoints() * 5;
                }
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), GAME_CLEANING)) {
                    chickenDrop += playerProfile.getHuntingSkillTree().getTree().get(GAME_CLEANING).getSkillPoints() * 5;
                }

                if (SkillUtils.chanceCheck(featherDrop)) {
                    event.getDrops().add(new ItemStack(Material.FEATHER, 1));
                }

                if (SkillUtils.chanceCheck(chickenDrop)) {
                    event.getDrops().add(new ItemStack(Material.CHICKEN, 1));
                }
            }


            //Cow Drops
            if (event.getEntity().getType().equals(EntityType.COW)) {

                int beefDrop = 0;
                int leatherDrop = 0;
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), EXPERT_SKINNING)) {
                    leatherDrop += playerProfile.getHuntingSkillTree().getTree().get(EXPERT_SKINNING).getSkillPoints() * 5;
                }
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), GAME_CLEANING)) {
                    beefDrop += playerProfile.getHuntingSkillTree().getTree().get(GAME_CLEANING).getSkillPoints() * 5;
                }

                if (SkillUtils.chanceCheck(beefDrop)) {
                    event.getDrops().add(new ItemStack(Material.BEEF, 1));
                }

                if (SkillUtils.chanceCheck(leatherDrop)) {
                    event.getDrops().add(new ItemStack(Material.LEATHER, 1));
                }
            }

            //Sheep Drops
            if (event.getEntity().getType().equals(EntityType.SHEEP)) {

                int muttonDrop = 0;
                int woolDrop = 0;
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), EXPERT_SKINNING)) {
                    woolDrop += playerProfile.getHuntingSkillTree().getTree().get(EXPERT_SKINNING).getSkillPoints() * 5;
                }
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), GAME_CLEANING)) {
                    muttonDrop += playerProfile.getHuntingSkillTree().getTree().get(GAME_CLEANING).getSkillPoints() * 5;
                }

                if (SkillUtils.chanceCheck(muttonDrop)) {
                    event.getDrops().add(new ItemStack(Material.MUTTON, 1));
                }

                if (SkillUtils.chanceCheck(woolDrop)) {
                    event.getDrops().add(new ItemStack(Material.WHITE_WOOL, 1));
                }
            }

            //Pork Drops
            if (event.getEntity().getType().equals(EntityType.PIG)) {

                int porkDrop = 0;

                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), GAME_CLEANING)) {
                    porkDrop += playerProfile.getHuntingSkillTree().getTree().get(GAME_CLEANING).getSkillPoints() * 5;
                }

                if (SkillUtils.chanceCheck(porkDrop)) {
                    event.getDrops().add(new ItemStack(Material.PORKCHOP, 1));
                }
            }

            //Rabbit Drops
            if (event.getEntity().getType().equals(EntityType.RABBIT)) {

                int rabbitHideDrop = 0;
                int rabbitDrop = 0;
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), EXPERT_SKINNING)) {
                    rabbitHideDrop += playerProfile.getHuntingSkillTree().getTree().get(EXPERT_SKINNING).getSkillPoints() * 5;
                }
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), SNARES)) {
                    rabbitHideDrop += playerProfile.getHuntingSkillTree().getTree().get(SNARES).getSkillPoints() * 5;
                }
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), GAME_CLEANING)) {
                    rabbitDrop += playerProfile.getHuntingSkillTree().getTree().get(GAME_CLEANING).getSkillPoints() * 5;
                }

                if (SkillUtils.chanceCheck(rabbitDrop)) {
                    event.getDrops().add(new ItemStack(Material.RABBIT, 1));
                }

                if (SkillUtils.chanceCheck(rabbitHideDrop)) {
                    event.getDrops().add(new ItemStack(Material.RABBIT_HIDE, 1));
                }
            }
        }

    }

    @EventHandler
    public void onPlayerCombatMobEvent(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player){
            Player player = (Player) event.getDamager();
            UUID uuid = player.getUniqueId();
            PlayerProfile playerProfile = globalPlayers.get(uuid);

            //Get Entity Type
            EntityType entityType = event.getEntityType();

            //Zombie Damage
            if (entityType.equals(EntityType.ZOMBIE)
                    || entityType.equals(EntityType.ZOMBIE_HORSE)
                    || entityType.equals(EntityType.ZOMBIE_VILLAGER)) {
                double originalDamage = event.getDamage();
                double percentIncrease = 0;
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), NOVICE_MONSTER_HUNTING)) {
                    percentIncrease += playerProfile.getHuntingSkillTree().getTree().get(NOVICE_MONSTER_HUNTING).getSkillPoints() * 0.05;
                }
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), INTERMEDIATE_MONSTER_HUNTER)) {
                    percentIncrease += playerProfile.getHuntingSkillTree().getTree().get(INTERMEDIATE_MONSTER_HUNTER).getSkillPoints() * 0.05;
                }
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), DEFT_STRIKES)) {
                    percentIncrease += playerProfile.getHuntingSkillTree().getTree().get(DEFT_STRIKES).getSkillPoints() * 0.05;
                }

                event.setDamage(originalDamage * (1 + percentIncrease));
            }

            //Skeleton Damage
            if (entityType.equals(EntityType.SKELETON)
                    || entityType.equals(EntityType.SKELETON_HORSE)) {
                double originalDamage = event.getDamage();
                double percentIncrease = 0;
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), UNDEAD_STALKER)) {
                    percentIncrease += playerProfile.getHuntingSkillTree().getTree().get(UNDEAD_STALKER).getSkillPoints() * 0.05;
                }
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), INTERMEDIATE_MONSTER_HUNTER)) {
                    percentIncrease += playerProfile.getHuntingSkillTree().getTree().get(INTERMEDIATE_MONSTER_HUNTER).getSkillPoints() * 0.05;
                }
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), DEFT_STRIKES)) {
                    percentIncrease += playerProfile.getHuntingSkillTree().getTree().get(DEFT_STRIKES).getSkillPoints() * 0.05;
                }

                event.setDamage(originalDamage * (1 + percentIncrease));
            }

            //Spider Damage
            if (entityType.equals(EntityType.SPIDER)
                    || entityType.equals(EntityType.CAVE_SPIDER)) {
                double originalDamage = event.getDamage();
                double percentIncrease = 0;
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), PEST_CONTROL)) {
                    percentIncrease += playerProfile.getHuntingSkillTree().getTree().get(PEST_CONTROL).getSkillPoints() * 0.05;
                }
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), INTERMEDIATE_MONSTER_HUNTER)) {
                    percentIncrease += playerProfile.getHuntingSkillTree().getTree().get(INTERMEDIATE_MONSTER_HUNTER).getSkillPoints() * 0.05;
                }
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), DEFT_STRIKES)) {
                    percentIncrease += playerProfile.getHuntingSkillTree().getTree().get(DEFT_STRIKES).getSkillPoints() * 0.05;
                }

                event.setDamage(originalDamage * (1 + percentIncrease));
            }

            //Wither Damage
            if (entityType.equals(EntityType.WITHER)
                    || entityType.equals(EntityType.WITHER_SKELETON)) {
                double originalDamage = event.getDamage();
                double percentIncrease = 0;
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), EXPERT_MONSTER_HUNTER)) {
                    percentIncrease += playerProfile.getHuntingSkillTree().getTree().get(EXPERT_MONSTER_HUNTER).getSkillPoints() * 0.05;
                }
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), INTERMEDIATE_MONSTER_HUNTER)) {
                    percentIncrease += playerProfile.getHuntingSkillTree().getTree().get(INTERMEDIATE_MONSTER_HUNTER).getSkillPoints() * 0.05;
                }
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), DEFT_STRIKES)) {
                    percentIncrease += playerProfile.getHuntingSkillTree().getTree().get(DEFT_STRIKES).getSkillPoints() * 0.05;
                }
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), BOSS_HUNTER)) {
                    percentIncrease += playerProfile.getHuntingSkillTree().getTree().get(BOSS_HUNTER).getSkillPoints() * 0.05;
                }

                event.setDamage(originalDamage * (1 + percentIncrease));
            }

            //Ender Dragon Damage
            if (entityType.equals(EntityType.ENDER_DRAGON)) {
                double originalDamage = event.getDamage();
                double percentIncrease = 0;
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), MASTER_MONSTER_HUNTER)) {
                    percentIncrease += playerProfile.getHuntingSkillTree().getTree().get(MASTER_MONSTER_HUNTER).getSkillPoints() * 0.1;
                }
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), INTERMEDIATE_MONSTER_HUNTER)) {
                    percentIncrease += playerProfile.getHuntingSkillTree().getTree().get(INTERMEDIATE_MONSTER_HUNTER).getSkillPoints() * 0.05;
                }
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), DEFT_STRIKES)) {
                    percentIncrease += playerProfile.getHuntingSkillTree().getTree().get(DEFT_STRIKES).getSkillPoints() * 0.05;
                }
                if (SkillUtils.playerHasSkill(plugin, playerProfile, playerProfile.getHuntingSkillTree(), BOSS_HUNTER)) {
                    percentIncrease += playerProfile.getHuntingSkillTree().getTree().get(BOSS_HUNTER).getSkillPoints() * 0.05;
                }

                event.setDamage(originalDamage * (1 + percentIncrease));
            }
        }
    }

}
