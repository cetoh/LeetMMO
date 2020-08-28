package us.toh.leetmmo.skills.normal.mining.skilltree;

import us.toh.leetmmo.skills.Skill;

import java.util.HashMap;

import static us.toh.leetmmo.skills.normal.NormalSkillEnums.MiningSkillNames.*;

public class MiningSkillTree {

    private HashMap<Enum, Skill> tree = new HashMap<Enum, Skill>();

    public MiningSkillTree() {
        buildSkillTree();
    }

    private void buildSkillTree() {
        Skill basicMining = new Skill(BASIC_MINING, "Gain ability to mine with wooden/gold pickaxes", 1);
        tree.put(basicMining.getSkillName(), basicMining);

        Skill Hewer = new Skill(HEWER, "Gain ability to plant wheat",1);
        Hewer.getPrerequesiteSkills().put(Hewer, Hewer.getSkillPointRequirement());
        tree.put(Hewer.getSkillName(), Hewer);




    }

    public HashMap<Enum, Skill> getTree() {
        return this.tree;
    }

}
