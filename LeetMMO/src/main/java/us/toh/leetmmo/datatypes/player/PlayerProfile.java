package us.toh.leetmmo.datatypes.player;

import org.bukkit.entity.Player;
import us.toh.leetmmo.datatypes.*;

import java.util.UUID;

public class PlayerProfile {

    private final Player player;
    private final String playerName;
    private UUID uuid;
    private NormalExperiencePool nEXPPool;
    private ClassExperiencePool cEXPPool;
    private NormalSkillPointPool nSPPool;
    private ClassSkillPointPool cSPPool;

    public PlayerProfile(Player player) {
        this.player = player;
        this.playerName = player.getName();
        this.uuid = player.getUniqueId();
    }

    public String getPlayerName() {
        return playerName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }


}
