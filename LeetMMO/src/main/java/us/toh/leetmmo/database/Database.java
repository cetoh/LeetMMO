package us.toh.leetmmo.database;

import us.toh.leetmmo.LeetMMO;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public LeetMMO plugin;

    public Database(LeetMMO plugin) {
        this.plugin = plugin;
    }

    public void createNewDatabase(String fileName) {

        File file = new File(plugin.getDataFolder().getAbsolutePath());
        if (!file.exists()) {
            file.mkdir();
        }

        String url = "jdbc:sqlite:" + plugin.getDataFolder().getAbsolutePath() + "\\" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A database connection has been made.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
