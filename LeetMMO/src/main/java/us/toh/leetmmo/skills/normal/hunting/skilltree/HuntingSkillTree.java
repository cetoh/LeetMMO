package us.toh.leetmmo.skills.normal.hunting.skilltree;

import us.toh.leetmmo.skills.Skill;

import java.util.HashMap;

import static us.toh.leetmmo.skills.normal.NormalSkillEnums.FarmingSkillNames.*;

public class HuntingSkillTree {

    private HashMap<Enum, Skill> tree = new HashMap<Enum, Skill>();

    public HuntingSkillTree() {
        buildSkillTree();
    }

    private void buildSkillTree() {


        //Add Children to skills

    }

    public HashMap<Enum, Skill> getTree() {
        return this.tree;
    }
}
