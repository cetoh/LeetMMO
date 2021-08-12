package us.toh.leetmmo.skills.classes.rogue;

import us.toh.leetmmo.skills.Skill;
import us.toh.leetmmo.skills.classes.ClassSkillTree;

import static us.toh.leetmmo.skills.classes.ClassEnums.Rogue.*;

public class RogueSkillTree extends ClassSkillTree {

    public RogueSkillTree() {
        buildSkillTree();
    }

    private void buildSkillTree() {
        Skill swiftness = new Skill(SWIFTNESS, "Gain swiftness. Double move speed for 10 seconds.", 1);
        getTree().put(swiftness.getSkillName(), swiftness);

        Skill backstab = new Skill(BACKSTAB, "Do increased damage when attacking from behind.", 4);
        getTree().put(backstab.getSkillName(), backstab);
        backstab.getPrerequesiteSkills().put(swiftness, swiftness.getSkillPointRequirement());

        //Add Child skills
        swiftness.getChildSkills().put(backstab, backstab.getSkillPointRequirement());
    }
}
