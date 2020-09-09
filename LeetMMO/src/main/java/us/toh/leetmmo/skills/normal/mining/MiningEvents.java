package us.toh.leetmmo.skills.normal.mining;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import us.toh.leetmmo.datatypes.player.PlayerProfile;

import java.util.Map;
import java.util.UUID;

public class MiningEvents implements Listener {

    private Map<UUID, PlayerProfile> globalPlayers;

    public void setGlobalPlayers(Map<UUID, PlayerProfile> globalPlayers) {
        this.globalPlayers = globalPlayers;
    }

    @EventHandler
    public void onTillSoil(BlockBreakEvent event) {
        

    }
}
