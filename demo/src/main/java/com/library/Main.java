package com.library;

import com.library.database.DB;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = DB.getConnection();
        System.out.println(connection);
        DB.closeConnection();
    }
}