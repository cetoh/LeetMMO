package us.toh.leetmmo.skills.normal.farming.skilltree;

import us.toh.leetmmo.skills.Skill;

import java.util.HashMap;

public class FarmingSkillTree {

    private HashMap<String, Skill> tree = new HashMap<String, Skill>();

    public FarmingSkillTree() {
        buildSkillTree();
    }

    private void buildSkillTree() {
        Skill basicAgriculture = new Skill("Basic Agriculture", "Gain ability to till dirt", 1);
        tree.put(basicAgriculture.getSkillName(), basicAgriculture);

        Skill triticumCultivation = new Skill("Triticum Cultivation", "Gain ability to plant wheat",1);
        triticumCultivation.getPrerequesiteSkills().put(basicAgriculture, basicAgriculture.getSkillPointRequirement());
        tree.put(triticumCultivation.getSkillName(), triticumCultivation);

        Skill fertilizer = new Skill("Fertilizer", "Gain ability to use bone meal",3);
        fertilizer.getPrerequesiteSkills().put(triticumCultivation, triticumCultivation.getSkillPointRequirement());
        tree.put(fertilizer.getSkillName(), fertilizer);

        Skill daucusCultivation = new Skill("Daucus Cultivation", "Gain ability to plant carrots",2);
        daucusCultivation.getPrerequesiteSkills().put(triticumCultivation, triticumCultivation.getSkillPointRequirement());
        tree.put(daucusCultivation.getSkillName(), daucusCultivation);

        Skill weedRemoval = new Skill("Weed Removal", "Each point in this skill improves wheat double drop chance by 5%",5);
        weedRemoval.getPrerequesiteSkills().put(triticumCultivation, triticumCultivation.getSkillPointRequirement());
        tree.put(weedRemoval.getSkillName(), weedRemoval);

        Skill cucurbitaCultivation = new Skill("Cucurbita Cultivation", "Gain ability to plant pumpkin", 2);
        cucurbitaCultivation.getPrerequesiteSkills().put(fertilizer, fertilizer.getSkillPointRequirement());
        cucurbitaCultivation.getPrerequesiteSkills().put(daucusCultivation, daucusCultivation.getSkillPointRequirement());
        tree.put(cucurbitaCultivation.getSkillName(), cucurbitaCultivation);

        Skill fungalFarming = new Skill("Fungal Farming", "Gain ability to plant red and brown mushrooms", 1);
        fungalFarming.getPrerequesiteSkills().put(fertilizer, fertilizer.getSkillPointRequirement());
        fungalFarming.getPrerequesiteSkills().put(cucurbitaCultivation, cucurbitaCultivation.getSkillPointRequirement());
        tree.put(fungalFarming.getSkillName(), fungalFarming);

        Skill tuberosemCultivation = new Skill("Tuberosem Cultivation", "Gain ability to plant potatoes", 2);
        tuberosemCultivation.getPrerequesiteSkills().put(daucusCultivation, daucusCultivation.getSkillPointRequirement());
        tree.put(tuberosemCultivation.getSkillName(), tuberosemCultivation);

        Skill mechanizedHarvesting = new Skill("Mechanized Harvesting", "Each point in this skill improves wheat double drop chance by 5%", 5);
        mechanizedHarvesting.getPrerequesiteSkills().put(weedRemoval, weedRemoval.getSkillPointRequirement());
        tree.put(mechanizedHarvesting.getSkillName(), mechanizedHarvesting);

        Skill indoorFungiculture = new Skill("Indoor Fungiculture", "Each point in this skill increases mushroom double drop chance by 25%", 4);
        indoorFungiculture.getPrerequesiteSkills().put(fungalFarming, fungalFarming.getSkillPointRequirement());
        tree.put(fungalFarming.getSkillName(), fungalFarming);

        Skill ianatusCultivation = new Skill("Ianatus Cultivation", "Gain ability to plant melon", 2);
        ianatusCultivation.getPrerequesiteSkills().put(cucurbitaCultivation, cucurbitaCultivation.getSkillPointRequirement());
        tree.put(ianatusCultivation.getSkillName(), ianatusCultivation);

        Skill trellisGourdTechniques = new Skill("Trellis Gourd Techniques", "Each point in this skill increases melon and double drop chance by 20%", 5);
        trellisGourdTechniques.getPrerequesiteSkills().put(fungalFarming, fungalFarming.getSkillPointRequirement());
        trellisGourdTechniques.getPrerequesiteSkills().put(ianatusCultivation, ianatusCultivation.getSkillPointRequirement());
        tree.put(trellisGourdTechniques.getSkillName(), trellisGourdTechniques);

        Skill saccharumCultivation = new Skill("Saccharum Cultivation", "Gain ability to plant and harvest sugarcane", 2);
        saccharumCultivation.getPrerequesiteSkills().put(cucurbitaCultivation, cucurbitaCultivation.getSkillPointRequirement());
        saccharumCultivation.getPrerequesiteSkills().put(tuberosemCultivation, tuberosemCultivation.getSkillPointRequirement());
        tree.put(saccharumCultivation.getSkillName(), saccharumCultivation);

        Skill vulgarisCultivation = new Skill("Vulgaris Cultivation", "Gain ability to plant beetroot", 2);
        vulgarisCultivation.getPrerequesiteSkills().put(tuberosemCultivation, tuberosemCultivation.getSkillPointRequirement());
        tree.put(vulgarisCultivation.getSkillName(), vulgarisCultivation);

        Skill cacaoCultivation = new Skill("Cacao Cultivation", "Gain ability to plant and harvest cocoa beans", 2);
        cacaoCultivation.getPrerequesiteSkills().put(saccharumCultivation, saccharumCultivation.getSkillPointRequirement());
        cacaoCultivation.getPrerequesiteSkills().put(vulgarisCultivation, vulgarisCultivation.getSkillPointRequirement());
        tree.put(cacaoCultivation.getSkillName(), cacaoCultivation);

        Skill plantations = new Skill("Plantations", "Each point in this ability increases double chance of sugarcane and cocoa beans by 25%", 4);
        plantations.getPrerequesiteSkills().put(saccharumCultivation, saccharumCultivation.getSkillPointRequirement());
        plantations.getPrerequesiteSkills().put(cacaoCultivation, cacaoCultivation.getSkillPointRequirement());
        tree.put(plantations.getSkillName(), plantations);

        Skill cropRotation = new Skill("Crop Rotation", "Each point in this ability increases double drop chance of wheat, potatoes, carrots, and beetroot by 10%", 5);
        cropRotation.getPrerequesiteSkills().put(vulgarisCultivation, vulgarisCultivation.getSkillPointRequirement());
        cropRotation.getPrerequesiteSkills().put(mechanizedHarvesting, mechanizedHarvesting.getSkillPointRequirement());
        tree.put(cropRotation.getSkillName(), cropRotation);

        Skill blightProtection = new Skill("Blight Protection", "Each point in this ability increases double drop chance of carrots, potatoes, and beetroots by 10%", 5);
        blightProtection.getPrerequesiteSkills().put(cacaoCultivation, cacaoCultivation.getSkillPointRequirement());
        blightProtection.getPrerequesiteSkills().put(cropRotation, cropRotation.getSkillPointRequirement());
        tree.put(blightProtection.getSkillName(), blightProtection);

        Skill hybridization = new Skill("Hybridization", "Gain ability to harvest and plant Nether Wart", 2);
        hybridization.getPrerequesiteSkills().put(blightProtection, blightProtection.getSkillPointRequirement());
        hybridization.getPrerequesiteSkills().put(indoorFungiculture, indoorFungiculture.getSkillPointRequirement());
        hybridization.getPrerequesiteSkills().put(trellisGourdTechniques, trellisGourdTechniques.getSkillPointRequirement());
        hybridization.getPrerequesiteSkills().put(plantations, plantations.getSkillPointRequirement());
        hybridization.getPrerequesiteSkills().put(cropRotation, cropRotation.getSkillPointRequirement());
        tree.put(hybridization.getSkillName(), hybridization);

        Skill gmoCrops = new Skill("GMO Crops", "Gain ability to plant and harvest Chorus Fruit", 2);
        gmoCrops.getPrerequesiteSkills().put(hybridization, hybridization.getSkillPointRequirement());
        tree.put(gmoCrops.getSkillName(), gmoCrops);

        Skill chemicalPesticides = new Skill("Chemical Pesticides", "Each point in this ability increases the double drop chance of nether wart and chorus fruit by 5%", 5);
        chemicalPesticides.getPrerequesiteSkills().put(hybridization, hybridization.getSkillPointRequirement());
        chemicalPesticides.getPrerequesiteSkills().put(gmoCrops, gmoCrops.getSkillPointRequirement());
        tree.put(chemicalPesticides.getSkillName(), chemicalPesticides);

        Skill farmingMastery = new Skill("Farming Mastery", "Increase Normal EXP gain by 50% for each harvest activity", 1);
        farmingMastery.getPrerequesiteSkills().put(gmoCrops, gmoCrops.getSkillPointRequirement());
        tree.put(farmingMastery.getSkillName(), farmingMastery);

        Skill transenvironmentalCultivation = new Skill("Transenvironmental Cultivation", "Each point in this ability increases the double drop chance of nether wart and chorus fruit by 10%", 5);
        transenvironmentalCultivation.getPrerequesiteSkills().put(chemicalPesticides, chemicalPesticides.getSkillPointRequirement());
        tree.put(transenvironmentalCultivation.getSkillName(), transenvironmentalCultivation);

        Skill improvedPhotosynthesis = new Skill("Improved Photosynthesis", "Each point in this ability increases the double drop chance of nether wart and chorus fruit by 5%", 5);
        improvedPhotosynthesis.getPrerequesiteSkills().put(transenvironmentalCultivation, transenvironmentalCultivation.getSkillPointRequirement());
        tree.put(improvedPhotosynthesis.getSkillName(), improvedPhotosynthesis);
    }

    public HashMap<String, Skill> getTree() {
        return this.tree;
    }
}
