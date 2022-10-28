/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import static java.lang.String.valueOf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaapplication1.InformatiiProfil.prenumePublic;
import static javaapplication1.LoginScreen.utilizatorPublic;
import static javaapplication1.LoginScreen.foodAdded;
import static javaapplication1.testGraph.getWeightFromUser;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ValueAxis;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import static javaapplication1.AddFood.totalCaloriiMicDejun;
import static javaapplication1.AddFood.totalCaloriiPranz;
import static javaapplication1.AddFood.totalCaloriiCina;
import static javaapplication1.MainApp.getCinaCalories;
import static javaapplication1.MainApp.getMicDejunCalories;
import static javaapplication1.MainApp.getPranzCalories;
import static javaapplication1.MainApp.insertBurnedCalories;
import static javaapplication1.MainApp.insertCaloriesEachDay;
import static javaapplication1.MainApp.stergeMicDejun;
import static javaapplication1.MainApp.updateCinaCalories;
import static javaapplication1.MainApp.updateMicDejunCalories;
import static javaapplication1.MainApp.updatePranzCalories;

/**
 *
 * @author adeli
 */
public class MainScreen extends javax.swing.JFrame {
CardLayout cardLayout;
    /**
     * Creates new form MainScreen
     */
static boolean micDejunSentMe=false,pranzSentMe=false,cinaSentMe=false;
static int lowCal,midCal,highCal;
static boolean micdejunAdded,pranzAdded,cinaAdded;
static boolean inserted=false;
static int Varsta,calsPer100,cantitate;
static String foodBuffer;
static boolean obiectivAntrenament=false;
 static int slabeste,ingrasa,caloriesToBurn=0;
static int micdejunCal=0,pranzCal=0,cinaCal=0;
StringBuilder mdj= new StringBuilder();
static float Greutate,Inaltime;
static  String Activitate,Sex,micDejun,pranz,cina;
static float BMI;
static int caloriiArse=0,healthLevel=100;
static String numarPasi="";
static int RMB,TDEE;
static double minWeight=999,maxWeight=0;
static double[] valori=new double[50];
    static int nrValori=0;
    public MainScreen() throws SQLException {
        initComponents();
       /* Component [] components = this.getContentPane().getComponents();
        for(Component component : components)
        {
            if(component instanceof JButton)
            {
                ((JButton)component).setUI(new BasicButtonUI());
            }
        }
        */
       healthLevel=100;
       healthIndicator.setValue(healthLevel);
       micdejunCal=0;pranzCal=0;cinaCal=0;
        double multiplicator=1;
        
        cardLayout = (CardLayout)(Content.getLayout());
      prenumePublic=null;
       getProfileInfo(utilizatorPublic);
        if(!(prenumePublic == null)) lblBunVenit.setText("Bine ai venit "+prenumePublic+"!");
       valoareGreutateProfil.setText(valueOf(Greutate));
       valoareInaltimeProfil.setText(valueOf(Inaltime));
       valoareVarstaProfil.setText(valueOf(Varsta));
       if(Activitate.equals("Sedentar"))
       {
           sliderActivitate.setValue(1);
           multiplicator=1.2;
       }
        if(Activitate.equals("Mai putin activ"))
       {
           sliderActivitate.setValue(2);
           multiplicator=1.375;
       }
         if(Activitate.equals("Activ"))
       {
           sliderActivitate.setValue(3);
           multiplicator=1.55;
       }
          if(Activitate.equals("Foarte activ"))
       {
           sliderActivitate.setValue(4);
           multiplicator=1.725;
       }
         
      ///////////////////////////////////////////// CALCUL BMI //////////////////
     // System.out.println("Greutate "+Greutate);
     // System.out.println("Inaltime "+Inaltime);
      if(Inaltime!=0) BMI=Greutate/((Inaltime*Inaltime)/10000);
      else BMI=0;
     //  System.out.println("BMI "+BMI);
        String bmi=valueOf(BMI);
        String oneDecimal="";
        StringBuilder sb = new StringBuilder(bmi);
        if(BMI!=0)
        for(int i=0;i<=3;i++)
       {
           oneDecimal=oneDecimal+sb.charAt(i);
       }
       lblIMCValue.setText(oneDecimal);
///////////////////////////////////////////////////////////////////////////////
//////////////////////////////CALCUL RMB ////////////////////////////////////
        if(Sex.equals("Masculin"))
        {
            RMB=(int) (66.47+(13.75*Greutate)+(5.003*Inaltime)-(6.755*Varsta));
           RMB=RMB-65;
        lblRMBValue.setText(valueOf(RMB));
        }
        else
        {
            RMB=(int) (655.1+(9.563*Greutate)+(1.85*Inaltime)-(4.676*Varsta));
            RMB=RMB-65;
            lblRMBValue.setText(valueOf(RMB));
        }
        
       TDEE=(int) (RMB*multiplicator);
       midCal=TDEE;
       lblTDEEValue.setText(valueOf(TDEE));
     
       double procent;
       System.out.println(TDEE);
       procent=0.15;
       procent=procent*TDEE;
       slabeste=(int) (TDEE-procent);
       lowCal=slabeste;
       procent=0.1*TDEE;
       ingrasa=(int) (TDEE+procent);
     highCal=ingrasa;
      
        caloriiMentine.setText(valueOf(TDEE));
        caloriiSlabeste.setText(valueOf(slabeste));
        caloriiIngrasa.setText(valueOf(ingrasa));
        caloriesToBurn=TDEE-slabeste;
        progressCaloriiArse.setMaximum(caloriesToBurn);
        if(BMI>18.5&&BMI<24.9)
        {   lblConditie.setForeground(Color.GREEN);
            lblConditie.setText("Conditie fizica normala ");
        }
        if(BMI<18.6)
        {   lblConditie.setForeground(Color.RED);
            lblConditie.setText("Atentie !! Greutatea dumneavoastra este sub limitele normale");
        }
        if(BMI>24.9)
        {   lblConditie.setForeground(Color.RED);
            lblConditie.setText("Atentie !! Greutatea dumneavoastra este peste limitele normale");
        }
        /////////////////////////////////////////// ALIMENTATIE //////////////////////////////////
      lblCaloriiMicDejun.setText(valueOf(getMicDejunCalories(utilizatorPublic)));
       lblCaloriiPranz.setText(valueOf(getPranzCalories(utilizatorPublic)));
       lblCaloriiCina.setText(valueOf(getCinaCalories(utilizatorPublic)));
        if(!(BMI>21&&BMI<22))
        {
            healthLevel-=15;
            healthIndicator.setValue(healthLevel);
            System.out.println("Am scazut pentru BMI");
        }
        /* if(!foodAdded)
       {
        lblCaloriiMicDejun.setText(valueOf(getMicDejunCalories(utilizatorPublic)));
       lblCaloriiPranz.setText(valueOf(getPranzCalories(utilizatorPublic)));
       lblCaloriiCina.setText(valueOf(getCinaCalories(utilizatorPublic)));
       }
       else
       {
        if(micdejunAdded)lblCaloriiMicDejun.setText(valueOf(getMicDejunCalories(utilizatorPublic)));
        if(pranzAdded)lblCaloriiPranz.setText(valueOf(getPranzCalories(utilizatorPublic)));
        if(cinaAdded) lblCaloriiCina.setText(valueOf(getCinaCalories(utilizatorPublic)));
       }*/
      
       if(!foodAdded)
       {
        if(getMicDejunCalories(utilizatorPublic)!=null)micdejunCal=Integer.parseInt(getMicDejunCalories(utilizatorPublic));
        else micdejunCal=0;
        System.out.println("CALORII MIC DEJUN : "+micdejunCal);
        if(getPranzCalories(utilizatorPublic)!=null)pranzCal=Integer.parseInt(getPranzCalories(utilizatorPublic));
        else pranzCal=0;
        if(getCinaCalories(utilizatorPublic)!=null)cinaCal=Integer.parseInt(getCinaCalories(utilizatorPublic));
        else cinaCal=0;
       }
       else
       {
         micdejunCal=Integer.parseInt(getMicDejunCalories(utilizatorPublic));
         pranzCal=Integer.parseInt(getPranzCalories(utilizatorPublic));
         cinaCal=Integer.parseInt(getCinaCalories(utilizatorPublic));
       }
        totalCaloriiConsumate.setText(valueOf(micdejunCal+pranzCal+cinaCal));
        progressCaloriiConsumate.setValue(micdejunCal+pranzCal+cinaCal);
        mesajObiectiv.setText("Inca nu ti-ai atins obiectivul pentru astazi . Continua antrenamentele !");
        
        
    }
public static void getProfileInfo(String Utilizator) throws SQLException {
    // lets read specific row on the database
    Connection con = DbConnectionProfileInfoDatabase.connect(); 
    PreparedStatement ps = null; 
    ResultSet rs = null; 
    boolean found=false;
  
    try {
      String sql = "Select Varsta,Greutate,Inaltime,Activitate,Sex,Utilizator,Prenume from ProfileData";  // Selecteaza datele din BD
      ps = con.prepareStatement(sql); 
      rs = ps.executeQuery(); 
     while(rs.next()){
      // we are reading one row, so no need to loop 
      if(rs.getString(6).equals(Utilizator)){
      Varsta = Integer.parseInt(rs.getString(1)); 
      Greutate=Float.parseFloat(rs.getString(2));
      Inaltime=Float.parseFloat(rs.getString(3));
      Activitate=rs.getString(4);
      Sex=rs.getString(5);
      prenumePublic=rs.getString(7);
      found=true;
      }
      
     }
     if(!found)
     {
         Varsta=0;
         Greutate=0;
         Inaltime=0;
         Activitate="";
         Sex="";
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
    //valoareGreutateProfil.setText(Greutate);
    
  
  } 

public static void getWeightFromUser(String nume) throws SQLException {
    // lets read specific row on the database
    nrValori=0;
    minWeight=999;
    maxWeight=0;
    Connection con = DbConnectionProfileInfoDatabase.connect(); 
    PreparedStatement ps = null; 
    ResultSet rs = null; 
    String raspuns = null;
    try {
      String sql = "Select Greutate from ProfileData WHERE Utilizator=?";  // Selecteaza utilizatorii din BD
      ps = con.prepareStatement(sql); 
       ps.setString(1, nume);
      rs = ps.executeQuery(); 
     while(rs.next()){
      // we are reading one row, so no need to loop 
      raspuns = rs.getString(1); 
      System.out.println(raspuns);
      valori[nrValori]=Double.parseDouble(raspuns);
      if(valori[nrValori]<minWeight)minWeight=valori[nrValori];
      if(valori[nrValori]>maxWeight)maxWeight=valori[nrValori];
      nrValori++;
     // return raspuns;
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


public static void getCaloriesFromUser(String nume) throws SQLException {
    // lets read specific row on the database
    nrValori=0;
    minWeight=999;
    maxWeight=0;
    Connection con = DbConnectionCaloriesForEachDatabase.connect(); 
    PreparedStatement ps = null; 
    ResultSet rs = null; 
    String raspuns = null;
    try {
      String sql = "Select Calorii from CaloriesEachDay WHERE Utilizator=?";  // Selecteaza utilizatorii din BD
      ps = con.prepareStatement(sql); 
       ps.setString(1, nume);
      rs = ps.executeQuery(); 
     while(rs.next()){
      // we are reading one row, so no need to loop 
      raspuns = rs.getString(1); 
      System.out.println(raspuns);
      valori[nrValori]=Double.parseDouble(raspuns);
      if(valori[nrValori]<minWeight)minWeight=valori[nrValori];
      if(valori[nrValori]>maxWeight)maxWeight=valori[nrValori];
      nrValori++;
     // return raspuns;
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

public static void getBurnedCaloriesFromUser(String nume) throws SQLException {
    // lets read specific row on the database
    nrValori=0;
    minWeight=999;
    maxWeight=0;
    Connection con = DbConnectionCaloriesForEachDatabase.connect(); 
    PreparedStatement ps = null; 
    ResultSet rs = null; 
    String raspuns = null;
    try {
      String sql = "Select Calorii_arse from BurnedCalories WHERE Utilizator=?";  // Selecteaza utilizatorii din BD
      ps = con.prepareStatement(sql); 
       ps.setString(1, nume);
      rs = ps.executeQuery(); 
     while(rs.next()){
      // we are reading one row, so no need to loop 
      raspuns = rs.getString(1); 
      System.out.println(raspuns);
      valori[nrValori]=Double.parseDouble(raspuns);
      if(valori[nrValori]<minWeight)minWeight=valori[nrValori];
      if(valori[nrValori]>maxWeight)maxWeight=valori[nrValori];
      nrValori++;
     // return raspuns;
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

public static void getStepsFromUser(String nume) throws SQLException {
    // lets read specific row on the database
    nrValori=0;
    minWeight=999;
    maxWeight=0;
    Connection con = DbConnectionCaloriesForEachDatabase.connect(); 
    PreparedStatement ps = null; 
    ResultSet rs = null; 
    String raspuns = null;
    try {
      String sql = "Select Pasi from BurnedCalories WHERE Utilizator=?";  // Selecteaza utilizatorii din BD
      ps = con.prepareStatement(sql); 
       ps.setString(1, nume);
      rs = ps.executeQuery(); 
     while(rs.next()){
      // we are reading one row, so no need to loop 
      raspuns = rs.getString(1); 
      System.out.println(raspuns);
      valori[nrValori]=Double.parseDouble(raspuns);
      if(valori[nrValori]<minWeight)minWeight=valori[nrValori];
      if(valori[nrValori]>maxWeight)maxWeight=valori[nrValori];
      nrValori++;
     // return raspuns;
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




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        Menu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        butonAlimentatie = new javax.swing.JButton();
        butonConditie = new javax.swing.JButton();
        butonMasuratori = new javax.swing.JButton();
        butonGrafic = new javax.swing.JButton();
        butonAntrenament = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        Content = new javax.swing.JPanel();
        panelAntrenament = new javax.swing.JPanel();
        tipAntrenament = new javax.swing.JComboBox<>();
        minuteAntrenament = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        adaugaMinute = new javax.swing.JButton();
        nrPasi = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        progressNumarPasi = new javax.swing.JProgressBar();
        jLabel54 = new javax.swing.JLabel();
        progressCaloriiArse = new javax.swing.JProgressBar();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelAntrenament = new javax.swing.JTable();
        mesajObiectiv = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        panelAlimentatie = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        lblCaloriiPranz = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        lblCaloriiMicDejun = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        lblCaloriiCina = new javax.swing.JLabel();
        adaugaCina = new javax.swing.JButton();
        adaugaMicDejun = new javax.swing.JButton();
        adaugaPranz = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        totalCaloriiConsumate = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        progressCaloriiConsumate = new javax.swing.JProgressBar();
        high = new javax.swing.JButton();
        low = new javax.swing.JButton();
        mid = new javax.swing.JButton();
        lblToReach = new javax.swing.JLabel();
        warningOver = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        StergeCina = new javax.swing.JButton();
        stergeMicDejun = new javax.swing.JButton();
        StergePranz = new javax.swing.JButton();
        panelConditie = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        caloriiIngrasa = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        caloriiMentine = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        caloriiSlabeste = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblTDEEValue = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        lblIMCValue = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lblRMBValue = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lblConditie = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        panelProfil = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        healthIndicator = new javax.swing.JProgressBar();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        valoareGreutateProfil = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        valoareInaltimeProfil = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        valoareVarstaProfil = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        sliderActivitate = new javax.swing.JSlider();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        updateFromProfile = new javax.swing.JButton();
        lblBunVenit = new javax.swing.JLabel();
        panelGrafic = new javax.swing.JPanel();
        panel = new javax.swing.JPanel();
        showWeightEvo = new javax.swing.JButton();
        buttonWorkoutEvo = new javax.swing.JButton();
        showCaloriesEvo = new javax.swing.JButton();
        buttonPasiEvo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1135, 700));
        setResizable(false);

        Menu.setBackground(new java.awt.Color(43, 61, 76));
        Menu.setPreferredSize(new java.awt.Dimension(310, 680));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/img/MainScreenLogo.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/img/smll60.png"))); // NOI18N

        butonAlimentatie.setBackground(new java.awt.Color(43, 61, 76));
        butonAlimentatie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/img/butonAlimentatie.png"))); // NOI18N
        butonAlimentatie.setBorder(null);
        butonAlimentatie.setBorderPainted(false);
        butonAlimentatie.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butonAlimentatie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                butonAlimentatieMouseClicked(evt);
            }
        });
        butonAlimentatie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonAlimentatieActionPerformed(evt);
            }
        });

        butonConditie.setBackground(new java.awt.Color(43, 61, 76));
        butonConditie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/img/ButonConditieFizica.png"))); // NOI18N
        butonConditie.setBorder(null);
        butonConditie.setBorderPainted(false);
        butonConditie.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butonConditie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonConditieActionPerformed(evt);
            }
        });

        butonMasuratori.setBackground(new java.awt.Color(43, 61, 76));
        butonMasuratori.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/img/butonProfil.png"))); // NOI18N
        butonMasuratori.setBorder(null);
        butonMasuratori.setBorderPainted(false);
        butonMasuratori.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butonMasuratori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonMasuratoriActionPerformed(evt);
            }
        });

