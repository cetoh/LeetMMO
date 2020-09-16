package us.toh.leetmmo.skills.normal.mining.skilltree;

import us.toh.leetmmo.skills.Skill;

import java.util.HashMap;

import static us.toh.leetmmo.skills.normal.NormalSkillEnums.MiningSkillNames.*;

public class MiningSkillTree {

    private HashMap<Enum, Skill> tree = new HashMap<Enum, Skill>();

    public MiningSkillTree() {
        buildSkillTree();
    }

    private void buildSkillTree() {

        // Root
        Skill basicMining = new Skill(BASIC_MINING, "Gain ability to mine with wooden/gold pickaxes", 1);
        tree.put(basicMining.getSkillName(), basicMining);

        // Basic Mining Pre-requisite
        Skill miningExpertise = new Skill(MINING_EXPERTISE, "Each point improves mining speed by %5",10);
        miningExpertise.getPrerequesiteSkills().put(basicMining, basicMining.getSkillPointRequirement());
        tree.put(miningExpertise.getSkillName(), miningExpertise);

        Skill hewer = new Skill(HEWER, "Gain ability to mine with stone pickaxes",2);
        hewer.getPrerequesiteSkills().put(basicMining, basicMining.getSkillPointRequirement());
        tree.put(hewer.getSkillName(), hewer);

        Skill ligniteExtraction = new Skill(LIGNITE_EXTRACTION, "Each point increases coal double drop chance by %5",6);
        ligniteExtraction.getPrerequesiteSkills().put(basicMining, basicMining.getSkillPointRequirement());
        tree.put(ligniteExtraction.getSkillName(), ligniteExtraction);

        Skill thermoluminescenceDating = new Skill(THERMOLUMINESCENCE_DATING, "Each point increases glowstone double drop chance by %25",4);
        thermoluminescenceDating.getPrerequesiteSkills().put(basicMining, basicMining.getSkillPointRequirement());
        tree.put(thermoluminescenceDating.getSkillName(), thermoluminescenceDating);

        Skill siliconeExtraction = new Skill(SILICONE_EXTRACTION, "Each point increases quartz double drop chance by %25",4);
        siliconeExtraction.getPrerequesiteSkills().put(basicMining, basicMining.getSkillPointRequirement());
        tree.put(siliconeExtraction.getSkillName(), siliconeExtraction);

        // Thermoluminescence Dating & Silicone Extraction Pre-requisiste
        Skill foolsGold = new Skill(FOOLS_GOLD, "Each point increases nether gold double drop chance by %25",4);
        foolsGold.getPrerequesiteSkills().put(thermoluminescenceDating, thermoluminescenceDating.getSkillPointRequirement());
        foolsGold.getPrerequesiteSkills().put(siliconeExtraction, siliconeExtraction.getSkillPointRequirement());
        tree.put(foolsGold.getSkillName(), foolsGold);

        // Hewer Pre-requisite
        Skill tunnelDweller = new Skill(TUNNEL_DWELLER, "Each point increases EXP gain by %10 for all mining actions",3);
        tunnelDweller.getPrerequesiteSkills().put(hewer, hewer.getSkillPointRequirement());
        tree.put(tunnelDweller.getSkillName(), tunnelDweller);

        Skill templeDesecrator = new Skill(TEMPLE_DESECRATOR, "Increases prismarine crystal drop amount by x4 for sea lanterns",3);
        templeDesecrator.getPrerequesiteSkills().put(hewer, hewer.getSkillPointRequirement());
        tree.put(templeDesecrator.getSkillName(), templeDesecrator);

        Skill blastMining = new Skill(BLAST_MINING, "Increases all ore drop amounts by x2 from TNT explosions",3);
        blastMining.getPrerequesiteSkills().put(hewer, hewer.getSkillPointRequirement());
        tree.put(blastMining.getSkillName(), blastMining);

        Skill bituminousExtraction = new Skill(BITUMINOUS_EXTRACTION, "Each point increases coal double drop chance by %10",4);
        bituminousExtraction.getPrerequesiteSkills().put(hewer, hewer.getSkillPointRequirement());
        bituminousExtraction.getPrerequesiteSkills().put(ligniteExtraction, ligniteExtraction.getSkillPointRequirement());
        tree.put(bituminousExtraction.getSkillName(), bituminousExtraction);

        Skill comminution = new Skill(COMMINUTION, "Each point increases iron double drop chance by %5",6);
        comminution.getPrerequesiteSkills().put(hewer, hewer.getSkillPointRequirement());
        tree.put(comminution.getSkillName(), comminution);

        Skill lazuriteExtractionEfficiency = new Skill(LAZURITE_EXTRACTION_EFFICIENCY, "Each point increases lapis lazuli double drop chance by %25",4);
        lazuriteExtractionEfficiency.getPrerequesiteSkills().put(hewer, hewer.getSkillPointRequirement());
        tree.put(lazuriteExtractionEfficiency.getSkillName(), lazuriteExtractionEfficiency);

        Skill prospector = new Skill(PROSPECTOR, "Gain ability to mine with iron pickaxes",2);
        prospector.getPrerequesiteSkills().put(hewer, hewer.getSkillPointRequirement());
        tree.put(prospector.getSkillName(), prospector);

        // Prospector Pre-requisite
        Skill powerMining = new Skill(POWER_MINING, "Each point increases redstone double drop chance by %15",4);
        hewer.getPrerequesiteSkills().put(prospector, prospector.getSkillPointRequirement());
        tree.put(powerMining.getSkillName(), powerMining);

        Skill xrayFlourescence = new Skill(XRAY_FLOURESCENCE, "Each point increases diamond double drop chance by %15",4);
        xrayFlourescence.getPrerequesiteSkills().put(prospector, prospector.getSkillPointRequirement());
        tree.put(xrayFlourescence.getSkillName(), xrayFlourescence);

        Skill postlapidaryOiling = new Skill(POSTLAPIDARY_OILING, "Each point increases emerald double drop chance by %25",4);
        postlapidaryOiling.getPrerequesiteSkills().put(prospector, prospector.getSkillPointRequirement());
        tree.put(postlapidaryOiling.getSkillName(), postlapidaryOiling);

        Skill anthraciteExtraction = new Skill(ANTHRACITE_EXTRACTION, "Each point increases coal double drop chance by %15",2);
        anthraciteExtraction.getPrerequesiteSkills().put(prospector, prospector.getSkillPointRequirement());
        anthraciteExtraction.getPrerequesiteSkills().put(bituminousExtraction, bituminousExtraction.getSkillPointRequirement());
        tree.put(anthraciteExtraction.getSkillName(), anthraciteExtraction);

        Skill gravitySeparation = new Skill(GRAVITY_SEPARATION, "Each point increases iron double drop chance by %10",4);
        gravitySeparation.getPrerequesiteSkills().put(prospector, prospector.getSkillPointRequirement());
        gravitySeparation.getPrerequesiteSkills().put(comminution, comminution.getSkillPointRequirement());
        tree.put(gravitySeparation.getSkillName(), gravitySeparation);

        Skill hydraulicMining = new Skill(HYDRAULIC_MINING, "Each point increases gold double drop chance by %15",3);
        hydraulicMining.getPrerequesiteSkills().put(prospector, prospector.getSkillPointRequirement());
        tree.put(hydraulicMining.getSkillName(), hydraulicMining);

        Skill geoengineer = new Skill(GEOENGINEER, "Gain ability to mine with diamond/netherite pickaxes",2);
        geoengineer.getPrerequesiteSkills().put(prospector, prospector.getSkillPointRequirement());
        tree.put(geoengineer.getSkillName(), geoengineer);

        // Geoengineer Pre-requisite
        Skill electromagneticRadation = new Skill(ELECROMAGNETIC_RADIATION, "Each point increases redstone double drop chance by %20",2);
        electromagneticRadation.getPrerequesiteSkills().put(geoengineer, geoengineer.getSkillPointRequirement());
        electromagneticRadation.getPrerequesiteSkills().put(powerMining, powerMining.getSkillPointRequirement());
        tree.put(electromagneticRadation.getSkillName(), electromagneticRadation);

        Skill cuttingEfficiency = new Skill(CUTTING_EFFICIENCY, "Each point increases diamond double drop chance by %20",2);
        cuttingEfficiency.getPrerequesiteSkills().put(geoengineer, geoengineer.getSkillPointRequirement());
        cuttingEfficiency.getPrerequesiteSkills().put(xrayFlourescence, xrayFlourescence.getSkillPointRequirement());
        tree.put(cuttingEfficiency.getSkillName(), cuttingEfficiency);

        Skill wurtziteBoronNitrideDrill = new Skill(WURTZITE_BORON_NITRIDE_DRILL, "Inceases obsidian mining speed by 100%",3);
        wurtziteBoronNitrideDrill.getPrerequesiteSkills().put(geoengineer, geoengineer.getSkillPointRequirement());
        tree.put(wurtziteBoronNitrideDrill.getSkillName(), wurtziteBoronNitrideDrill);

        Skill mantleDrilling = new Skill(MANTLE_DRILLING, "Each point increases netherite double drop chance by %25",4);
        mantleDrilling.getPrerequesiteSkills().put(geoengineer, geoengineer.getSkillPointRequirement());
        tree.put(mantleDrilling.getSkillName(), mantleDrilling);

        Skill magnetiteFrothFlotation = new Skill(MAGNETITE_FROTH_FLOTATION, "Each point increases iron double drop chance by %15",2);
        magnetiteFrothFlotation.getPrerequesiteSkills().put(geoengineer, geoengineer.getSkillPointRequirement());
        magnetiteFrothFlotation.getPrerequesiteSkills().put(gravitySeparation, gravitySeparation.getSkillPointRequirement());
        tree.put(magnetiteFrothFlotation.getSkillName(), magnetiteFrothFlotation);

        Skill leaching = new Skill(LEACHING, "Each point increases gold double drop chance by %20",2);
        leaching.getPrerequesiteSkills().put(geoengineer, geoengineer.getSkillPointRequirement());
        leaching.getPrerequesiteSkills().put(hydraulicMining, hydraulicMining.getSkillPointRequirement());
        tree.put(leaching.getSkillName(), leaching);

        Skill miningMastery = new Skill(MINING_MASTERY, "Increases EXP gain by %50 for all mining actions",1);
        miningMastery.getPrerequesiteSkills().put(geoengineer, geoengineer.getSkillPointRequirement());
        tree.put(miningMastery.getSkillName(), miningMastery);

        //Add Children to skills
        basicMining.getChildSkills().put(miningExpertise, miningExpertise.getSkillPointRequirement());
        basicMining.getChildSkills().put(hewer, hewer.getSkillPointRequirement());
        basicMining.getChildSkills().put(ligniteExtraction, ligniteExtraction.getSkillPointRequirement());
        basicMining.getChildSkills().put(thermoluminescenceDating, thermoluminescenceDating.getSkillPointRequirement());
        basicMining.getChildSkills().put(siliconeExtraction, siliconeExtraction.getSkillPointRequirement());

        ligniteExtraction.getChildSkills().put(bituminousExtraction, bituminousExtraction.getSkillPointRequirement());

        thermoluminescenceDating.getChildSkills().put(foolsGold, foolsGold.getSkillPointRequirement());

        siliconeExtraction.getChildSkills().put(foolsGold, foolsGold.getSkillPointRequirement());

        hewer.getChildSkills().put(tunnelDweller, tunnelDweller.getSkillPointRequirement());
        hewer.getChildSkills().put(templeDesecrator, templeDesecrator.getSkillPointRequirement());
        hewer.getChildSkills().put(blastMining, blastMining.getSkillPointRequirement());
        hewer.getChildSkills().put(prospector, prospector.getSkillPointRequirement());
        hewer.getChildSkills().put(bituminousExtraction, bituminousExtraction.getSkillPointRequirement());
        hewer.getChildSkills().put(comminution, comminution.getSkillPointRequirement());
        hewer.getChildSkills().put(lazuriteExtractionEfficiency, lazuriteExtractionEfficiency.getSkillPointRequirement());

        bituminousExtraction.getChildSkills().put(anthraciteExtraction, anthraciteExtraction.getSkillPointRequirement());

        comminution.getChildSkills().put(gravitySeparation, gravitySeparation.getSkillPointRequirement());

        prospector.getChildSkills().put(powerMining, powerMining.getSkillPointRequirement());
        prospector.getChildSkills().put(xrayFlourescence, xrayFlourescence.getSkillPointRequirement());
        prospector.getChildSkills().put(postlapidaryOiling, postlapidaryOiling.getSkillPointRequirement());
        prospector.getChildSkills().put(geoengineer, geoengineer.getSkillPointRequirement());
        prospector.getChildSkills().put(anthraciteExtraction, anthraciteExtraction.getSkillPointRequirement());
        prospector.getChildSkills().put(gravitySeparation, gravitySeparation.getSkillPointRequirement());
        prospector.getChildSkills().put(hydraulicMining, hydraulicMining.getSkillPointRequirement());

        powerMining.getChildSkills().put(electromagneticRadation, electromagneticRadation.getSkillPointRequirement());

        xrayFlourescence.getChildSkills().put(cuttingEfficiency, cuttingEfficiency.getSkillPointRequirement());

        gravitySeparation.getChildSkills().put(magnetiteFrothFlotation, magnetiteFrothFlotation.getSkillPointRequirement());

        hydraulicMining.getChildSkills().put(leaching, leaching.getSkillPointRequirement());

        geoengineer.getChildSkills().put(electromagneticRadation, electromagneticRadation.getSkillPointRequirement());
        geoengineer.getChildSkills().put(cuttingEfficiency, cuttingEfficiency.getSkillPointRequirement());
        geoengineer.getChildSkills().put(wurtziteBoronNitrideDrill, wurtziteBoronNitrideDrill.getSkillPointRequirement());
        geoengineer.getChildSkills().put(mantleDrilling, mantleDrilling.getSkillPointRequirement());
        geoengineer.getChildSkills().put(magnetiteFrothFlotation, magnetiteFrothFlotation.getSkillPointRequirement());
        geoengineer.getChildSkills().put(leaching, leaching.getSkillPointRequirement());
        geoengineer.getChildSkills().put(miningMastery, miningMastery.getSkillPointRequirement());
    }

    public HashMap<Enum, Skill> getTree() {
        return this.tree;
    }

}
