/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package operatorPackage;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Graphics;
import java.awt.Image;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.security.cert.Extension;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author prasu
 */

public class Invoice extends javax.swing.JInternalFrame {

    /**
     * Creates new form Customer
     */String url="jdbc:mysql://cloud-db.cv9uvbky3tsl.ap-south-1.rds.amazonaws.com/universal";
            String un="root";
            String pass="Password123";
            Connection con;
    public Invoice() {
        initComponents();
        //autoId();
        broadband();
        operators();
                  
    
       
        view();
        
    }
    public void view()
    {
        
        Connection con;
    
       
        try { 
            txtCount.setText("");
        flag.setText("");
        txtSum.setText("");
            jTableClient.setModel(new DefaultTableModel(null,new String[]{"CUST IP","CUST_NAME","CUST_ADDRESS","CUST_CONTACT","INVOICE NO","RECHARGE DATE","AMOUNT","OPERATOR NAME","BROADBAND"}));
            
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url,un,pass);
            
                  Statement st1=con.createStatement();
                  Statement st2=con.createStatement();
                  Statement st3=con.createStatement();
                  Statement st4=con.createStatement();
                  String query1="select  cl.user_id_ip,cl.client_name,cl.client_address,res.cust_contact_no,cl.invoice_no,"
                          + "cl.recharge_date,cl.amount,res.operator_name,res.cust_broadband from (select * from invoice where "
                          + "invoice.amount!=0 )as cl left outer join(select cust_user_id,cust_contact_no,cust_broadband,operator_name"
                          + " from customer join operator where cust_operator_id=operator_id )as res on res.cust_user_id=cl.user_id_ip;";
                  
                    String query2="select  sum(cl.amount) from (select * from invoice where invoice.amount!=0 )as"
                            + " cl left outer join(select cust_user_id,cust_contact_no,cust_broadband,operator_name "
                            + "from customer join operator where cust_operator_id=operator_id )as res on res.cust_user_id=cl.user_id_ip ;";
                    
                  String query3="select  count(*) from (select * from invoice where invoice.amount!=0 )as cl "
                          + "left outer join(select cust_user_id,cust_contact_no,cust_broadband,operator_name "
                          + "from customer join operator where cust_operator_id=operator_id )as res on "
                          + "res.cust_user_id=cl.user_id_ip ;" ;
                  
                  String query4="select  count(*) from (select * from invoice where invoice.amount<0 )as"
                          + " cl left outer join(select cust_user_id,cust_contact_no,cust_broadband,operator_name from customer "
                          + "join operator where cust_operator_id=operator_id )as res on res.cust_user_id=cl.user_id_ip;";
                  //System.out.print(query1);

       ResultSet rs1=st1.executeQuery(query1);
         ResultSet rs2=st2.executeQuery(query2);
          ResultSet rs3=st3.executeQuery(query3);
          ResultSet rs4=st4.executeQuery(query4);
         
         rs2.next();
         rs3.next();
         rs4.next();
         int a=0;
         if(rs2.getString(1)!=null)
          a= new Double(rs2.getString(1)).intValue();
         //System.out.print(a);
         txtSum.setText(Integer.toString(a));
         txtCount.setText(rs3.getString(1));
         if(!(rs4.getString(1).equals("0")))
         {flag.setText(rs4.getString(1)+"  Recharge Cancelled");
         int count=new Integer(rs3.getString(1)).intValue()-2*new Integer(rs4.getString(1)).intValue();
         txtCount.setText(Integer.toString(count));
         
         }
         

        DefaultTableModel model=(DefaultTableModel)jTableClient.getModel();
        while(rs1.next())
        {model.addRow(new String[]{rs1.getString(1),rs1.getString(2).toUpperCase(),rs1.getString(3),rs1.getString(4),rs1.getString(5), rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9)});         
        //System.out.println(rs1.getString(2));
        }
        txtCust.setText("");
        txtamount.setText("");
          jComboBox1.setSelectedIndex(-1);
          jComboBox4.setSelectedIndex(-1);
        
        }       
        
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
            
 public void filter()
 {
      Connection con;
        String name="";
       if(jComboBox1.getSelectedIndex() != -1)
       {name=jComboBox1.getSelectedItem().toString();
       }
       
    
        String amount= txtamount.getText();
        String cust_name=txtCust.getText().toUpperCase();
        String band="";
         if(jComboBox4.getSelectedIndex() != -1)
       {band=jComboBox4.getSelectedItem().toString();
       }
        
       
        try {
            
             txtCount.setText("");
        flag.setText("");
        txtSum.setText("");
            jTableClient.setModel(new DefaultTableModel(null,new String[]{"CUST IP","CUST_NAME","CUST_ADDRESS","CUST_CONTACT","INVOICE NO","RECHARGE DATE","AMOUNT","OPERATOR NAME","BROADBAND"}));
            
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url,un,pass);
            
                 Statement st1=con.createStatement();
                  Statement st2=con.createStatement();
                  Statement st3=con.createStatement();
                  Statement st4=con.createStatement();
                  String query1="select  cl.user_id_ip,cl.client_name,cl.client_address,res.cust_contact_no,cl.invoice_no,"
                          + "cl.recharge_date,cl.amount,res.operator_name,res.cust_broadband from (select * from invoice where "
                          + "invoice.amount!=0 ";
                  String query2="select  sum(cl.amount) from (select * from invoice where invoice.amount!=0 " ;
                    
                  String query3="select  count(*) from (select * from invoice where invoice.amount!=0 " ;
                  
                  String query4="select  count(*) from (select * from invoice where invoice.amount<0  ";
                  
                   if(amount.length()!= 0)
                  { int l= new Double(amount).intValue();
                  int r=l+1;
                      query1=query1+"AND invoice.amount BETWEEN'"+l+"' AND '"+r+"'" ;
                      query2=query2+"AND invoice.amount BETWEEN'"+l+"' AND '"+r+"'" ;
                      query3=query3+"AND invoice.amount BETWEEN'"+l+"' AND '"+r+"'" ;
                      query4=query4+"AND invoice.amount BETWEEN'"+l+"' AND '"+r+"'" ;
                  }
                          
                  query1=query1.concat(")as cl left outer join(select cust_user_id,cust_contact_no,cust_broadband,operator_name  from customer join operator where cust_operator_id=operator_id )as res on res.cust_user_id=cl.user_id_ip ");
                  query2=query2.concat(")as cl left outer join(select cust_user_id,cust_contact_no,cust_broadband,operator_name  from customer join operator where cust_operator_id=operator_id )as res on res.cust_user_id=cl.user_id_ip ");
                  query3=query3.concat(")as cl left outer join(select cust_user_id,cust_contact_no,cust_broadband,operator_name  from customer join operator where cust_operator_id=operator_id )as res on res.cust_user_id=cl.user_id_ip ");
                  query4=query4.concat(")as cl left outer join(select cust_user_id,cust_contact_no,cust_broadband,operator_name  from customer join operator where cust_operator_id=operator_id )as res on res.cust_user_id=cl.user_id_ip ");
 
                  if(name.length()!=0)
                  {
                      
                      query1=query1+" WHERE res.operator_name='"+name+"'";
                     query2=query2+"WHERE res.operator_name='"+name+"'";
                     query3=query3+"WHERE res.operator_name='"+name+"'";
                     query4=query4+"WHERE res.operator_name='"+name+"'";


                  }
                 
                  if(cust_name.length()!=0)
                  { cust_name="%"+cust_name+"%";
                      if(name.length()!=0)
                      {
                  query1=query1+" AND cl.client_name like'"+cust_name+"'";
                  query2=query2+"AND cl.client_name like'"+cust_name+"'";
                  query3=query3+"AND cl.client_name like'"+cust_name+"'";
                  query4=query4+"AND cl.client_name like'"+cust_name+"'";
                      }
                      else
                      { query1=query1+" WHERE cl.client_name like'"+cust_name+"'";
                     query2=query2+"WHERE cl.client_name like'"+cust_name+"'";
                    query3=query3+"WHERE cl.client_name like'"+cust_name+"'";
                    query4=query4+"WHERE cl.client_name like'"+cust_name+"'";
                          
                      }
                  
                  }
                  if(band.length()!=0)
                  { if(name.length()!=0 ||cust_name.length()!=0)
                  { query1=query1+" AND res.cust_broadband='"+band+"'";
                    query2=query2+" AND res.cust_broadband='"+band+"'";
                    query3=query3+" AND res.cust_broadband='"+band+"'";
                    query4=query4+" AND res.cust_broadband='"+band+"'";
                  }
                  else
                  {query1=query1+" WHERE res.cust_broadband='"+band+"'";
                    query2=query2+" WHERE res.cust_broadband='"+band+"'";
                    query3=query3+" WHERE res.cust_broadband='"+band+"'";
                    query4=query4+" WHERE res.cust_broadband='"+band+"'";
                      
                  }
                  }
                      
                  
                  query1=query1+";";
                  query2=query2+";";
                  query3=query3+";";
                  query4=query4+";";
                // System.out.print(query1);
        
        ResultSet rs1=st1.executeQuery(query1);
         ResultSet rs2=st2.executeQuery(query2);
          ResultSet rs3=st3.executeQuery(query3);
          ResultSet rs4=st4.executeQuery(query4);
         rs2.next();
         //System.out.print(rs2.getString(1));
         rs3.next();
         rs4.next();
         if(!(rs4.getString(1).equals("0")))
         {flag.setText(rs4.getString(1)+"  Recharge Cancelled");
         int count=new Integer(rs3.getString(1)).intValue()-2*new Integer(rs4.getString(1)).intValue();
         txtCount.setText(Integer.toString(count));
         
         }
        
         int a=0;
         if(rs2.getString(1)!=null)
         a= new Double(rs2.getString(1)).intValue();
        
         txtSum.setText(Integer.toString(a));
         
         txtCount.setText(rs3.getString(1));

        DefaultTableModel model=(DefaultTableModel)jTableClient.getModel();
        while(rs1.next())
        {model.addRow(new String[]{rs1.getString(1),rs1.getString(2).toUpperCase(),rs1.getString(3),rs1.getString(4),rs1.getString(5), rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9)});         
        }
        
        }     
        
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
           


   
    public void operators()
    {Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url,un,pass);
            Statement st=con.createStatement();
        
        ResultSet rs=st.executeQuery("select operator_name from operator;");
        
        
        jComboBox1.removeAllItems();
        while(rs.next())
        {
        jComboBox1.addItem(rs.getString(1));
       
        }
        
        
       
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
            
           
    }
    
    
     public void broadband()
    { Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url,un,pass);
            Statement st=con.createStatement();
        
        ResultSet rs=st.executeQuery("select cust_broadband from customer group by cust_broadband;");
        
         jComboBox4.removeAllItems();
        
        while(rs.next())
        {jComboBox4.addItem(rs.getString(1));
            
       
        }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
     
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        txtPath = new javax.swing.JTextField();
        gtplLoad = new javax.swing.JButton();
        browse = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        txtPath1 = new javax.swing.JTextField();
        allianceLoad1 = new javax.swing.JButton();
        browse1 = new javax.swing.JButton();
        txtCust = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        reset = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        filter1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        trunate = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtamount = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableClient = new javax.swing.JTable();
        flag = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtSum = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCount = new javax.swing.JTextField();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        txtPath2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        txtPath3 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableClient1 = new javax.swing.JTable();
        txtCust1 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox<>();
        txtCount1 = new javax.swing.JTextField();
        reset1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        filter2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 102, 153));
        setClosable(true);
        setForeground(new java.awt.Color(255, 123, 162));
        setIconifiable(true);
        setRequestFocusEnabled(false);

        jPanel1.setBackground(new java.awt.Color(75, 72, 103));

        jTabbedPane1.setBackground(new java.awt.Color(204, 170, 102));
        jTabbedPane1.setForeground(new java.awt.Color(204, 0, 102));
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jPanel10.setBackground(new java.awt.Color(204, 170, 102));

        gtplLoad.setBackground(new java.awt.Color(51, 153, 0));
        gtplLoad.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        gtplLoad.setForeground(new java.awt.Color(255, 255, 255));
        gtplLoad.setText("GTPL UPLOAD ");
        gtplLoad.setPreferredSize(new java.awt.Dimension(144, 26));
        gtplLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gtplLoadActionPerformed(evt);
            }
        });
        gtplLoad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                gtplLoadKeyPressed(evt);
            }
        });

        browse.setBackground(new java.awt.Color(102, 102, 102));
        browse.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        browse.setForeground(new java.awt.Color(255, 255, 255));
        browse.setText("BROWSE");
        browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });
        browse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                browseKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(browse, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(gtplLoad, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gtplLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browse, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("GTPL", jPanel10);

        jPanel9.setBackground(new java.awt.Color(141, 181, 128));

        allianceLoad1.setBackground(new java.awt.Color(51, 153, 0));
        allianceLoad1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        allianceLoad1.setForeground(new java.awt.Color(255, 255, 255));
        allianceLoad1.setText("ALLIANCE UPLOAD ");
        allianceLoad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allianceLoad1ActionPerformed(evt);
            }
        });
        allianceLoad1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                allianceLoad1KeyPressed(evt);
            }
        });

        browse1.setBackground(new java.awt.Color(102, 102, 102));
        browse1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        browse1.setForeground(new java.awt.Color(255, 255, 255));
        browse1.setText("BROWSE");
        browse1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browse1ActionPerformed(evt);
            }
        });
        browse1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                browse1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(txtPath1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(browse1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(allianceLoad1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPath1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(allianceLoad1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browse1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ALLIANCE", jPanel9);

        txtCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustActionPerformed(evt);
            }
        });
        txtCust.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCustKeyPressed(evt);
            }
        });

        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        reset.setBackground(new java.awt.Color(0, 0, 204));
        reset.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        reset.setForeground(new java.awt.Color(255, 255, 255));
        reset.setText("RESET");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        reset.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                resetKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Customer Name");

        jComboBox4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox4KeyPressed(evt);
            }
        });

        filter1.setBackground(new java.awt.Color(255, 0, 51));
        filter1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        filter1.setForeground(new java.awt.Color(255, 255, 255));
        filter1.setText("FILTER");
        filter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter1ActionPerformed(evt);
            }
        });
        filter1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                filter1KeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Operator ");

        jLabel15.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Broadband");

        trunate.setBackground(new java.awt.Color(255, 0, 51));
        trunate.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        trunate.setForeground(new java.awt.Color(255, 255, 255));
        trunate.setText("DELETE");
        trunate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trunateActionPerformed(evt);
            }
        });
        trunate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                trunateKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Amount");

        txtamount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtamountFocusGained(evt);
            }
        });
        txtamount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtamountKeyPressed(evt);
            }
        });

        jScrollPane1.setPreferredSize(new java.awt.Dimension(1066, 678));

        jTableClient.setAutoCreateRowSorter(true);
        jTableClient.setBackground(new java.awt.Color(38, 110, 115));
        jTableClient.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTableClient.setForeground(new java.awt.Color(255, 255, 255));
        jTableClient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CUST IP/USER ID", "CUST NAME", "CUST ADDRESS", "CUST CONTACT", "INVOICE NO", "RECHARGE DATE", "AMOUNT", "OPERATOR NAME", "BROADBAND"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableClient.setColumnSelectionAllowed(true);
        jTableClient.setGridColor(new java.awt.Color(0, 0, 0));
        jTableClient.setRowSelectionAllowed(false);
        jTableClient.setSelectionBackground(new java.awt.Color(38, 70, 63));
        jTableClient.setShowGrid(true);
        jScrollPane1.setViewportView(jTableClient);

        flag.setBackground(new java.awt.Color(255, 0, 51));
        flag.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 14)); // NOI18N
        flag.setForeground(new java.awt.Color(255, 0, 51));
        flag.setText("Total Recharge");

        jLabel14.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Total Recharge");

        txtSum.setBackground(new java.awt.Color(255, 255, 0));
        txtSum.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtSum.setForeground(new java.awt.Color(0, 0, 0));
        txtSum.setText("jTextField1");
        txtSum.setFocusable(false);
        txtSum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSumActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Count");

        txtCount.setBackground(new java.awt.Color(255, 255, 0));
        txtCount.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtCount.setForeground(new java.awt.Color(0, 0, 0));
        txtCount.setText("jTextField1");
        txtCount.setFocusable(false);
        txtCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(flag, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(3, 3, 3)
                .addComponent(txtSum, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122)
                .addComponent(jLabel12)
                .addGap(3, 3, 3)
                .addComponent(txtCount, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(trunate, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(1, 1, 1)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCust, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtamount, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(jLabel15)
                .addGap(3, 3, 3)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(510, 510, 510)
                        .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(filter1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtCust, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filter1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(flag)
                    .addComponent(jLabel14)
                    .addComponent(txtSum, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCount, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(trunate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jInternalFrame1.setBackground(new java.awt.Color(0, 102, 153));
        jInternalFrame1.setClosable(true);
        jInternalFrame1.setForeground(new java.awt.Color(255, 123, 162));
        jInternalFrame1.setIconifiable(true);
        jInternalFrame1.setRequestFocusEnabled(false);

        jPanel2.setBackground(new java.awt.Color(75, 72, 103));

        jTabbedPane2.setBackground(new java.awt.Color(204, 170, 102));
        jTabbedPane2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jPanel7.setBackground(new java.awt.Color(204, 170, 102));

        jButton4.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton4.setText("Browse");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(51, 153, 0));
        jButton6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton6.setText("Load Data");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPath2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(710, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPath2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4))
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("GTPL", jPanel7);

        jPanel8.setBackground(new java.awt.Color(204, 170, 102));

        jButton7.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton7.setText("Browse");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(51, 153, 0));
        jButton8.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton8.setText("Load Data");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPath3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(710, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPath3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7))
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("ALLIANCE", jPanel8);

        jTableClient1.setAutoCreateRowSorter(true);
        jTableClient1.setBackground(new java.awt.Color(38, 110, 115));
        jTableClient1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTableClient1.setForeground(new java.awt.Color(255, 255, 255));
        jTableClient1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CUST NAME", "CUST IP", "CUST CONTACT", "OPERATOR NAME", "BROADBAND"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableClient1.setGridColor(new java.awt.Color(0, 0, 0));
        jTableClient1.setRowSelectionAllowed(false);
        jTableClient1.setShowGrid(true);
        jScrollPane2.setViewportView(jTableClient1);

        txtCust1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCust1KeyPressed(evt);
            }
        });

        jComboBox5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox5KeyPressed(evt);
            }
        });

        txtCount1.setBackground(new java.awt.Color(255, 255, 255));
        txtCount1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtCount1.setForeground(new java.awt.Color(0, 0, 0));
        txtCount1.setText("jTextField1");
        txtCount1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCount1ActionPerformed(evt);
            }
        });

        reset1.setBackground(new java.awt.Color(0, 0, 204));
        reset1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        reset1.setForeground(new java.awt.Color(255, 255, 255));
        reset1.setText("RESET");
        reset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset1ActionPerformed(evt);
            }
        });
        reset1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                reset1KeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Customer Name");

        jComboBox6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox6KeyPressed(evt);
            }
        });

        filter2.setBackground(new java.awt.Color(255, 0, 51));
        filter2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        filter2.setForeground(new java.awt.Color(255, 255, 255));
        filter2.setText("FILTER");
        filter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter2ActionPerformed(evt);
            }
        });
        filter2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                filter2KeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Operator ");

        jLabel16.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Broadband");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(1, 1, 1)
                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCust1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(178, 178, 178)
                                .addComponent(jLabel16)
                                .addGap(2, 2, 2)
                                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(reset1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(filter2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(350, 350, 350)
                                .addComponent(txtCount1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCust1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reset1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filter2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCount1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
      
        view();
    }//GEN-LAST:event_resetActionPerformed

    private void resetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_resetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_resetKeyPressed

    private void txtCustKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCustKeyPressed
        // TODO add your handling code here:
        
        filter();

       
        
    }//GEN-LAST:event_txtCustKeyPressed

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        filter();
        

        
    }//GEN-LAST:event_jComboBox1KeyPressed

    private void txtCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCountActionPerformed

    private void jComboBox4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox4KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        filter();
    }//GEN-LAST:event_jComboBox4KeyPressed

    private void filter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter1ActionPerformed
        // TODO add your handling code here:
        filter();
       
    }//GEN-LAST:event_filter1ActionPerformed

    private void filter1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filter1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_filter1KeyPressed
