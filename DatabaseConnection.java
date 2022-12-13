package com.database;

import com.json2Java.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.sql.*;

public class DatabaseConnection {


    public void getCred(String user, String pass){
        String username = user;
        String password = pass;

        System.out.println("username: " + username);
        System.out.println("password: " + password);
    }

    public void getConnection(String username, String password) {
        Credentials cred = new Credentials(username, password);
        try (Connection ACCconn = DriverManager.getConnection("jdbc:derby://localhost:1527/myDataBase", username, password)) {
            //Statement stmt = ACCconn.createStatement();
            //ResultSet rs = stmt.executeQuery("SELECT a, b, c FROM table1");
           /* while (rs.next()) {
                int x = rs.getInt("a");
                String s = rs.getString("b");
                float f = rs.getFloat("c");
            } */

           System.out.println("User: " + username + " Pass" + password);
            System.out.println("DB Connection is: " + ACCconn);

        }  catch (SQLException sqle) {
            System.out.println("Error is: " + sqle.getErrorCode());
            System.out.println("SQL State: " + sqle.getSQLState());
            System.out.println("Errormessage: " + sqle.getMessage());
        }
        close(ACCConn);
    }

    public static void Main (String[] args){
        return null;

    }
}
