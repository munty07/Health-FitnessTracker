/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author adeli
 */

public class MainApp {
     public static void main(String[] args) throws SQLException {
        DbConnectionAccountsDatabase.connect();
        
        
        LoginScreen log=new LoginScreen();
        log.setVisible(true);
        
    }
     
     
     //INSERARE UTILIZATOR NOU 
     public static void insert(String Utilizator, String Parola, String Intrebare, String Raspuns) throws SQLException {
  Connection con = DbConnectionAccountsDatabase.connect();
  PreparedStatement ps = null; 
  try {
    String sql = "INSERT INTO Accounts(Utilizator, Parola,Intrebare, Raspuns) VALUES(?,?,?,?) ";
    ps = con.prepareStatement(sql);
    ps.setString(1, Utilizator);
    ps.setString(2, Parola);
    ps.setString(3, Intrebare);
    ps.setString(4, Raspuns);
    ps.execute();
    System.out.println("Data has been inserted!");
  } catch(SQLException e) {
    System.out.println(e.toString());
  
  } finally {
    try{
      ps.close();
      con.close();
    } catch(SQLException e) {
      System.out.println(e.toString());
    }
  }
}
     
     
// INSERARE INFORMATII PROFIL
 public static void insertProfileInfo(String Varsta, String Greutate, String Inaltime, String Activitate, String Sex,String Utilizator,String Prenume) throws SQLException {
  Connection con = DbConnectionProfileInfoDatabase.connect();
  PreparedStatement ps = null; 
  try {
    String sql = "INSERT INTO ProfileData(Varsta, Greutate,Inaltime, Activitate,Sex,Utilizator,Prenume) VALUES(?,?,?,?,?,?,?) ";
    ps = con.prepareStatement(sql);
    ps.setString(1, Varsta);
    ps.setString(2, Greutate);
    ps.setString(3, Inaltime);
    ps.setString(4, Activitate);
    ps.setString(5, Sex);
    ps.setString(6, Utilizator);
    ps.setString(7,Prenume);
    ps.execute();
    System.out.println("Data has been inserted!");
  } catch(SQLException e) {
    System.out.println(e.toString());
    // always remember to close database connections
  } finally {
    try{
      ps.close();
      con.close();
    } catch(SQLException e) {
      System.out.println(e.toString());
    }
    
  }
}
 
 public static void insertNewAliment(String Aliment, String Calorii,DefaultTableModel mod) throws SQLException {
  Connection con = DbConnectionFoodInfoDatabase.connect();
  PreparedStatement ps = null; 
 
  try {
    String sql = "INSERT INTO Food(Aliment, Calorii) VALUES(?,?) ";
    ps = con.prepareStatement(sql);
    ps.setString(1, Aliment);
    ps.setString(2, Calorii);
    ps.execute();
    System.out.println("Data has been inserted into FoodInfoDatabase!");
    String []newToAdd={Aliment,Calorii};
    mod.addRow(newToAdd);
  } catch(SQLException e) {
    System.out.println(e.toString());
    // always remember to close database connections
  } finally {
    try{
      ps.close();
      con.close();
    } catch(SQLException e) {
      System.out.println(e.toString());
    }
  }
}
     
