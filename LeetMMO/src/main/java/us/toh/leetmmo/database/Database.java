package us.toh.leetmmo.database;

import us.toh.leetmmo.LeetMMO;
import us.toh.leetmmo.datatypes.experience.ClassExperiencePool;
import us.toh.leetmmo.datatypes.experience.NormalExperiencePool;
import us.toh.leetmmo.datatypes.level.ClassLevel;
import us.toh.leetmmo.datatypes.level.NormalLevel;
import us.toh.leetmmo.datatypes.player.PlayerProfile;
import us.toh.leetmmo.datatypes.skillpoint.ClassSkillPointPool;
import us.toh.leetmmo.datatypes.skillpoint.NormalSkillPointPool;

import java.io.File;
import java.sql.*;

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
                                + "      cexp int, \n"
                                + "      nexpcap int,\n"
                                + "      cexpcap int, \n"
                                + "      nlvl int, \n"
                                + "      clvl int, \n"
                                + "      nsp int, \n"
                                + "      csp int);";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(playerTableCheck);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
        if (!checkIfPlayerExists(playerProfile)) {
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

    public void updatePlayerProfile(PlayerProfile playerProfile) {
        if (checkIfPlayerExists(playerProfile)) {
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

    public PlayerProfile getPlayerProfileFromDatabase(PlayerProfile playerProfile) {

        if (checkIfPlayerExists(playerProfile)) {
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

        return playerProfile;
    }

    public boolean checkIfPlayerExists(PlayerProfile playerProfile) {
        boolean doesExist = false;

        String sql = "SELECT (count(*) > 0) as found FROM player WHERE name = ? AND uuid = ?";
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
