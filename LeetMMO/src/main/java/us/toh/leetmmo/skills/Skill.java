package us.toh.leetmmo.skills;

import java.util.HashMap;

public class Skill {

    private Enum skillName;
    private String description;


    private int skillPoints = 0;
    private int skillPointRequirement = 0;
    private boolean hasPrerequesite = false;
    private boolean hasChild = false;

    private HashMap<Skill, Integer> prerequesiteSkills = new HashMap<Skill, Integer>();
    private HashMap<Skill, Integer> childSkills = new HashMap<Skill, Integer>();

    public Skill(Enum skillName, String description, int skillPointRequirement) {
        this.skillName = skillName;
        this.description = description;
        this.skillPointRequirement = skillPointRequirement;
    }

    public Enum getSkillName() {
        return skillName;
    }

    public void setSkillName(Enum skillName) {
        this.skillName = skillName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public boolean addSkillPoint() {
        if (skillPoints < skillPointRequirement) {
            this.skillPoints++;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean removeSkillPoint() {
        if (skillPoints > 0) {
            this.skillPoints--;
            return true;
        }
        else {
            return false;
        }
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    public int getSkillPointRequirement() {
        return skillPointRequirement;
    }

    public void setSkillPointRequirement(int skillPointRequirement) {
        this.skillPointRequirement = skillPointRequirement;
    }

    public boolean hasPrerequesite() {
        return hasPrerequesite;
    }

    public void setHasPrerequesite(boolean hasPrerequesite) {
        this.hasPrerequesite = hasPrerequesite;
    }

    public boolean hasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public HashMap<Skill, Integer> getPrerequesiteSkills() {
        return prerequesiteSkills;
    }

    public void setPrerequesiteSkills(HashMap<Skill, Integer> prerequesiteSkills) {
        this.prerequesiteSkills = prerequesiteSkills;
    }

    public HashMap<Skill, Integer> getChildSkills() {
        return childSkills;
    }

    public void setChildSkills(HashMap<Skill, Integer> childSkills) {
        this.childSkills = childSkills;
    }

}