 public static void insertUserForCalories(String User) throws SQLException {
  Connection con = DbConnectionCaloriesForEachDatabase.connect();
  PreparedStatement ps = null; 
 
  try {
    String sql = "INSERT INTO CaloriesForEach(Utilizator) VALUES(?) ";
    ps = con.prepareStatement(sql);
    ps.setString(1, User);
    ps.execute();
    System.out.println("Data has been inserted into CaloriesForEach!");
    
  } catch(SQLException e) {
    System.out.println(e.toString());
    // always remember to close database connections
  } finally {
    try{
      ps.close();
      con.close();
    } catch(SQLException e) {
      System.out.println(e.toString());
    }
  }
}
 public static void insertCaloriesEachDay(String Calorii,String User) throws SQLException {
  Connection con = DbConnectionCaloriesForEachDatabase.connect();
  PreparedStatement ps = null; 
 
  try {
    String sql = "INSERT INTO CaloriesEachDay(Calorii,Utilizator) VALUES(?,?) ";
    ps = con.prepareStatement(sql);
    ps.setString(1, Calorii);
    ps.setString(2,User);
    ps.execute();
    System.out.println("Data has been inserted into CaloriesEachDay!");
    
  } catch(SQLException e) {
    System.out.println(e.toString());
    // always remember to close database connections
  } finally {
    try{
      ps.close();
      con.close();
    } catch(SQLException e) {
      System.out.println(e.toString());
    }
  }
}
  
 
 public static void insertBurnedCalories(String Arse,String Pasi,String User) throws SQLException {
  Connection con = DbConnectionCaloriesForEachDatabase.connect();
  PreparedStatement ps = null; 
 
  try {
    String sql = "INSERT INTO BurnedCalories(Calorii_arse,Pasi,Utilizator) VALUES(?,?,?) ";
    ps = con.prepareStatement(sql);
    ps.setString(1, Arse);
    ps.setString(2,Pasi);
    ps.setString(3, User);
    ps.execute();
    System.out.println("Data has been inserted into BurnedCalories!");
    
  } catch(SQLException e) {
    System.out.println(e.toString());
    // always remember to close database connections
  } finally {
    try{
      ps.close();
      con.close();
    } catch(SQLException e) {
      System.out.println(e.toString());
    }
  }
}
     
 
   public static int readSpecificRow(String nume) throws SQLException {
    // lets read specific row on the database
    Connection con = DbConnectionAccountsDatabase.connect(); 
    PreparedStatement ps = null; 
    ResultSet rs = null; 
    String utilizator = null;
    try {
      String sql = "Select Utilizator from Accounts";  // Selecteaza utilizatorii din BD
      ps = con.prepareStatement(sql); 
      
      rs = ps.executeQuery(); 
     while(rs.next()){
      // we are reading one row, so no need to loop 
      utilizator = rs.getString(1); 
      if(nume.equals(utilizator))// Verifica daca numele de utilizator exista deja in baza de date
      {
          return -1;
      }
     }
     } catch(SQLException e) {
      System.out.println(e.toString());
    } finally {
      // close connections
      try{
        rs.close(); 
        ps.close();
        con.close(); 
      } catch (SQLException e) {
        // TODO: handle exception
        System.out.println(e.toString());
      }
    }
    return 0;
   }
    
    
    public static int VerifyUser(String nume,String parola) throws SQLException {
    // lets read specific row on the database
    Connection con = DbConnectionAccountsDatabase.connect(); 
    PreparedStatement ps = null; 
    ResultSet rs = null; 
    String utilizator = null;
    String pass=null;
    try {
      String sql = "Select Utilizator,Parola from Accounts";  // Selecteaza utilizatorii din BD
      ps = con.prepareStatement(sql); 
      rs = ps.executeQuery(); 
     while(rs.next()){
      // we are reading one row, so no need to loop 
      utilizator = rs.getString(1); 
      pass=rs.getString(2);
      if(nume.equals(utilizator))// Verifica daca numele de utilizator exista deja in baza de date
      {
          if(parola.equals(pass))
          {
              return 1;
          }
      }
     }
     } catch(SQLException e) {
      System.out.println(e.toString());
    } finally {
      // close connections
      try{
        rs.close(); 
        ps.close();
        con.close(); 
      } catch (SQLException e) {
        // TODO: handle exception
        System.out.println(e.toString());
      }
    }
    
    return 0;
  }  
     
     
    
    public static int readAnswer(String nume,String raspunsDat) throws SQLException {
    // lets read specific row on the database
    Connection con = DbConnectionAccountsDatabase.connect(); 
    PreparedStatement ps = null; 
    ResultSet rs = null; 
    String raspuns = null;
    try {
      String sql = "Select Raspuns from Accounts WHERE Utilizator=?";  // Selecteaza utilizatorii din BD
      ps = con.prepareStatement(sql); 
       ps.setString(1, nume);
      rs = ps.executeQuery(); 
     while(rs.next()){
      // we are reading one row, so no need to loop 
      raspuns = rs.getString(1); 
      if(raspuns.equals(raspunsDat))// Verifica daca numele de utilizator exista deja in baza de date
      {
          return 1;
      }
     }
     } catch(SQLException e) {
      System.out.println(e.toString());
    } finally {
      // close connections
      try{
        rs.close(); 
        ps.close();
        con.close(); 
      } catch (SQLException e) {
        // TODO: handle exception
        System.out.println(e.toString());
      }
    }
    return 0;
   }
     
