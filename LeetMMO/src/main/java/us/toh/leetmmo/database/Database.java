package us.toh.leetmmo.database;

import us.toh.leetmmo.LeetMMO;
import us.toh.leetmmo.datatypes.experience.ClassExperiencePool;
import us.toh.leetmmo.datatypes.experience.NormalExperiencePool;
import us.toh.leetmmo.datatypes.level.ClassLevel;
import us.toh.leetmmo.datatypes.level.NormalLevel;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.datatypes.skillpoint.ClassSkillPointPool;
import us.toh.leetmmo.datatypes.skillpoint.NormalSkillPointPool;
import us.toh.leetmmo.skills.Skill;

import java.io.File;
import java.sql.*;
import java.util.HashMap;

import static us.toh.leetmmo.skills.normal.NormalSkillEnums.ConstructionSkillNames.*;
import static us.toh.leetmmo.skills.normal.NormalSkillEnums.CraftingSkillNames.*;
import static us.toh.leetmmo.skills.normal.NormalSkillEnums.FarmingSkillNames.*;
import static us.toh.leetmmo.skills.normal.NormalSkillEnums.FishingSkillNames.*;
import static us.toh.leetmmo.skills.normal.NormalSkillEnums.HuntingSkillNames.*;
import static us.toh.leetmmo.skills.normal.NormalSkillEnums.HusbandrySkillNames.*;
import static us.toh.leetmmo.skills.normal.NormalSkillEnums.MiningSkillNames.*;
import static us.toh.leetmmo.skills.normal.NormalSkillEnums.WoodcuttingSkillNames.*;
import static us.toh.leetmmo.skills.normal.NormalSkillEnums.SmeltingSkillNames.*;
import static us.toh.leetmmo.skills.normal.NormalSkillEnums.SmithingSkillNames.*;

public class Database {

    public LeetMMO plugin;

    private String fileName;
    private Connection conn = null;

    public Database(LeetMMO plugin) {
        this.plugin = plugin;
    }

