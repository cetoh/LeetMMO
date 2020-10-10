package us.toh.leetmmo.datatypes.experience;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerFishEvent;
import us.toh.leetmmo.LeetMMO;
import us.toh.leetmmo.configuration.ExperienceConfigLoader;
import us.toh.leetmmo.database.Database;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.skills.SkillUtils;

import java.util.Map;
import java.util.UUID;

import static us.toh.leetmmo.skills.normal.NormalSkillEnums.FarmingSkillNames.*;
import static us.toh.leetmmo.skills.normal.NormalSkillEnums.FishingSkillNames.*;
import static us.toh.leetmmo.skills.normal.NormalSkillEnums.HuntingSkillNames.*;
import static us.toh.leetmmo.skills.normal.NormalSkillEnums.MiningSkillNames.*;

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

        //Add mastery based modifiers
        expGain = addFarmingMasteryMultiplier(event, profile, expGain);

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

            double expGain = 0.5;

            if (SkillUtils.playerHasSkill(LeetMMO.plugin, p, p.getHuntingSkillTree(), HUNTER_INSIGHT)) {
                expGain *= (1 + p.getHuntingSkillTree().getTree().get(HUNTER_INSIGHT).getSkillPoints() * 0.1) ;
            }
            if (SkillUtils.playerHasSkill(LeetMMO.plugin, p, p.getHuntingSkillTree(), MONSTER_LESSONS)) {
                expGain *= (1 + p.getHuntingSkillTree().getTree().get(MONSTER_LESSONS).getSkillPoints() * 0.1) ;
            }
            if (SkillUtils.playerHasSkill(LeetMMO.plugin, p, p.getHuntingSkillTree(), MONSTER_LORE)) {
                expGain *= (1 + p.getHuntingSkillTree().getTree().get(MONSTER_LORE).getSkillPoints() * 0.1) ;
            }

            //Give player normal experience
            p.addExperience(expGain, PlayerProfile.expType.NORMAL);

            //Give player class experience
            p.addExperience(expGain, PlayerProfile.expType.CLASS);

            globalPlayers.put(p.getUuid(), p);
        }
    }

    //Farming Mastery (50% boost per farming event)
    private double addFarmingMasteryMultiplier(BlockBreakEvent event, PlayerProfile profile, double expGain) {
        if (profile.getFarmingSkillTree().getTree().get(FARMING_MASTERY).getSkillPoints() ==
                profile.getFarmingSkillTree().getTree().get(FARMING_MASTERY).getSkillPointRequirement()) {
            switch (event.getBlock().getType()) {
                case WHEAT:
                case CARROT:
                case CARROTS:
                case BEETROOT:
                case POTATO:
                case POTATOES:
                case MELON:
                case PUMPKIN:
                case SUGAR_CANE:
                case COCOA_BEANS:
                case BROWN_MUSHROOM:
                case RED_MUSHROOM:
                case NETHER_WART:
                case CHORUS_FRUIT:
                    expGain = expGain * 1.5;
                    break;
            }
        }

        return expGain;
    }

    //Mining Mastery & Expertise (50% boost per mining event + 10% boost per skill point)
    private double addMiningMasteryMultiplier(BlockBreakEvent event, PlayerProfile profile, double expGain) {
        double boost = 1;

        boost += profile.getMiningSkillTree().getTree().get(MINING_MASTERY).getSkillPoints() * 0.5;
        boost += profile.getMiningSkillTree().getTree().get(MINING_EXPERTISE).getSkillPoints() * 0.1;

        switch (event.getBlock().getType()) {
            case CLAY:
            case COAL_ORE:
            case REDSTONE_ORE:
            case IRON_ORE:
            case GOLD_ORE:
            case LAPIS_ORE:
            case EMERALD_ORE:
            case DIAMOND_ORE:
            case NETHER_QUARTZ_ORE:
            case NETHER_GOLD_ORE:
            case ANCIENT_DEBRIS:
                expGain = expGain * boost;
                break;
        }

        return expGain;
    }

    @EventHandler
    public void onFishCaught(PlayerFishEvent event) {
        PlayerProfile profile = globalPlayers.get(event.getPlayer().getUniqueId());

        double expGainBase = expConfigLoader.getCustomConfig().getDouble("expBaseGain");
        double expGainModifier = 1;

        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            Item caught = (Item) event.getCaught();
            Material itemType = caught.getItemStack().getType();

            if (itemType.equals(Material.COD)) {
                expGainBase = 75;
            }
            else if (itemType.equals(Material.SALMON)) {
                expGainBase = 100;
            }
            else if (itemType.equals(Material.TROPICAL_FISH)) {
                expGainBase = 125;
            }
            else if (itemType.equals(Material.PUFFERFISH)) {
                expGainBase = 150;
            }


        }

        //Calculate final exp gain
        double expGain = expGainBase * expGainModifier;

        //Add skill modifiers
        expGain = addFishingMasteryMultiplier(profile, expGain);

        //Give player normal experience
        profile.addExperience(expGain, PlayerProfile.expType.NORMAL);

        //Give player class experience
        profile.addExperience(Math.floor(expGain * expConfigLoader.getCustomConfig().getDouble("classExpGainModifier")), PlayerProfile.expType.CLASS );

        globalPlayers.put(profile.getUuid(), profile);
    }

    //Fisherman Folk Stories (5% boost per fish caught event)
    private double addFishingMasteryMultiplier(PlayerProfile profile, double expGain) {
        /* Fisherman Folk Stories */
        if (SkillUtils.playerHasSkill(LeetMMO.plugin, profile, profile.getFishingSkillTree(), FISHERMAN_FOLK_STORIES)) {
            expGain = expGain * 1.05;
        }

        return expGain;
    }

}
