package us.toh.leetmmo.skills.normal;

import java.util.HashMap;
import java.util.Map;

public class NormalSkillEnums {

    public enum ConstructionSkillNames {

    }

    public enum CraftingSkillNames {

    }

    public enum FarmingSkillNames {
        BASIC_AGRICULTURE("BASIC_AGRICULTURE"),
        BLIGHT_PROTECTION("BLIGHT_PROTECTION"),
        CACAO_CULTIVATION("CACAO_CULTIVATION"),
        CHEMICAL_PESTICIDES("CHEMICAL_PESTICIDES"),
        CROP_ROTATION("CROP_ROTATION"),
        CUCURBITA_CULTIVATION("CUCURBITA_CULTIVATION"),
        DAUCUS_CULTIVATION("DAUCUS_CULTIVATION"),
        FARMING_MASTERY("FARMING_MASTERY"),
        FERTILIZER("FERTILIZER"),
        FUNGAL_FARMING("FUNGAL_FARMING"),
        GMO_CROPS("GMO_CROPS"),
        HYBRIDIZATION("HYBRIDIZATION"),
        IANATUS_CULTIVATION("IANATUS_CULTIVATION"),
        IMPROVED_PHOTOSYNTHESIS("IMPROVED_PHOTOSYNTHESIS"),
        INDOOR_FUNGICULTURE("INDOOR_FUNGICULTURE"),
        MECHANIZED_HARVESTING("MECHANIZED_HARVESTING"),
        PLANTATIONS("PLANTATIONS"),
        SACCHARUM_CULTIVATION("SACCHARUM_CULTIVATION"),
        TRANSENVIRONMENTAL_CULTIVATION("TRANSENVIRONMENTAL_CULTIVATION"),
        TRELLIS_GOURD_TECHNIQUES("TRELLIS_GOURD_TECHNIQUES"),
        TRITICUM_CULTIVATION("TRITICUM_CULTIVATION"),
        TUBEROSEM_CULTIVATION("TUBEROSEM_CULTIVATION"),
        VULGARUS_CULTIVATION("VULGARUS_CULTIVATION"),
        WEED_REMOVAL("WEED_REMOVAL");

        private String skillName;

        FarmingSkillNames(String skillName) {
            this.skillName = skillName;
        }

        public String toString() {
            return skillName;
        }

        //****** Reverse Lookup Implementation************//

        //Lookup table
        private static final Map<String, FarmingSkillNames> lookup = new HashMap<>();

        //Populate the lookup table on loading time
        static
        {
            for(FarmingSkillNames env : FarmingSkillNames.values())
            {
                lookup.put(env.toString(), env);
            }
        }

        //This method can be used for reverse lookup purpose
        public static FarmingSkillNames get(String url)
        {
            return lookup.get(url);
        }
    }

    public enum FishingSkillNames {
        BASIC_FISHING("BASIC_FISHING"),
        FISHING_BAIT("FISHING_BAIT"),
        FISHING_TECHNIQUE("FISHING_TECHNIQUE"),
        FISHERMAN_FOLK_STORIES("FISHERMAN_FOLK_STORIES"),
        ROD_CARE("ROD_CARE"),
        CURRENT_WATCHER("CURRENT_WATCHER"),
        GRIZZLY_INSTINCTS("GRIZZLY_INSTINCTS"),
        OCEANOGRAPHY("OCEANOGRAPHY"),
        DEADLIEST_CATCH("DEADLIEST_CATCH"),
        FISHERMAN("FISHERMAN"),
        FISHERMAN_DIET("FISHERMAN_DIET"),
        PIRATE_LORE("PIRATE_LORE"),
        PIRATE_SUPERSTITION("PIRATE_SUPERSTITION"),
        FISHERMAN_LUCK("FISHERMAN_LUCK"),
        TROLLING("TROLLING"),
        NETS("NETS"),
        PROFICIENT_FISHERMAN("PROFICIENT_FISHERMAN"),
        SUSHI("SUSHI"),
        FISH_CLEANING("FISH_CLEANING"),
        PIRATE_MAPS("PIRATE_MAPS"),
        SONAR("SONAR"),
        FRESHWATER_BAITS("FRESHWATER_BAITS"),
        OCEAN_BAITS("OCEAN_BAITS"),
        MASTER_ANGLER("MASTER_ANGLER"),
        SEAFARER_WISDOM("SEAFARER_WISDOM"),
        AQUATIC_INSIGHT("AQUATIC_INSIGHT"),
        PIRATE_LEGENDS("PIRATE_LEGENDS"),
        BOTTOM_TRAWLING("BOTTOM_TRAWLING"),
        ELECTROFISHING("ELECTROFISHING"),
        SHIPWRECK_DIVING("SHIPWRECK_DIVING"),
        SUSTAINABLE_FISHING("SUSTAINABLE_FISHING"),
        CLEAN_WATERS("CLEAN_WATERS");

        private String skillName;

        FishingSkillNames(String skillName) {
            this.skillName = skillName;
        }

        public String toString() {
            return skillName;
        }

        //****** Reverse Lookup Implementation************//

        //Lookup table
        private static final Map<String, FishingSkillNames> lookup = new HashMap<>();

        //Populate the lookup table on loading time
        static
        {
            for(FishingSkillNames env : FishingSkillNames.values())
            {
                lookup.put(env.toString(), env);
            }
        }

        //This method can be used for reverse lookup purpose
        public static FishingSkillNames get(String url)
        {
            return lookup.get(url);
        }
    }

