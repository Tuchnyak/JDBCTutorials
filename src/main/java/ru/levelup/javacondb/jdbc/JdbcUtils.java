package ru.levelup.javacondb.jdbc;

import java.sql.*;

public class JdbcUtils {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("********\nCould not connect register driver " + e.getMessage() + "\n********");
        }
    }

    public static Connection getConnection() {
        try {
            System.out.println("MySQL");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/posts?useSSL=false" +
                    "&useJDBCCompliantTimezoneShift=true&useLegacyDateTimeCode=false" +
                    "&serverTimezone=UTC", "root", "Y|p9!]Zu9-.|7Dm)LQ*;");
        } catch (SQLException e) {
            throw new RuntimeException("********\nCould not get connection " + e.getMessage() + "\n********");
        }
    }

    public static final String CREATE_TABLE_USER = "create table if not exists USER (" +
            "userID int primary key auto_increment," +
            "login varchar(20) unique not null," +
            "name varchar(30) not null," +
            "last_name varchar(30) not null" +
            ")";

    public static final String CREATE_TABLE_POSTS = "create table if not exists POSTS (" +
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

    public static void teachersSelection() {
        try (Connection connection = getConnection();
        Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("Select u.name, u.last_name, u.login," +
                    "p.title, p. text, p.postID " +
                    "from user as u, posts as p where u.userID = p.userID;");
            while (rs.next()) {
                String name = rs.getString("u.name");
                String lastName = rs.getString("u.last_name");
                String login = rs.getString("u.login");
                String title = rs.getString("p.title");
                String text = rs.getString("p.text");
                int postID= rs.getInt("p.PostID");

                System.out.println(name + " | " + lastName + " | " + login + " | "
                        + title + " | " + text + " | " + postID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void showAllUserPosts() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery("select * from USER;");
            while (rs.next()) {
                int userID = rs.getInt("userID");
                String login = rs.getString("login");
                String name = rs.getString("name");
                String lastName = rs.getString("last_name");

                System.out.println("User ID: " + userID + " Login: " + login +
                        " Name: " + name + " Last name: " + lastName);
            }

            rs = statement.executeQuery("select * from POSTS;");
            while (rs.next()) {
                int postID = rs.getInt("postID");
                String title = rs.getString("title");
                String text = rs.getString("text");
                Date postedAt = rs.getDate("postedAt");
                int userID = rs.getInt("userID");

                System.out.println("Post ID: " + postID + " Title: " + title + " Text: " + text +
                " Posted at: " + postedAt + " User ID: " + userID);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
