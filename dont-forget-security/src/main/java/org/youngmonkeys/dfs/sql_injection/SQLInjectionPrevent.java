package org.youngmonkeys.dfs.sql_injection;

import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/login")
public class SQLInjectionPrevent {

    public static void main(String[] args) {
        String username = args[0];
        String password = args[1];
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test",
                "root",
                "12345678"
            );
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM users WHERE username = ?0 AND password = ?1";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(0, username);
            preparedStatement.setString(1, password);
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                System.out.println("Login successful");
            } else {
                System.out.println("Invalid credentials");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
