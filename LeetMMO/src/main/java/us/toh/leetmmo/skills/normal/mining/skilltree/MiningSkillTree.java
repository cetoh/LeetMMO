package us.toh.leetmmo.skills.normal.mining.skilltree;

import us.toh.leetmmo.skills.Skill;

import java.util.HashMap;

import static us.toh.leetmmo.skills.normal.NormalSkillEnums.Mining.*;

public class MiningSkillTree {

    private HashMap<Enum, Skill> tree = new HashMap<Enum, Skill>();

    public MiningSkillTree() {
        buildSkillTree();
    }

    private void buildSkillTree() {
        Skill basicMining = new Skill(BASIC_MINING, "Gain ability to mine with wooden/gold pickaxes", 1);
        tree.put(basicMining.getSkillName(), basicMining);

        Skill triticumCultivation = new Skill("Triticum Cultivation", "Gain ability to plant wheat",1);
        triticumCultivation.getPrerequesiteSkills().put(basicAgriculture, basicAgriculture.getSkillPointRequirement());
        tree.put(triticumCultivation.getSkillName(), triticumCultivation);




    }

    public HashMap<Enum, Skill> getTree() {
        return this.tree;
    }

}
