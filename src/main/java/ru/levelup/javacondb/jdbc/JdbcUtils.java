package ru.levelup.javacondb.jdbc;

import java.sql.*;

public class JdbcUtils {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not connect register driver" + e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            System.out.println("MySQL");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/posts?useSSL=false" +
                    "&useJDBCCompliantTimezoneShift=true&useLegacyDateTimeCode=false" +
                    "&serverTimezone=UTC", "root", "Y|p9!]Zu9-.|7Dm)LQ*;");
        } catch (SQLException e) {
            throw new RuntimeException("Couldn't get connection " + e.getMessage());
        }
    }

    public static final String CREATE_TABLE_USER = "create table if not exists USER (" +
            "userID int primary key auto_increment," +
            "login varchar(20) unique not null," +
            "name varchar(30) not null," +
            "last_name varchar(30) not null" +
            ")";

    public static final String  CREATE_TABLE_POSTS = "create table if no exists POSTS (" +
            "postID int primary key auto_increment," +
            "title varchar(100) not null," +
            "text varchar(300) not null," +
            "postedAt timestamp not null," +
            "userID int not null," +
            "foreign key (userID) references USER(userID)" +
            ")";

    public static void execute(String sql) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            System.out.println(statement.executeUpdate(sql));
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

//    public static void execute(String sql) {
//        try (Connection connection = getConnection();
//             Statement statement = connection.createStatement()){
//            System.out.println(statement.executeUpdate(sql));
//            ResultSet rs = statement.executeQuery("select * from user");
//            while (rs.next()) {
//
//                int userId = rs.getInt("user_id");
//                String login = rs.getString("login");
//
//                System.out.println("Id = " + userId + ", Login = " + login);
//
//            }
//        } catch (SQLException e) {
//            throw  new RuntimeException(e);
//        }
//    }

}
