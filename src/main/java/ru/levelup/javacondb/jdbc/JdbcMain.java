package ru.levelup.javacondb.jdbc;

import java.sql.SQLException;

public class JdbcMain {

    public static void main(String[] args) throws SQLException{
//        Connection mysql = JdbcUtils.getConnection();
//        mysql.close();

        JdbcUtils.execute(JdbcUtils.CREATE_TABLE_USER);

        JdbcUtils.execute(JdbcUtils.CREATE_TABLE_POSTS);


        JdbcUtils.execute("insert into user (login, name, last_name) values ('krispo', 'Mark', 'Antonii');");
        JdbcUtils.execute("insert into user (login, name, last_name) values ('spooky', 'Tony', 'Likeapony');");
        JdbcUtils.execute("insert into user (login, name, last_name) values ('chunck', 'Ash', 'Dash');");

        JdbcUtils.execute("insert into posts (title, text, postedAt, userID)" +
                "values ('About spheres', 'Is it possible to curve spheres?', now(), 1);");
        JdbcUtils.execute("insert into posts (title, text, postedAt, userID)" +
                "values ('re: About spheres', 'Is it a cow?', now(), 1);");
        JdbcUtils.execute("insert into posts (title, text, postedAt, userID)" +
                "values ('Tony', 'Does Tony like a pony?', now(), 3);");

//        JdbcUtils.showAllUserPosts();

        JdbcUtils.teachersSelection();

    }

}
