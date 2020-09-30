package us.toh.leetmmo.skills.normal.hunting.skilltree;

import us.toh.leetmmo.skills.Skill;

import java.util.HashMap;

import static us.toh.leetmmo.skills.normal.NormalSkillEnums.HuntingSkillNames.*;

public class HuntingSkillTree {

    private HashMap<Enum, Skill> tree = new HashMap<Enum, Skill>();

    public HuntingSkillTree() {
        buildSkillTree();
    }

    private void buildSkillTree() {
        //Root
        Skill chickenCatcher = new Skill(CHICKEN_CATCHER, "Each point in this skill improves feather double drop chance by 5%", 5);
        tree.put(chickenCatcher.getSkillName(), chickenCatcher);

        Skill gameHunter = new Skill(GAME_HUNTER, "Each point in this skill increases damage done to chicken, pigs, sheep, and cows by 5%", 5);
        tree.put(gameHunter.getSkillName(), gameHunter);

        Skill noviceMonsterHunting = new Skill(NOVICE_MONSTER_HUNTING, "Each point in this skill increases damage done to zombies by 5%", 5);
        tree.put(noviceMonsterHunting.getSkillName(), noviceMonsterHunting);

        //Rest of skill tree
        Skill undeadStalker = new Skill(UNDEAD_STALKER, "Each point in this skill increases damage done to skeleton and skeleton horseman by 5%", 5);
        undeadStalker.getPrerequesiteSkills().put(noviceMonsterHunting, 5);
        tree.put(undeadStalker.getSkillName(), undeadStalker);

        Skill snares = new Skill(SNARES, "Each point in this skill improves rabbit hide double drop chance by 5%", 5);
        snares.getPrerequesiteSkills().put(chickenCatcher, 5);
        tree.put(snares.getSkillName(), snares);

        Skill gameCleaning = new Skill(GAME_CLEANING, "Each point in this skill improves raw rabbit, raw chicken, raw mutton, raw beef, and raw porkchop double drop chance by 5%", 5);
        gameCleaning.getPrerequesiteSkills().put(snares, 5);
        gameCleaning.getPrerequesiteSkills().put(gameHunter, 5);
        tree.put(gameCleaning.getSkillName(), gameCleaning);

        Skill wildernessResourcefulness = new Skill(WILDERNESS_RESOURCEFULNESS, "Eac point in this skill improves bones double drop chance by 5%", 5);
        wildernessResourcefulness.getPrerequesiteSkills().put(gameCleaning, 5);
        wildernessResourcefulness.getPrerequesiteSkills().put(noviceMonsterHunting, 5);
        tree.put(wildernessResourcefulness.getSkillName(), wildernessResourcefulness);

        Skill pestControl = new Skill(PEST_CONTROL, "Each point in this skill increases damage done to spiders by 5%", 5);
        pestControl.getPrerequesiteSkills().put(undeadStalker, 5);
        pestControl.getPrerequesiteSkills().put(wildernessResourcefulness, 5);
        tree.put(pestControl.getSkillName(), pestControl);

        Skill expertSkinning = new Skill(EXPERT_SKINNING, "Each point in this skill improves rabbit hide, leather, and wool double drop chance by 5%", 5);
        expertSkinning.getPrerequesiteSkills().put(gameCleaning, 5);
        tree.put(expertSkinning.getSkillName(), expertSkinning);

        Skill intermediateMonsterHunter = new Skill(INTERMEDIATE_MONSTER_HUNTER, "Each point in this skill increases damage done to all hostile mobs by 5%", 5);
        intermediateMonsterHunter.getPrerequesiteSkills().put(pestControl, 5);
        tree.put(intermediateMonsterHunter.getSkillName(), intermediateMonsterHunter);

        Skill scavenger = new Skill(SCAVENGER, "Each point in this skill improves hostile mobs double drop chance by 5%", 5);
        scavenger.getPrerequesiteSkills().put(wildernessResourcefulness, 5);
        scavenger.getPrerequesiteSkills().put(expertSkinning, 5);
        scavenger.getPrerequesiteSkills().put(intermediateMonsterHunter, 5);
        tree.put(scavenger.getSkillName(), scavenger);

        Skill hunterInsight = new Skill(HUNTER_INSIGHT, "Increase EXP gain for killing mobs by 10%", 1);
        hunterInsight.getPrerequesiteSkills().put(intermediateMonsterHunter, 5);
        tree.put(hunterInsight.getSkillName(), hunterInsight);

        Skill wildernessToughness = new Skill(WILDERNESS_TOUGHNESS, "Each point in this skill decreases damage done by all hostile mobs by 5%", 5);
        wildernessToughness.getPrerequesiteSkills().put(intermediateMonsterHunter, 5);
        tree.put(wildernessToughness.getSkillName(), wildernessToughness);

        Skill wildernessReflexes = new Skill(WILDERNESS_REFLEXES, "Each point in this skill increases dodge chance when attacked by hostile mobs by 2%", 5);
        wildernessReflexes.getPrerequesiteSkills().put(intermediateMonsterHunter, 5);
        tree.put(wildernessReflexes.getSkillName(), wildernessReflexes);

        Skill expertMonsterHunter = new Skill(EXPERT_MONSTER_HUNTER, "Each point in this skil increases damage done to wither by 5%", 5);
        expertMonsterHunter.getPrerequesiteSkills().put(wildernessReflexes, 5);
        expertMonsterHunter.getPrerequesiteSkills().put(wildernessToughness, 5);
        tree.put(expertMonsterHunter.getSkillName(), expertMonsterHunter);

        Skill monsterLessons = new Skill(MONSTER_LESSONS, "Increase EXP gain for killing mobs by 10%", 5);
        monsterLessons.getPrerequesiteSkills().put(expertMonsterHunter, 5);
        tree.put(monsterLessons.getSkillName(), monsterLessons);

        Skill wildernessDeftness = new Skill(WILDERNESS_DEFTNESS, "Each point in this skill increases dodge chance when attached by hostile mobs by 2%", 5);
        wildernessDeftness.getPrerequesiteSkills().put(expertMonsterHunter, 5);
        tree.put(wildernessDeftness.getSkillName(), wildernessDeftness);

        Skill bossHunter = new Skill(BOSS_HUNTER, "Each point in this skill increases damage done to wither by 5%", 5);
        bossHunter.getPrerequesiteSkills().put(expertMonsterHunter, 5);
        tree.put(bossHunter.getSkillName(), bossHunter);

        Skill masterMonsterHunter = new Skill(MASTER_MONSTER_HUNTER, "Each point in this skill increases damage done to Ender Dragon by 5%", 5);
        masterMonsterHunter.getPrerequesiteSkills().put(wildernessDeftness, 5);
        masterMonsterHunter.getPrerequesiteSkills().put(bossHunter, 5);
        tree.put(masterMonsterHunter.getSkillName(), masterMonsterHunter);

        Skill monsterLore = new Skill(MONSTER_LORE, "Increase EXP gain for killing mobs by 10%", 1);
        monsterLessons.getPrerequesiteSkills().put(masterMonsterHunter, 5);
        tree.put(monsterLore.getSkillName(), monsterLore);

        Skill situationalAwareness = new Skill(SITUATIONAL_AWARENESS, "Each point in this skill increases critical strike chance to all mobs by 5%", 5);
        situationalAwareness.getPrerequesiteSkills().put(masterMonsterHunter, 5);
        tree.put(situationalAwareness.getSkillName(), situationalAwareness);

        Skill deftStrikes = new Skill(DEFT_STRIKES, "Each point in this skill increases damage done to all mobs by 5%", 5);
        deftStrikes.getPrerequesiteSkills().put(masterMonsterHunter, 5);
        tree.put(deftStrikes.getSkillName(), deftStrikes);

        Skill calluses = new Skill(CALLUSES, "Each point in this skill decreases damage taken from all mobs by 5%", 5);
        calluses.getPrerequesiteSkills().put(masterMonsterHunter, 5);
        tree.put(calluses.getSkillName(), calluses);

        Skill flexibility = new Skill(FLEXIBILITY, "Each point in this skill increases dodge chance when attacked by mobs by 5%", 5);
        flexibility.getPrerequesiteSkills().put(masterMonsterHunter, 5);
        tree.put(flexibility.getSkillName(), flexibility);

        //Add Children to skills
        chickenCatcher.getChildSkills().put(snares, 5);

        gameHunter.getChildSkills().put(gameCleaning, 5);

        noviceMonsterHunting.getChildSkills().put(undeadStalker, 5);
        noviceMonsterHunting.getChildSkills().put(wildernessResourcefulness, 5);

        undeadStalker.getChildSkills().put(pestControl, 5);

        snares.getChildSkills().put(gameCleaning, 5);

        gameCleaning.getChildSkills().put(wildernessResourcefulness, 5);
        gameCleaning.getChildSkills().put(expertSkinning, 5);

        wildernessResourcefulness.getChildSkills().put(pestControl, 5);
        wildernessResourcefulness.getChildSkills().put(scavenger, 5);

        pestControl.getChildSkills().put(intermediateMonsterHunter, 5);

        expertSkinning.getChildSkills().put(scavenger, 5);

        intermediateMonsterHunter.getChildSkills().put(scavenger, 5);
        intermediateMonsterHunter.getChildSkills().put(hunterInsight, 5);
        intermediateMonsterHunter.getChildSkills().put(wildernessReflexes, 5);
        intermediateMonsterHunter.getChildSkills().put(wildernessToughness, 5);

        wildernessReflexes.getChildSkills().put(expertMonsterHunter, 5);

        wildernessToughness.getChildSkills().put(expertMonsterHunter, 5);

        expertMonsterHunter.getChildSkills().put(wildernessDeftness, 5);
        expertMonsterHunter.getChildSkills().put(monsterLessons, 5);
        expertMonsterHunter.getChildSkills().put(bossHunter, 5);

        wildernessDeftness.getChildSkills().put(masterMonsterHunter, 5);

        bossHunter.getChildSkills().put(masterMonsterHunter, 5);

        masterMonsterHunter.getChildSkills().put(monsterLore, 5);
        masterMonsterHunter.getChildSkills().put(situationalAwareness, 5);
        masterMonsterHunter.getChildSkills().put(deftStrikes, 5);
        masterMonsterHunter.getChildSkills().put(calluses, 5);
        masterMonsterHunter.getChildSkills().put(flexibility, 5);
    }

    public HashMap<Enum, Skill> getTree() {
        return this.tree;
    }
}
