package us.toh.leetmmo.datatypes.experience;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import us.toh.leetmmo.configuration.ExperienceConfigLoader;
import us.toh.leetmmo.database.Database;
import us.toh.leetmmo.datatypes.player.PlayerProfile;

import java.util.Map;
import java.util.UUID;

public class ExperienceEvents implements Listener {

    private Map<UUID,PlayerProfile> globalPlayers;

    private ExperienceConfigLoader expConfigLoader;

    public void setGlobalPlayers(Map<UUID,PlayerProfile> gp) {
        this.globalPlayers = gp;

    }

    public void setExpConfigManager(ExperienceConfigLoader expConfigLoader) {
        this.expConfigLoader = expConfigLoader;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        PlayerProfile profile = globalPlayers.get(event.getPlayer().getUniqueId());

        double expGainBase = expConfigLoader.getCustomConfig().getDouble("expBaseGain");
        double expGainModifier = 1;
        switch(event.getBlock().getType()) {

            //Mining
            case CLAY:
                expGainModifier = 5;
                break;
            case COAL_ORE:
                expGainModifier = 7.5;
                break;
            case REDSTONE_ORE:
                expGainModifier = 40;
                break;
            case IRON_ORE:
                expGainModifier = 12.5;
                break;
            case GOLD_ORE:
                expGainModifier = 132.5;
                break;
            case LAPIS_ORE:
                expGainModifier = 302.5;
                break;
            case EMERALD_ORE:
                expGainModifier = 90;
                break;
            case DIAMOND_ORE:
                expGainModifier = 332.5;
                break;
            case NETHER_QUARTZ_ORE:
                expGainModifier = 62.5;
                break;
            case NETHER_GOLD_ORE:
                expGainModifier = 100;
                break;
            case ANCIENT_DEBRIS:
                expGainModifier = 587.5;
                break;

            //Woodcutting
            case BROWN_MUSHROOM_BLOCK:
            case RED_MUSHROOM_BLOCK:
            case ACACIA_LOG:
            case BIRCH_LOG:
            case DARK_OAK_LOG:
            case JUNGLE_LOG:
            case OAK_LOG:
            case SPRUCE_LOG:
                expGainModifier = 2.5;
                break;

            //Farming
            case WHEAT:
            case CARROT:
            case CARROTS:
            case BEETROOT:
            case POTATO:
            case POTATOES:
                expGainModifier = 15;
                break;
            case MELON:
            case PUMPKIN:
                expGainModifier = 50;
                break;

            case SUGAR_CANE:
                expGainModifier = 25;
                break;
            case COCOA_BEANS:
                expGainModifier = 50;
                break;
            case BROWN_MUSHROOM:
            case RED_MUSHROOM:
                expGainModifier = 20;
                break;
            case NETHER_WART:
                expGainModifier = 25;
                break;
            case CHORUS_FRUIT:
                expGainModifier = 150;
                break;
            default:
                expGainBase = 0;
        }

        //Calculate final exp gain
        double expGain = expGainBase * expGainModifier;

        //Give player normal experience
        profile.addExperience(expGain, PlayerProfile.expType.NORMAL);

        //Give player class experience
        profile.addExperience(Math.floor(expGain * expConfigLoader.getCustomConfig().getDouble("classExpGainModifier")), PlayerProfile.expType.CLASS );

        globalPlayers.put(profile.getUuid(), profile);

    }

    @EventHandler
    public void onPlayerTakeDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            PlayerProfile p = globalPlayers.get(event.getEntity().getUniqueId());

            //Give player normal experience
            p.addExperience(0.5, PlayerProfile.expType.NORMAL);

            //Give player class experience
            p.addExperience(0.5, PlayerProfile.expType.CLASS );

            globalPlayers.put(p.getUuid(), p);
        }
    }

    @EventHandler
    public void onPlayerDealDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            PlayerProfile p = globalPlayers.get(event.getDamager().getUniqueId());

            //Give player normal experience
            p.addExperience(0.5, PlayerProfile.expType.NORMAL);

            //Give player class experience
            p.addExperience(0.5, PlayerProfile.expType.CLASS);

            globalPlayers.put(p.getUuid(), p);
        }
    }


}
