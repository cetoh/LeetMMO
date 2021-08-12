package us.toh.leetmmo.datatypes.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import us.toh.leetmmo.datatypes.experience.*;
import us.toh.leetmmo.datatypes.level.*;
import us.toh.leetmmo.datatypes.skillpoint.*;
import us.toh.leetmmo.skills.classes.ClassEnums;
import us.toh.leetmmo.skills.classes.ClassSkillTree;
import us.toh.leetmmo.skills.classes.rogue.RogueSkillTree;
import us.toh.leetmmo.skills.normal.farming.skilltree.FarmingSkillTree;
import us.toh.leetmmo.skills.normal.fishing.skilltree.FishingSkillTree;
import us.toh.leetmmo.skills.normal.hunting.skilltree.HuntingSkillTree;
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

    //Class
    private ClassEnums.ClassType currentClass = ClassEnums.ClassType.NONE;
    private ClassSkillTree classSkillTree = new ClassSkillTree();

    //Skill trees
    private HuntingSkillTree huntingSkillTree = new HuntingSkillTree();
    private FarmingSkillTree farmingSkillTree = new FarmingSkillTree();
    private FishingSkillTree fishingSkillTree = new FishingSkillTree();
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

    //Base Player Getters and Setters
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

    //Class related Getter and Setters
    public ClassEnums.ClassType getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(ClassEnums.ClassType currentClass) {
        this.currentClass = currentClass;

        switch (currentClass) {
            case CLERIC:
                break;
            case ROGUE:
                this.classSkillTree = new RogueSkillTree();
                break;
            case PALADIN:
                break;
        }
    }

    public ClassSkillTree getClassSkillTree() {
        return classSkillTree;
    }

    public void setClassSkillTree(ClassSkillTree classSkillTree) {
        this.classSkillTree = classSkillTree;
    }

    //Skill Tree Getters and Setters
    public HuntingSkillTree getHuntingSkillTree() {
        return huntingSkillTree;
    }

    public void setHuntingSkillTree(HuntingSkillTree huntingSkillTree) {
        this.huntingSkillTree = huntingSkillTree;
    }

    public FarmingSkillTree getFarmingSkillTree() {
        return farmingSkillTree;
    }

    public void setFarmingSkillTree(FarmingSkillTree farmingSkillTree) {
        this.farmingSkillTree = farmingSkillTree;
    }


    public FishingSkillTree getFishingSkillTree() {
        return fishingSkillTree;
    }

    public void setFishingSkillTree(FishingSkillTree fishingSkillTree) {
        this.fishingSkillTree = fishingSkillTree;
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
        return ChatColor.BLUE + "Normal Level: " + ChatColor.GOLD + nLvl.getCurrentLevel() + ChatColor.BLUE + " Normal EXP: " + Math.floor(nEXPPool.getPoints()) + "/" + Math.floor(nEXPPool.getPoolCap()) + "\n"
                + ChatColor.AQUA + "Normal Skill Points: " + ChatColor.YELLOW + nSPPool.getNumPoints() + "\n"
                + ChatColor.BLUE + "Class Level: " + ChatColor.GOLD + cLvl.getCurrentLevel() + ChatColor.BLUE + " Class EXP: " + Math.floor(cEXPPool.getPoints()) + "/" + Math.floor(cEXPPool.getPoolCap()) + "\n"
                + ChatColor.AQUA + "Class Skill Points: " + ChatColor.YELLOW + cSPPool.getNumPoints() + "\n";
    }


}
