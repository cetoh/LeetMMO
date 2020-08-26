package us.toh.leetmmo.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
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

        player.addExperience(10, PlayerProfile.expType.NORMAL);

        globalPlayers.put(player.getUuid(), player);

        event.getPlayer().sendMessage(player.displayLevels());

    }
}
