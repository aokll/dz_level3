package com.chat.auth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private DataSource(){}

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BD1",
                    "root",
                    ""
            );
        } catch (SQLException throwables) {
            throw new RuntimeException("SWW",throwables);
        }
    }
    public static void close(Connection connection){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throw new RuntimeException("SWW",throwables);
        }
    }
}
