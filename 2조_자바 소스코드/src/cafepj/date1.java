package cafepj;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.text.DateFormat;
import  java.text.MessageFormat;
import  java.text.SimpleDateFormat;
import  java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Font;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * @author 82102
 */

public class date1 extends javax.swing.JFrame {
    private static final String username = "root";
    private static final String Password = "root";
    private static final String dataConn = "jdbc:mysql://localhost/cafe?serverTimezone=UTC";
    
        Connection sqlConn =null;
    PreparedStatement pst=null;
    ResultSet rs = null;
  


    int q,i,id,deleteItem;
    /**
     * Creates new form SignUp
     */
    Connection conn=null;
    public date1() {
        initComponents();
        conn = new DB_connect().makeConnection();
        setTitle("메뉴수정");
        setIconImage(Toolkit.getDefaultToolkit().getImage("img/투썸로고.PNG"));
        setResizable(false);// 창사이즈 못움직이게하기
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//중앙에 창띄우기
        setVisible(true);
        
        
    }
    

private void tablelord(){

    try{
            Class.forName("com.mysql.jdbc.Driver");
            sqlConn = DriverManager.getConnection(dataConn,username,Password);
            pst =sqlConn.prepareStatement(String.format("select * from menu "));
            rs = pst.executeQuery();
            ResultSetMetaData stData =rs.getMetaData();
            
            q= stData.getColumnCount();
            
            DefaultTableModel RecordTable =( DefaultTableModel )jTable1.getModel();
                    RecordTable.setRowCount(0);
            while(rs.next()){
                Vector columnData = new Vector();
                for(i=1;i<=q; i++){
                    columnData.add(rs.getString("menu_name"));
                    columnData.add(rs.getString("menu_type"));
                    columnData.add(rs.getString("menu_value"));
 
                }
                RecordTable.addRow(columnData);
            }
        } catch (Exception ex) {
            JOptionPane.showConfirmDialog(null,ex);
            
        }
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        PASS1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton1.setForeground(Color.WHITE);
        jButton2 = new javax.swing.JButton();
        jButton2.setForeground(Color.WHITE);
        jButton3 = new javax.swing.JButton();
        jButton3.setForeground(Color.WHITE);
        jButton5 = new javax.swing.JButton();
        jButton5.setForeground(Color.WHITE);
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 400));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        jLabel1.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 14)); // NOI18N
        jLabel1.setText("메뉴");

        ID.setFont(new Font("함초롬돋움", Font.ITALIC, 14)); // NOI18N
        ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDActionPerformed(evt);
            }
        });

        jLabel2.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 14)); // NOI18N
        jLabel2.setText("카테고리");

        name.setFont(new Font("함초롬돋움", Font.ITALIC, 14)); // NOI18N
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 14)); // NOI18N
        jLabel3.setText("가격");

        PASS1.setFont(new Font("함초롬돋움", Font.ITALIC, 14)); // NOI18N
        PASS1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PASS1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
        					.addComponent(PASS1, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(name, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(ID, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)))
        			.addGap(107))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(22)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel1)
        				.addComponent(ID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel2)
        				.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(15)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel3)
        				.addComponent(PASS1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1.setLayout(jPanel1Layout);

        jButton1.setBackground(new java.awt.Color(213, 61, 61));
        jButton1.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 14)); // NOI18N
        jButton1.setText("수정");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 14)); // NOI18N
        jButton2.setText("닫기");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(213, 61, 61));
        jButton3.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 14)); // NOI18N
        jButton3.setText("받아오기");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(213, 61, 61));
        jButton5.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 14)); // NOI18N
        jButton5.setText("추가");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(12, 12, 12)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(400, 400));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "메뉴", "카테고리", "가격"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void formWindowClosing(java.awt.event.WindowEvent evt) {                                   
        // TODO add your handling code here:
                int exitBoolean = JOptionPane.showConfirmDialog(this, 
                "정말 종료 하시겠습니까?", "종료",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if(exitBoolean == JOptionPane.YES_OPTION){
            System.out.println("YES");
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } else {
            System.out.println("NO");
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }                                  

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    DefaultTableModel RecordTable =(DefaultTableModel)jTable1.getModel();
                   int SelectedRows =jTable1.getSelectedRow();
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            sqlConn = DriverManager.getConnection(dataConn,username,Password);
            pst =sqlConn.prepareStatement("update  menu set menu_type=?,menu_value=? where menu_name=?");

         
            
            pst.setString(3,ID.getText());
            pst.setString(1,name.getText());
            pst.setString(2,PASS1.getText());
 
            
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "수정되었습니다.");
            tablelord();
            
            
        } catch (ClassNotFoundException ex) {
        	ex.getStackTrace();
        } catch (SQLException ex) {
        	ex.getStackTrace();
        }       
    }                                        
private JFrame frame;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
                frame = new JFrame("Exit");
        if(JOptionPane.showConfirmDialog(frame,"종료하시겠습니까?","닫기",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
        	
            dispose();
        }
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        tablelord();
    }                                        

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {                                     
         DefaultTableModel RecordTable =(DefaultTableModel)jTable1.getModel();
                   int SelectedRows =jTable1.getSelectedRow();
                  
                   ID.setText(RecordTable.getValueAt(SelectedRows,0).toString());
                   name.setText(RecordTable.getValueAt(SelectedRows,1).toString());
                   PASS1.setText(RecordTable.getValueAt(SelectedRows,2).toString());

                   
    }                                    

    private void PASS1ActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
    }                                     

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
    }                                    

    private void IDActionPerformed(java.awt.event.ActionEvent evt) {                                   
        // TODO add your handling code here:
    }                                  

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
                     try{
            Class.forName("com.mysql.jdbc.Driver");
            sqlConn = DriverManager.getConnection(dataConn,username,Password);
            pst =sqlConn.prepareStatement("insert  into menu(menu_name,menu_type,menu_value)value(?,?,?) ");
            
            pst.setString(1,ID.getText());
            pst.setString(2,name.getText());
            pst.setString(3,PASS1.getText());

            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "메뉴가 추가되었습니다.");
            tablelord();
                       
            
            
        } catch (ClassNotFoundException ex) {
            ex.getStackTrace();
        } catch (SQLException ex) {
        	ex.getStackTrace();
        }
    }                                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    	
    	date1 date1 = new date1();
    	
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(date1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(date1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(date1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(date1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>


        /* Create and display the form */

    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextField ID;
    private javax.swing.JTextField PASS1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField name;
    // End of variables declaration                   


}