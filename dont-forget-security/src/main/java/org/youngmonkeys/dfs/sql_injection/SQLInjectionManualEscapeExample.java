package org.youngmonkeys.dfs.sql_injection;

import java.sql.*;

public class SQLInjectionManualEscapeExample {

    public static void main(String[] args) throws Exception {
        String username = "' OR '1'='1";
        String password = "' OR '1'='1";
        if (args.length > 1) {
            username = args[0];
            password = args[1];
        }

        // Escape chuỗi để tránh SQL Injection
        username = escapeSql(username);
        password = escapeSql(password);

        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String dbPassword = "12345678";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword)) {
            String query = "SELECT * FROM users WHERE username = '" + username +
                "' AND password = '" + password + "'";
            System.out.println("SQL query sau khi escape: " + query);

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

    public static String escapeSql(String input) {
        if (input == null) return null;
        return input
            .replace("\\", "\\\\")
            .replace("'", "''")
            .replace("\"", "\\\"")
            .replace(";", "\\;")
            .replace("--", "\\--");
    }
}

