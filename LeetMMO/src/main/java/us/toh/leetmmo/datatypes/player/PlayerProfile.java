package us.toh.leetmmo.datatypes.player;

import org.bukkit.entity.Player;
import us.toh.leetmmo.datatypes.experience.*;
import us.toh.leetmmo.datatypes.level.*;
import us.toh.leetmmo.datatypes.skillpoint.*;


import java.util.UUID;

public class PlayerProfile {

    private final Player player;
    private final String playerName;
    private UUID uuid;
    private NormalExperiencePool nEXPPool;
    private ClassExperiencePool cEXPPool;
    private NormalSkillPointPool nSPPool;
    private ClassSkillPointPool cSPPool;
    private NormalLevel nLvl;
    private ClassLevel cLvl;

    public enum expType {NORMAL, CLASS};


    public PlayerProfile(Player player) {
        this.player = player;
        this.playerName = player.getName();
        this.uuid = player.getUniqueId();

        nEXPPool = new NormalExperiencePool(0, 100);
        cEXPPool = new ClassExperiencePool(0, 100);
        nSPPool = new NormalSkillPointPool(0);
        cSPPool = new ClassSkillPointPool(0);
        nLvl = new NormalLevel();
        cLvl = new ClassLevel();
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

    public void addExperience (double exp, PlayerProfile.expType t ) {
        switch(t) {
            case NORMAL:
                nEXPPool.addPoints(exp);
                if (nEXPPool.getPoints() >= nEXPPool.getPoolCap()) {
                    nLvl.increaseLevel();
                    nEXPPool.setPoolCap(nEXPPool.getPoolCap() * 1.1);
                    nSPPool.addNumPoints(1);
                    nEXPPool.setPoints(nEXPPool.getCarry());
                    nEXPPool.setCarry(0);
                }
                break;
            case CLASS:
                cEXPPool.addPoints(exp);
                if (cEXPPool.getPoints() >= cEXPPool.getPoolCap()) {
                    cLvl.increaseLevel();
                    cEXPPool.setPoolCap(cEXPPool.getPoolCap() * 1.1);
                    cSPPool.addNumPoints(1);
                    cEXPPool.setPoints(cEXPPool.getCarry());
                    cEXPPool.setCarry(0);
                }
                break;
        }
    }

    public String displayLevels () {
        return "nLevel: " + nLvl.getCurrentLevel() + " nEXP: " + nEXPPool.getPoints() + "/" + nEXPPool.getPoolCap() + "\n"
                + "nSkill Points: " + nSPPool.getNumPoints() + "\n"
                + "cLevel: " + cLvl.getCurrentLevel() + " cEXP: " + cEXPPool.getPoints() + "/" + cEXPPool.getPoolCap() + "\n"
                + "cSkill Points: " + cSPPool.getNumPoints() + "\n";
    }


}
