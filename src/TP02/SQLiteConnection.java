/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP02;

/**
 *
 * @author hisham
 */
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class SQLiteConnection {
    public static Connection conn = null;
    public static String sqliteServer = "jdbc:sqlite:";
    public static String resetPath = "";
    
    public static boolean isDatabaseExist(String dbFilePath){
        File dbFile = new File(dbFilePath);
        return dbFile.exists();
    }

    
    public static void connect() {
        try {
            // db parameters
            sqliteServer = "jdbc:sqlite:";
            String getFilePath = new File("").getAbsolutePath();
            String fileAbsolutePath = getFilePath.concat("/src/TP02/DB/tp03DB.sqlite");
            resetPath = fileAbsolutePath;

            System.out.println(sqliteServer);
            System.out.println(getFilePath);
            System.out.println(fileAbsolutePath);
            // create a connection to the database
            
            if(isDatabaseExist(fileAbsolutePath)){
                System.out.println("DB Selection: ");
                conn = DriverManager.getConnection(sqliteServer+fileAbsolutePath);
                System.out.println("Connection to SQLite has been established.");
            }else{
                try{
                    createNewDatabase("DB", "tp03DB");
                }catch (Exception ex){
                    System.out.println("Error: " + ex);
                }             
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        /*
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        */
    }
    
    public static void createNewDatabase(String fileSubFolder, String fileName) {
 
        String getFilePath = new File("").getAbsolutePath();
        String fileAbsolutePath = "";
        
        if(fileSubFolder.isEmpty()){
            fileAbsolutePath = getFilePath.concat("/src/TP02/"+fileName+".sqlite");
            resetPath = fileAbsolutePath;
        }else{
            fileAbsolutePath = getFilePath.concat("/src/TP02/"+fileSubFolder+"/"+fileName+".sqlite");
            resetPath = fileAbsolutePath;
        }
        
        try (Connection conn = DriverManager.getConnection(sqliteServer+fileAbsolutePath)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                // System.out.println("The driver name is " + meta.getDriverName());
                Statement statement  = conn.createStatement();
                statement.executeQuery("CREATE TABLE history(ID INT PRIMARY KEY NOT NULL, Input TEXT NOT NULL, Hexa TEXT NOT NULL, Octal TEXT NOT NULL, Binary TEXT NOT NULL);");
                System.out.println("Database Has Been Created!");
                // statement.executeQuery("INSERT INTO history (Input, Hexa, Octal, Binary) VALUES (0, 0, 0, 0)");
            }
            createDatabaseTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void createDatabaseTable(){
        try (Connection conn = DriverManager.getConnection(sqliteServer+resetPath)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("Database Path: " + resetPath);
                
                Statement statement  = conn.createStatement();
                statement.executeUpdate("CREATE TABLE history(ID INT PRIMARY KEY NOT NULL, Input TEXT NOT NULL, Hexa TEXT NOT NULL, Octal TEXT NOT NULL, Binary TEXT NOT NULL);");
                System.out.println("Table Has Been Created!");
                // statement.executeQuery("INSERT INTO history (Input, Hexa, Octal, Binary) VALUES (0, 0, 0, 0)");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void insertDatabaseData(int id, String Input, String Hexa, String Octal, String Binary) {
        String sql = "INSERT INTO history (ID, Input, Hexa, Octal, Binary) VALUES (?, ?, ?, ?, ?)";
 
        try (Connection conn = DriverManager.getConnection(sqliteServer+resetPath)) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, Input);
            pstmt.setString(3, Hexa);
            pstmt.setString(4, Octal);
            pstmt.setString(5, Binary);
            pstmt.executeUpdate();
            
            System.out.println("Data Inserted!");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
