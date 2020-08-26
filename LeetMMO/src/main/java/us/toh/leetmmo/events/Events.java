package us.toh.leetmmo.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import us.toh.leetmmo.datatypes.player.PlayerProfile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Events implements Listener {

    private Map<UUID,PlayerProfile> globalPlayers = new HashMap<UUID, PlayerProfile>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        globalPlayers.put(event.getPlayer().getUniqueId(), new PlayerProfile(event.getPlayer()));
    }

    @EventHandler
    public void onLeaveBed(PlayerBedLeaveEvent event) {
        Player player  = event.getPlayer();
        player.sendMessage("Hi boi");
    }

    @EventHandler
    public void onHarvestBlock(BlockBreakEvent event) {

        PlayerProfile player = globalPlayers.get(event.getPlayer().getUniqueId());

        double expGainBase = 100;
        double expGainModifier = 0;
        switch(event.getBlock().getType()) {

            //Mining
            case STONE:
                expGainModifier = 11520;
                break;
            case CLAY:
                expGainModifier = 150;
                break;
            case COAL_ORE:
                expGainModifier = 124;
                break;
            case REDSTONE_ORE:
                expGainModifier = 25;
                break;
            case IRON_ORE:
                expGainModifier = 72;
                break;
            case GOLD_ORE:
                expGainModifier = 7.5;
                break;
            case LAPIS_ORE:
                expGainModifier = 3.43;
                break;
            case EMERALD_ORE:
                expGainModifier = 11;
                break;
            case DIAMOND_ORE:
                expGainModifier = 3;
                break;
            case NETHER_QUARTZ_ORE:
                expGainModifier = 16;
                break;
            case NETHER_GOLD_ORE:
                expGainModifier = 10;
                break;
            case ANCIENT_DEBRIS:
                expGainModifier = 1.7;
                break;

            //Woodcutting
            case ACACIA_WOOD:
            case BIRCH_WOOD:
            case DARK_OAK_WOOD:
            case JUNGLE_WOOD:
            case OAK_WOOD:
            case SPRUCE_WOOD:
                expGainModifier = 400;
                break;

            //Farming
            case WHEAT:
            case CARROT:
            case CARROTS:
            case BEETROOT:
            case POTATO:
            case POTATOES:
                expGainModifier = 67;
                break;
            case MELON:
            case PUMPKIN:
                expGainModifier = 200;
                break;

            case SUGAR_CANE:
                expGainModifier = 400;
                break;
            case COCOA_BEANS:
                expGainModifier = 20;
                break;
            case BROWN_MUSHROOM:
            case RED_MUSHROOM:
                expGainModifier = 50;
                break;
            case NETHER_WART:
                expGainModifier = 40;
                break;
            case CHORUS_FRUIT:
                expGainModifier = 6.7;
                break;
        }

        //Calculate final exp gain
        double expGain = 0.25 * Math.abs((expGainBase / expGainModifier) / 0.25);
        if (event.getBlock().getType().equals(Material.STONE)){
            expGain = 0.1;
        }

        //Give player normal experience
        player.addExperience(expGain, PlayerProfile.expType.NORMAL);

        //Give player class experience
        player.addExperience(Math.floor(expGain * 0.1), PlayerProfile.expType.CLASS );

        globalPlayers.put(player.getUuid(), player);

        event.getPlayer().sendMessage(player.displayLevels());

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
            p.addExperience(0.5, PlayerProfile.expType.CLASS );

            globalPlayers.put(p.getUuid(), p);
        }
    }
}
