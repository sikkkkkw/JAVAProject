package cafepj;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.ScrollPaneConstants;

public class Shopping extends JFrame implements ActionListener {
       Statement stmt;
       ResultSet rs;
       String query;
       Connection con = new DB_connect().makeConnection();
       String temp = null;
   	   int order_number = place_select.getorder_number();
       int order_value = 0; //결제 금액
       int order_total_value = 0;
       int sum=0;
       int ins;
   
   
   
   private JFrame frame;
   JButton btnNewButton = new JButton("결제");
   JButton btnNewButton_1 = new JButton("전부 삭제");
   JButton btnNewButton_1_1 = new JButton("뒤로 가기");
   JLabel lblNewLabel_9 = new JLabel();
   
   public static void main(String[] args) throws Exception {
      Shopping frame = new Shopping();
   }
   public Shopping() throws Exception {
      initialize();
   }
   private void initialize() throws SQLException {
      frame = new JFrame();
      frame.getContentPane().setBackground(Color.WHITE);
      frame.setBackground(Color.WHITE);
      frame.setTitle("장바구니");
	  frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("img/투썸로고.PNG")));
      frame.setBounds(100, 100, 450, 429);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      frame.setVisible(true);
      
      JPanel panel = new JPanel();
      panel.setBackground(new Color(220, 20, 60));
      panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
      panel.setBounds(12, 10, 410, 82);
      frame.getContentPane().add(panel);
      panel.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("Shopping");
      lblNewLabel.setForeground(Color.WHITE);
      lblNewLabel.setFont(new Font("Arial Black", Font.ITALIC, 50));
      lblNewLabel.setBounds(76, 10, 271, 62);
      panel.add(lblNewLabel);
      
      JPanel panel_1 = new JPanel();
      panel_1.setBackground(Color.WHITE);
      panel_1.setBorder(new LineBorder(Color.PINK));
      panel_1.setBounds(0, 327, 434, 63);
      frame.getContentPane().add(panel_1);
      panel_1.setLayout(null);
      
      
      btnNewButton.setForeground(Color.WHITE);
      btnNewButton.setBackground(new Color(220, 20, 60));
      btnNewButton.setFont(new Font("함초롬돋움", Font.ITALIC, 12));
      btnNewButton.setBounds(325, 10, 97, 43);
      btnNewButton.addActionListener(this);
      panel_1.add(btnNewButton);
      
      btnNewButton_1.setBackground(new Color(220, 20, 60));
      btnNewButton_1.setForeground(Color.WHITE);
      btnNewButton_1.setFont(new Font("함초롬돋움", Font.ITALIC, 12));
      btnNewButton_1.setBounds(208, 10, 97, 43);
      btnNewButton_1.addActionListener(this);
      panel_1.add(btnNewButton_1);
      
      
      btnNewButton_1_1.setForeground(Color.WHITE);
      btnNewButton_1_1.setBackground(Color.DARK_GRAY);
      btnNewButton_1_1.setFont(new Font("함초롬돋움", Font.ITALIC, 12));
      btnNewButton_1_1.setBounds(12, 10, 97, 43);
      btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        	    try {
  					frame.dispose();
					new menu_select();
  				} catch (SQLException e1) {
  					e1.printStackTrace();
  				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
      panel_1.add(btnNewButton_1_1);
      
      JPanel panel_3 = new JPanel();
      panel_3.setBackground(Color.WHITE);
      panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
      panel_3.setBounds(12, 102, 410, 42);
      frame.getContentPane().add(panel_3);
      panel_3.setLayout(null);
      
      JLabel lblNewLabel_5 = new JLabel("<HOT/ICE>");
      lblNewLabel_5.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
      lblNewLabel_5.setBounds(12, 11, 73, 15);
      panel_3.add(lblNewLabel_5);
      
      JLabel lblNewLabel_6 = new JLabel("<Menu>");
      lblNewLabel_6.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
      lblNewLabel_6.setVerticalAlignment(SwingConstants.TOP);
      lblNewLabel_6.setBounds(109, 10, 57, 15);
      panel_3.add(lblNewLabel_6);
      
      JLabel lblNewLabel_7 = new JLabel("<수량>");
      lblNewLabel_7.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
      lblNewLabel_7.setBounds(205, 10, 57, 15);
      panel_3.add(lblNewLabel_7);
      
      JLabel lblNewLabel_8 = new JLabel("<가격>");
      lblNewLabel_8.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
      lblNewLabel_8.setBounds(283, 10, 57, 15);
      panel_3.add(lblNewLabel_8);
      
      JPanel panel_4 = new JPanel();
      panel_4.setBackground(Color.WHITE);
      panel_4.setBounds(263, 267, 159, 50);
      frame.getContentPane().add(panel_4);
      panel_4.setLayout(null);
      
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(12, 154, 410, 105);
         frame.getContentPane().add(scrollPane);
      
       JPanel borderlaoutpanel = new JPanel();
       borderlaoutpanel.setBackground(Color.WHITE);
       scrollPane.setViewportView(borderlaoutpanel);
       borderlaoutpanel.setLayout(new BorderLayout(0, 0));
      
       JPanel columnpanel = new JPanel();
        borderlaoutpanel.add(columnpanel, BorderLayout.CENTER);
        columnpanel.setLayout(new GridLayout(0,1));
        columnpanel.setBackground(Color.WHITE);
      
      
      //12, 154, 410, 105
      
      
        query = String.format("select cart_temperature, menu_name, cart_number , cart_value  from cart where order_number = %d", order_number);
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        rs = stmt.executeQuery(query);
        rs.last();
        int row_count = rs.getRow();
        rs.beforeFirst();
        if (row_count == 0) {
        	btnNewButton.setVisible(false);
        } else {
        	btnNewButton.setVisible(true);
        }
        
        for(int i=0;i<row_count;i++) {
            rs.next();
             JPanel rowPanel = new JPanel();
             rowPanel.setPreferredSize(new Dimension(380,45));
             columnpanel.add(rowPanel);
             rowPanel.setLayout(null);
             if(rs.getInt(1) == 1) {
                temp = "HOT";
             } else if(rs.getInt(1) == 2) {
                temp = "ICE";
             } else {
            	 temp = "디저트";
             }
          JLabel j1 = new JLabel(String.format("<%s>", temp)); 
          j1.setBounds(12, 10, 57, 15);
          j1.setHorizontalAlignment(SwingConstants.CENTER);
          rowPanel.add(j1);
          
          JLabel j2 = new JLabel(String.format("%s", rs.getString(2)));
          j2.setHorizontalAlignment(SwingConstants.CENTER);
          j2.setBounds(104, 10, 65, 15);
          j1.setHorizontalAlignment(SwingConstants.CENTER);
          rowPanel.add(j2);
          
          JLabel j3 = new JLabel(String.format("%d개", rs.getInt(3)));
          j3.setHorizontalAlignment(SwingConstants.CENTER);
          j3.setBounds(214, 10, 57, 15);
          j1.setHorizontalAlignment(SwingConstants.CENTER);
          rowPanel.add(j3);
          
          JLabel j4 = new JLabel(String.format("%d원", rs.getInt(3) * rs.getInt(4)));
          j4.setHorizontalAlignment(SwingConstants.CENTER);
          j4.setBounds(293, 10, 57, 15);
          j1.setHorizontalAlignment(SwingConstants.CENTER);
          rowPanel.add(j4);
          order_total_value += rs.getInt(3) * rs.getInt(4);
          
          btnNewButton_1.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				query = String.format ("DELETE FROM cart WHERE order_number = %d", order_number);
  				rowPanel.removeAll();
  				btnNewButton.setVisible(false);
  				order_total_value = 0;
  				lblNewLabel_9.setText(String.format("Total price:       %s 원", order_total_value));
  				rowPanel.revalidate();
  				rowPanel.repaint();
        	    try {
  					stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
  					int rs = stmt.executeUpdate(query);
  				} catch (SQLException e1) {
  					e1.printStackTrace();
  				}
  			}
  		});
    }      
        

      lblNewLabel_9.setText(String.format("Total price:       %s 원", order_total_value));
      lblNewLabel_9.setFont(new Font("함초롬돋움", Font.ITALIC, 12));
      lblNewLabel_9.setBounds(0, 25, 147, 15);
      panel_4.add(lblNewLabel_9);

   }
    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == btnNewButton) {
            frame.dispose();
            new payment_select();
         } 
    }
}

   

