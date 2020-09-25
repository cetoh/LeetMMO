package us.toh.leetmmo.skills;

import java.util.HashMap;

public class SkillTree {

    private HashMap<Enum, Skill> tree = new HashMap<Enum, Skill>();

    public SkillTree() {

    }


    public HashMap<Enum, Skill> getTree() {
        return this.tree;
    }
}