String filePath=null;
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void txtCust1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCust1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCust1KeyPressed

    private void jComboBox5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5KeyPressed

    private void txtCount1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCount1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCount1ActionPerformed

    private void reset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reset1ActionPerformed

    private void reset1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reset1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_reset1KeyPressed

    private void jComboBox6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6KeyPressed

    private void filter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filter2ActionPerformed

    private void filter2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filter2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_filter2KeyPressed

    private void trunateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trunateActionPerformed
        // TODO add your handling code here:
        
        Connection con;
    
       
        try { 
            
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url,un,pass);
            
                  Statement st1=con.createStatement();
                  
                  String query1="truncate invoice ;";
                  int row=st1.executeUpdate(query1);
                  view();
        
         
        
        }       
        
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_trunateActionPerformed

    private void trunateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_trunateKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_trunateKeyPressed

    private void txtamountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtamountKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        filter();
    }//GEN-LAST:event_txtamountKeyPressed

    private void txtSumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSumActionPerformed

    private void txtCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustActionPerformed

    private void browse1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_browse1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_browse1KeyPressed

    private void browse1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browse1ActionPerformed
        // TODO add your handling code here:
        JFileChooser choser=new JFileChooser(new File("C:\\Users\\prasu\\Downloads"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv", "csv");
        choser.setFileFilter(filter);
        choser.showOpenDialog(null);
        File f=choser.getSelectedFile();
        filePath=f.getAbsolutePath();

        txtPath1.setText(filePath);
    }//GEN-LAST:event_browse1ActionPerformed

    private void allianceLoad1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_allianceLoad1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_allianceLoad1KeyPressed

    private void allianceLoad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allianceLoad1ActionPerformed
        // TODO add your handling code here:]

        if(filePath!=null)
        {try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url,un,pass);
            //filePath="C:\\Users\\prasu\\Documents\\load.csv";

            int batchSize=20;

            String query="insert into universal.invoice values(?,?,?,?,?,?,?);";

            BufferedReader lineReader=new BufferedReader(new FileReader(filePath));
            if(lineReader==null)
            JOptionPane.showMessageDialog(this,"Invalid Path");

            PreparedStatement st=con.prepareStatement(query);

            String lineText=null;
            int count=0;

            lineReader.readLine();
            int i=0;
            while ((lineText=lineReader.readLine())!=null){
                String[] data=lineText.split(";");
                if(data[0].equals("N"))
                break;
                if (data[0].equals(""))
                continue;
                //

                String invoice_no=data[10];
                String client_name=data[1];
                String address=data[6];
                String client_ip=data[8];
                String recharge_date=data[12];
                String ipackage=data[13];
                int amo=new Double(data[14]).intValue();
                String amount=Integer.toString(amo);

                st.setString(1,(invoice_no));
                st.setString(2,client_name);
                st.setString(3,address);
                st.setString(4,client_ip);
                st.setString(5,recharge_date);
                st.setFloat(6,parseFloat(ipackage));
                st.setFloat(7,parseFloat(amount));

                st.addBatch();
                if(count%batchSize==0){
                    st.executeBatch();

                }

            }
            lineReader.close();
            st.executeBatch();
            //con.commit();
            con.close();

            JOptionPane.showMessageDialog(this,"<html><b style=\"color:GREEN;font-size:20px;\">Record updated</b></html>");
            view();
            txtPath1.setText("");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }}
        else
        JOptionPane.showMessageDialog(this,"<html><b style=\"color:RED;font-size:20px;\">Invalid Path</b></html>");
    }//GEN-LAST:event_allianceLoad1ActionPerformed
    
    
    
    private void gtplLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gtplLoadActionPerformed
        // TODO add your handling code here:
        
          if(filePath!=null)
        {try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url,un,pass);
            //filePath="C:\\Users\\prasu\\Documents\\load.csv";

            int batchSize=20;

            String query="insert into universal.invoice values(?,?,?,?,?,?,?);";

            BufferedReader lineReader=new BufferedReader(new FileReader(filePath));
            if(lineReader==null)
            JOptionPane.showMessageDialog(this,"Invalid Path");

            PreparedStatement st=con.prepareStatement(query);

            String lineText=null;
            int count=0;

            lineReader.readLine();
            lineReader.readLine();
            lineReader.readLine();
            int i=0;
            while ((lineText=lineReader.readLine())!=null){
                
                    
                String[] data=lineText.split(";");
                String invoice_no=data[1];
                String client_name=data[3];
                String address=data[17];
                String client_ip=data[2];
                String recharge_date=data[6];
                String ipackage=data[18];
                String amount=data[23];

                st.setString(1,(invoice_no));
                st.setString(2,client_name);
                st.setString(3,address);
                st.setString(4,client_ip);
                st.setString(5,recharge_date);
                st.setFloat(6,parseFloat(ipackage));
                st.setFloat(7,parseFloat(amount));

                st.addBatch();
                if(count%batchSize==0){
                    st.executeBatch();

                }

            }
            lineReader.close();
            st.executeBatch();
            //con.commit();
            con.close();

            JOptionPane.showMessageDialog(this,"<html><b style=\"color:GREEN;font-size:20px;\">Record updated</b></html>");
            view();
            txtPath.setText("");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, ex);
        }}
        else
        JOptionPane.showMessageDialog(this,"<html><b style=\"color:RED;font-size:20px;\">Invalid Path</b></html>");
   
    }//GEN-LAST:event_gtplLoadActionPerformed

    private void gtplLoadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gtplLoadKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_gtplLoadKeyPressed

    private void browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseActionPerformed
        // TODO add your handling code here:
        JFileChooser choser=new JFileChooser(new File("C:\\Users\\prasu\\Downloads"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv", "csv");
        choser.setFileFilter(filter);
        choser.showOpenDialog(null);
        File f=choser.getSelectedFile();
        filePath=f.getAbsolutePath();

        txtPath.setText(filePath);
    }//GEN-LAST:event_browseActionPerformed

    private void browseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_browseKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_browseKeyPressed

    private void txtamountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtamountFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtamountFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton allianceLoad1;
    private javax.swing.JButton browse;
    private javax.swing.JButton browse1;
    private javax.swing.JButton filter1;
    private javax.swing.JButton filter2;
    private javax.swing.JLabel flag;
    private javax.swing.JButton gtplLoad;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTableClient;
    private javax.swing.JTable jTableClient1;
    private javax.swing.JButton reset;
    private javax.swing.JButton reset1;
    private javax.swing.JButton trunate;
    private javax.swing.JTextField txtCount;
    private javax.swing.JTextField txtCount1;
    private javax.swing.JTextField txtCust;
    private javax.swing.JTextField txtCust1;
    private javax.swing.JTextField txtPath;
    private javax.swing.JTextField txtPath1;
    private javax.swing.JTextField txtPath2;
    private javax.swing.JTextField txtPath3;
    private javax.swing.JTextField txtSum;
    private javax.swing.JTextField txtamount;
    // End of variables declaration//GEN-END:variables

   
}