    public enum HuntingSkillNames {
        BOSS_HUNTER("BOSS_HUNTER"),
        CALLUSES("CALLUSES"),
        CHICKEN_CATCHER("CHICKEN_CATCHER"),
        DEFT_STRIKES("DEFT_STRIKES"),
        EXPERT_MONSTER_HUNTER("EXPERT_MONSTER_HUNTER"),
        EXPERT_SKINNING("EXPERT_SKINNING"),
        FLEXIBILITY("FLEXIBILITY"),
        GAME_CLEANING("GAME_CLEANING"),
        GAME_HUNTER("GAME_HUNTER"),
        HUNTER_INSIGHT("HUNTER_INSIGHT"),
        INTERMEDIATE_MONSTER_HUNTER("INTERMEDIATE_MONSTER_HUNTER"),
        MASTER_MONSTER_HUNTER("MASTER_MONSTER_HUNTER"),
        MONSTER_LESSONS("MONSTER_LESSONS"),
        MONSTER_LORE("MONSTER_LORE"),
        NOVICE_MONSTER_HUNTING("NOVICE_MONSTER_HUNTING"),
        PEST_CONTROL("PEST_CONTROL"),
        SCAVENGER("SCAVENGER"),
        SITUATIONAL_AWARENESS("SITUATIONAL_AWARENESS"),
        SNARES("SNARES"),
        UNDEAD_STALKER("UNDEAD_STALKER"),
        WILDERNESS_DEFTNESS("WILDERNESS_DEFTNESS"),
        WILDERNESS_REFLEXES("WILDERNESS_REFLEXES"),
        WILDERNESS_RESOURCEFULNESS("WILDERNESS_RESOURCEFULNESS"),
        WILDERNESS_TOUGHNESS("WILDERNESS_TOUGHNESS");

        private String skillName;

        HuntingSkillNames(String skillName) {
            this.skillName = skillName;
        }

        public String toString() {
            return skillName;
        }

        //****** Reverse Lookup Implementation************//

        //Lookup table
        private static final Map<String, HuntingSkillNames> lookup = new HashMap<>();

        //Populate the lookup table on loading time
        static
        {
            for(HuntingSkillNames env : HuntingSkillNames.values())
            {
                lookup.put(env.toString(), env);
            }
        }

        //This method can be used for reverse lookup purpose
        public static HuntingSkillNames get(String url)
        {
            return lookup.get(url);
        }
    }

    public enum HusbandrySkillNames {

    }

    public enum MiningSkillNames {
        ANTHRACITE_EXTRACTION("ANTHRACITE_EXTRACTION"),
        BASIC_MINING("BASIC_MINING"),
        BITUMINOUS_EXTRACTION("BITUMINOUS_EXTRACTION"),
        COMMINUTION("COMMINUTION"),
        CUTTING_EFFICIENCY("CUTTING_EFFICIENCY"),
        ELECROMAGNETIC_RADIATION("ELECROMAGNETIC_RADIATION"),
        BLAST_MINING("BLAST_MINING"),
        FOOLS_GOLD("FOOLS_GOLD"),
        GEOENGINEER("GEOENGINEER"),
        GRAVITY_SEPARATION("GRAVITY_SEPARATION"),
        HEWER("HEWER"),
        HYDRAULIC_MINING("HYDRAULIC_MINING"),
        LAZURITE_EXTRACTION("LAZURITE_EXTRACTION"),
        LEACHING("LEACHING"),
        LIGNITE_EXTRACTION("LIGNITE_EXTRACTION"),
        MAGNETITE_FROTH_FLOTATION("MAGNETITE_FROTH_FLOTATION"),
        MANTLE_DRILLING("MANTLE_DRILLING"),
        MINING_EXPERTISE("MINING_EXPERTISE"),
        MINING_MASTERY("MINING_MASTERY"),
        POSTLAPIDARY_OILING("POSTLAPIDARY_OILING"),
        POWER_MINING("POWER_MINING"),
        PROSPECTOR("PROSPECTOR"),
        SILICONE_EXTRACTION("SILICONE_EXTRACTION"),
        TEMPLE_DESECRATOR("TEMPLE_DESECRATOR"),
        THERMOLUMINESCENCE_DATING("THERMOLUMINESCENCE_DATING"),
        TUNNEL_DWELLER("TUNNEL_DWELLER"),
        WURTZITE_BORON_NITRIDE_DRILL("WURTZITE_BORON_NITRIDE_DRILL"),
        XRAY_FLOURESCENCE("XRAY_FLOURESCENCE");

        private String skillName;

        MiningSkillNames(String skillName) {
            this.skillName = skillName;
        }

        public String toString() {
            return skillName;
        }

        //****** Reverse Lookup Implementation************//

        //Lookup table
        private static final Map<String, MiningSkillNames> lookup = new HashMap<>();

        //Populate the lookup table on loading time
        static
        {
            for(MiningSkillNames env : MiningSkillNames.values())
            {
                lookup.put(env.toString(), env);
            }
        }

        //This method can be used for reverse lookup purpose
        public static MiningSkillNames get(String url)
        {
            return lookup.get(url);
        }
    }

    public enum WoodcuttingSkillNames {

    }

    public enum SmeltingSkillNames {

    }

    public enum SmithingSkillNames {

    }

    public static NormalSkillEnums.FarmingSkillNames isFarmingSkillEnum(String str) {
        for (FarmingSkillNames me : FarmingSkillNames.values()) {
            if (me.name().equalsIgnoreCase(str))
                return me;
        }
        return null;
    }

    public static NormalSkillEnums.MiningSkillNames isMiningSkillEnum(String str) {
        for (MiningSkillNames me : MiningSkillNames.values()) {
            if (me.name().equalsIgnoreCase(str))
                return me;
        }
        return null;
    }
}
