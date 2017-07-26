package ru.levelup.javacondb.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcMain {

    public static void main(String[] args) throws SQLException{
//        Connection mysql = JdbcUtils.getConnection();
//        mysql.close();

        JdbcUtils.execute(JdbcUtils.CREATE_TABLE_USER);

    }

}
