package us.toh.leetmmo.skills.normal.fishing.skilltree;

import us.toh.leetmmo.skills.Skill;

import java.util.HashMap;

import static us.toh.leetmmo.skills.normal.NormalSkillEnums.FishingSkillNames.*;

public class FishingSkillTree {

    private HashMap<Enum, Skill> tree = new HashMap<Enum, Skill>();

    public FishingSkillTree() {
        buildSkillTree();
    }

    private void buildSkillTree() {
        Skill basicFishing = new Skill(BASIC_FISHING, "Gain ability to use fishing rod", 1);
        tree.put(basicFishing.getSkillName(), basicFishing);

        Skill fishingBait = new Skill(FISHING_BAIT, "Decrease fishing wait time by 5%", 1);
        tree.put(fishingBait.getSkillName(), fishingBait);

        Skill fishingTechnique = new Skill(FISHING_TECHNIQUE, "Decrease fishing wait time by 5%", 1);
        tree.put(fishingTechnique.getSkillName(), fishingTechnique);

        Skill fishermanFolkStories = new Skill(FISHERMAN_FOLK_STORIES, "Increases EXP gained from successful catch by 5%", 1);
        tree.put(fishermanFolkStories.getSkillName(), fishermanFolkStories);

        Skill rodCare = new Skill(ROD_CARE, "Increases fishing rod durability by 10%", 1);
        tree.put(rodCare.getSkillName(), rodCare);

        Skill currentWatcher = new Skill(CURRENT_WATCHER, "Each point in this skill increases double drop chance of raw cod by 5%", 1);
        tree.put(currentWatcher.getSkillName(), currentWatcher);

        Skill grizzlyInstincts = new Skill(GRIZZLY_INSTINCTS, "Each point in this skill increases double drop chance of raw salmon by 5%", 1);
        tree.put(grizzlyInstincts.getSkillName(), grizzlyInstincts);

        Skill oceanography = new Skill(OCEANOGRAPHY, "Each point in this skill increases double drop chance of tropical fish by 5%", 1);
        tree.put(oceanography.getSkillName(), oceanography);

        Skill deadliestCatch = new Skill(DEADLIEST_CATCH, "Each point in this skill increases double drop chance of pufferfish by 5%", 1);
        tree.put(deadliestCatch.getSkillName(), deadliestCatch);

        Skill fisherman = new Skill(FISHERMAN, "Get a 1% chance of a gold nugget per catch", 1);
        tree.put(fisherman.getSkillName(), fisherman);

        Skill fishermanDiet = new Skill(FISHERMAN_DIET, "Get 10% more health from eating cooked fish", 1);
        tree.put(fishermanDiet.getSkillName(), fishermanDiet);

        Skill pirateLore = new Skill(PIRATE_LORE, "Get a 1% chance of a gold bar per catch", 1);
        tree.put(pirateLore.getSkillName(), pirateLore);

        Skill pirateSuperstition = new Skill(PIRATE_SUPERSTITION, "Increases damage done to drowned by 50%", 1);
        tree.put(pirateSuperstition.getSkillName(), pirateSuperstition);

        Skill trolling = new Skill(TROLLING, "Decrease fishing wait time by 20%", 1);
        tree.put(trolling.getSkillName(), trolling);

        Skill nets = new Skill(NETS, "Each point in this skill increases double drop chance of all fish by 5%", 4);
        tree.put(nets.getSkillName(), nets);

        Skill fishermanLuck = new Skill(FISHERMAN_LUCK, "Get a 5% chance of an iron bar per catch", 1);
        tree.put(fishermanLuck.getSkillName(), fishermanLuck);

        Skill proficientFisherman = new Skill(PROFICIENT_FISHERMAN, "Increase to 5% chance of a gold nugget per catch. Get 1% chance of diamond per catch", 1);
        tree.put(proficientFisherman.getSkillName(), proficientFisherman);

        Skill sushi = new Skill(SUSHI, "Get 50% more health from eating raw fish", 1);
        tree.put(sushi.getSkillName(), sushi);

        Skill fishCleaning = new Skill(FISH_CLEANING, "Get 50% more health from eating cooked fish", 1);
        tree.put(fishCleaning.getSkillName(), fishCleaning);

        Skill pirateMaps = new Skill(PIRATE_MAPS, "Increase to 20% chance of iron bar. Get 5% chance of emerald per catch", 1);
        tree.put(pirateMaps.getSkillName(), pirateMaps);

        Skill sonar = new Skill(SONAR, "Decrease fishing wait time by 25%", 1);
        tree.put(sonar.getSkillName(), sonar);

        Skill freshwaterBaits = new Skill(FRESHWATER_BAITS, "Each point in this skill increases double drop chance of raw cod and raw salmon by 10%", 5);
        tree.put(freshwaterBaits.getSkillName(), freshwaterBaits);

        Skill oceanBaits = new Skill(OCEAN_BAITS, "Each point in this skill increases double drop chance of tropical fish and pufferfish by 10%", 5);
        tree.put(oceanBaits.getSkillName(), oceanBaits);

        Skill masterAngler = new Skill(MASTER_ANGLER, "Increases to 25% chance of gold nugget per catch. Get a 5% chance of a diamond per catch", 1);
        tree.put(masterAngler.getSkillName(), masterAngler);

        Skill pirateLegends = new Skill(PIRATE_LEGENDS, "Increases to 50% chance of iron bar. Increases to 25% chance of gold bar. Get a 10% chance of emerald per catch", 1);
        tree.put(pirateLegends.getSkillName(), pirateLegends);

        Skill seafarerWisdom = new Skill(SEAFARER_WISDOM, "Increases EXP gained from fishing by 20%", 1);
        tree.put(seafarerWisdom.getSkillName(), seafarerWisdom);

        Skill aquaticInsight = new Skill(AQUATIC_INSIGHT, "Get EXP per fish caught", 1);
        tree.put(aquaticInsight.getSkillName(), aquaticInsight);

        Skill shipwreckDiving = new Skill(SHIPWRECK_DIVING, "Increases to 25% chance of emerald per catch. Increases to 10% chance of diamond per catch", 1);
        tree.put(shipwreckDiving.getSkillName(), shipwreckDiving);

        Skill bottomTrawling = new Skill(BOTTOM_TRAWLING, "Increases double drop chance of tropical fish and pufferfish by 25%", 1);
        tree.put(bottomTrawling.getSkillName(), bottomTrawling);

        Skill electrofishing = new Skill(ELECTROFISHING, "Increases double drop chance of raw cod and raw salmon by 25%", 1);
        tree.put(electrofishing.getSkillName(), electrofishing);

        Skill sustainableFishing = new Skill(SUSTAINABLE_FISHING, "Each point in this skill increases triple drop chance of all fish by 5%", 1);
        tree.put(sustainableFishing.getSkillName(), sustainableFishing);

        Skill cleanWaters = new Skill(CLEAN_WATERS, "Get experience for all items caught while fishing", 1);
        tree.put(cleanWaters.getSkillName(), cleanWaters);

        //Add Children to skills

    }

    public HashMap<Enum, Skill> getTree() {
        return this.tree;
    }
}