        butonGrafic.setBackground(new java.awt.Color(43, 61, 76));
        butonGrafic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/img/butonGrafic.png"))); // NOI18N
        butonGrafic.setBorder(null);
        butonGrafic.setBorderPainted(false);
        butonGrafic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butonGrafic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonGraficActionPerformed(evt);
            }
        });

        butonAntrenament.setBackground(new java.awt.Color(153, 204, 255));
        butonAntrenament.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        butonAntrenament.setForeground(new java.awt.Color(0, 153, 204));
        butonAntrenament.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/img/butonAntrenament.png"))); // NOI18N
        butonAntrenament.setToolTipText("");
        butonAntrenament.setBorder(null);
        butonAntrenament.setBorderPainted(false);
        butonAntrenament.setContentAreaFilled(false);
        butonAntrenament.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butonAntrenament.setFocusPainted(false);
        butonAntrenament.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonAntrenamentActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel8.setFont(new java.awt.Font("MingLiU_HKSCS-ExtB", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("LOG OUT");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addGroup(MenuLayout.createSequentialGroup()
                            .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(butonAlimentatie)
                                .addComponent(butonConditie)
                                .addComponent(butonMasuratori)
                                .addComponent(butonGrafic)
                                .addComponent(butonAntrenament, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(19, 19, 19)))
                    .addGroup(MenuLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel2))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(48, 48, 48)
                .addComponent(butonMasuratori)
                .addGap(18, 18, 18)
                .addComponent(butonAntrenament, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(butonConditie)
                .addGap(18, 18, 18)
                .addComponent(butonAlimentatie)
                .addGap(18, 18, 18)
                .addComponent(butonGrafic)
                .addGap(108, 108, 108)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(Menu);

        Content.setBackground(new java.awt.Color(204, 255, 204));
        Content.setPreferredSize(new java.awt.Dimension(758, 680));
        Content.setLayout(new java.awt.CardLayout());

        panelAntrenament.setBackground(new java.awt.Color(59, 70, 82));
        panelAntrenament.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tipAntrenament.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Flotari", "Abdomene", "Genuflexiuni", "Jogging", "Ciclism", "Ridicari greutati", "Sarituri coarda", " " }));
        panelAntrenament.add(tipAntrenament, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 285, 36));

        minuteAntrenament.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        panelAntrenament.add(minuteAntrenament, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 290, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Minute");
        panelAntrenament.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 290, 30));

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("Tip antrenament");
        panelAntrenament.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 290, 30));

        adaugaMinute.setBackground(new java.awt.Color(153, 255, 153));
        adaugaMinute.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        adaugaMinute.setForeground(new java.awt.Color(255, 255, 255));
        adaugaMinute.setText("Adauga");
        adaugaMinute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adaugaMinuteActionPerformed(evt);
            }
        });
        panelAntrenament.add(adaugaMinute, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 120, 30));

        nrPasi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        panelAntrenament.add(nrPasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 290, 40));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("Numar pasi");
        panelAntrenament.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 290, 30));

        jButton4.setBackground(new java.awt.Color(102, 255, 102));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Adauga");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        panelAntrenament.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 120, 30));

        progressNumarPasi.setMaximum(6000);
        panelAntrenament.add(progressNumarPasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 550, 680, 30));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Calorii arse");
        panelAntrenament.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, -1, -1));

        progressCaloriiArse.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        progressCaloriiArse.setForeground(new java.awt.Color(153, 255, 153));
        progressCaloriiArse.setMaximum(2500);
        progressCaloriiArse.setString("");
        progressCaloriiArse.setStringPainted(true);
        panelAntrenament.add(progressCaloriiArse, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 680, 30));

        tabelAntrenament.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tip antrenament", "Durata"
            }
        ));
        jScrollPane2.setViewportView(tabelAntrenament);

        panelAntrenament.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 250, 680, 160));

        mesajObiectiv.setBackground(new java.awt.Color(153, 255, 153));
        mesajObiectiv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mesajObiectiv.setForeground(new java.awt.Color(153, 255, 153));
        mesajObiectiv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelAntrenament.add(mesajObiectiv, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 680, -1));

        jButton3.setBackground(new java.awt.Color(102, 255, 102));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Confirma antrenamente");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        panelAntrenament.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 593, 420, 40));

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Obiectiv : 6000");
        panelAntrenament.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 530, 100, -1));

        Content.add(panelAntrenament, "Antrenament");

        panelAlimentatie.setBackground(new java.awt.Color(59, 70, 82));
        panelAlimentatie.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Cina");
        panelAlimentatie.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, 210, 30));

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("Mic dejun");
        panelAlimentatie.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 210, 30));

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Pranz");
        panelAlimentatie.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 210, 30));

        jPanel15.setBackground(new java.awt.Color(247, 107, 63));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("Calorii consumate");
        jPanel15.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 210, 30));

        lblCaloriiPranz.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        lblCaloriiPranz.setForeground(new java.awt.Color(255, 255, 255));
        lblCaloriiPranz.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCaloriiPranz.setText("0");
        jPanel15.add(lblCaloriiPranz, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 40));

        panelAlimentatie.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, 210, 70));

        jPanel16.setBackground(new java.awt.Color(255, 179, 41));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel58.setText("jLabel58");
        jPanel16.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, -1, -1));

        lblCaloriiMicDejun.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        lblCaloriiMicDejun.setForeground(new java.awt.Color(255, 255, 255));
        lblCaloriiMicDejun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCaloriiMicDejun.setText("0");
        jPanel16.add(lblCaloriiMicDejun, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 40));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("Calorii consumate");
        jPanel16.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 200, 30));

        panelAlimentatie.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 210, 70));

        jPanel17.setBackground(new java.awt.Color(56, 170, 164));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("Calorii consumate");
        jPanel17.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 210, 30));

        lblCaloriiCina.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        lblCaloriiCina.setForeground(new java.awt.Color(255, 255, 255));
        lblCaloriiCina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCaloriiCina.setText("0");
        jPanel17.add(lblCaloriiCina, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 40));

        panelAlimentatie.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, 210, 70));

        adaugaCina.setBackground(new java.awt.Color(153, 204, 255));
        adaugaCina.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        adaugaCina.setText("Adauga cina");
        adaugaCina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adaugaCinaActionPerformed(evt);
            }
        });
        panelAlimentatie.add(adaugaCina, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 150, 210, 60));

        adaugaMicDejun.setBackground(new java.awt.Color(153, 204, 255));
        adaugaMicDejun.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        adaugaMicDejun.setText("Adauga mic dejun");
        adaugaMicDejun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adaugaMicDejunActionPerformed(evt);
            }
        });
        panelAlimentatie.add(adaugaMicDejun, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 210, 60));

        adaugaPranz.setBackground(new java.awt.Color(153, 204, 255));
        adaugaPranz.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        adaugaPranz.setText("Adauga pranz");
        adaugaPranz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adaugaPranzActionPerformed(evt);
            }
        });
        panelAlimentatie.add(adaugaPranz, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 210, 60));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/img/alimentatie.png"))); // NOI18N
        panelAlimentatie.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 310, 50));

        jPanel19.setBackground(new java.awt.Color(153, 153, 255));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        totalCaloriiConsumate.setFont(new java.awt.Font("Tahoma", 1, 53)); // NOI18N
        totalCaloriiConsumate.setForeground(new java.awt.Color(255, 255, 255));
        totalCaloriiConsumate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalCaloriiConsumate.setText("2543");
        jPanel19.add(totalCaloriiConsumate, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 50));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Total calorii consumate");
        jPanel19.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 410, 30));

        panelAlimentatie.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 410, 80));

        progressCaloriiConsumate.setForeground(new java.awt.Color(102, 255, 102));
        progressCaloriiConsumate.setValue(30);
        panelAlimentatie.add(progressCaloriiConsumate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 760, 30));

        high.setText("Ma ingras");
        high.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                highActionPerformed(evt);
            }
        });
        panelAlimentatie.add(high, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 550, 120, 30));

        low.setText("Slabesc");
        low.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lowActionPerformed(evt);
            }
        });
        panelAlimentatie.add(low, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 550, 120, 30));

        mid.setText("Ma mentin");
        mid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                midActionPerformed(evt);
            }
        });
        panelAlimentatie.add(mid, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 550, 120, 30));

        lblToReach.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblToReach.setForeground(new java.awt.Color(255, 255, 255));
        lblToReach.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblToReach.setText("0");
        panelAlimentatie.add(lblToReach, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 430, 170, 30));

        warningOver.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        warningOver.setForeground(new java.awt.Color(255, 0, 0));
        warningOver.setToolTipText("");
        panelAlimentatie.add(warningOver, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 500, -1, -1));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Vreau sa ");
        panelAlimentatie.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 514, 120, 30));

        jButton2.setBackground(new java.awt.Color(153, 153, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Adauga mesele de astazi");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        panelAlimentatie.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 600, 320, 40));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("Ultimele calorii consumate");
        panelAlimentatie.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 360, 30));

        StergeCina.setBackground(new java.awt.Color(153, 153, 255));
        StergeCina.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        StergeCina.setText("Sterge");
        StergeCina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StergeCinaActionPerformed(evt);
            }
        });
        panelAlimentatie.add(StergeCina, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 310, -1, -1));

        stergeMicDejun.setBackground(new java.awt.Color(153, 153, 255));
        stergeMicDejun.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        stergeMicDejun.setText("Sterge");
        stergeMicDejun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stergeMicDejunActionPerformed(evt);
            }
        });
        panelAlimentatie.add(stergeMicDejun, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, -1, -1));

        StergePranz.setBackground(new java.awt.Color(153, 153, 255));
        StergePranz.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        StergePranz.setText("Sterge");
        StergePranz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StergePranzActionPerformed(evt);
            }
        });
        panelAlimentatie.add(StergePranz, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, -1, -1));

        Content.add(panelAlimentatie, "Alimentatie");

        panelConditie.setBackground(new java.awt.Color(59, 70, 82));
        panelConditie.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(43, 61, 76));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(255, 195, 32));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        caloriiIngrasa.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        caloriiIngrasa.setForeground(new java.awt.Color(255, 255, 255));
        caloriiIngrasa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        caloriiIngrasa.setText("2430");
        jPanel12.add(caloriiIngrasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 200, 50));

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("calorii");
        jPanel12.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 200, 30));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("(+10% TDEE ~)");
        jPanel12.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 200, 30));

        jPanel7.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 200, 110));

        jPanel13.setBackground(new java.awt.Color(17, 168, 171));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        caloriiMentine.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        caloriiMentine.setForeground(new java.awt.Color(255, 255, 255));
        caloriiMentine.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        caloriiMentine.setText("2430");
        jPanel13.add(caloriiMentine, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 200, 50));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("calorii");
        jPanel13.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 200, 30));

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("( TDEE )");
        jPanel13.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 200, 30));

        jPanel7.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 200, 110));

        jPanel14.setBackground(new java.awt.Color(102, 255, 102));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        caloriiSlabeste.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        caloriiSlabeste.setForeground(new java.awt.Color(255, 255, 255));
        caloriiSlabeste.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        caloriiSlabeste.setText("2430");
        jPanel14.add(caloriiSlabeste, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 200, 40));

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("calorii");
        jPanel14.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 200, 30));

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("(-15% TDEE)");
        jPanel14.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 200, 30));

        jPanel7.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 200, 110));

        jLabel40.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Pentru a te mentine");
        jPanel7.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 200, 35));

        jLabel43.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Pentru a slăbi");
        jPanel7.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 200, 35));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("<html>\nConsumul zilnic <br>\nde calorii :<hmtl>");
        jPanel7.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Pentru a lua in greutate");
        jPanel7.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 492, 210, 30));

        panelConditie.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 240, 680));

        jPanel9.setBackground(new java.awt.Color(180, 183, 194));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTDEEValue.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTDEEValue.setForeground(new java.awt.Color(255, 255, 255));
        lblTDEEValue.setText("0.00");
        jPanel9.add(lblTDEEValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 80, 70));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText(" TDEE :");
        jPanel9.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 70));

        panelConditie.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 160, 70));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("<html> <p style=\"text-align: center;\"><strong><span style=\"color: rgb(44, 130, 201);\">Ce este IMC ?</span></strong>  </html>");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        panelConditie.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 150, 30));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("<html> <p style=\"text-align: center;\"><span style=\"color: rgb(44, 130, 201);\"><strong>Ce este TDEE ?</strong></span> </p> <p>&nbsp;</p> </html>");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        panelConditie.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 120, 40));

        jPanel10.setBackground(new java.awt.Color(180, 183, 194));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("IMC :");
        jPanel10.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 60));

        lblIMCValue.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblIMCValue.setForeground(new java.awt.Color(255, 255, 255));
        lblIMCValue.setText("0.00");
        jPanel10.add(lblIMCValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 90, 60));

        panelConditie.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 160, 60));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/img/imcreSized.jpg"))); // NOI18N
        panelConditie.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, -1, 210));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/img/resizedDonut222222.png"))); // NOI18N
        panelConditie.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, -1, -1));

        jPanel11.setBackground(new java.awt.Color(180, 183, 194));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRMBValue.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblRMBValue.setForeground(new java.awt.Color(255, 255, 255));
        lblRMBValue.setText("0.00");
        jPanel11.add(lblRMBValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 90, 70));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 26)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("RMB :");
        jPanel11.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 70));

        panelConditie.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 160, 70));

        jLabel33.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("<html>\n<p style=\"text-align: center;\"><span style=\"color: rgb(44, 130, 201);\"><strong>Ce este RMB ?</strong></span> </p>\n<p>&nbsp;</p>\n</html>");
        jLabel33.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
        });
        panelConditie.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 120, 40));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/img/tdee.png"))); // NOI18N
        panelConditie.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 510, -1, -1));

        lblConditie.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblConditie.setForeground(new java.awt.Color(255, 255, 255));
        lblConditie.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblConditie.setText("To be modified");
        panelConditie.add(lblConditie, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 410, 40));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Conditie generala :");
        panelConditie.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 140, 40));

        Content.add(panelConditie, "Conditie");

        panelProfil.setBackground(new java.awt.Color(59, 70, 82));
        panelProfil.setPreferredSize(new java.awt.Dimension(800, 678));
        panelProfil.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(43, 61, 76));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/img/InfoProfil.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jLabel6)
                .addContainerGap(226, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        panelProfil.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel8.setBackground(new java.awt.Color(59, 70, 82));

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(683, 296));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel22.setText("Inaltime");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel23.setText("Varsta");

        healthIndicator.setBackground(new java.awt.Color(255, 102, 102));
        healthIndicator.setFont(new java.awt.Font("Times New Roman", 0, 26)); // NOI18N
        healthIndicator.setForeground(new java.awt.Color(0, 255, 0));
        healthIndicator.setToolTipText("");
        healthIndicator.setValue(50);
        healthIndicator.setPreferredSize(new java.awt.Dimension(140, 17));
        healthIndicator.setStringPainted(true);

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 21)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 153, 204));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Nivel de sanatate");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 0, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Foarte scazut");

        jLabel25.setBackground(new java.awt.Color(204, 204, 204));
        jLabel25.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(204, 204, 204));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("<html>Nici un obiectiv atins<br>Valori peste / sub limite </html>");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 204, 0));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Scazut");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(204, 204, 204));
        jLabel27.setText("<html>Cateva obiective atinse<br>Valori apropiate de limite</html>");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(204, 255, 102));
        jLabel28.setText("Optim");

        jLabel29.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(204, 204, 204));
        jLabel29.setText("<html>Majoritatea obiectivelor atinse<br>Valori normale</html>");

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 255, 51));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Ideal");

        jLabel31.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(204, 204, 204));
        jLabel31.setText("<html>Toate obiectivele atinse<br>Valori ideale</html>");

        jPanel4.setBackground(new java.awt.Color(163, 184, 204));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        valoareGreutateProfil.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        valoareGreutateProfil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valoareGreutateProfil.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(valoareGreutateProfil, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(valoareGreutateProfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        jPanel5.setBackground(new java.awt.Color(163, 184, 204));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel5.setPreferredSize(new java.awt.Dimension(136, 112));

        valoareInaltimeProfil.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        valoareInaltimeProfil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valoareInaltimeProfil.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(valoareInaltimeProfil, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(valoareInaltimeProfil, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        jPanel6.setBackground(new java.awt.Color(163, 184, 204));
        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        valoareVarstaProfil.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        valoareVarstaProfil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valoareVarstaProfil.setText("0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(valoareVarstaProfil, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(valoareVarstaProfil, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel17.setText("Greutate");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(jLabel26)
                                .addGap(92, 92, 92))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 20, Short.MAX_VALUE)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel30)
                                .addGap(18, 18, 18))))
                    .addComponent(healthIndicator, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel17)
                .addGap(147, 147, 147)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addGap(102, 102, 102))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel21)
                .addGap(5, 5, 5)
                .addComponent(healthIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel28)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        panelProfil.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 90, -1, -1));

        sliderActivitate.setMaximum(4);
        sliderActivitate.setMinimum(1);
        sliderActivitate.setMinorTickSpacing(1);
        sliderActivitate.setPaintTicks(true);
        sliderActivitate.setSnapToTicks(true);
        sliderActivitate.setValue(1);
        sliderActivitate.setOpaque(false);
        panelProfil.add(sliderActivitate, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 397, 607, 45));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Sedentar");
        panelProfil.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 448, -1, -1));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("<html>Faci mai putin de<br>5000 de pasi zilnic</html>");
        panelProfil.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 476, -1, -1));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setText("Mai putin activ");
        panelProfil.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 448, -1, -1));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("<html>Faci intre 5.000 si<br>10.000 de pasi zilnic</html>");
        panelProfil.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 476, -1, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Activ");
        panelProfil.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 448, -1, -1));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("<html>Faci 10.000 sau<br>mai multi pasi zilnic</html>");
        panelProfil.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 476, -1, -1));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Foarte activ");
        panelProfil.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(633, 448, -1, -1));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 204, 204));
        jLabel16.setText("<html>Faci peste 12.5000<br>de pasi zilnic</html>");
        panelProfil.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(633, 476, -1, -1));

        updateFromProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication1/img/butonUpdateProfil.png"))); // NOI18N
        updateFromProfile.setBorder(null);
        updateFromProfile.setBorderPainted(false);
        updateFromProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateFromProfileActionPerformed(evt);
            }
        });
        panelProfil.add(updateFromProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 539, -1, -1));

        lblBunVenit.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblBunVenit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelProfil.add(lblBunVenit, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 780, 40));

        Content.add(panelProfil, "Profil");

        panelGrafic.setBackground(new java.awt.Color(102, 102, 102));
        panelGrafic.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel.setLayout(new java.awt.BorderLayout());
        panelGrafic.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 740, 410));

        showWeightEvo.setBackground(new java.awt.Color(153, 204, 255));
        showWeightEvo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        showWeightEvo.setForeground(new java.awt.Color(255, 255, 255));
        showWeightEvo.setText("Greutate");
        showWeightEvo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showWeightEvoActionPerformed(evt);
            }
        });
        panelGrafic.add(showWeightEvo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 430, 140, 40));

        buttonWorkoutEvo.setBackground(new java.awt.Color(153, 204, 255));
        buttonWorkoutEvo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        buttonWorkoutEvo.setForeground(new java.awt.Color(255, 255, 255));
        buttonWorkoutEvo.setText("Antrenament");
        buttonWorkoutEvo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonWorkoutEvoActionPerformed(evt);
            }
        });
        panelGrafic.add(buttonWorkoutEvo, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 430, 140, 40));

        showCaloriesEvo.setBackground(new java.awt.Color(153, 204, 255));
        showCaloriesEvo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        showCaloriesEvo.setForeground(new java.awt.Color(255, 255, 255));
        showCaloriesEvo.setText("Calorii");
        showCaloriesEvo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showCaloriesEvoActionPerformed(evt);
            }
        });
        panelGrafic.add(showCaloriesEvo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 140, 40));

        buttonPasiEvo.setBackground(new java.awt.Color(153, 204, 255));
        buttonPasiEvo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        buttonPasiEvo.setForeground(new java.awt.Color(255, 255, 255));
        buttonPasiEvo.setText("Pasi");
        buttonPasiEvo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPasiEvoActionPerformed(evt);
            }
        });
        panelGrafic.add(buttonPasiEvo, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 430, 140, 40));

        Content.add(panelGrafic, "Grafic");

        jSplitPane1.setRightComponent(Content);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1135, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butonAlimentatieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonAlimentatieActionPerformed
        // TODO add your handling code here:
        cardLayout.show(Content,"Alimentatie");
     
    }//GEN-LAST:event_butonAlimentatieActionPerformed

    private void butonAlimentatieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butonAlimentatieMouseClicked
        // TODO add your handling code here:
       //cardLayout.next(Content);
    }//GEN-LAST:event_butonAlimentatieMouseClicked

    private void butonAntrenamentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonAntrenamentActionPerformed
        // TODO add your handling code here:
      cardLayout.show(Content,"Antrenament");
    }//GEN-LAST:event_butonAntrenamentActionPerformed

    private void butonConditieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonConditieActionPerformed
        // TODO add your handling code here:
        cardLayout.show(Content,"Conditie");
    }//GEN-LAST:event_butonConditieActionPerformed

    private void butonMasuratoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonMasuratoriActionPerformed
        // TODO add your handling code here:
        cardLayout.show(Content,"Profil");
    }//GEN-LAST:event_butonMasuratoriActionPerformed

    private void butonGraficActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonGraficActionPerformed
        // TODO add your handling code here:
        cardLayout.show(Content,"Grafic");
    }//GEN-LAST:event_butonGraficActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
      LoginScreen login = new LoginScreen();
      login.setVisible(true);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void updateFromProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateFromProfileActionPerformed
        // TODO add your handling code here:
        InformatiiProfil updateInfo = new InformatiiProfil();
        updateInfo.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_updateFromProfileActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
         JOptionPane.showMessageDialog(null,"IMC -Indicele de masa corporala este indicatorul oficial de calculare a greutatii corporale ideale, pentru o inaltime data.\n"
                 + " Indicele de masa corporala ajuta la stabilirea grupei de greutate in care se incadreaza o persoana, adica gradul de obezitate.\n "
                 + "Indicele de masa corporala mai poate fi folosit pentru calcularea numarului de kilograme pe care o persoana trebuie sa le piarda sau sa le castige,\n"
                 + " pentru a ajunge la greutatea ideala conform inaltimii.\n\n Formula folosita pentru a calcula IMC este : \n IMC=greutate(kg) / inaltime^2(m)");
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        // TODO add your handling code here:
        
         JOptionPane.showMessageDialog(null,"RMB - Rata metabolică bazală este o estimare a numărului minim de calorii de care o persoană are nevoie în fiecare zi pentru a\n"
                 + " menține funcțiile de bază (respirația, circulația și digestia) în starea de repaus.\n  Există două ecuații prin care se poate calcula RMB-ul:\n- ecuația Harris-Benedict\n"
                 + "- ecuația Mifflin-St. Jeor.\n\n Ambele oferă o estimare separată pentru bărbați și femei.\n RMB-ul dvs este calculat folosind ecuatia Harris-Benedict astfel :\n"
                 + "Barbati : RMB = 66.5 + ( 13.75 × greutate in kg ) + ( 5.003 × inaltime in cm ) – ( 6.755 × varsta in ani )\n"
                 + "Femei : RMB = 655 + ( 9.563 × greutate in kg ) + ( 1.850 × inaltime in cm ) – ( 4.676 × varsta in ani )");
    }//GEN-LAST:event_jLabel33MouseClicked

    private void showWeightEvoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showWeightEvoActionPerformed
        // TODO add your handling code here:
    
        try {
            getWeightFromUser(utilizatorPublic);
        } catch (SQLException ex) {
            Logger.getLogger(testGraph.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=0;i<nrValori;i++)
        {
            dataset.setValue(valori[i], "Greutate",""+i);
            
        }
        
        JFreeChart chart = ChartFactory.createLineChart("Evolutie greutate","Zile","kilograme",dataset,PlotOrientation.VERTICAL,true,true,false);
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.removeAll();
        panel.add(chartPanel,BorderLayout.CENTER);
        panel.validate();
        CategoryPlot plot = chart.getCategoryPlot();
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        plot.setRenderer(renderer);
        renderer.setSeriesStroke(1, new BasicStroke(1.0f));
        DecimalFormat decimalformat1 = new DecimalFormat("##.#");
        renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
        renderer.setItemLabelsVisible(true);
        renderer.setSeriesVisible(true);
        plot.getRangeAxis().setAutoRange(true);                            // uncomment
       plot.getRangeAxis();
       org.jfree.chart.axis.ValueAxis rangeAxis = plot.getRangeAxis();
      rangeAxis.setLowerBound(minWeight-1);
      rangeAxis.setUpperBound(maxWeight+1);
      rangeAxis.setAutoRangeMinimumSize(1);
      
        chartPanel.setVisible(true);
        
    }//GEN-LAST:event_showWeightEvoActionPerformed

    private void adaugaMicDejunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugaMicDejunActionPerformed
        // TODO add your handling code here:
     foodAdded=true;
     micdejunAdded=true;
         AddFood addFood = null;
    try {
        addFood = new AddFood();
    } catch (SQLException ex) {
        Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
    }   this.setVisible(false);
        micDejunSentMe=true;
        addFood.setVisible(true);
        
                
                
                
    }//GEN-LAST:event_adaugaMicDejunActionPerformed

    private void adaugaPranzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugaPranzActionPerformed
        // TODO add your handling code here:
        foodAdded=true;
        pranzAdded=true;
        pranzSentMe=true;
         AddFood addFood = null;
    try {
        addFood = new AddFood();
    } catch (SQLException ex) {
        Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
    }   this.setVisible(false);
        
        addFood.setVisible(true);
    }//GEN-LAST:event_adaugaPranzActionPerformed

    private void adaugaCinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugaCinaActionPerformed
        // TODO add your handling code here:
        foodAdded=true;
        cinaAdded=true;
         cinaSentMe=true;
         AddFood addFood = null;
    try {
        addFood = new AddFood();
    } catch (SQLException ex) {
        Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
    }   this.setVisible(false);
        
        addFood.setVisible(true);
    }//GEN-LAST:event_adaugaCinaActionPerformed

    private void lowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lowActionPerformed
        // TODO add your handling code here:
        low.setBackground(new Color(28,201,34));
         mid.setBackground(new Color(240,240,240));
         high.setBackground(new Color(240,240,240));
         progressCaloriiConsumate.setMaximum(lowCal);
         progressCaloriiConsumate.setValue(micdejunCal+pranzCal+cinaCal);
         lblToReach.setText("Obiectiv: "+valueOf(lowCal));
         
         if(micdejunCal+pranzCal+cinaCal > lowCal)
         {
             progressCaloriiConsumate.setForeground(Color.RED);
             warningOver.setText("NUMAR CALORII DEPASIT !");
         }
         else 
         { 
             progressCaloriiConsumate.setForeground(new Color(28, 201, 34));
             warningOver.setText("");
         }
    }//GEN-LAST:event_lowActionPerformed

    private void midActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_midActionPerformed
        // TODO add your handling code here:
        mid.setBackground(new Color(28, 201, 34));
         low.setBackground(new Color(240,240,240));
         high.setBackground(new Color(240,240,240));
         progressCaloriiConsumate.setMaximum(midCal);
         progressCaloriiConsumate.setValue(micdejunCal+pranzCal+cinaCal);
        lblToReach.setText("Obiectiv: "+valueOf(midCal));
         
         if(micdejunCal+pranzCal+cinaCal > midCal)
         {
             progressCaloriiConsumate.setForeground(Color.RED);
              warningOver.setText("NUMAR CALORII DEPASIT !");
         }
         else 
         {
             progressCaloriiConsumate.setForeground(new Color(28, 201, 34));
             warningOver.setText("");
         }
         
    }//GEN-LAST:event_midActionPerformed

    private void highActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_highActionPerformed
        // TODO add your handling code here:
        high.setBackground(new Color(28, 201, 34));
         mid.setBackground(new Color(240,240,240));
         low.setBackground(new Color(240,240,240));
         progressCaloriiConsumate.setMaximum(highCal);
         progressCaloriiConsumate.setValue(micdejunCal+pranzCal+cinaCal);
          lblToReach.setText("Obiectiv: "+valueOf(highCal));
        if(micdejunCal+pranzCal+cinaCal > midCal)
         {
             progressCaloriiConsumate.setForeground(Color.RED);
              warningOver.setText("NUMAR CALORII DEPASIT !");
         }
         else 
         {
             progressCaloriiConsumate.setForeground(new Color(28, 201, 34));
            warningOver.setText("");
         }
    }//GEN-LAST:event_highActionPerformed
