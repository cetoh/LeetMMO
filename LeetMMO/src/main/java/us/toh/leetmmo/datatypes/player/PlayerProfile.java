package us.toh.leetmmo.datatypes.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import us.toh.leetmmo.datatypes.experience.*;
import us.toh.leetmmo.datatypes.level.*;
import us.toh.leetmmo.datatypes.skillpoint.*;
import us.toh.leetmmo.skills.normal.farming.skilltree.FarmingSkillTree;
import us.toh.leetmmo.skills.normal.mining.skilltree.MiningSkillTree;


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

    private FarmingSkillTree farmingSkillTree = new FarmingSkillTree();
    private MiningSkillTree miningSkillTree = new MiningSkillTree();

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

    public NormalExperiencePool getnEXPPool() {
        return nEXPPool;
    }

    public void setnEXPPool(NormalExperiencePool nEXPPool) {
        this.nEXPPool = nEXPPool;
    }

    public ClassExperiencePool getcEXPPool() {
        return cEXPPool;
    }

    public void setcEXPPool(ClassExperiencePool cEXPPool) {
        this.cEXPPool = cEXPPool;
    }

    public NormalSkillPointPool getnSPPool() {
        return nSPPool;
    }

    public void setnSPPool(NormalSkillPointPool nSPPool) {
        this.nSPPool = nSPPool;
    }

    public ClassSkillPointPool getcSPPool() {
        return cSPPool;
    }

    public void setcSPPool(ClassSkillPointPool cSPPool) {
        this.cSPPool = cSPPool;
    }

    public NormalLevel getnLvl() {
        return nLvl;
    }

    public void setnLvl(NormalLevel nLvl) {
        this.nLvl = nLvl;
    }

    public ClassLevel getcLvl() {
        return cLvl;
    }

    public void setcLvl(ClassLevel cLvl) {
        this.cLvl = cLvl;
    }


    public FarmingSkillTree getFarmingSkillTree() {
        return farmingSkillTree;
    }

    public void setFarmingSkillTree(FarmingSkillTree farmingSkillTree) {
        this.farmingSkillTree = farmingSkillTree;
    }

    public MiningSkillTree getMiningSkillTree() {
        return miningSkillTree;
    }

    public void setMiningSkillTree(MiningSkillTree miningSkillTree) {
        this.miningSkillTree = miningSkillTree;
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
        return ChatColor.BLUE + "Normal Level: " + ChatColor.GOLD + nLvl.getCurrentLevel() + ChatColor.BLUE + " Normal EXP: " + nEXPPool.getPoints() + "/" + nEXPPool.getPoolCap() + "\n"
                + ChatColor.AQUA + "Normal Skill Points: " + ChatColor.YELLOW + nSPPool.getNumPoints() + "\n"
                + ChatColor.BLUE + "Class Level: " + ChatColor.GOLD + cLvl.getCurrentLevel() + ChatColor.BLUE + " Class EXP: " + cEXPPool.getPoints() + "/" + cEXPPool.getPoolCap() + "\n"
                + ChatColor.AQUA + "Class Skill Points: " + ChatColor.YELLOW + cSPPool.getNumPoints() + "\n";
    }


}
