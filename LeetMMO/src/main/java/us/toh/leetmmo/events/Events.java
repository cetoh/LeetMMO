package us.toh.leetmmo.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import us.toh.leetmmo.configuration.ExperienceConfigLoader;
import us.toh.leetmmo.database.Database;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.gui.advancements.NormalSkillTreeGUI;

import java.util.Map;
import java.util.UUID;

public class Events implements Listener {

    private Map<UUID,PlayerProfile> globalPlayers;

    private Database db;

    public void setGlobalPlayers(Map<UUID,PlayerProfile> gp) {
        this.globalPlayers = gp;

    }

    public Database getDb() {
        return db;
    }

    public void setDb(Database db) {
        this.db = db;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        //Create New Player Profile
        PlayerProfile pp = new PlayerProfile(event.getPlayer());

        //See if player exists in database. If player exists load profile.
        if (db.checkIfPlayerExists(pp, "player")) {
            pp = db.getPlayerProfileFromDatabase(pp);
        }

        globalPlayers.put(event.getPlayer().getUniqueId(), pp);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        //Get Player Profile
        PlayerProfile pp = globalPlayers.get(event.getPlayer().getUniqueId());

        //See if player exists in database. If player exists update profile. Else insert new profile
        if (db.checkIfPlayerExists(pp, "player")
                && db.checkIfPlayerExists(pp, "farming")) {
            db.updatePlayerProfile(pp);
        } else {
            db.insertNewPlayerProfile(pp);
            db.updatePlayerProfile(pp);
        }

    }
}
