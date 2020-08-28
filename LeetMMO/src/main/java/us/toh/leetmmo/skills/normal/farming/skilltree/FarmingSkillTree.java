package us.toh.leetmmo.skills.normal.farming.skilltree;

import us.toh.leetmmo.skills.Skill;

import java.util.HashMap;

public class FarmingSkillTree {

    private HashMap<String, Skill> tree = new HashMap<String, Skill>();

    public FarmingSkillTree() {
        buildSkillTree();
    }

    private void buildSkillTree() {
        Skill basicAgriculture = new Skill("Basic Agriculture", "Gain ability to till dirt", 1);
        tree.put(basicAgriculture.getSkillName(), basicAgriculture);

        Skill triticumCultivation = new Skill("Triticum Cultivation", "Gain ability to plant wheat",1);
        triticumCultivation.getPrerequesiteSkills().put(basicAgriculture, basicAgriculture.getSkillPointRequirement());
        tree.put(triticumCultivation.getSkillName(), triticumCultivation);

        Skill fertilizer = new Skill("Fertilizer", "Gain ability to use bone meal",3);
        fertilizer.getPrerequesiteSkills().put(triticumCultivation, triticumCultivation.getSkillPointRequirement());
        tree.put(fertilizer.getSkillName(), fertilizer);

        Skill daucusCultivation = new Skill("Daucus Cultivation", "Gain ability to plant carrots",2);
        daucusCultivation.getPrerequesiteSkills().put(triticumCultivation, triticumCultivation.getSkillPointRequirement());
        tree.put(daucusCultivation.getSkillName(), daucusCultivation);

        Skill weedRemoval = new Skill("Weed Removal", "Each point in this skill improves wheat double drop chance by 5%",5);
        weedRemoval.getPrerequesiteSkills().put(triticumCultivation, triticumCultivation.getSkillPointRequirement());
        tree.put(weedRemoval.getSkillName(), weedRemoval);



    }

    public HashMap<String, Skill> getTree() {
        return this.tree;
    }
}
