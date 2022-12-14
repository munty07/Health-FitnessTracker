/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import static java.lang.String.valueOf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaapplication1.LoginScreen.utilizatorPublic;
import static javaapplication1.MainApp.insertNewAliment;
import static javaapplication1.MainApp.updateCinaCalories;
import static javaapplication1.MainApp.updateMicDejunCalories;
import static javaapplication1.MainApp.updatePranzCalories;
import static javaapplication1.MainScreen.cinaSentMe;
import static javaapplication1.MainScreen.micDejunSentMe;
import static javaapplication1.MainScreen.pranzSentMe;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author adeli
 */
public class AddFood extends javax.swing.JFrame {

    /**
     * Creates new form AddFood
     */
    static int totalCaloriiMicDejun=0,totalCaloriiPranz=0,totalCaloriiCina=0;
    public AddFood() throws SQLException {
        initComponents();
        //totalCaloriiMicDejun=0;
         this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        DefaultTableModel foodTab = (DefaultTableModel)databaseAlimente.getModel();
        getFoodFromDatabase(foodTab);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    /////////////////////////// FUNCTII ////////////////////////
   
    @SuppressWarnings("empty-statement")
    public static void getFoodFromDatabase(DefaultTableModel tblModel) throws SQLException {
    // lets read specific row on the database
    Connection con = DbConnectionFoodInfoDatabase.connect(); 
    
    PreparedStatement ps = null; 
    ResultSet rs = null; 
    String food = null;
   
    String calories=null;
    try {
      String sql = "Select Aliment,Calorii from Food";  // Selecteaza utilizatorii din BD
      ps = con.prepareStatement(sql); 
      rs = ps.executeQuery(); 
     while(rs.next()){
      // we are reading one row, so no need to loop 
      food = rs.getString(1); 
      calories=rs.getString(2);
        String []date={food,calories};
        if(!food.equals("")||calories.equals(""))tblModel.addRow(date);
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
    
 
  }  
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        databaseAlimente = new javax.swing.JTable();
        newAliment = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        newAlimentCalorii = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        adaugaAlimentSel = new javax.swing.JButton();
        newAlimentToDatabase = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        cantitateAliment = new javax.swing.JTextField();
        calCount = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 501));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        databaseAlimente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Aliment", "Calorii / 100g"
            }
        ));
        jScrollPane1.setViewportView(databaseAlimente);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 851, 260));
        getContentPane().add(newAliment, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 210, -1));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel41.setText("Calorii / 100g");
        getContentPane().add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 90, 30));
        getContentPane().add(newAlimentCalorii, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 210, -1));

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel49.setText("Aliment");
        getContentPane().add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 90, 30));

        adaugaAlimentSel.setText("Adauga aliment selectat");
        adaugaAlimentSel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adaugaAlimentSelActionPerformed(evt);
            }
        });
        getContentPane().add(adaugaAlimentSel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 380, 210, 50));

        newAlimentToDatabase.setText("Adauga aliment nou in lista");
        newAlimentToDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAlimentToDatabaseActionPerformed(evt);
            }
        });
        getContentPane().add(newAlimentToDatabase, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 210, 50));

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel45.setText("Cantitate (grame)");
        getContentPane().add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 310, 180, 30));
        getContentPane().add(cantitateAliment, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 340, 210, -1));

        calCount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        calCount.setText("Calorii consumate :");
        getContentPane().add(calCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, -1, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Gata");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 390, 120, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

     
    
    private void newAlimentToDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAlimentToDatabaseActionPerformed
        // TODO add your handling code here:
        String aliment,calorii;
        aliment=newAliment.getText();
        calorii=newAlimentCalorii.getText();
        DefaultTableModel modelTab = (DefaultTableModel)databaseAlimente.getModel();
        try {
           if(!(aliment.equals("")||calorii.equals(""))){
               insertNewAliment(aliment,calorii,modelTab);
                newAliment.setText("");
        newAlimentCalorii.setText("");
           }
           else JOptionPane.showMessageDialog(null,"Nu ati introdus toate datele necesare !");
        } catch (SQLException ex) {
            Logger.getLogger(AddFood.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }//GEN-LAST:event_newAlimentToDatabaseActionPerformed

    private void adaugaAlimentSelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugaAlimentSelActionPerformed
        // TODO add your handling code here:
        int calsPer100=0,rowIndex=0;
        int calsPerAliment=0;
        int qty=0;
        if(!(cantitateAliment.getText().equals(""))){
        qty=Integer.parseInt(cantitateAliment.getText());
        DefaultTableModel mdl = (DefaultTableModel)databaseAlimente.getModel();
        if(databaseAlimente.getSelectedRows().length > 1) {
         JOptionPane.showMessageDialog(null,"Selectati alimentele pe rand pentru a stabili cantitatea !");
        }
        else 
        {if(qty!=0)
        {if(!(databaseAlimente.getSelectedRows().length<1)){
            rowIndex=databaseAlimente.getSelectedRow();
            calsPer100=calsPer100+Integer.parseInt((String) mdl.getValueAt(rowIndex,1));
             calsPerAliment=(qty*calsPer100)/100;
            if(micDejunSentMe==true){
                totalCaloriiMicDejun=totalCaloriiMicDejun+calsPerAliment;
             calCount.setText("Calorii consumate: "+valueOf(totalCaloriiMicDejun));
            try {
                updateMicDejunCalories(utilizatorPublic,valueOf(totalCaloriiMicDejun));
            } catch (SQLException ex) {
                Logger.getLogger(AddFood.class.getName()).log(Level.SEVERE, null, ex);
            }
             
            }
            if(pranzSentMe==true)
            {
                totalCaloriiPranz=totalCaloriiPranz+calsPerAliment;
             calCount.setText("Calorii consumate: "+valueOf(totalCaloriiPranz));
             
            try {
                updatePranzCalories(utilizatorPublic,valueOf(totalCaloriiPranz));
            } catch (SQLException ex) {
                Logger.getLogger(AddFood.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            if(cinaSentMe==true)
            {
                totalCaloriiCina=totalCaloriiCina+calsPerAliment;
             calCount.setText("Calorii consumate: "+valueOf(totalCaloriiCina));
            try {
                updateCinaCalories(utilizatorPublic,valueOf(totalCaloriiCina));
            } catch (SQLException ex) {
                Logger.getLogger(AddFood.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
        else JOptionPane.showMessageDialog(null,"Nu ati selectat niciun aliment !");
        }
        else JOptionPane.showMessageDialog(null,"Introduceti cantitatea !");
            
        }
        }
        else JOptionPane.showMessageDialog(null,"Introduceti cantitatea !");
    }//GEN-LAST:event_adaugaAlimentSelActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      try {
          // TODO add your handling code here:
          MainScreen gotoMain = new MainScreen();
          gotoMain.setVisible(true);
      } catch (SQLException ex) {
          Logger.getLogger(AddFood.class.getName()).log(Level.SEVERE, null, ex);
      }
      if(micDejunSentMe==true)micDejunSentMe=false;
      if(pranzSentMe==true)pranzSentMe=false;
      if(cinaSentMe==true)cinaSentMe=false;
        this.setVisible(false);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddFood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddFood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddFood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddFood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AddFood().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(AddFood.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adaugaAlimentSel;
    private javax.swing.JLabel calCount;
    private javax.swing.JTextField cantitateAliment;
    private javax.swing.JTable databaseAlimente;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField newAliment;
    private javax.swing.JTextField newAlimentCalorii;
    private javax.swing.JButton newAlimentToDatabase;
    // End of variables declaration//GEN-END:variables
}
