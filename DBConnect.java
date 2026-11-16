package vn.iotstar.configs;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    private final String serverName = "localhost";
    private final String instanceName = "SQLEXPRESS";
    private final String dbName = "UserAuthDB";
    private final String userID = "sa";
    private final String password = "123";

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://" + serverName + "\\" + instanceName 
                    + ";databaseName=" + dbName 
                    + ";encrypt=true;trustServerCertificate=true;";

            try {
                conn = DriverManager.getConnection(url, userID, password);
            } catch (Exception e) {
                String urlIntegrated = "jdbc:sqlserver://" + serverName + "\\" + instanceName 
                        + ";databaseName=" + dbName 
                        + ";integratedSecurity=true"
                        + ";encrypt=true;trustServerCertificate=true;";
                conn = DriverManager.getConnection(urlIntegrated);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
