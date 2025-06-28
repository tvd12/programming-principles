package org.youngmonkeys.dfs.sql_injection;

import java.sql.*;

public class SQLInjectionExample {

    public static void main(String[] args) throws Exception {
        String username = "\"' OR 1=1 -- \" world";
        String password = "\"' OR 1=1 -- \" world";
        if (args.length > 2) {
            username = args[0];
            password = args[1];
        }
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String dbPassword = "12345678";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection =
                 DriverManager.getConnection(url, user, dbPassword)) {
            // Tạo truy vấn SQL không an toàn
            String query =
                "SELECT * FROM users WHERE username = '" + username +
                    "' AND password = '" + password + "'";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                System.out.println("Đăng nhập thành công!");
            } else {
                System.out.println("Đăng nhập không thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

