package sample;

import sample.Message;
import sample.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evangelos on 20/4/2015.
 */
public class MySQLConnect {


    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/twiter";
    static final String USER = "twiter";
    static final String PASS = "1234";

    public static void connect() {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * from user";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Email: " + email);
                System.out.print(", Password: " + password);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main


    public static User loggin(String email, String password) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * from user where email ='" + email + "'AND password='" + password + "'";
            ResultSet rs = stmt.executeQuery(sql);
            int cnt = 0;
            while (rs.next()) {
                cnt++;
                User u = new User(rs.getInt("id"), rs.getString("email"), rs.getString("password"));
                return u;
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return null;
    }//end main


    public static boolean postMessage(int userid, String message) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO message (msg,userid ) " + "VALUES ('" + message + "'," + userid + " )";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
            return true;

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return false;
    }//end

/*
    String sql = "INSERT INTO Registration " +
            "VALUES (100, 'Zara', 'Ali', 18)";
    stmt.executeUpdate(sql);
    sql = "INSERT INTO Registration " +
            "VALUES (101, 'Mahnaz', 'Fatma', 25)";
    stmt.executeUpdate(sql);
    sql = "INSERT INTO Registration " +
            "VALUES (102, 'Zaid', 'Khan', 30)";
    stmt.executeUpdate(sql);
    sql = "INSERT INTO Registration " +
            "VALUES(103, 'Sumit', 'Mittal', 28)";
    stmt.executeUpdate(sql);
    System.out.println("Inserted records into the table...");*/

    public static List<Message> getMessages(String email) {
        Connection conn = null;
        Statement stmt = null;
        List<Message> answer = new ArrayList<Message>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT message.id, user.email, message.message  \n" +
                    "FROM user Join message on user.id=message.uid\n" +
                    "where user.email= '" + email + "'";
            ResultSet rs = stmt.executeQuery(sql);
            int cnt = 0;

            while (rs.next()) {
                Message m = new Message(String.valueOf(rs.getInt("id")),rs.getString("email"), rs.getString("message"));
                answer.add(m);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return answer;
    }//end main

}
