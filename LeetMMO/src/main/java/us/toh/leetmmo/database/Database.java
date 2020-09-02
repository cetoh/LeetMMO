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

import static us.toh.leetmmo.skills.normal.NormalSkillEnums.FarmingSkillNames.*;

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

    /**
     * Insert a new row into the warehouses table
     *
     * @param playerProfile
     */
    public void insertNewPlayerProfile(PlayerProfile playerProfile) {
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
                    "vulgarisCultivation," +
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

    public void updatePlayerProfile(PlayerProfile playerProfile) {
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
                    "vulgarisCultivation = ?, " +
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

    public PlayerProfile getPlayerProfileFromDatabase(PlayerProfile playerProfile) {

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

        return playerProfile;
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

    private void createSkillTables(Connection conn) {

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
                + "      vulgarisCultivation int,\n"
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
}
