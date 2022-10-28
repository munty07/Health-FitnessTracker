/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author adeli
 */
public class DbConnectionCaloriesForEachDatabase {
    
    public static Connection connect() throws SQLException
    {
        Connection con = null; 
    try {
      Class.forName("org.sqlite.JDBC");
      con = DriverManager.getConnection("jdbc:sqlite:CaloriesForEach.db"); // Conectare la baza de date
      System.out.println("Conectat la baza de date calorii partiale !");
    } catch (ClassNotFoundException | SQLException e ) {
      // TODO Auto-generated catch block
      System.out.println(e+"");
    }
    
    return con; 
  }
    
}