    public static String getQuestion(String nume) throws SQLException {
    // lets read specific row on the database
    Connection con = DbConnectionAccountsDatabase.connect(); 
    PreparedStatement ps = null; 
    ResultSet rs = null; 
    String raspuns = null;
    try {
      String sql = "Select Intrebare from Accounts WHERE Utilizator=?";  // Selecteaza utilizatorii din BD
      ps = con.prepareStatement(sql); 
       ps.setString(1, nume);
      rs = ps.executeQuery(); 
     while(rs.next()){
      // we are reading one row, so no need to loop 
      raspuns = rs.getString(1); 
      return raspuns;
     }
     } catch(SQLException e) {
      System.out.println(e.toString());
    } finally {
      // close connections
      try{
        rs.close(); 
        ps.close();
        con.close(); 
      } catch (SQLException e) {
        // TODO: handle exception
        System.out.println(e.toString());
      }
    }
    return null;
   }
    
    
    public static String getMicDejunCalories(String User) throws SQLException {
    // lets read specific row on the database
    Connection con = DbConnectionCaloriesForEachDatabase.connect(); 
    PreparedStatement ps = null; 
    ResultSet rs = null; 
    String raspuns = null;
    try {
      String sql = "Select CalMicDejun from CaloriesForEach WHERE Utilizator=?";  // Selecteaza utilizatorii din BD
      ps = con.prepareStatement(sql); 
       ps.setString(1, User);
      rs = ps.executeQuery(); 
     while(rs.next()){
      // we are reading one row, so no need to loop 
      raspuns = rs.getString(1); 
      return raspuns;
     }
     } catch(SQLException e) {
      System.out.println(e.toString());
    } finally {
      // close connections
      try{
        rs.close(); 
        ps.close();
        con.close(); 
      } catch (SQLException e) {
        // TODO: handle exception
        System.out.println(e.toString());
      }
    }
    return null;
   }
    
    
    public static String getPranzCalories(String User) throws SQLException {
    // lets read specific row on the database
    Connection con = DbConnectionCaloriesForEachDatabase.connect(); 
    PreparedStatement ps = null; 
    ResultSet rs = null; 
    String raspuns = null;
    try {
      String sql = "Select CalPranz from CaloriesForEach WHERE Utilizator=?";  // Selecteaza utilizatorii din BD
      ps = con.prepareStatement(sql); 
       ps.setString(1, User);
      rs = ps.executeQuery(); 
     while(rs.next()){
      // we are reading one row, so no need to loop 
      raspuns = rs.getString(1); 
      return raspuns;
     }
     } catch(SQLException e) {
      System.out.println(e.toString());
    } finally {
      // close connections
      try{
        rs.close(); 
        ps.close();
        con.close(); 
      } catch (SQLException e) {
        // TODO: handle exception
        System.out.println(e.toString());
      }
    }
    return null;
   }
    
    public static String getCinaCalories(String User) throws SQLException {
    // lets read specific row on the database
    Connection con = DbConnectionCaloriesForEachDatabase.connect(); 
    PreparedStatement ps = null; 
    ResultSet rs = null; 
    String raspuns = null;
    try {
      String sql = "Select CalCina from CaloriesForEach WHERE Utilizator=?";  // Selecteaza utilizatorii din BD
      ps = con.prepareStatement(sql); 
       ps.setString(1, User);
      rs = ps.executeQuery(); 
     while(rs.next()){
      // we are reading one row, so no need to loop 
      raspuns = rs.getString(1); 
      return raspuns;
     }
     } catch(SQLException e) {
      System.out.println(e.toString());
    } finally {
      // close connections
      try{
        rs.close(); 
        ps.close();
        con.close(); 
      } catch (SQLException e) {
        // TODO: handle exception
        System.out.println(e.toString());
      }
    }
    return null;
   }
    
    
    public static String getExistingUser(String User) throws SQLException {
    // lets read specific row on the database
    Connection con = DbConnectionCaloriesForEachDatabase.connect(); 
    PreparedStatement ps = null; 
    ResultSet rs = null; 
    String raspuns = null;
    try {
      String sql = "Select Utilizator from CaloriesForEach WHERE Utilizator=?";  // Selecteaza utilizatorii din BD
      ps = con.prepareStatement(sql); 
       ps.setString(1, User);
      rs = ps.executeQuery(); 
     while(rs.next()){
      // we are reading one row, so no need to loop 
      raspuns = rs.getString(1); 
      return raspuns;
     }
     } catch(SQLException e) {
      System.out.println(e.toString());
    } finally {
      // close connections
      try{
        rs.close(); 
        ps.close();
        con.close(); 
      } catch (SQLException e) {
        // TODO: handle exception
        System.out.println(e.toString());
      }
    }
    return null;
   }
    
    
    