int diferentaCalorii;
boolean done=false;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    try {
        // TODO add your handling code here:
        done=false;
        foodAdded=false;
        micdejunAdded=false;
        pranzAdded=false;
        cinaAdded=false;
      
        updateMicDejunCalories(utilizatorPublic,"0");
        updatePranzCalories(utilizatorPublic,"0");
        updateCinaCalories(utilizatorPublic,"0");
        diferentaCalorii=Integer.parseInt(totalCaloriiConsumate.getText())-TDEE;
        System.out.println("DIFERENTA CALORII: "+diferentaCalorii);
        if(diferentaCalorii>600||diferentaCalorii<-600)
        {
            healthLevel-=35;
             System.out.println("Am scazut 35");
            done=true;
        }
        if(done!=true)
        {
            if(diferentaCalorii>400||diferentaCalorii<-400)
        {
            healthLevel-=25;
             System.out.println("Am scazut 25");
            done=true;
        }
        }
        if(done!=true)
        {
            if(diferentaCalorii>200||diferentaCalorii<-200)
        {
            healthLevel-=15;
             System.out.println("Am scazut 15");
            done=true;
        }
        }
        if(done!=true)
        {
            if(diferentaCalorii>0&&diferentaCalorii<200 ||diferentaCalorii<0&&diferentaCalorii>-200)
        {
            healthLevel-=10;
            System.out.println("Am scazut 10");
            done=true;
        }
        }
        if(!(totalCaloriiConsumate.getText()).equals("0"))
        {   
        insertCaloriesEachDay(totalCaloriiConsumate.getText(),utilizatorPublic);
        lblCaloriiMicDejun.setText("0");
        lblCaloriiPranz.setText("0");
        lblCaloriiCina.setText("0");
        totalCaloriiConsumate.setText("0");
        micdejunCal=0;
        pranzCal=0;
        cinaCal=0;
        totalCaloriiMicDejun=0;
        totalCaloriiPranz=0;
        totalCaloriiCina=0;
        }
        else
        {
             JOptionPane.showMessageDialog(null,"Numarul total de calorii nu poate fi 0 !");
        }
    } catch (SQLException ex) {
        Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
    }
    healthIndicator.setValue(healthLevel);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void showCaloriesEvoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showCaloriesEvoActionPerformed
        // TODO add your handling code here:
        
         try {
            getCaloriesFromUser(utilizatorPublic);
        } catch (SQLException ex) {
            Logger.getLogger(testGraph.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=0;i<nrValori;i++)
        {
            dataset.setValue(valori[i], "Calorii",""+i);
            
        }
        
        JFreeChart chart = ChartFactory.createLineChart("Evolutie calorii consumate","Zile","Calorii consumate",dataset,PlotOrientation.VERTICAL,true,true,false);
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.removeAll();
        panel.add(chartPanel,BorderLayout.CENTER);
        panel.validate();
        CategoryPlot plot = chart.getCategoryPlot();
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        plot.setRenderer(renderer);
        renderer.setSeriesStroke(1, new BasicStroke(1.0f));
        DecimalFormat decimalformat1 = new DecimalFormat("##.#");
        renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
        renderer.setItemLabelsVisible(true);
        renderer.setSeriesVisible(true);
        plot.getRangeAxis().setAutoRange(true);                            // uncomment
       plot.getRangeAxis();
       org.jfree.chart.axis.ValueAxis rangeAxis = plot.getRangeAxis();
      rangeAxis.setLowerBound(minWeight-100);
      rangeAxis.setUpperBound(maxWeight+200);
      rangeAxis.setAutoRangeMinimumSize(1);
      
        chartPanel.setVisible(true);
 
    }//GEN-LAST:event_showCaloriesEvoActionPerformed

    private void stergeMicDejunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stergeMicDejunActionPerformed
        // TODO add your handling code here:
        totalCaloriiMicDejun=0;
        lblCaloriiMicDejun.setText("0");
        micdejunCal=0;
        totalCaloriiConsumate.setText(valueOf(micdejunCal+pranzCal+cinaCal));
    try {
        stergeMicDejun(utilizatorPublic,"0");
    } catch (SQLException ex) {
        Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_stergeMicDejunActionPerformed

    private void StergePranzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StergePranzActionPerformed
        // TODO add your handling code here:
        totalCaloriiPranz=0;
        lblCaloriiPranz.setText("0");
        pranzCal=0;
        totalCaloriiConsumate.setText(valueOf(micdejunCal+pranzCal+cinaCal));
    try {
        updatePranzCalories(utilizatorPublic,"0");
    } catch (SQLException ex) {
        Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_StergePranzActionPerformed

    private void StergeCinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StergeCinaActionPerformed
        // TODO add your handling code here:
       totalCaloriiCina=0;
        lblCaloriiCina.setText("0");
        cinaCal=0;
        totalCaloriiConsumate.setText(valueOf(micdejunCal+pranzCal+cinaCal));
    try {
        updateCinaCalories(utilizatorPublic,"0");
    } catch (SQLException ex) {
        Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_StergeCinaActionPerformed

    private void adaugaMinuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adaugaMinuteActionPerformed
        // TODO add your handling code here:
         DefaultTableModel tabAntr = (DefaultTableModel)tabelAntrenament.getModel();
         String minute=minuteAntrenament.getText();
         if(minute.equals(""))JOptionPane.showMessageDialog(null,"Introduceti numarul de minute !");
         String tipAntr=(String) tipAntrenament.getSelectedItem();
         String []infoAntrenament={tipAntr,minute};
        if(!minute.equals("")) tabAntr.addRow(infoAntrenament);
        if(minute.equals("0"))minute="0";
         if(tipAntr.equals("Flotari"))
         {
             caloriiArse=caloriiArse+Integer.parseInt(minute)*6;
             progressCaloriiArse.setValue(caloriiArse);
             progressCaloriiArse.setString(valueOf(caloriiArse));
         }
         if(tipAntr.equals("Abdomene"))
         {
             caloriiArse=caloriiArse+Integer.parseInt(minute)*5;
             progressCaloriiArse.setValue(caloriiArse);
             progressCaloriiArse.setString(valueOf(caloriiArse));
         }
          if(tipAntr.equals("Genuflexiuni"))
         {
             caloriiArse=caloriiArse+Integer.parseInt(minute)*4;
             progressCaloriiArse.setValue(caloriiArse);
             progressCaloriiArse.setString(valueOf(caloriiArse));
         }
           if(tipAntr.equals("Jogging"))
         {
             caloriiArse=caloriiArse+Integer.parseInt(minute)*9;
             progressCaloriiArse.setValue(caloriiArse);
             progressCaloriiArse.setString(valueOf(caloriiArse));
         }
            if(tipAntr.equals("Ciclism"))
         {
             caloriiArse=caloriiArse+Integer.parseInt(minute)*8;
             progressCaloriiArse.setValue(caloriiArse);
             progressCaloriiArse.setString(valueOf(caloriiArse));
         }
             if(tipAntr.equals("Ridicari greutati"))
         {
             caloriiArse=caloriiArse+Integer.parseInt(minute)*6;
             progressCaloriiArse.setValue(caloriiArse);
             progressCaloriiArse.setString(valueOf(caloriiArse));
         }
              if(tipAntr.equals("Sarituri coarda"))
         {
             caloriiArse=caloriiArse+Integer.parseInt(minute)*13;
             progressCaloriiArse.setValue(caloriiArse);
             progressCaloriiArse.setString(valueOf(caloriiArse));
         }
         if(caloriiArse>=caloriesToBurn)
         {obiectivAntrenament=true;
         mesajObiectiv.setText("Ti-ai atins obiectivul de antrenament pentru astazi . Felicitari!");
         
         }
         else {mesajObiectiv.setText("Inca nu ti-ai atins obiectivul pentru astazi . Continua antrenamentele !");
         
       
         }
    }//GEN-LAST:event_adaugaMinuteActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    try {
        // TODO add your handling code here:
        boolean allok=true;
        inserted=false;
       
        if(obiectivAntrenament==false){healthLevel-=10;healthIndicator.setValue(healthLevel);System.out.println("Am scazut 10 pentru antrenament");}
        if(numarPasi.equals(""))allok=false;
        if(caloriiArse==0)allok=false;
        if(allok){
            insertBurnedCalories(valueOf(caloriiArse),numarPasi,utilizatorPublic);
        inserted=true;
       
        }
        else {JOptionPane.showMessageDialog(null,"Nu ai introdus toate datele !");
        
        healthLevel+=10;
        System.out.println("Corectat 10 antrenament");
        }
                numarPasi="";
    } catch (SQLException ex) {
        Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    DefaultTableModel tabAntrDelete = (DefaultTableModel)tabelAntrenament.getModel();
    if(inserted)
    {
        tabAntrDelete.setRowCount(0);
         nrPasi.setText("");
         numarPasi="0";
        minuteAntrenament.setText("");
        progressNumarPasi.setValue(0);
        progressCaloriiArse.setValue(0);
        progressCaloriiArse.setString("0");
        caloriiArse=0;
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        numarPasi=nrPasi.getText();
        if(numarPasi.equals(""))JOptionPane.showMessageDialog(null,"Introduceti numarul de pasi!");
        if(!(numarPasi.equals("")))progressNumarPasi.setValue(Integer.parseInt(numarPasi));
        if(Integer.parseInt(numarPasi)<6000)
        {
            healthLevel-=15;
            healthIndicator.setValue(healthLevel);
            System.out.println("AM SCAZUT PENTRU PASI ");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:
        
         JOptionPane.showMessageDialog(null,"TDEE - Total Daily Energy Expenditure este o estimare a numarului de calorii consumate intr-o zi\n"
                 + "atunci cand se ia in calcul nivelul de activitate.\n"
                 + "TDEE se calculeaza folosind RMB(Rata metabolica bazala) care se inmulteste cu un multiplicator de activitate\n");
       
    }//GEN-LAST:event_jLabel20MouseClicked

    private void buttonWorkoutEvoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonWorkoutEvoActionPerformed
        // TODO add your handling code here:
        
          
         try {
            getBurnedCaloriesFromUser(utilizatorPublic);
        } catch (SQLException ex) {
            Logger.getLogger(testGraph.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=0;i<nrValori;i++)
        {
            dataset.setValue(valori[i], "Calorii arse",""+i);
            
        }
        
        JFreeChart chart = ChartFactory.createLineChart("Evolutie calorii arse","Zile","Calorii arse",dataset,PlotOrientation.VERTICAL,true,true,false);
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.removeAll();
        panel.add(chartPanel,BorderLayout.CENTER);
        panel.validate();
        CategoryPlot plot = chart.getCategoryPlot();
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        plot.setRenderer(renderer);
        renderer.setSeriesStroke(1, new BasicStroke(1.0f));
        DecimalFormat decimalformat1 = new DecimalFormat("##.#");
        renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
        renderer.setItemLabelsVisible(true);
        renderer.setSeriesVisible(true);
        plot.getRangeAxis().setAutoRange(true);                            // uncomment
       plot.getRangeAxis();
       org.jfree.chart.axis.ValueAxis rangeAxis = plot.getRangeAxis();
      rangeAxis.setLowerBound(minWeight-100);
      rangeAxis.setUpperBound(maxWeight+200);
      rangeAxis.setAutoRangeMinimumSize(1);
      
        chartPanel.setVisible(true);
        
        
        
    }//GEN-LAST:event_buttonWorkoutEvoActionPerformed

    private void buttonPasiEvoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPasiEvoActionPerformed
        // TODO add your handling code here:
        
        try {
            getStepsFromUser(utilizatorPublic);
        } catch (SQLException ex) {
            Logger.getLogger(testGraph.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=0;i<nrValori;i++)
        {
            dataset.setValue(valori[i], "Numar pasi",""+i);
            
        }
        
        JFreeChart chart = ChartFactory.createLineChart("Evolutie pasi","Zile","Numar pasi",dataset,PlotOrientation.VERTICAL,true,true,false);
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.removeAll();
        panel.add(chartPanel,BorderLayout.CENTER);
        panel.validate();
        CategoryPlot plot = chart.getCategoryPlot();
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        plot.setRenderer(renderer);
        renderer.setSeriesStroke(1, new BasicStroke(1.0f));
        DecimalFormat decimalformat1 = new DecimalFormat("##.#");
        renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
        renderer.setItemLabelsVisible(true);
        renderer.setSeriesVisible(true);
        plot.getRangeAxis().setAutoRange(true);                            // uncomment
       plot.getRangeAxis();
       org.jfree.chart.axis.ValueAxis rangeAxis = plot.getRangeAxis();
      rangeAxis.setLowerBound(minWeight-100);
      rangeAxis.setUpperBound(maxWeight+200);
      rangeAxis.setAutoRangeMinimumSize(1);
      
        chartPanel.setVisible(true);
        
        
    }//GEN-LAST:event_buttonPasiEvoActionPerformed

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
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainScreen().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content;
    private javax.swing.JPanel Menu;
    private javax.swing.JButton StergeCina;
    private javax.swing.JButton StergePranz;
    private javax.swing.JButton adaugaCina;
    private javax.swing.JButton adaugaMicDejun;
    private javax.swing.JButton adaugaMinute;
    private javax.swing.JButton adaugaPranz;
    private javax.swing.JButton butonAlimentatie;
    private javax.swing.JButton butonAntrenament;
    private javax.swing.JButton butonConditie;
    private javax.swing.JButton butonGrafic;
    private javax.swing.JButton butonMasuratori;
    private javax.swing.JButton buttonPasiEvo;
    private javax.swing.JButton buttonWorkoutEvo;
    private javax.swing.JLabel caloriiIngrasa;
    private javax.swing.JLabel caloriiMentine;
    private javax.swing.JLabel caloriiSlabeste;
    private javax.swing.JProgressBar healthIndicator;
    private javax.swing.JButton high;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblBunVenit;
    private javax.swing.JLabel lblCaloriiCina;
    private javax.swing.JLabel lblCaloriiMicDejun;
    private javax.swing.JLabel lblCaloriiPranz;
    private javax.swing.JLabel lblConditie;
    private javax.swing.JLabel lblIMCValue;
    private javax.swing.JLabel lblRMBValue;
    private javax.swing.JLabel lblTDEEValue;
    private javax.swing.JLabel lblToReach;
    private javax.swing.JButton low;
    private javax.swing.JLabel mesajObiectiv;
    private javax.swing.JButton mid;
    private javax.swing.JTextField minuteAntrenament;
    private javax.swing.JTextField nrPasi;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelAlimentatie;
    private javax.swing.JPanel panelAntrenament;
    private javax.swing.JPanel panelConditie;
    private javax.swing.JPanel panelGrafic;
    private javax.swing.JPanel panelProfil;
    private javax.swing.JProgressBar progressCaloriiArse;
    private javax.swing.JProgressBar progressCaloriiConsumate;
    private javax.swing.JProgressBar progressNumarPasi;
    private javax.swing.JButton showCaloriesEvo;
    private javax.swing.JButton showWeightEvo;
    private javax.swing.JSlider sliderActivitate;
    private javax.swing.JButton stergeMicDejun;
    private javax.swing.JTable tabelAntrenament;
    private javax.swing.JComboBox<String> tipAntrenament;
    private javax.swing.JLabel totalCaloriiConsumate;
    private javax.swing.JButton updateFromProfile;
    private javax.swing.JLabel valoareGreutateProfil;
    private javax.swing.JLabel valoareInaltimeProfil;
    private javax.swing.JLabel valoareVarstaProfil;
    private javax.swing.JLabel warningOver;
    // End of variables declaration//GEN-END:variables
}
