package us.toh.leetmmo.skills.normal.farming;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import us.toh.leetmmo.datatypes.player.PlayerProfile;

import java.util.Map;
import java.util.UUID;

public class FarmingEvents implements Listener {

    private Map<UUID, PlayerProfile> globalPlayers;

    public void setGlobalPlayers(Map<UUID, PlayerProfile> globalPlayers) {
        this.globalPlayers = globalPlayers;
    }

    @EventHandler
    public void onTillSoil(BlockPlaceEvent event) {

        if(event.getBlockPlaced().equals(Material.FARMLAND)) {
            event.getPlayer().getUniqueId();
        }

    }
}