    public void createNewDatabase(String fileName) {
        this.fileName = fileName;

        File file = new File(plugin.getDataFolder().getAbsolutePath());
        if (!file.exists()) {
            file.mkdir();
        }

        String url = "jdbc:sqlite:" + plugin.getDataFolder().getAbsolutePath() + "\\" + this.fileName;

        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A database connection has been made.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // TODO Figure out what datatypes to set the columns of the table
        String playerTableCheck = "CREATE TABLE IF NOT EXISTS player(\n"
                                + "      name varchar(40),\n"
                                + "      uuid varchar(36),\n"
                                + "      nexp int,\n"
                                + "      cexp int,\n"
                                + "      nexpcap int,\n"
                                + "      cexpcap int,\n"
                                + "      nlvl int,\n"
                                + "      clvl int,\n"
                                + "      nsp int,\n"
                                + "      csp int);";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(playerTableCheck);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        createSkillTables(conn);
    }

    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    private Connection reconnect() {
        // SQLite connection string
        String url = "jdbc:sqlite:" + plugin.getDataFolder().getAbsolutePath() + "\\" + this.fileName;
        conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    private void disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());        }

    }

    /*******************************************/
    /***** Insert New Player Into Database *****/
    /*******************************************/

    /**
     * Insert a new row into the warehouses table
     *
     * @param playerProfile
     */
    public void insertNewPlayerProfile(PlayerProfile playerProfile) {
        insertBasicInformation(playerProfile);
        insertFarmingSkillInformation(playerProfile);
        insertFishingSkillInformation(playerProfile);
        insertMiningSkillInformation(playerProfile);
    }

    private void insertBasicInformation(PlayerProfile playerProfile) {
        if (!checkIfPlayerExists(playerProfile,"player")) {
            String sql = "INSERT INTO player(name,uuid,nexp,cexp,nexpcap,cexpcap,nlvl,clvl,nsp,csp) VALUES(?,?,?,?,?,?,?,?,?,?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, playerProfile.getPlayerName());
                pstmt.setString(2, playerProfile.getUuid().toString());
                pstmt.setDouble(3, playerProfile.getnEXPPool().getPoints());
                pstmt.setDouble(4, playerProfile.getcEXPPool().getPoints());
                pstmt.setDouble(5, playerProfile.getnEXPPool().getPoolCap());
                pstmt.setDouble(6, playerProfile.getcEXPPool().getPoolCap());
                pstmt.setInt(7, playerProfile.getnLvl().getCurrentLevel());
                pstmt.setInt(8, playerProfile.getcLvl().getCurrentLevel());
                pstmt.setInt(9, playerProfile.getnSPPool().getNumPoints());
                pstmt.setInt(10, playerProfile.getcSPPool().getNumPoints());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void insertFarmingSkillInformation(PlayerProfile playerProfile) {
        if(!checkIfPlayerExists(playerProfile, "farming")) {
            String farming = "INSERT INTO farming(name,uuid," +
                    "basicAgriculture," +
                    "triticumCultivation," +
                    "fertilizer," +
                    "weedRemoval," +
                    "daucusCultivation,"+
                    "cucurbitaCultivation," +
                    "mechanizedHarvesting," +
                    "tuberosemCultivation," +
                    "saccharumCultivation," +
                    "fungalFarming," +
                    "ianatusCultivation," +
                    "vulgarusCultivation," +
                    "cacaoCultivation," +
                    "plantations," +
                    "indoorFungiculture," +
                    "trellisGourdTechniques," +
                    "cropRotation," +
                    "blightProtection," +
                    "hybridization," +
                    "chemicalPesticides," +
                    "gmoCrops," +
                    "transenvironmentalCultivation," +
                    "improvedPhotosynthesis," +
                    "farmingMastery)" +
                    " VALUES(?,?,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)";

            try (PreparedStatement pstmt = conn.prepareStatement(farming)) {
                pstmt.setString(1, playerProfile.getPlayerName());
                pstmt.setString(2, playerProfile.getUuid().toString());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void insertFishingSkillInformation(PlayerProfile playerProfile) {
        if(!checkIfPlayerExists(playerProfile, "fishing")) {
            String farming = "INSERT INTO fishing(name,uuid,"+
                    "basicFishing," +
                    "fishingBait," +
                    "fishingTechnique," +
                    "fishermanFolkStories ," +
                    "rodCare," +
                    "currentWatcher," +
                    "grizzlyInstincts," +
                    "oceanography," +
                    "deadliestCatch," +
                    "fishermanDiet," +
                    "fisherman," +
                    "pirateLore," +
                    "trolling," +
                    "nets," +
                    "fishermanLuck," +
                    "fishCleaning," +
                    "sushi," +
                    "proficientFisherman," +
                    "pirateMaps," +
                    "sonar," +
                    "freshwaterBaits," +
                    "oceanBaits," +
                    "aquaticInsight," +
                    "seafarerWisdom," +
                    "masterAngler," +
                    "pirateLegends ," +
                    "bottomTrawling," +
                    "electrofishing," +
                    "shipwreckDiving," +
                    "sustainableFishing," +
                    "cleanWaters)" +
                    " VALUES(?,?,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)";

            try (PreparedStatement pstmt = conn.prepareStatement(farming)) {
                pstmt.setString(1, playerProfile.getPlayerName());
                pstmt.setString(2, playerProfile.getUuid().toString());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void insertMiningSkillInformation(PlayerProfile playerProfile) {
        if(!checkIfPlayerExists(playerProfile, "mining")) {
            String farming = "INSERT INTO mining(name,uuid," +
                    "basicMining," +
                    "miningExpertise," +
                    "hewer," +
                    "ligniteExtraction," +
                    "thermoluminescenceDating,"+
                    "siliconeExtraction," +
                    "foolsGold," +
                    "tunnelDweller," +
                    "templeDesecrator," +
                    "blastMining," +
                    "prospector," +
                    "bituminousExtraction," +
                    "comminution," +
                    "lazuriteExtractionEfficiency," +
                    "powerMining," +
                    "xrayFlourescence," +
                    "postlapidaryOiling," +
                    "geoengineer," +
                    "anthraciteExtraction," +
                    "gravitySeparation," +
                    "hydraulicMining," +
                    "electromagneticRadiation," +
                    "cuttingEfficiency," +
                    "wurtziteBoronNitrideDrill," +
                    "mantleDrilling," +
                    "magnetiteFrothFlotation," +
                    "leaching," +
                    "miningMastery)" +
                    " VALUES(?,?,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)";

            try (PreparedStatement pstmt = conn.prepareStatement(farming)) {
                pstmt.setString(1, playerProfile.getPlayerName());
                pstmt.setString(2, playerProfile.getUuid().toString());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**********************************************/
    /***** Update Existing Player in Database *****/
    /**********************************************/

    public void updatePlayerProfile(PlayerProfile playerProfile) {
        updateBasicInformation(playerProfile);
        updatePlayerFarmingSkillInformation(playerProfile);
        updatePlayerFishingSkillInformation(playerProfile);
        updatePlayerMiningSkillInformation(playerProfile);
    }

    private void updateBasicInformation(PlayerProfile playerProfile) {
        if (checkIfPlayerExists(playerProfile, "player")) {
            String sql = "UPDATE player SET name = ?, " +
                    "uuid = ?, " +
                    "nexp = ?, " +
                    "cexp = ?, " +
                    "nexpcap = ?, " +
                    "cexpcap = ?, " +
                    "nlvl = ?, " +
                    "clvl = ?, " +
                    "nsp = ?, " +
                    "csp = ? " +
                    "WHERE name = ? AND uuid = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, playerProfile.getPlayerName());
                pstmt.setString(2, playerProfile.getUuid().toString());
                pstmt.setDouble(3, playerProfile.getnEXPPool().getPoints());
                pstmt.setDouble(4, playerProfile.getcEXPPool().getPoints());
                pstmt.setDouble(5, playerProfile.getnEXPPool().getPoolCap());
                pstmt.setDouble(6, playerProfile.getcEXPPool().getPoolCap());
                pstmt.setInt(7, playerProfile.getnLvl().getCurrentLevel());
                pstmt.setInt(8, playerProfile.getcLvl().getCurrentLevel());
                pstmt.setInt(9, playerProfile.getnSPPool().getNumPoints());
                pstmt.setInt(10, playerProfile.getcSPPool().getNumPoints());
                pstmt.setString(11, playerProfile.getPlayerName());
                pstmt.setString(12, playerProfile.getUuid().toString());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private void updatePlayerFarmingSkillInformation(PlayerProfile playerProfile) {
        if (checkIfPlayerExists(playerProfile, "farming")) {
            String farming = "UPDATE farming SET name = ?, uuid = ?, " +
                    "basicAgriculture = ?, " +
                    "triticumCultivation = ?, " +
                    "fertilizer = ?, " +
                    "weedRemoval = ?, " +
                    "daucusCultivation = ?, "+
                    "cucurbitaCultivation = ?, " +
                    "mechanizedHarvesting = ?, " +
                    "tuberosemCultivation = ?, " +
                    "saccharumCultivation = ?, " +
                    "fungalFarming = ?, " +
                    "ianatusCultivation = ?, " +
                    "vulgarusCultivation = ?, " +
                    "cacaoCultivation = ?, " +
                    "plantations = ?, " +
                    "indoorFungiculture = ?, " +
                    "trellisGourdTechniques = ?, " +
                    "cropRotation = ?, " +
                    "blightProtection = ?, " +
                    "hybridization = ?, " +
                    "chemicalPesticides = ?, " +
                    "gmoCrops = ?, " +
                    "transenvironmentalCultivation = ?, " +
                    "improvedPhotosynthesis = ?, " +
                    "farmingMastery = ? " +
                    " WHERE name = ? AND uuid = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(farming)) {
                HashMap<Enum, Skill> farmingSkillTree = playerProfile.getFarmingSkillTree().getTree();
                pstmt.setString(1, playerProfile.getPlayerName());
                pstmt.setString(2, playerProfile.getUuid().toString());
                pstmt.setInt(3, farmingSkillTree.get(BASIC_AGRICULTURE).getSkillPoints());
                pstmt.setInt(4, farmingSkillTree.get(TRITICUM_CULTIVATION).getSkillPoints());
                pstmt.setInt(5, farmingSkillTree.get(FERTILIZER).getSkillPoints());
                pstmt.setInt(6, farmingSkillTree.get(WEED_REMOVAL).getSkillPoints());
                pstmt.setInt(7, farmingSkillTree.get(DAUCUS_CULTIVATION).getSkillPoints());
                pstmt.setInt(8, farmingSkillTree.get(CUCURBITA_CULTIVATION).getSkillPoints());
                pstmt.setInt(9, farmingSkillTree.get(MECHANIZED_HARVESTING).getSkillPoints());
                pstmt.setInt(10, farmingSkillTree.get(TUBEROSEM_CULTIVATION).getSkillPoints());
                pstmt.setInt(11, farmingSkillTree.get(SACCHARUM_CULTIVATION).getSkillPoints());
                pstmt.setInt(12, farmingSkillTree.get(FUNGAL_FARMING).getSkillPoints());
                pstmt.setInt(13, farmingSkillTree.get(IANATUS_CULTIVATION).getSkillPoints());
                pstmt.setInt(14, farmingSkillTree.get(VULGARUS_CULTIVATION).getSkillPoints());
                pstmt.setInt(15, farmingSkillTree.get(CACAO_CULTIVATION).getSkillPoints());
                pstmt.setInt(16, farmingSkillTree.get(PLANTATIONS).getSkillPoints());
                pstmt.setInt(17, farmingSkillTree.get(INDOOR_FUNGICULTURE).getSkillPoints());
                pstmt.setInt(18, farmingSkillTree.get(TRELLIS_GOURD_TECHNIQUES).getSkillPoints());
                pstmt.setInt(19, farmingSkillTree.get(CROP_ROTATION).getSkillPoints());
                pstmt.setInt(20, farmingSkillTree.get(BLIGHT_PROTECTION).getSkillPoints());
                pstmt.setInt(21, farmingSkillTree.get(HYBRIDIZATION).getSkillPoints());
                pstmt.setInt(22, farmingSkillTree.get(CHEMICAL_PESTICIDES).getSkillPoints());
                pstmt.setInt(23, farmingSkillTree.get(GMO_CROPS).getSkillPoints());
                pstmt.setInt(24, farmingSkillTree.get(TRANSENVIRONMENTAL_CULTIVATION).getSkillPoints());
                pstmt.setInt(25, farmingSkillTree.get(IMPROVED_PHOTOSYNTHESIS).getSkillPoints());
                pstmt.setInt(26, farmingSkillTree.get(FARMING_MASTERY).getSkillPoints());
                pstmt.setString(27, playerProfile.getPlayerName());
                pstmt.setString(28, playerProfile.getUuid().toString());

                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void updatePlayerFishingSkillInformation(PlayerProfile playerProfile) {
        if (checkIfPlayerExists(playerProfile, "fishing")) {
            String farming = "UPDATE fishing SET name = ?, uuid = ?, " +
                    "basicFishing = ?," +
                    "fishingBait = ?," +
                    "fishingTechnique = ?," +
                    "fishermanFolkStories  = ?," +
                    "rodCare = ?," +
                    "currentWatcher = ?," +
                    "grizzlyInstincts = ?," +
                    "oceanography = ?," +
                    "deadliestCatch = ?," +
                    "fishermanDiet = ?," +
                    "fisherman = ?," +
                    "pirateLore = ?," +
                    "trolling = ?," +
                    "nets = ?," +
                    "fishermanLuck = ?," +
                    "fishCleaning = ?," +
                    "sushi = ?," +
                    "proficientFisherman = ?," +
                    "pirateMaps = ?," +
                    "sonar = ?," +
                    "freshwaterBaits = ?," +
                    "oceanBaits = ?," +
                    "aquaticInsight = ?," +
                    "seafarerWisdom = ?," +
                    "masterAngler = ?," +
                    "pirateLegends  = ?," +
                    "bottomTrawling = ?," +
                    "electrofishing = ?," +
                    "shipwreckDiving = ?," +
                    "sustainableFishing = ?," +
                    "cleanWaters = ? " +
                    " WHERE name = ? AND uuid = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(farming)) {
                HashMap<Enum, Skill> fishingSkillTree = playerProfile.getFishingSkillTree().getTree();
                pstmt.setString(1, playerProfile.getPlayerName());
                pstmt.setString(2, playerProfile.getUuid().toString());
                pstmt.setInt(3, fishingSkillTree.get(BASIC_FISHING).getSkillPoints());
                pstmt.setInt(4, fishingSkillTree.get(FISHING_BAIT).getSkillPoints());
                pstmt.setInt(5, fishingSkillTree.get(FISHING_TECHNIQUE).getSkillPoints());
                pstmt.setInt(6, fishingSkillTree.get(FISHERMAN_FOLK_STORIES).getSkillPoints());
                pstmt.setInt(7, fishingSkillTree.get(ROD_CARE).getSkillPoints());
                pstmt.setInt(8, fishingSkillTree.get(CURRENT_WATCHER).getSkillPoints());
                pstmt.setInt(9, fishingSkillTree.get(GRIZZLY_INSTINCTS).getSkillPoints());
                pstmt.setInt(10, fishingSkillTree.get(OCEANOGRAPHY).getSkillPoints());
                pstmt.setInt(11, fishingSkillTree.get(DEADLIEST_CATCH).getSkillPoints());
                pstmt.setInt(12, fishingSkillTree.get(FISHERMAN_DIET).getSkillPoints());
                pstmt.setInt(13, fishingSkillTree.get(FISHERMAN).getSkillPoints());
                pstmt.setInt(14, fishingSkillTree.get(PIRATE_LORE).getSkillPoints());
                pstmt.setInt(15, fishingSkillTree.get(TROLLING).getSkillPoints());
                pstmt.setInt(16, fishingSkillTree.get(NETS).getSkillPoints());
                pstmt.setInt(17, fishingSkillTree.get(FISHERMAN_LUCK).getSkillPoints());
                pstmt.setInt(18, fishingSkillTree.get(FISH_CLEANING).getSkillPoints());
                pstmt.setInt(19, fishingSkillTree.get(SUSHI).getSkillPoints());
                pstmt.setInt(20, fishingSkillTree.get(PROFICIENT_FISHERMAN).getSkillPoints());
                pstmt.setInt(21, fishingSkillTree.get(PIRATE_MAPS).getSkillPoints());
                pstmt.setInt(22, fishingSkillTree.get(SONAR).getSkillPoints());
                pstmt.setInt(23, fishingSkillTree.get(FRESHWATER_BAITS).getSkillPoints());
                pstmt.setInt(24, fishingSkillTree.get(OCEAN_BAITS).getSkillPoints());
                pstmt.setInt(25, fishingSkillTree.get(AQUATIC_INSIGHT).getSkillPoints());
                pstmt.setInt(26, fishingSkillTree.get(SEAFARER_WISDOM).getSkillPoints());
                pstmt.setInt(27, fishingSkillTree.get(MASTER_ANGLER).getSkillPoints());
                pstmt.setInt(28, fishingSkillTree.get(PIRATE_LEGENDS).getSkillPoints());
                pstmt.setInt(29, fishingSkillTree.get(BOTTOM_TRAWLING).getSkillPoints());
                pstmt.setInt(30, fishingSkillTree.get(ELECTROFISHING).getSkillPoints());
                pstmt.setInt(31, fishingSkillTree.get(SHIPWRECK_DIVING).getSkillPoints());
                pstmt.setInt(32, fishingSkillTree.get(SUSTAINABLE_FISHING).getSkillPoints());
                pstmt.setInt(33, fishingSkillTree.get(CLEAN_WATERS).getSkillPoints());
                pstmt.setString(34, playerProfile.getPlayerName());
                pstmt.setString(35, playerProfile.getUuid().toString());

                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    private void updatePlayerMiningSkillInformation(PlayerProfile playerProfile) {
        if (checkIfPlayerExists(playerProfile, "mining")) {
            String farming = "UPDATE mining SET name = ?, uuid = ?, " +
                    "basicMining = ?, " +
                    "miningExpertise = ?, " +
                    "hewer = ?, " +
                    "ligniteExtraction = ?, " +
                    "thermoluminescenceDating = ?, "+
                    "siliconeExtraction = ?, " +
                    "foolsGold = ?, " +
                    "tunnelDweller = ?, " +
                    "templeDesecrator = ?, " +
                    "blastMining = ?, " +
                    "prospector = ?, " +
                    "bituminousExtraction = ?, " +
                    "comminution = ?, " +
                    "lazuriteExtractionEfficiency = ?, " +
                    "powerMining = ?, " +
                    "xrayFlourescence = ?, " +
                    "postlapidaryOiling = ?, " +
                    "geoengineer = ?, " +
                    "anthraciteExtraction = ?, " +
                    "gravitySeparation = ?, " +
                    "hydraulicMining = ?, " +
                    "electromagneticRadiation = ?, " +
                    "cuttingEfficiency = ?, " +
                    "wurtziteBoronNitrideDrill = ?, " +
                    "mantleDrilling = ?, " +
                    "magnetiteFrothFlotation = ?, " +
                    "leaching = ?, " +
                    "miningMastery = ? " +
                    " WHERE name = ? AND uuid = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(farming)) {
                HashMap<Enum, Skill> miningSkillTree = playerProfile.getMiningSkillTree().getTree();
                pstmt.setString(1, playerProfile.getPlayerName());
                pstmt.setString(2, playerProfile.getUuid().toString());
                pstmt.setInt(3, miningSkillTree.get(BASIC_MINING).getSkillPoints());
                pstmt.setInt(4, miningSkillTree.get(MINING_EXPERTISE).getSkillPoints());
                pstmt.setInt(5, miningSkillTree.get(HEWER).getSkillPoints());
                pstmt.setInt(6, miningSkillTree.get(LIGNITE_EXTRACTION).getSkillPoints());
                pstmt.setInt(7, miningSkillTree.get(THERMOLUMINESCENCE_DATING).getSkillPoints());
                pstmt.setInt(8, miningSkillTree.get(SILICONE_EXTRACTION).getSkillPoints());
                pstmt.setInt(9, miningSkillTree.get(FOOLS_GOLD).getSkillPoints());
                pstmt.setInt(10, miningSkillTree.get(TUNNEL_DWELLER).getSkillPoints());
                pstmt.setInt(11, miningSkillTree.get(TEMPLE_DESECRATOR).getSkillPoints());
                pstmt.setInt(12, miningSkillTree.get(BLAST_MINING).getSkillPoints());
                pstmt.setInt(13, miningSkillTree.get(PROSPECTOR).getSkillPoints());
                pstmt.setInt(14, miningSkillTree.get(BITUMINOUS_EXTRACTION).getSkillPoints());
                pstmt.setInt(15, miningSkillTree.get(COMMINUTION).getSkillPoints());
                pstmt.setInt(16, miningSkillTree.get(LAZURITE_EXTRACTION).getSkillPoints());
                pstmt.setInt(17, miningSkillTree.get(POWER_MINING).getSkillPoints());
                pstmt.setInt(18, miningSkillTree.get(XRAY_FLOURESCENCE).getSkillPoints());
                pstmt.setInt(19, miningSkillTree.get(POSTLAPIDARY_OILING).getSkillPoints());
                pstmt.setInt(20, miningSkillTree.get(GEOENGINEER).getSkillPoints());
                pstmt.setInt(21, miningSkillTree.get(ANTHRACITE_EXTRACTION).getSkillPoints());
                pstmt.setInt(22, miningSkillTree.get(GRAVITY_SEPARATION).getSkillPoints());
                pstmt.setInt(23, miningSkillTree.get(HYDRAULIC_MINING).getSkillPoints());
                pstmt.setInt(24, miningSkillTree.get(ELECROMAGNETIC_RADIATION).getSkillPoints());
                pstmt.setInt(25, miningSkillTree.get(CUTTING_EFFICIENCY).getSkillPoints());
                pstmt.setInt(26, miningSkillTree.get(WURTZITE_BORON_NITRIDE_DRILL).getSkillPoints());
                pstmt.setInt(27, miningSkillTree.get(MANTLE_DRILLING).getSkillPoints());
                pstmt.setInt(28, miningSkillTree.get(MAGNETITE_FROTH_FLOTATION).getSkillPoints());
                pstmt.setInt(29, miningSkillTree.get(LEACHING).getSkillPoints());
                pstmt.setInt(30, miningSkillTree.get(MINING_MASTERY).getSkillPoints());
                pstmt.setString(31, playerProfile.getPlayerName());
                pstmt.setString(32, playerProfile.getUuid().toString());

                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /***********************************/
    /***** Get Player Profile Data *****/
    /***********************************/

    public PlayerProfile getPlayerProfileFromDatabase(PlayerProfile playerProfile) {

        getBasicInformation(playerProfile);
        getFarmingSkillInformation(playerProfile);
        getFishingSkillInformation(playerProfile);
        getMiningSkillInformation(playerProfile);

        return playerProfile;
    }

    private void getBasicInformation(PlayerProfile playerProfile) {
        if (checkIfPlayerExists(playerProfile, "player")) {
            //Load basic information
            String sql = "SELECT * FROM player WHERE name = ? AND uuid = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, playerProfile.getPlayerName());
                pstmt.setString(2, playerProfile.getUuid().toString());
                ResultSet rs = pstmt.executeQuery();

                //Get updates
                NormalExperiencePool nEXPPool = playerProfile.getnEXPPool();
                nEXPPool.setPoints(rs.getDouble("nexp"));
                nEXPPool.setPoolCap(rs.getDouble("nexpcap"));

                ClassExperiencePool cEXPPool = playerProfile.getcEXPPool();
                cEXPPool.setPoints(rs.getDouble("cexp"));
                cEXPPool.setPoolCap(rs.getDouble("cexpcap"));

                NormalLevel nLvl = playerProfile.getnLvl();
                nLvl.setCurrentLevel(rs.getInt("nlvl"));

                ClassLevel cLvl = playerProfile.getcLvl();
                cLvl.setCurrentLevel(rs.getInt("clvl"));

                NormalSkillPointPool nsp = playerProfile.getnSPPool();
                nsp.setNumPoints(rs.getInt("nsp"));

                ClassSkillPointPool csp = playerProfile.getcSPPool();
                csp.setNumPoints(rs.getInt("csp"));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void getFarmingSkillInformation(PlayerProfile playerProfile) {
        if (checkIfPlayerExists(playerProfile, "farming")) {
            //Load Farming Skill Tree
            String farming = "SELECT * FROM farming WHERE name = ? AND uuid = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(farming)) {
                pstmt.setString(1, playerProfile.getPlayerName());
                pstmt.setString(2, playerProfile.getUuid().toString());
                ResultSet rs = pstmt.executeQuery();

                //Get updates
                HashMap<Enum, Skill> farmingSkillTree = playerProfile.getFarmingSkillTree().getTree();

                farmingSkillTree.get(BASIC_AGRICULTURE).setSkillPoints(rs.getInt("basicAgriculture"));
                farmingSkillTree.get(TRITICUM_CULTIVATION).setSkillPoints(rs.getInt("triticumCultivation"));
                farmingSkillTree.get(FERTILIZER).setSkillPoints(rs.getInt("fertilizer"));
                farmingSkillTree.get(DAUCUS_CULTIVATION).setSkillPoints(rs.getInt("daucusCultivation"));
                farmingSkillTree.get(WEED_REMOVAL).setSkillPoints(rs.getInt("weedRemoval"));
                farmingSkillTree.get(MECHANIZED_HARVESTING).setSkillPoints(rs.getInt("mechanizedHarvesting"));
                farmingSkillTree.get(CUCURBITA_CULTIVATION).setSkillPoints(rs.getInt("cucurbitaCultivation"));
                farmingSkillTree.get(TUBEROSEM_CULTIVATION).setSkillPoints(rs.getInt("tuberosemCultivation"));
                farmingSkillTree.get(SACCHARUM_CULTIVATION).setSkillPoints(rs.getInt("saccharumCultivation"));
                farmingSkillTree.get(IANATUS_CULTIVATION).setSkillPoints(rs.getInt("ianatusCultivation"));
                farmingSkillTree.get(INDOOR_FUNGICULTURE).setSkillPoints(rs.getInt("indoorFungiculture"));
                farmingSkillTree.get(TRELLIS_GOURD_TECHNIQUES).setSkillPoints(rs.getInt("trellisGourdTechniques"));
                farmingSkillTree.get(PLANTATIONS).setSkillPoints(rs.getInt("plantations"));
                farmingSkillTree.get(CACAO_CULTIVATION).setSkillPoints(rs.getInt("cacaoCultivation"));
                farmingSkillTree.get(VULGARUS_CULTIVATION).setSkillPoints(rs.getInt("vulgarusCultivation"));
                farmingSkillTree.get(CROP_ROTATION).setSkillPoints(rs.getInt("cropRotation"));
                farmingSkillTree.get(BLIGHT_PROTECTION).setSkillPoints(rs.getInt("blightProtection"));
                farmingSkillTree.get(HYBRIDIZATION).setSkillPoints(rs.getInt("hybridization"));
                farmingSkillTree.get(GMO_CROPS).setSkillPoints(rs.getInt("gmoCrops"));
                farmingSkillTree.get(CHEMICAL_PESTICIDES).setSkillPoints(rs.getInt("chemicalPesticides"));
                farmingSkillTree.get(FARMING_MASTERY).setSkillPoints(rs.getInt("farmingMastery"));
                farmingSkillTree.get(TRANSENVIRONMENTAL_CULTIVATION).setSkillPoints(rs.getInt("transenvironmentalCultivation"));
                farmingSkillTree.get(IMPROVED_PHOTOSYNTHESIS).setSkillPoints(rs.getInt("improvedPhotosynthesis"));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void getFishingSkillInformation(PlayerProfile playerProfile) {
        if (checkIfPlayerExists(playerProfile, "fishing")) {
            //Load Farming Skill Tree
            String farming = "SELECT * FROM fishing WHERE name = ? AND uuid = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(farming)) {
                pstmt.setString(1, playerProfile.getPlayerName());
                pstmt.setString(2, playerProfile.getUuid().toString());
                ResultSet rs = pstmt.executeQuery();

                //Get updates
                HashMap<Enum, Skill> fishingSkillTree = playerProfile.getFishingSkillTree().getTree();

                fishingSkillTree.get(BASIC_FISHING).setSkillPoints(rs.getInt("basicFishing"));
                fishingSkillTree.get(FISHING_BAIT).setSkillPoints(rs.getInt("fishingBait"));
                fishingSkillTree.get(FISHING_TECHNIQUE).setSkillPoints(rs.getInt("fishingTechnique"));
                fishingSkillTree.get(FISHERMAN_FOLK_STORIES).setSkillPoints(rs.getInt("fishermanFolkStories"));
                fishingSkillTree.get(ROD_CARE).setSkillPoints(rs.getInt("rodCare"));
                fishingSkillTree.get(CURRENT_WATCHER).setSkillPoints(rs.getInt("currentWatcher"));
                fishingSkillTree.get(GRIZZLY_INSTINCTS).setSkillPoints(rs.getInt("grizzlyInstincts"));
                fishingSkillTree.get(OCEANOGRAPHY).setSkillPoints(rs.getInt("oceanography"));
                fishingSkillTree.get(DEADLIEST_CATCH).setSkillPoints(rs.getInt("deadliestCatch"));
                fishingSkillTree.get(FISHERMAN_DIET).setSkillPoints(rs.getInt("fishermanDiet"));
                fishingSkillTree.get(FISHERMAN).setSkillPoints(rs.getInt("fisherman"));
                fishingSkillTree.get(PIRATE_LORE).setSkillPoints(rs.getInt("pirateLore"));
                fishingSkillTree.get(TROLLING).setSkillPoints(rs.getInt("trolling"));
                fishingSkillTree.get(NETS).setSkillPoints(rs.getInt("nets"));
                fishingSkillTree.get(FISHERMAN_LUCK).setSkillPoints(rs.getInt("fishermanLuck "));
                fishingSkillTree.get(FISH_CLEANING).setSkillPoints(rs.getInt("fishCleaning"));
                fishingSkillTree.get(SUSHI).setSkillPoints(rs.getInt("sushi"));
                fishingSkillTree.get(PROFICIENT_FISHERMAN).setSkillPoints(rs.getInt("proficientFisherman"));
                fishingSkillTree.get(PIRATE_MAPS).setSkillPoints(rs.getInt("pirateMaps"));
                fishingSkillTree.get(SONAR).setSkillPoints(rs.getInt("sonar"));
                fishingSkillTree.get(FRESHWATER_BAITS).setSkillPoints(rs.getInt("freshwaterBaits"));
                fishingSkillTree.get(OCEAN_BAITS).setSkillPoints(rs.getInt("oceanBaits"));
                fishingSkillTree.get(AQUATIC_INSIGHT).setSkillPoints(rs.getInt("aquaticInsight"));
                fishingSkillTree.get(SEAFARER_WISDOM).setSkillPoints(rs.getInt("seafarerWisdom"));
                fishingSkillTree.get(MASTER_ANGLER).setSkillPoints(rs.getInt("masterAngler"));
                fishingSkillTree.get(PIRATE_LEGENDS).setSkillPoints(rs.getInt("pirateLegends"));
                fishingSkillTree.get(SHIPWRECK_DIVING).setSkillPoints(rs.getInt("shipwreckDiving"));
                fishingSkillTree.get(BOTTOM_TRAWLING).setSkillPoints(rs.getInt("bottomTrawling"));
                fishingSkillTree.get(ELECTROFISHING).setSkillPoints(rs.getInt("electrofishing"));
                fishingSkillTree.get(SUSTAINABLE_FISHING).setSkillPoints(rs.getInt("sustainableFishing"));
                fishingSkillTree.get(CLEAN_WATERS).setSkillPoints(rs.getInt("cleanWaters"));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void getMiningSkillInformation(PlayerProfile playerProfile) {
        if (checkIfPlayerExists(playerProfile, "mining")) {
            //Load Farming Skill Tree
            String farming = "SELECT * FROM mining WHERE name = ? AND uuid = ?";

            try (PreparedStatement pstmt = conn.prepareStatement(farming)) {
                pstmt.setString(1, playerProfile.getPlayerName());
                pstmt.setString(2, playerProfile.getUuid().toString());
                ResultSet rs = pstmt.executeQuery();

                //Get updates
                HashMap<Enum, Skill> miningSkillTree = playerProfile.getMiningSkillTree().getTree();

                miningSkillTree.get(BASIC_MINING).setSkillPoints(rs.getInt("basicMining"));
                miningSkillTree.get(MINING_EXPERTISE).setSkillPoints(rs.getInt("miningExpertise"));
                miningSkillTree.get(HEWER).setSkillPoints(rs.getInt("hewer"));
                miningSkillTree.get(LIGNITE_EXTRACTION).setSkillPoints(rs.getInt("ligniteExtraction"));
                miningSkillTree.get(THERMOLUMINESCENCE_DATING).setSkillPoints(rs.getInt("thermoluminescenceDating"));
                miningSkillTree.get(SILICONE_EXTRACTION).setSkillPoints(rs.getInt("siliconeExtraction"));
                miningSkillTree.get(FOOLS_GOLD).setSkillPoints(rs.getInt("foolsGold"));
                miningSkillTree.get(TUNNEL_DWELLER).setSkillPoints(rs.getInt("tunnelDweller"));
                miningSkillTree.get(TEMPLE_DESECRATOR).setSkillPoints(rs.getInt("templeDesecrator"));
                miningSkillTree.get(BLAST_MINING).setSkillPoints(rs.getInt("blastMining"));
                miningSkillTree.get(PROSPECTOR).setSkillPoints(rs.getInt("prospector"));
                miningSkillTree.get(BITUMINOUS_EXTRACTION).setSkillPoints(rs.getInt("bituminousExtraction"));
                miningSkillTree.get(COMMINUTION).setSkillPoints(rs.getInt("comminution"));
                miningSkillTree.get(LAZURITE_EXTRACTION).setSkillPoints(rs.getInt("lazuriteExtractionEfficiency"));
                miningSkillTree.get(POWER_MINING).setSkillPoints(rs.getInt("powerMining"));
                miningSkillTree.get(XRAY_FLOURESCENCE).setSkillPoints(rs.getInt("xrayFlourescence"));
                miningSkillTree.get(POSTLAPIDARY_OILING).setSkillPoints(rs.getInt("postlapidaryOiling"));
                miningSkillTree.get(GEOENGINEER).setSkillPoints(rs.getInt("geoengineer"));
                miningSkillTree.get(ANTHRACITE_EXTRACTION).setSkillPoints(rs.getInt("anthraciteExtraction"));
                miningSkillTree.get(GRAVITY_SEPARATION).setSkillPoints(rs.getInt("gravitySeparation"));
                miningSkillTree.get(HYDRAULIC_MINING).setSkillPoints(rs.getInt("hydraulicMining"));
                miningSkillTree.get(ELECROMAGNETIC_RADIATION).setSkillPoints(rs.getInt("electromagneticRadiation"));
                miningSkillTree.get(CUTTING_EFFICIENCY).setSkillPoints(rs.getInt("cuttingEfficiency"));
                miningSkillTree.get(WURTZITE_BORON_NITRIDE_DRILL).setSkillPoints(rs.getInt("wurtziteBoronNitrideDrill"));
                miningSkillTree.get(MANTLE_DRILLING).setSkillPoints(rs.getInt("mantleDrilling"));
                miningSkillTree.get(MAGNETITE_FROTH_FLOTATION).setSkillPoints(rs.getInt("magnetiteFrothFlotation"));
                miningSkillTree.get(LEACHING).setSkillPoints(rs.getInt("leaching"));
                miningSkillTree.get(MINING_MASTERY).setSkillPoints(rs.getInt("miningMastery"));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*******************************/
    /***** Create Skill Tables *****/
    /*******************************/

    private void createSkillTables(Connection conn) {

        createFarmingTable(conn);
        createFishingTable(conn);
        createMiningTable(conn);

    }

    private void createFarmingTable(Connection conn) {
        String farming = "CREATE TABLE IF NOT EXISTS farming(\n"
                + "      name varchar(40),\n"
                + "      uuid varchar(36),\n"
                + "      basicAgriculture int,\n"
                + "      triticumCultivation int,\n"
                + "      fertilizer int,\n"
                + "      weedRemoval int,\n"
                + "      daucusCultivation int,\n"
                + "      cucurbitaCultivation int,\n"
                + "      mechanizedHarvesting int,\n"
                + "      tuberosemCultivation int,\n"
                + "      saccharumCultivation int,\n"
                + "      fungalFarming int,\n"
                + "      ianatusCultivation int,\n"
                + "      vulgarusCultivation int,\n"
                + "      cacaoCultivation int,\n"
                + "      plantations int,\n"
                + "      indoorFungiculture int,\n"
                + "      trellisGourdTechniques int,\n"
                + "      cropRotation int,\n"
                + "      blightProtection int,\n"
                + "      hybridization int,\n"
                + "      chemicalPesticides int,\n"
                + "      gmoCrops int,\n"
                + "      transenvironmentalCultivation int,\n"
                + "      improvedPhotosynthesis int,\n"
                + "      farmingMastery int);";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(farming);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createFishingTable(Connection conn) {
        String farming = "CREATE TABLE IF NOT EXISTS fishing(\n"
                + "      name varchar(40),\n"
                + "      uuid varchar(36),\n"
                + "      basicFishing int,\n"
                + "      fishingBait int,\n"
                + "      fishingTechnique int,\n"
                + "      fishermanFolkStories  int,\n"
                + "      rodCare int,\n"
                + "      currentWatcher int,\n"
                + "      grizzlyInstincts int,\n"
                + "      oceanography int,\n"
                + "      deadliestCatch int,\n"
                + "      fishermanDiet int,\n"
                + "      fisherman int,\n"
                + "      pirateLore int,\n"
                + "      trolling int,\n"
                + "      nets int,\n"
                + "      fishermanLuck int,\n"
                + "      fishCleaning int,\n"
                + "      sushi int,\n"
                + "      proficientFisherman int,\n"
                + "      pirateMaps int,\n"
                + "      sonar int,\n"
                + "      freshwaterBaits int,\n"
                + "      oceanBaits int,\n"
                + "      aquaticInsight int,\n"
                + "      seafarerWisdom int,\n"
                + "      masterAngler int,\n"
                + "      pirateLegends  int,\n"
                + "      bottomTrawling int,\n"
                + "      electrofishing int,\n"
                + "      shipwreckDiving int,\n"
                + "      sustainableFishing int,\n"
                + "      cleanWaters int);";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(farming);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createMiningTable(Connection conn) {
        String mining = "CREATE TABLE IF NOT EXISTS mining(\n"
                + "      name varchar(40),\n"
                + "      uuid varchar(36),\n"
                + "      basicMining int,\n"
                + "      miningExpertise int,\n"
                + "      hewer int,\n"
                + "      ligniteExtraction int,\n"
                + "      thermoluminescenceDating int,\n"
                + "      siliconeExtraction int,\n"
                + "      foolsGold int,\n"
                + "      tunnelDweller int,\n"
                + "      templeDesecrator int,\n"
                + "      blastMining int,\n"
                + "      prospector int,\n"
                + "      bituminousExtraction int,\n"
                + "      comminution int,\n"
                + "      lazuriteExtractionEfficiency int,\n"
                + "      powerMining int,\n"
                + "      xrayFlourescence int,\n"
                + "      postlapidaryOiling int,\n"
                + "      geoengineer int,\n"
                + "      anthraciteExtraction int,\n"
                + "      gravitySeparation int,\n"
                + "      hydraulicMining int,\n"
                + "      electromagneticRadiation int,\n"
                + "      cuttingEfficiency int,\n"
                + "      wurtziteBoronNitrideDrill int,\n"
                + "      mantleDrilling int,\n"
                + "      magnetiteFrothFlotation int,\n"
                + "      leaching int,\n"
                + "      miningMastery int);";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(mining);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public boolean checkIfPlayerExists(PlayerProfile playerProfile, String table) {
        boolean doesExist = false;

        String sql = "SELECT (count(*) > 0) as found FROM " + table +" WHERE name = ? AND uuid = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, playerProfile.getPlayerName());
            pstmt.setString(2, playerProfile.getUuid().toString());
            try (ResultSet rs = pstmt.executeQuery()) {
                // Only expecting a single result
                if (rs.next()) {
                    doesExist = rs.getBoolean(1); // "found" column
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return doesExist;
    }
}
