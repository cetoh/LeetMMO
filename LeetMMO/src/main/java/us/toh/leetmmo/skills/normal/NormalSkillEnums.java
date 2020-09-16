package us.toh.leetmmo.skills.normal;

public class NormalSkillEnums {

    public enum ConstructionSkillNames {

    }

    public enum CraftingSkillNames {

    }

    public enum FarmingSkillNames {
        BASIC_AGRICULTURE,
        BLIGHT_PROTECTION,
        CACAO_CULTIVATION,
        CHEMICAL_PESTICIDES,
        CROP_ROTATION,
        CUCURBITA_CULTIVATION,
        DAUCUS_CULTIVATION,
        FARMING_MASTERY,
        FERTILIZER,
        FUNGAL_FARMING,
        GMO_CROPS,
        HYBRIDIZATION,
        IANATUS_CULTIVATION,
        IMPROVED_PHOTOSYNTHESIS,
        INDOOR_FUNGICULTURE,
        MECHANIZED_HARVESTING,
        PLANTATIONS,
        SACCHARUM_CULTIVATION,
        TRANSENVIRONMENTAL_CULTIVATION,
        TRELLIS_GOURD_TECHNIQUES,
        TRITICUM_CULTIVATION,
        TUBEROSEM_CULTIVATION,
        VULGARUS_CULTIVATION,
        WEED_REMOVAL

    }

    public enum FishingSkillNames {
        BASIC_FISHING,
        FISHING_BAIT,
        FISHING_TECHNIQUE,
        FISHERMAN_FOLK_STORIES,
        ROD_CARE,
        CURRENT_WATCHER,
        GRIZZLY_INSTINCTS,
        OCEANOGRAPHY,
        DEADLIEST_CATCH,
        FISHERMAN,
        FISHERMAN_DIET,
        PIRATE_LORE,
        PIRATE_SUPERSTITION,
        FISHERMAN_LUCK,
        TROLLING,
        NETS,
        PROFICIENT_FISHERMAN,
        SUSHI,
        FISH_CLEANING,
        PIRATE_MAPS,
        SONAR,
        FRESHWATER_BAITS,
        OCEAN_BAITS,
        MASTER_ANGLER,
        SEAFARER_WISDOM,
        AQUATIC_INSIGHT,
        PIRATE_LEGENDS,
        BOTTOM_TRAWLING,
        ELECTROFISHING,
        SHIPWRECK_DIVING,
        SUSTAINABLE_FISHING,
        CLEAN_WATERS
    }

    public enum HusbandrySkillNames {

    }

    public enum HuntingSkillNames {

    }

    public enum MiningSkillNames {
        ANTHRACITE_EXTRACTION,
        BASIC_MINING,
        BITUMINOUS_EXTRACTION,
        COMMINUTION,
        CUTTING_EFFICIENCY,
        ELECROMAGNETIC_RADIATION,
        BLAST_MINING,
        FOOLS_GOLD,
        GEOENGINEER,
        GRAVITY_SEPARATION,
        HEWER,
        HYDRAULIC_MINING,
        LAZURITE_EXTRACTION_EFFICIENCY,
        LEACHING,
        LIGNITE_EXTRACTION,
        MAGNETITE_FROTH_FLOTATION,
        MANTLE_DRILLING,
        MINING_EXPERTISE,
        MINING_MASTERY,
        POSTLAPIDARY_OILING,
        POWER_MINING,
        PROSPECTOR,
        SILICONE_EXTRACTION,
        TEMPLE_DESECRATOR,
        THERMOLUMINESCENCE_DATING,
        TUNNEL_DWELLER,
        WURTZITE_BORON_NITRIDE_DRILL,
        XRAY_FLOURESCENCE

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
