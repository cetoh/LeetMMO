package us.toh.leetmmo.skills;

import java.util.HashMap;

public class Skill {

    private String skillName;
    private int skillPointRequirement = 0;
    private boolean hasPrerequesite = false;

    private HashMap<String, Skill> prerequesiteSkills = new HashMap<String, Skill>();

    public Skill(String skillName, int skillPointRequirement) {
        this.skillName = skillName;
        this.skillPointRequirement = skillPointRequirement;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getSkillPointRequirement() {
        return skillPointRequirement;
    }

    public void setSkillPointRequirement(int skillPointRequirement) {
        this.skillPointRequirement = skillPointRequirement;
    }

    public boolean isHasPrerequesite() {
        return hasPrerequesite;
    }

    public void setHasPrerequesite(boolean hasPrerequesite) {
        this.hasPrerequesite = hasPrerequesite;
    }

}
