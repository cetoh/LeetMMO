package us.toh.leetmmo.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;

public class Events implements Listener {

    @EventHandler
    public void onLeaveBed(PlayerBedLeaveEvent event) {
        Player player  = event.getPlayer();
        player.sendMessage("Hi boi");
    }

    @EventHandler
    public void onHarvestBlock(PlayerHarvestBlockEvent event) {
        Player player = event.getPlayer();

    }
}
