
package net.codejava.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MainApp {
	
	
    public static void main(String[] args){
        String url= "jdbc:sqlserver://DESKTOP-H8BR098\\SQLEXPRESS:1433;databaseName=InchirieriMasini;integratedSecurity=true";
        try {
           Connection connection = DriverManager.getConnection(url) ;
            GUI_Login obj = new GUI_Login(connection);
           // connection.close();
            System.out.println("Database has been successfully connected.");
        } catch (SQLException e){
            e.printStackTrace();
        }
  
    }
}
