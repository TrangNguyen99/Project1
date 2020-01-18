package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDatabase {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-HOJAS0N\\SQLEXPRESS:1433;" +
                    "databaseName=Project1v2;integratedSecurity=true;");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
