/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package javaapplication1;
import java.awt.Color;
import java.awt.Cursor;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaapplication1.MainApp.insert;
import static javaapplication1.MainApp.readSpecificRow;
import javax.swing.JOptionPane;
//import static javaapplication1.MainApp.readSpecificRow;


/**
 *
 * @author adeli
 */
public class CreateNewAccount extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public CreateNewAccount() {
        initComponents();
        eroareNewAcc.setVisible(false); // label eroare
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        UserNewAcc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        SecurityQuestion = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        SecurityAnswer = new javax.swing.JTextField();
        ButtonCreateAccount = new javax.swing.JButton();
        GoToLogin = new javax.swing.JButton();
        PassNewAccount = new javax.swing.JPasswordField();
        confirmPass = new javax.swing.JPasswordField();
        eroareNewAcc = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("MingLiU_HKSCS-ExtB", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Introduceti datele");

        UserNewAcc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        UserNewAcc.setText("utilizator");
        UserNewAcc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UserNewAccFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                UserNewAccFocusLost(evt);
            }
        });
        UserNewAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserNewAccActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("MingLiU_HKSCS-ExtB", 0, 12)); // NOI18N
        jLabel2.setText("Utilizator");

        jLabel3.setFont(new java.awt.Font("MingLiU_HKSCS-ExtB", 0, 12)); // NOI18N
        jLabel3.setText("Parola");

        jLabel7.setFont(new java.awt.Font("MingLiU_HKSCS-ExtB", 0, 12)); // NOI18N
        jLabel7.setText("Confirma parola");

        jLabel4.setFont(new java.awt.Font("MingLiU_HKSCS-ExtB", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("pentru noul cont");

        jLabel8.setFont(new java.awt.Font("MingLiU_HKSCS-ExtB", 0, 12)); // NOI18N
        jLabel8.setText("Intrebare pentru recuperare");

        SecurityQuestion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "In ce zi a saptamanii te-ai nascut?", "Care este numele animalului tau de companie?", "Care este numarul casei tale?", "Care este numarul tau norocos?" }));

        jLabel9.setFont(new java.awt.Font("MingLiU_HKSCS-ExtB", 0, 12)); // NOI18N
        jLabel9.setText("Raspuns");

        ButtonCreateAccount.setText("Creeaza cont");
        ButtonCreateAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonCreateAccountActionPerformed(evt);
            }
        });

        GoToLogin.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        GoToLogin.setText("Mergi la fereastra de logare");
        GoToLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GoToLoginActionPerformed(evt);
            }
        });

        PassNewAccount.setText("parola");
        PassNewAccount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PassNewAccountFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PassNewAccountFocusLost(evt);
            }
        });

        confirmPass.setText("parola");
        confirmPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                confirmPassFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                confirmPassFocusLost(evt);
            }
        });

        eroareNewAcc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        eroareNewAcc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        eroareNewAcc.setText("mesaj");
        eroareNewAcc.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SecurityAnswer)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(SecurityQuestion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(GoToLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(84, 84, 84)
                            .addComponent(ButtonCreateAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(87, 87, 87)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel8)
                                .addComponent(jLabel7)
                                .addComponent(jLabel3)
                                .addComponent(UserNewAcc)
                                .addComponent(jLabel2)
                                .addComponent(PassNewAccount, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                .addComponent(confirmPass)))))
                .addGap(0, 83, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(eroareNewAcc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(eroareNewAcc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UserNewAcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PassNewAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SecurityQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SecurityAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonCreateAccount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GoToLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("MingLiU-ExtB", 1, 21)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Creeaza un cont nou");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/img/logov3.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GoToLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GoToLoginActionPerformed
        // TODO add your handling code here:  /////////////// mergi la logare
       LoginScreen loginWindows = new LoginScreen();
        this.setVisible(false);
       
       loginWindows.setVisible(true);
    }//GEN-LAST:event_GoToLoginActionPerformed

    private void ButtonCreateAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonCreateAccountActionPerformed
        // TODO add your handling code here:
        String nume;
        String pass,confirm;
        String ans;
        String question;
        eroareNewAcc.setVisible(false);
        int RecordExists=0; 
        boolean allOk=true;
        nume=UserNewAcc.getText();
        pass=PassNewAccount.getText();
        confirm=confirmPass.getText();
        ans=SecurityAnswer.getText();
        question=SecurityQuestion.getSelectedItem().toString();
        if(!pass.equals(confirm)) {
            allOk=false;
            eroareNewAcc.setVisible(true);
            eroareNewAcc.setText("Parolele nu se potrivesc !");
            eroareNewAcc.setForeground(new Color(250,0,0));
        }
        
        try {
            RecordExists=readSpecificRow(nume); // verificare utilizator existent
            
        } catch (SQLException ex) {
            Logger.getLogger(CreateNewAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(nume.equals("utilizator"))
        {
            allOk=false;
         JOptionPane.showMessageDialog(null,"Introduceti un nume de utilizator !");
        }
        
        if(pass.equals("parola"))
        {
         allOk=false;
         JOptionPane.showMessageDialog(null,"Introduceti o parola !");
        }
        
         if(ans.equals("Raspuns"))
        {
            allOk=false;
         JOptionPane.showMessageDialog(null,"Nu ati raspuns la intrebarea de securitate !");
        }
        try {
           if(allOk) if(RecordExists==0) { // toate campurile completate + utilizatorul nu exista deja in BD
               insert(nume,pass,question,ans); // inserare utilizator nou
               JOptionPane.showMessageDialog(null,"Contul a fost creat cu succes !");
           }
           
           else 
           {
              // System.out.println("UTILIZATORUL EXISTA !");
           UserNewAcc.setText("Utilizator existent !");
           UserNewAcc.setForeground(new Color(200,0,0));
           }
           
        } catch (SQLException ex) {
            Logger.getLogger(CreateNewAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        
    }//GEN-LAST:event_ButtonCreateAccountActionPerformed
//////////////////////////////////////////////////////////////// FOCUS FIELDS /////////////////////////////////////
    
    private void UserNewAccFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UserNewAccFocusGained
        // TODO add your handling code here:
        if(UserNewAcc.getText().trim().toLowerCase().equals("utilizator")|| UserNewAcc.getText().trim().toLowerCase().equals("utilizator existent !"))
        {
            UserNewAcc.setText("");
            UserNewAcc.setForeground(Color.black);
        }
    }//GEN-LAST:event_UserNewAccFocusGained

    private void UserNewAccFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UserNewAccFocusLost
        // TODO add your handling code here:
        if(UserNewAcc.getText().trim().equals("") || UserNewAcc.getText().trim().toLowerCase().equals("utilizator")|| UserNewAcc.getText().trim().toLowerCase().equals("utilizator existent !"))
        {
            UserNewAcc.setText("utilizator");
            UserNewAcc.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_UserNewAccFocusLost

    private void PassNewAccountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PassNewAccountFocusGained
        // TODO add your handling code here:
        String parola = String.valueOf(PassNewAccount.getPassword());
        if(parola.trim().toLowerCase().equals("parola"))
        {
            PassNewAccount.setText("");
            PassNewAccount.setForeground(Color.black);
        }
    }//GEN-LAST:event_PassNewAccountFocusGained

    private void PassNewAccountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PassNewAccountFocusLost
        // TODO add your handling code here:
        String parola = String.valueOf(PassNewAccount.getPassword());
        
        if(parola.trim().equals("") || parola.trim().toLowerCase().equals("parola"))
        {
            PassNewAccount.setText("Parola");
            PassNewAccount.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_PassNewAccountFocusLost

    private void confirmPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmPassFocusGained
        // TODO add your handling code here:
        String parola = String.valueOf(confirmPass.getPassword());
        if(parola.trim().toLowerCase().equals("parola"))
        {
            confirmPass.setText("");
            confirmPass.setForeground(Color.black);
        }
    }//GEN-LAST:event_confirmPassFocusGained

    private void confirmPassFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmPassFocusLost
        // TODO add your handling code here:
        String parola = String.valueOf(confirmPass.getPassword());
        
        if(parola.trim().equals("") || parola.trim().toLowerCase().equals("parola"))
        {
            confirmPass.setText("Parola");
            confirmPass.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_confirmPassFocusLost

    private void UserNewAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserNewAccActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UserNewAccActionPerformed
//////////////////////////////////////////////////// END FOCUS FIELDS /////////////////////////////////////
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
            java.util.logging.Logger.getLogger(CreateNewAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateNewAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateNewAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateNewAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateNewAccount().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCreateAccount;
    private javax.swing.JButton GoToLogin;
    private javax.swing.JPasswordField PassNewAccount;
    private javax.swing.JTextField SecurityAnswer;
    private javax.swing.JComboBox<String> SecurityQuestion;
    public javax.swing.JTextField UserNewAcc;
    private javax.swing.JPasswordField confirmPass;
    private javax.swing.JLabel eroareNewAcc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
