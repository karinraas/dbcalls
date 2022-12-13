package com.database;


import com.json2Java.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DatabaseConnection {
    Credentials cred = new Credentials();

    public void getCred(getUser, getPass){
        username = getUser;
        password = getPass;

        System.out.println("username: " + username);
        System.out.println("password: " + password);
    }

    public void getConnection () {
        Credentials cred = new Credentials(user, pass);
        try (Connection ACCconn = DriverManager.getConnection("dbaddress.se", username, password)) {
            //Statement stmt = ACCconn.createStatement();
            //ResultSet rs = stmt.executeQuery("SELECT a, b, c FROM table1");
           /* while (rs.next()) {
                int x = rs.getInt("a");
                String s = rs.getString("b");
                float f = rs.getFloat("c");
            } */

            System.out.println("User: " + cred.user + " Pass" + cred.pass);
            System.out.println("DB Connection is: " + ACCconn);
            close(ACCConn);

        }  catch (SQLException sqle) {
            System.out.println("Error is: " + sqle.getErrorCode());
            System.out.println("SQL State: " + sqle.getSQLState());
            System.out.println("Errormessage: " + sqle.getMessage());
        }
    }

    public static void Main (String[] args){
        return null;

    }
}
