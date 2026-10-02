/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connectionSQLServer;
import java.sql.Connection;
import java.sql.DriverManager;

public class connection {
    public static Connection getConnection() {
        try {
            String url =
                "jdbc:sqlserver://localhost:1433;"
                + "databaseName=THITRACNGHIEM;"
                + "encrypt=true;"
                + "trustServerCertificate=true;";

            String user = "sa";
            String password = "123";

            Connection conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
            System.out.println("Ket noi thanh cong!");
            return conn;
            } else {
            System.out.println("Ket noi that bai!");
            return null;
        }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Connection getConnectionSV() {
    try {
        String url =
            "jdbc:sqlserver://localhost:1433;"
            + "databaseName=THITRACNGHIEM;"
            + "encrypt=true;"
            + "trustServerCertificate=true;"
            + "loginTimeout=10;"
            + "socketTimeout=20000;"
            + "cancelQueryTimeout=3;";

        String user = "sv";
        String password = "Hson1234@";

        Connection conn = DriverManager.getConnection(url, user, password);

        if (conn != null) {
            System.out.println("Ket noi bang tai khoan SV thanh cong!");
            return conn;
            } else {
            System.out.println("Ket noi SV that bai!");
            return null;
        }
        

    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    
}
    public static Connection getConnectionGV(String user, String password) throws java.sql.SQLException {
        String url =
            "jdbc:sqlserver://localhost:1433;"
            + "databaseName=THITRACNGHIEM;"
            + "encrypt=true;"
            + "trustServerCertificate=true;"
            + "loginTimeout=10;"
            + "socketTimeout=20000;"
            + "cancelQueryTimeout=3;";

        return DriverManager.getConnection(url, user, password);
    }
    public static void main(String[] args) {
        getConnection();
        getConnectionSV();
 
    }
}
