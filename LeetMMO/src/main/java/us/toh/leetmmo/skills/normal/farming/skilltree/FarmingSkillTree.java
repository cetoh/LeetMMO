package us.toh.leetmmo.skills.normal.farming.skilltree;

import us.toh.leetmmo.skills.Skill;
import us.toh.leetmmo.skills.SkillTree;

import java.util.HashMap;

import static us.toh.leetmmo.skills.normal.NormalSkillEnums.FarmingSkillNames.*;

public class FarmingSkillTree extends SkillTree {

    public FarmingSkillTree() {
        buildSkillTree();
    }

    private void buildSkillTree() {
        Skill basicAgriculture = new Skill(BASIC_AGRICULTURE, "Gain ability to till dirt", 1);
        getTree().put(basicAgriculture.getSkillName(), basicAgriculture);

        Skill triticumCultivation = new Skill(TRITICUM_CULTIVATION, "Gain ability to plant wheat",1);
        triticumCultivation.getPrerequesiteSkills().put(basicAgriculture, basicAgriculture.getSkillPointRequirement());
        getTree().put(triticumCultivation.getSkillName(), triticumCultivation);

        Skill fertilizer = new Skill(FERTILIZER, "Gain ability to use bone meal",3);
        fertilizer.getPrerequesiteSkills().put(triticumCultivation, triticumCultivation.getSkillPointRequirement());
        getTree().put(fertilizer.getSkillName(), fertilizer);

        Skill daucusCultivation = new Skill(DAUCUS_CULTIVATION, "Gain ability to plant carrots",2);
        daucusCultivation.getPrerequesiteSkills().put(triticumCultivation, triticumCultivation.getSkillPointRequirement());
        getTree().put(daucusCultivation.getSkillName(), daucusCultivation);

        Skill weedRemoval = new Skill(WEED_REMOVAL, "Each point in this skill improves wheat double drop chance by 5%",5);
        weedRemoval.getPrerequesiteSkills().put(triticumCultivation, triticumCultivation.getSkillPointRequirement());
        getTree().put(weedRemoval.getSkillName(), weedRemoval);

        Skill cucurbitaCultivation = new Skill(CUCURBITA_CULTIVATION, "Gain ability to plant pumpkin", 2);
        cucurbitaCultivation.getPrerequesiteSkills().put(fertilizer, fertilizer.getSkillPointRequirement());
        cucurbitaCultivation.getPrerequesiteSkills().put(daucusCultivation, daucusCultivation.getSkillPointRequirement());
        getTree().put(cucurbitaCultivation.getSkillName(), cucurbitaCultivation);

        Skill fungalFarming = new Skill(FUNGAL_FARMING, "Gain ability to plant red and brown mushrooms", 1);
        fungalFarming.getPrerequesiteSkills().put(fertilizer, fertilizer.getSkillPointRequirement());
        fungalFarming.getPrerequesiteSkills().put(cucurbitaCultivation, cucurbitaCultivation.getSkillPointRequirement());
        getTree().put(fungalFarming.getSkillName(), fungalFarming);

        Skill tuberosemCultivation = new Skill(TUBEROSEM_CULTIVATION, "Gain ability to plant potatoes", 2);
        tuberosemCultivation.getPrerequesiteSkills().put(daucusCultivation, daucusCultivation.getSkillPointRequirement());
        getTree().put(tuberosemCultivation.getSkillName(), tuberosemCultivation);

        Skill mechanizedHarvesting = new Skill(MECHANIZED_HARVESTING, "Each point in this skill improves wheat double drop chance by 5%", 5);
        mechanizedHarvesting.getPrerequesiteSkills().put(weedRemoval, weedRemoval.getSkillPointRequirement());
        getTree().put(mechanizedHarvesting.getSkillName(), mechanizedHarvesting);

        Skill indoorFungiculture = new Skill(INDOOR_FUNGICULTURE, "Each point in this skill increases mushroom double drop chance by 25%", 4);
        indoorFungiculture.getPrerequesiteSkills().put(fungalFarming, fungalFarming.getSkillPointRequirement());
        getTree().put(indoorFungiculture.getSkillName(), indoorFungiculture);

        Skill ianatusCultivation = new Skill(IANATUS_CULTIVATION, "Gain ability to plant melon", 2);
        ianatusCultivation.getPrerequesiteSkills().put(cucurbitaCultivation, cucurbitaCultivation.getSkillPointRequirement());
        getTree().put(ianatusCultivation.getSkillName(), ianatusCultivation);

        Skill trellisGourdTechniques = new Skill(TRELLIS_GOURD_TECHNIQUES, "Each point in this skill increases melon and double drop chance by 20%", 5);
        trellisGourdTechniques.getPrerequesiteSkills().put(fungalFarming, fungalFarming.getSkillPointRequirement());
        trellisGourdTechniques.getPrerequesiteSkills().put(ianatusCultivation, ianatusCultivation.getSkillPointRequirement());
        getTree().put(trellisGourdTechniques.getSkillName(), trellisGourdTechniques);

        Skill saccharumCultivation = new Skill(SACCHARUM_CULTIVATION, "Gain ability to plant and harvest sugarcane", 2);
        saccharumCultivation.getPrerequesiteSkills().put(cucurbitaCultivation, cucurbitaCultivation.getSkillPointRequirement());
        saccharumCultivation.getPrerequesiteSkills().put(tuberosemCultivation, tuberosemCultivation.getSkillPointRequirement());
        getTree().put(saccharumCultivation.getSkillName(), saccharumCultivation);

        Skill vulgarisCultivation = new Skill(VULGARUS_CULTIVATION, "Gain ability to plant beetroot", 2);
        vulgarisCultivation.getPrerequesiteSkills().put(tuberosemCultivation, tuberosemCultivation.getSkillPointRequirement());
        getTree().put(vulgarisCultivation.getSkillName(), vulgarisCultivation);

        Skill cacaoCultivation = new Skill(CACAO_CULTIVATION, "Gain ability to plant and harvest cocoa beans", 2);
        cacaoCultivation.getPrerequesiteSkills().put(saccharumCultivation, saccharumCultivation.getSkillPointRequirement());
        cacaoCultivation.getPrerequesiteSkills().put(vulgarisCultivation, vulgarisCultivation.getSkillPointRequirement());
        getTree().put(cacaoCultivation.getSkillName(), cacaoCultivation);

        Skill plantations = new Skill(PLANTATIONS, "Each point in this ability increases double chance of sugarcane and cocoa beans by 25%", 4);
        plantations.getPrerequesiteSkills().put(saccharumCultivation, saccharumCultivation.getSkillPointRequirement());
        plantations.getPrerequesiteSkills().put(cacaoCultivation, cacaoCultivation.getSkillPointRequirement());
        getTree().put(plantations.getSkillName(), plantations);

        Skill cropRotation = new Skill(CROP_ROTATION, "Each point in this ability increases double drop chance of wheat, potatoes, carrots, and beetroot by 10%", 5);
        cropRotation.getPrerequesiteSkills().put(vulgarisCultivation, vulgarisCultivation.getSkillPointRequirement());
        cropRotation.getPrerequesiteSkills().put(mechanizedHarvesting, mechanizedHarvesting.getSkillPointRequirement());
        getTree().put(cropRotation.getSkillName(), cropRotation);

        Skill blightProtection = new Skill(BLIGHT_PROTECTION, "Each point in this ability increases double drop chance of carrots, potatoes, and beetroots by 10%", 5);
        blightProtection.getPrerequesiteSkills().put(cacaoCultivation, cacaoCultivation.getSkillPointRequirement());
        blightProtection.getPrerequesiteSkills().put(cropRotation, cropRotation.getSkillPointRequirement());
        getTree().put(blightProtection.getSkillName(), blightProtection);

        Skill hybridization = new Skill(HYBRIDIZATION, "Gain ability to harvest and plant Nether Wart", 2);
        hybridization.getPrerequesiteSkills().put(blightProtection, blightProtection.getSkillPointRequirement());
        hybridization.getPrerequesiteSkills().put(indoorFungiculture, indoorFungiculture.getSkillPointRequirement());
        hybridization.getPrerequesiteSkills().put(trellisGourdTechniques, trellisGourdTechniques.getSkillPointRequirement());
        hybridization.getPrerequesiteSkills().put(plantations, plantations.getSkillPointRequirement());
        hybridization.getPrerequesiteSkills().put(cropRotation, cropRotation.getSkillPointRequirement());
        getTree().put(hybridization.getSkillName(), hybridization);

        Skill gmoCrops = new Skill(GMO_CROPS, "Gain ability to plant and harvest Chorus Fruit", 2);
        gmoCrops.getPrerequesiteSkills().put(hybridization, hybridization.getSkillPointRequirement());
        getTree().put(gmoCrops.getSkillName(), gmoCrops);

        Skill chemicalPesticides = new Skill(CHEMICAL_PESTICIDES, "Each point in this ability increases the double drop chance of nether wart and chorus fruit by 5%", 5);
        chemicalPesticides.getPrerequesiteSkills().put(hybridization, hybridization.getSkillPointRequirement());
        chemicalPesticides.getPrerequesiteSkills().put(gmoCrops, gmoCrops.getSkillPointRequirement());
        getTree().put(chemicalPesticides.getSkillName(), chemicalPesticides);

        Skill farmingMastery = new Skill(FARMING_MASTERY, "Increase Normal EXP gain by 50% for each harvest activity", 1);
        farmingMastery.getPrerequesiteSkills().put(gmoCrops, gmoCrops.getSkillPointRequirement());
        getTree().put(farmingMastery.getSkillName(), farmingMastery);

        Skill transenvironmentalCultivation = new Skill(TRANSENVIRONMENTAL_CULTIVATION, "Each point in this ability increases the double drop chance of nether wart and chorus fruit by 10%", 5);
        transenvironmentalCultivation.getPrerequesiteSkills().put(chemicalPesticides, chemicalPesticides.getSkillPointRequirement());
        getTree().put(transenvironmentalCultivation.getSkillName(), transenvironmentalCultivation);

        Skill improvedPhotosynthesis = new Skill(IMPROVED_PHOTOSYNTHESIS, "Each point in this ability increases the double drop chance of nether wart and chorus fruit by 5%", 5);
        improvedPhotosynthesis.getPrerequesiteSkills().put(transenvironmentalCultivation, transenvironmentalCultivation.getSkillPointRequirement());
        getTree().put(improvedPhotosynthesis.getSkillName(), improvedPhotosynthesis);

        //Add Children to skills
        basicAgriculture.getChildSkills().put(triticumCultivation, triticumCultivation.getSkillPointRequirement());

        triticumCultivation.getChildSkills().put(fertilizer, fertilizer.getSkillPointRequirement());
        triticumCultivation.getChildSkills().put(daucusCultivation, daucusCultivation.getSkillPointRequirement());
        triticumCultivation.getChildSkills().put(weedRemoval, weedRemoval.getSkillPointRequirement());

        fertilizer.getChildSkills().put(fungalFarming, fungalFarming.getSkillPointRequirement());
        fertilizer.getChildSkills().put(cucurbitaCultivation, cucurbitaCultivation.getSkillPointRequirement());

        daucusCultivation.getChildSkills().put(cucurbitaCultivation, cucurbitaCultivation.getSkillPointRequirement());
        daucusCultivation.getChildSkills().put(tuberosemCultivation, tuberosemCultivation.getSkillPointRequirement());

        weedRemoval.getChildSkills().put(mechanizedHarvesting, mechanizedHarvesting.getSkillPointRequirement());

        cucurbitaCultivation.getChildSkills().put(fungalFarming, fungalFarming.getSkillPointRequirement());
        cucurbitaCultivation.getChildSkills().put(ianatusCultivation, ianatusCultivation.getSkillPointRequirement());
        cucurbitaCultivation.getChildSkills().put(saccharumCultivation, saccharumCultivation.getSkillPointRequirement());

        fungalFarming.getChildSkills().put(indoorFungiculture, indoorFungiculture.getSkillPointRequirement());
        fungalFarming.getChildSkills().put(trellisGourdTechniques, trellisGourdTechniques.getSkillPointRequirement());

        ianatusCultivation.getChildSkills().put(trellisGourdTechniques, trellisGourdTechniques.getSkillPointRequirement());

        tuberosemCultivation.getChildSkills().put(saccharumCultivation, saccharumCultivation.getSkillPointRequirement());
        tuberosemCultivation.getChildSkills().put(vulgarisCultivation, vulgarisCultivation.getSkillPointRequirement());

        saccharumCultivation.getChildSkills().put(plantations, plantations.getSkillPointRequirement());
        saccharumCultivation.getChildSkills().put(cacaoCultivation, cacaoCultivation.getSkillPointRequirement());

        vulgarisCultivation.getChildSkills().put(cacaoCultivation, cacaoCultivation.getSkillPointRequirement());
        vulgarisCultivation.getChildSkills().put(cropRotation, cropRotation.getSkillPointRequirement());
        vulgarisCultivation.getChildSkills().put(blightProtection, blightProtection.getSkillPointRequirement());

        cacaoCultivation.getChildSkills().put(plantations, plantations.getSkillPointRequirement());
        cacaoCultivation.getChildSkills().put(blightProtection, blightProtection.getSkillPointRequirement());

        mechanizedHarvesting.getChildSkills().put(cropRotation, cropRotation.getSkillPointRequirement());

        cropRotation.getChildSkills().put(blightProtection, blightProtection.getSkillPointRequirement());
        cropRotation.getChildSkills().put(hybridization, hybridization.getSkillPointRequirement());

        indoorFungiculture.getChildSkills().put(hybridization, hybridization.getSkillPointRequirement());

        trellisGourdTechniques.getChildSkills().put(hybridization, hybridization.getSkillPointRequirement());

        plantations.getChildSkills().put(hybridization, hybridization.getSkillPointRequirement());

        blightProtection.getChildSkills().put(hybridization, hybridization.getSkillPointRequirement());

        hybridization.getChildSkills().put(gmoCrops, gmoCrops.getSkillPointRequirement());
        hybridization.getChildSkills().put(chemicalPesticides, chemicalPesticides.getSkillPointRequirement());

        gmoCrops.getChildSkills().put(chemicalPesticides, chemicalPesticides.getSkillPointRequirement());
        gmoCrops.getChildSkills().put(farmingMastery, farmingMastery.getSkillPointRequirement());

        chemicalPesticides.getChildSkills().put(transenvironmentalCultivation, transenvironmentalCultivation.getSkillPointRequirement());

        transenvironmentalCultivation.getChildSkills().put(improvedPhotosynthesis, improvedPhotosynthesis.getSkillPointRequirement());
    }
}
