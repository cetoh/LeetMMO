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
        fishingBait.getPrerequesiteSkills().put(basicFishing, basicFishing.getSkillPointRequirement());
        tree.put(fishingBait.getSkillName(), fishingBait);

        Skill fishingTechnique = new Skill(FISHING_TECHNIQUE, "Decrease fishing wait time by 5%", 1);
        fishingTechnique.getPrerequesiteSkills().put(fishingTechnique, fishingTechnique.getSkillPointRequirement());
        tree.put(fishingTechnique.getSkillName(), fishingTechnique);

        Skill fishermanFolkStories = new Skill(FISHERMAN_FOLK_STORIES, "Increases EXP gained from successful catch by 5%", 1);
        fishermanFolkStories.getPrerequesiteSkills().put(fishermanFolkStories, fishermanFolkStories.getSkillPointRequirement());
        tree.put(fishermanFolkStories.getSkillName(), fishermanFolkStories);

        Skill rodCare = new Skill(ROD_CARE, "Increases fishing rod durability by 10%", 1);
        rodCare.getPrerequesiteSkills().put(fishingBait, fishingBait.getSkillPointRequirement());
        rodCare.getPrerequesiteSkills().put(fishingTechnique, fishingTechnique.getSkillPointRequirement());
        tree.put(rodCare.getSkillName(), rodCare);

        Skill currentWatcher = new Skill(CURRENT_WATCHER, "Each point in this skill increases double drop chance of raw cod by 5%", 1);
        currentWatcher.getPrerequesiteSkills().put(rodCare, rodCare.getSkillPointRequirement());
        tree.put(currentWatcher.getSkillName(), currentWatcher);

        Skill grizzlyInstincts = new Skill(GRIZZLY_INSTINCTS, "Each point in this skill increases double drop chance of raw salmon by 5%", 1);
        grizzlyInstincts.getPrerequesiteSkills().put(rodCare, rodCare.getSkillPointRequirement());
        tree.put(grizzlyInstincts.getSkillName(), grizzlyInstincts);

        Skill oceanography = new Skill(OCEANOGRAPHY, "Each point in this skill increases double drop chance of tropical fish by 5%", 1);
        oceanography.getPrerequesiteSkills().put(rodCare, rodCare.getSkillPointRequirement());
        tree.put(oceanography.getSkillName(), oceanography);

        Skill deadliestCatch = new Skill(DEADLIEST_CATCH, "Each point in this skill increases double drop chance of pufferfish by 5%", 1);
        deadliestCatch.getPrerequesiteSkills().put(rodCare, rodCare.getSkillPointRequirement());
        tree.put(deadliestCatch.getSkillName(), deadliestCatch);

        Skill fisherman = new Skill(FISHERMAN, "Get a 1% chance of a gold nugget per catch", 1);
        fisherman.getPrerequesiteSkills().put(currentWatcher, currentWatcher.getSkillPointRequirement());
        fisherman.getPrerequesiteSkills().put(grizzlyInstincts, grizzlyInstincts.getSkillPointRequirement());
        fisherman.getPrerequesiteSkills().put(oceanography, oceanography.getSkillPointRequirement());
        fisherman.getPrerequesiteSkills().put(deadliestCatch, deadliestCatch.getSkillPointRequirement());
        tree.put(fisherman.getSkillName(), fisherman);

        Skill fishermanDiet = new Skill(FISHERMAN_DIET, "Get 10% more health from eating cooked fish", 1);
        fishermanDiet.getPrerequesiteSkills().put(fisherman, fisherman.getSkillPointRequirement());
        tree.put(fishermanDiet.getSkillName(), fishermanDiet);

        Skill pirateLore = new Skill(PIRATE_LORE, "Get a 1% chance of a gold bar per catch", 1);
        pirateLore.getPrerequesiteSkills().put(fisherman, fisherman.getSkillPointRequirement());
        pirateLore.getPrerequesiteSkills().put(fishermanFolkStories, fishermanFolkStories.getSkillPointRequirement());
        tree.put(pirateLore.getSkillName(), pirateLore);

        Skill pirateSuperstition = new Skill(PIRATE_SUPERSTITION, "Increases damage done to drowned by 50%", 1);
        pirateSuperstition.getPrerequesiteSkills().put(pirateLore, pirateLore.getSkillPointRequirement());
        tree.put(pirateSuperstition.getSkillName(), pirateSuperstition);

        Skill trolling = new Skill(TROLLING, "Decrease fishing wait time by 20%", 1);
        trolling.getPrerequesiteSkills().put(fisherman, fisherman.getSkillPointRequirement());
        tree.put(trolling.getSkillName(), trolling);

        Skill nets = new Skill(NETS, "Each point in this skill increases double drop chance of all fish by 5%", 4);
        nets.getPrerequesiteSkills().put(fisherman, fisherman.getSkillPointRequirement());
        tree.put(nets.getSkillName(), nets);

        Skill fishermanLuck = new Skill(FISHERMAN_LUCK, "Get a 5% chance of an iron bar per catch", 1);
        fishermanLuck.getPrerequesiteSkills().put(pirateLore, pirateLore.getSkillPointRequirement());
        tree.put(fishermanLuck.getSkillName(), fishermanLuck);

        Skill proficientFisherman = new Skill(PROFICIENT_FISHERMAN, "Increase to 5% chance of a gold nugget per catch. Get 1% chance of diamond per catch", 1);
        proficientFisherman.getPrerequesiteSkills().put(trolling, trolling.getSkillPointRequirement());
        proficientFisherman.getPrerequesiteSkills().put(nets, nets.getSkillPointRequirement());
        tree.put(proficientFisherman.getSkillName(), proficientFisherman);

        Skill sushi = new Skill(SUSHI, "Get 50% more health from eating raw fish", 1);
        sushi.getPrerequesiteSkills().put(proficientFisherman, proficientFisherman.getSkillPointRequirement());
        tree.put(sushi.getSkillName(), sushi);

        Skill fishCleaning = new Skill(FISH_CLEANING, "Get 50% more health from eating cooked fish", 1);
        fishCleaning.getPrerequesiteSkills().put(sushi, sushi.getSkillPointRequirement());
        tree.put(fishCleaning.getSkillName(), fishCleaning);

        Skill pirateMaps = new Skill(PIRATE_MAPS, "Increase to 20% chance of iron bar. Get 5% chance of emerald per catch", 1);
        pirateMaps.getPrerequesiteSkills().put(proficientFisherman, proficientFisherman.getSkillPointRequirement());
        pirateMaps.getPrerequesiteSkills().put(fishermanLuck, fishermanLuck.getSkillPointRequirement());
        tree.put(pirateMaps.getSkillName(), pirateMaps);

        Skill sonar = new Skill(SONAR, "Decrease fishing wait time by 25%", 1);
        sonar.getPrerequesiteSkills().put(proficientFisherman, proficientFisherman.getSkillPointRequirement());
        tree.put(sonar.getSkillName(), sonar);

        Skill freshwaterBaits = new Skill(FRESHWATER_BAITS, "Each point in this skill increases double drop chance of raw cod and raw salmon by 10%", 5);
        freshwaterBaits.getPrerequesiteSkills().put(proficientFisherman, proficientFisherman.getSkillPointRequirement());
        tree.put(freshwaterBaits.getSkillName(), freshwaterBaits);

        Skill oceanBaits = new Skill(OCEAN_BAITS, "Each point in this skill increases double drop chance of tropical fish and pufferfish by 10%", 5);
        oceanBaits.getPrerequesiteSkills().put(proficientFisherman, proficientFisherman.getSkillPointRequirement());
        tree.put(oceanBaits.getSkillName(), oceanBaits);

        Skill masterAngler = new Skill(MASTER_ANGLER, "Increases to 25% chance of gold nugget per catch. Get a 5% chance of a diamond per catch", 1);
        masterAngler.getPrerequesiteSkills().put(sonar, sonar.getSkillPointRequirement());
        masterAngler.getPrerequesiteSkills().put(freshwaterBaits, freshwaterBaits.getSkillPointRequirement());
        masterAngler.getPrerequesiteSkills().put(oceanBaits, oceanography.getSkillPointRequirement());
        tree.put(masterAngler.getSkillName(), masterAngler);

        Skill pirateLegends = new Skill(PIRATE_LEGENDS, "Increases to 50% chance of iron bar. Increases to 25% chance of gold bar. Get a 10% chance of emerald per catch", 1);
        pirateLegends.getPrerequesiteSkills().put(pirateMaps, pirateMaps.getSkillPointRequirement());
        pirateLegends.getPrerequesiteSkills().put(masterAngler, masterAngler.getSkillPointRequirement());
        tree.put(pirateLegends.getSkillName(), pirateLegends);

        Skill seafarerWisdom = new Skill(SEAFARER_WISDOM, "Increases EXP gained from fishing by 20%", 1);
        seafarerWisdom.getPrerequesiteSkills().put(masterAngler, masterAngler.getSkillPointRequirement());
        tree.put(seafarerWisdom.getSkillName(), seafarerWisdom);

        Skill aquaticInsight = new Skill(AQUATIC_INSIGHT, "Get EXP per fish caught", 1);
        aquaticInsight.getPrerequesiteSkills().put(seafarerWisdom, seafarerWisdom.getSkillPointRequirement());
        tree.put(aquaticInsight.getSkillName(), aquaticInsight);

        Skill shipwreckDiving = new Skill(SHIPWRECK_DIVING, "Increases to 25% chance of emerald per catch. Increases to 10% chance of diamond per catch", 1);
        shipwreckDiving.getPrerequesiteSkills().put(pirateLegends, pirateLegends.getSkillPointRequirement());
        tree.put(shipwreckDiving.getSkillName(), shipwreckDiving);

        Skill bottomTrawling = new Skill(BOTTOM_TRAWLING, "Increases double drop chance of tropical fish and pufferfish by 25%", 1);
        bottomTrawling.getPrerequesiteSkills().put(masterAngler, masterAngler.getSkillPointRequirement());
        tree.put(bottomTrawling.getSkillName(), bottomTrawling);

        Skill electrofishing = new Skill(ELECTROFISHING, "Increases double drop chance of raw cod and raw salmon by 25%", 1);
        electrofishing.getPrerequesiteSkills().put(masterAngler, masterAngler.getSkillPointRequirement());
        tree.put(electrofishing.getSkillName(), electrofishing);

        Skill sustainableFishing = new Skill(SUSTAINABLE_FISHING, "Each point in this skill increases triple drop chance of all fish by 5%", 1);
        sustainableFishing.getPrerequesiteSkills().put(bottomTrawling, bottomTrawling.getSkillPointRequirement());
        sustainableFishing.getPrerequesiteSkills().put(electrofishing, electrofishing.getSkillPointRequirement());
        tree.put(sustainableFishing.getSkillName(), sustainableFishing);

        Skill cleanWaters = new Skill(CLEAN_WATERS, "Get experience for all items caught while fishing", 1);
        cleanWaters.getPrerequesiteSkills().put(sustainableFishing, sustainableFishing.getSkillPointRequirement());
        tree.put(cleanWaters.getSkillName(), cleanWaters);

        //Add Children to skills
        basicFishing.getChildSkills().put(fishingBait, fishingBait.getSkillPointRequirement());
        basicFishing.getChildSkills().put(fishingTechnique, fishingTechnique.getSkillPointRequirement());
        basicFishing.getChildSkills().put(fishermanFolkStories, fishermanFolkStories.getSkillPointRequirement());

        fishingBait.getChildSkills().put(rodCare, rodCare.getSkillPointRequirement());

        fishingTechnique.getChildSkills().put(rodCare, rodCare.getSkillPointRequirement());

        rodCare.getChildSkills().put(currentWatcher, currentWatcher.getSkillPointRequirement());
        rodCare.getChildSkills().put(grizzlyInstincts, grizzlyInstincts.getSkillPointRequirement());
        rodCare.getChildSkills().put(oceanography, oceanography.getSkillPointRequirement());
        rodCare.getChildSkills().put(deadliestCatch, deadliestCatch.getSkillPointRequirement());

        currentWatcher.getChildSkills().put(fisherman, fisherman.getSkillPointRequirement());

        grizzlyInstincts.getChildSkills().put(fisherman, fisherman.getSkillPointRequirement());

        oceanography.getChildSkills().put(fisherman, fisherman.getSkillPointRequirement());

        deadliestCatch.getChildSkills().put(fisherman, fisherman.getSkillPointRequirement());

        fisherman.getChildSkills().put(fishermanDiet, fishermanDiet.getSkillPointRequirement());
        fisherman.getChildSkills().put(trolling, trolling.getSkillPointRequirement());
        fisherman.getChildSkills().put(nets, nets.getSkillPointRequirement());
        fisherman.getChildSkills().put(pirateLore, pirateLore.getSkillPointRequirement());

        fishermanFolkStories.getChildSkills().put(pirateLore, pirateLore.getSkillPointRequirement());

        pirateLore.getChildSkills().put(fishermanLuck, fishermanLuck.getSkillPointRequirement());
        pirateLore.getChildSkills().put(pirateSuperstition, pirateSuperstition.getSkillPointRequirement());

        fishermanLuck.getChildSkills().put(pirateMaps, pirateMaps.getSkillPointRequirement());

        trolling.getChildSkills().put(proficientFisherman, proficientFisherman.getSkillPointRequirement());

        nets.getChildSkills().put(proficientFisherman, proficientFisherman.getSkillPointRequirement());

        proficientFisherman.getChildSkills().put(sushi, sushi.getSkillPointRequirement());
        proficientFisherman.getChildSkills().put(sonar, sonar.getSkillPointRequirement());
        proficientFisherman.getChildSkills().put(freshwaterBaits, freshwaterBaits.getSkillPointRequirement());
        proficientFisherman.getChildSkills().put(oceanBaits, oceanBaits.getSkillPointRequirement());
        proficientFisherman.getChildSkills().put(pirateMaps, pirateMaps.getSkillPointRequirement());

        sushi.getChildSkills().put(fishCleaning, fishCleaning.getSkillPointRequirement());

        pirateMaps.getChildSkills().put(pirateLegends, pirateLegends.getSkillPointRequirement());

        sonar.getChildSkills().put(masterAngler, masterAngler.getSkillPointRequirement());

        freshwaterBaits.getChildSkills().put(masterAngler, masterAngler.getSkillPointRequirement());

        oceanBaits.getChildSkills().put(masterAngler, masterAngler.getSkillPointRequirement());

        
    }

    public HashMap<Enum, Skill> getTree() {
        return this.tree;
    }
}