    public static void updatePassword(String user,String newPassword) throws SQLException {
    // we will update only first name of a certain row
    Connection con = DbConnectionAccountsDatabase.connect(); 
    PreparedStatement ps = null; 
    try {
      String sql = "UPDATE Accounts set Parola = ? WHERE Utilizator = ? ";
      ps = con.prepareStatement(sql); 
      ps.setString(1, newPassword);
      ps.setString(2, user);
      ps.execute();
      System.out.println("Data has been updated");
    } catch (SQLException e) {
      // TODO: handle exception
      System.out.println(e.toString());
    }
  }
     public static void updateMicDejunCalories(String User,String calNumber) throws SQLException {
    // we will update only first name of a certain row
    Connection con = DbConnectionCaloriesForEachDatabase.connect(); 
    PreparedStatement ps = null; 
    try {
      String sql = "UPDATE CaloriesForEach set CalMicDejun = ? WHERE Utilizator = ? ";
      ps = con.prepareStatement(sql); 
      ps.setString(1, calNumber);
      ps.setString(2, User);
      ps.execute();
      System.out.println("Data has been updated - MIC DEJUN ");
    } catch (SQLException e) {
      // TODO: handle exception
      System.out.println(e.toString());
    }
  }
     
      public static void stergeMicDejun(String User,String calNumber) throws SQLException {
    // we will update only first name of a certain row
    Connection con = DbConnectionCaloriesForEachDatabase.connect(); 
    PreparedStatement ps = null; 
    try {
      String sql = "UPDATE CaloriesForEach set CalMicDejun = ? WHERE Utilizator = ? ";
      ps = con.prepareStatement(sql); 
      ps.setString(1, calNumber);
      ps.setString(2, User);
      ps.execute();
      System.out.println("Data has been updated - MIC DEJUN ");
    } catch (SQLException e) {
      // TODO: handle exception
      System.out.println(e.toString());
    }
  }
    
     public static void updatePranzCalories(String User,String calNumber) throws SQLException {
    // we will update only first name of a certain row
    Connection con = DbConnectionCaloriesForEachDatabase.connect(); 
    PreparedStatement ps = null; 
    try {
      String sql = "UPDATE CaloriesForEach set CalPranz = ? WHERE Utilizator = ? ";
      ps = con.prepareStatement(sql); 
      ps.setString(1, calNumber);
      ps.setString(2, User);
      ps.execute();
      System.out.println("Data has been updated - PRANZ ");
    } catch (SQLException e) {
      // TODO: handle exception
      System.out.println(e.toString());
    }
  }
    
     public static void updateCinaCalories(String User,String calNumber) throws SQLException {
    // we will update only first name of a certain row
    Connection con = DbConnectionCaloriesForEachDatabase.connect(); 
    PreparedStatement ps = null; 
    try {
      String sql = "UPDATE CaloriesForEach set CalCina = ? WHERE Utilizator = ? ";
      ps = con.prepareStatement(sql); 
      ps.setString(1, calNumber);
      ps.setString(2, User);
      ps.execute();
      System.out.println("Data has been updated - CINA ");
    } catch (SQLException e) {
      // TODO: handle exception
      System.out.println(e.toString());
    }
  }
     
}
