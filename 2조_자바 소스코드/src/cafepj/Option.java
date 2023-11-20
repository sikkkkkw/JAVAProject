package cafepj;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class Option extends JFrame implements ActionListener {
   Statement stmt;
    ResultSet rs;
    String query;
   Connection con = new DB_connect().makeConnection();
   String menu = menu_select.getmenu();
   int order_number = place_select.getorder_number();
   
   int tem = 1;
   int size = 1;
   int syrup = 0;
   int shot = 0;
   int num;
   int t_value;
   int number;
   int count = 1;
   
   int rst;
   
   
   private JFrame frame;

   public static void main(String[] args) throws SQLException{
      new Option();
   }

   
   public Option()  throws SQLException {
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    * @throws SQLException 
    */
   private void initialize() throws SQLException {
      frame = new JFrame();
      frame.getContentPane().setBackground(Color.WHITE);
	  frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("img/투썸로고.PNG")));
      frame.setTitle("추가 옵션");
      frame.setBounds(100, 100, 420, 315);
      frame.setLocationRelativeTo(null);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      
      JPanel panel = new JPanel();
      panel.setBackground(Color.WHITE);
      panel.setBorder(new LineBorder(new Color(220, 20, 60), 2, true));
      panel.setBounds(55, 19, 284, 41);
      frame.getContentPane().add(panel);
      panel.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("<HOT / ICE>");
      lblNewLabel.setBounds(12, 11, 79, 18);
      lblNewLabel.setFont(new Font("Arial Black", Font.ITALIC, 12));
      panel.add(lblNewLabel);
      
      JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("ICE");
      rdbtnNewRadioButton_1.setBackground(Color.WHITE);
      rdbtnNewRadioButton_1.setBounds(189, 10, 58, 23);
      panel.add(rdbtnNewRadioButton_1);
      
      JPanel panel_1 = new JPanel();
      panel_1.setBackground(Color.WHITE);
      panel_1.setBorder(new LineBorder(new Color(220, 20, 60), 2, true));
      panel_1.setBounds(55, 70, 284, 41);
      frame.getContentPane().add(panel_1);
      panel_1.setLayout(null);
      
      JLabel lblNewLabel_1 = new JLabel("<샷 추가>");
      lblNewLabel_1.setBounds(12, 11, 54, 16);
      lblNewLabel_1.setFont(new Font("함초롬돋움", Font.BOLD, 12));
      panel_1.add(lblNewLabel_1);
      
      JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("없음");
      rdbtnNewRadioButton_2.setSelected(true);
      rdbtnNewRadioButton_2.setBackground(Color.WHITE);
      rdbtnNewRadioButton_2.setBounds(74, 9, 58, 23);
      panel_1.add(rdbtnNewRadioButton_2);
      
      JRadioButton rdbtnNewRadioButton_2_1 = new JRadioButton("1회");
      rdbtnNewRadioButton_2_1.setBackground(Color.WHITE);
      rdbtnNewRadioButton_2_1.setBounds(136, 9, 58, 23);
      panel_1.add(rdbtnNewRadioButton_2_1);
      
      JRadioButton rdbtnNewRadioButton_2_1_1 = new JRadioButton("2회");
      rdbtnNewRadioButton_2_1_1.setBackground(Color.WHITE);
      rdbtnNewRadioButton_2_1_1.setBounds(198, 9, 58, 23);
      panel_1.add(rdbtnNewRadioButton_2_1_1);
      
      JPanel panel_2 = new JPanel();
      panel_2.setBackground(Color.WHITE);
      panel_2.setBorder(new LineBorder(new Color(220, 20, 60), 2, true));
      panel_2.setBounds(55, 172, 284, 41);
      frame.getContentPane().add(panel_2);
      panel_2.setLayout(null);
      
      JLabel lblNewLabel_2 = new JLabel("< SIZE >");
      lblNewLabel_2.setBounds(11, 11, 58, 18);
      lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 12));
      panel_2.add(lblNewLabel_2);
      
      JRadioButton rdbtnNewRadioButton_2_2_2 = new JRadioButton("Tall");
      rdbtnNewRadioButton_2_2_2.setSelected(true);
      rdbtnNewRadioButton_2_2_2.setBackground(Color.WHITE);
      rdbtnNewRadioButton_2_2_2.setBounds(77, 10, 58, 23);
      panel_2.add(rdbtnNewRadioButton_2_2_2);
      
      JRadioButton rdbtnNewRadioButton_2_2_2_1 = new JRadioButton("Grande");
      rdbtnNewRadioButton_2_2_2_1.setBackground(Color.WHITE);
      rdbtnNewRadioButton_2_2_2_1.setBounds(137, 10, 75, 23);
      panel_2.add(rdbtnNewRadioButton_2_2_2_1);
      
      JRadioButton rdbtnNewRadioButton_2_2_2_1_1 = new JRadioButton("Venti");
      rdbtnNewRadioButton_2_2_2_1_1.setBackground(Color.WHITE);
      rdbtnNewRadioButton_2_2_2_1_1.setBounds(218, 10, 58, 23);
      panel_2.add(rdbtnNewRadioButton_2_2_2_1_1);
      
      JPanel panel_2_1 = new JPanel();
      panel_2_1.setBorder(new LineBorder(new Color(220, 20, 60), 2, true));
      panel_2_1.setBackground(Color.WHITE);
      panel_2_1.setBounds(55, 121, 284, 41);
      frame.getContentPane().add(panel_2_1);
      panel_2_1.setLayout(null);
      
      JLabel lblNewLabel_2_1 = new JLabel("< Syrup >");
      lblNewLabel_2_1.setBounds(8, 11, 64, 18);
      lblNewLabel_2_1.setFont(new Font("Arial Black", Font.BOLD, 12));
      panel_2_1.add(lblNewLabel_2_1);
      
      JRadioButton rdbtnNewRadioButton_2_2 = new JRadioButton("Syrup");
      rdbtnNewRadioButton_2_2.setBackground(Color.WHITE);
      rdbtnNewRadioButton_2_2.setBounds(130, 10, 58, 23);
      panel_2_1.add(rdbtnNewRadioButton_2_2);
      
      JRadioButton rdbtnNewRadioButton_2_2_1 = new JRadioButton("Hazelnut");
      rdbtnNewRadioButton_2_2_1.setBackground(Color.WHITE);
      rdbtnNewRadioButton_2_2_1.setBounds(192, 10, 84, 23);
      panel_2_1.add(rdbtnNewRadioButton_2_2_1);
      
      JPanel panel_3 = new JPanel();
      panel_3.setBackground(Color.WHITE);
      panel_3.setBounds(169, 223, 170, 32);
      frame.getContentPane().add(panel_3);
      panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      
      JButton btnNewButton = new JButton("뒤로가기");
      btnNewButton.setBackground(Color.DARK_GRAY);
      btnNewButton.setForeground(Color.WHITE);
      btnNewButton.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
      btnNewButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  frame.dispose();
          }
        });
      panel_3.add(btnNewButton);
      
      JButton btnNewButton_1 = new JButton("완료");
      btnNewButton_1.setBackground(new Color(220, 20, 60));
      btnNewButton_1.setForeground(Color.WHITE);
      btnNewButton_1.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
      btnNewButton_1.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  try {
        		  query = String.format("select menu_value from menu where menu_name = '%s'", menu);
        	      stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        	      rs = stmt.executeQuery(query);
        	      rs.next();
        	      
     	         t_value = rs.getInt("menu_value");
     	         if (shot == 2) {
     	            t_value += 300;
     	         }
     	         else if (shot == 3) {
     	            t_value += 600;
     	         }
     	         if (size == 2) {
     	            t_value += 500;
     	         }
     	         else if (size == 3) {
     	            t_value += 1000;
     	         }
     	         setvalue(t_value);
        	      } catch (SQLException e1) {
        	         e1.getStackTrace();
        	      }
              try {
              	query = String.format("insert into cart(cart_value, cart_temperature, cart_syrup ,cart_size, cart_shot, cart_number, menu_name, order_number) values(%d,%d,%d,%d,%d,%d,'%s',%d)", getvalue(), tem, syrup ,size, shot, getcount(), menu, order_number);
  				stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
  				rst = stmt.executeUpdate(query);
  				frame.dispose();
  			} catch (SQLException e1) {
  				// TODO Auto-generated catch block
  				e1.printStackTrace();
  			}
           }
        });
      panel_3.add(btnNewButton_1);
      
      JPanel panel_4 = new JPanel();
      panel_4.setBackground(Color.WHITE);
      panel_4.setBounds(12, 223, 122, 43);
      frame.getContentPane().add(panel_4);
      panel_4.setLayout(null);
      
      JButton minus = new JButton("-");
      minus.setBounds(0, 0, 47, 43);
      minus.setFont(new Font("Arial", Font.PLAIN, 25));
      minus.addActionListener(this);
      panel_4.add(minus);
      
      JButton plus = new JButton("+");
      plus.setBounds(75, 0, 47, 43);
      plus.setFont(new Font("Arial", Font.PLAIN, 23));
      plus.addActionListener(this);
      panel_4.add(plus);
      
      JLabel lblNewLabel_3 = new JLabel("1");
      lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 12));
      lblNewLabel_3.setBounds(48, 13, 25, 15);
      panel_4.add(lblNewLabel_3);
      
      JRadioButton rdbtnNewRadioButton = new JRadioButton("HOT");
      rdbtnNewRadioButton.setSelected(true);
      rdbtnNewRadioButton.setBackground(Color.WHITE);
      rdbtnNewRadioButton.setBounds(127, 10, 58, 23);
      panel.add(rdbtnNewRadioButton);
      
      JRadioButton rdbtnNewRadioButton_2_3 = new JRadioButton("없음");
      rdbtnNewRadioButton_2_3.setSelected(true);
      rdbtnNewRadioButton_2_3.setBackground(Color.WHITE);
      rdbtnNewRadioButton_2_3.setBounds(76, 10, 58, 23);
      panel_2_1.add(rdbtnNewRadioButton_2_3);
      
      ButtonGroup t = new ButtonGroup();
      t.add(rdbtnNewRadioButton);
      t.add(rdbtnNewRadioButton_1);
      rdbtnNewRadioButton.addActionListener(this);
      
      ButtonGroup t1 = new ButtonGroup();
      t1.add(rdbtnNewRadioButton_2);
      t1.add(rdbtnNewRadioButton_2_1);
      t1.add(rdbtnNewRadioButton_2_1_1);
      
      ButtonGroup t2 = new ButtonGroup();
      t2.add(rdbtnNewRadioButton_2_2);
      t2.add(rdbtnNewRadioButton_2_2_1);
      t2.add(rdbtnNewRadioButton_2_3);
      
      ButtonGroup t3 = new ButtonGroup();
      t3.add(rdbtnNewRadioButton_2_2_2);
      t3.add(rdbtnNewRadioButton_2_2_2_1); 
      t3.add(rdbtnNewRadioButton_2_2_2_1_1);
      frame.setVisible(true);
      
      minus.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            count = Integer.parseInt(lblNewLabel_3.getText());
            count-=1;
            if(count <= 0) {
            	count = 1;
            }
            setcart_number(count);
            lblNewLabel_3.setText(String.valueOf(count));
         }
      });
      
      plus.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            count = Integer.parseInt(lblNewLabel_3.getText());
            count+=1;
            setcart_number(count);
            lblNewLabel_3.setText(String.valueOf(count));
         }
      });
      rdbtnNewRadioButton.addActionListener(new ActionListener( ) {
         public void actionPerformed(ActionEvent e) {
            tem = 1;
         }
      });
      rdbtnNewRadioButton_1.addActionListener(new ActionListener( ) {
         public void actionPerformed(ActionEvent e) {
            tem = 2;
         }
      });
      rdbtnNewRadioButton_2.addActionListener(new ActionListener( ) {
         public void actionPerformed(ActionEvent e) {
            shot = 1;
         }
      });
      rdbtnNewRadioButton_2_1.addActionListener(new ActionListener( ) {
         public void actionPerformed(ActionEvent e) {
            shot = 2;
         }
      });
      rdbtnNewRadioButton_2_1_1.addActionListener(new ActionListener( ) {
         public void actionPerformed(ActionEvent e) {
            shot = 3;
         }
      });
      rdbtnNewRadioButton_2_2.addActionListener(new ActionListener( ) {
         public void actionPerformed(ActionEvent e) {
            syrup = 1;
         }
      });
      rdbtnNewRadioButton_2_2_1.addActionListener(new ActionListener( ) {
         public void actionPerformed(ActionEvent e) {
            syrup = 2;
         }
      });
      rdbtnNewRadioButton_2_3.addActionListener(new ActionListener( ) {
         public void actionPerformed(ActionEvent e) {
            syrup = 3;
         }
      });
      rdbtnNewRadioButton_2_2_2.addActionListener(new ActionListener( ) {
         public void actionPerformed(ActionEvent e) {
            size = 1;
         }
      });
      rdbtnNewRadioButton_2_2_2_1.addActionListener(new ActionListener( ) {
         public void actionPerformed(ActionEvent e) {
            size = 2;
         }
      });
      rdbtnNewRadioButton_2_2_2_1_1.addActionListener(new ActionListener( ) {
         public void actionPerformed(ActionEvent e) {
            size = 3;
         }
      });
   }
   public void setcart_number(int num) {
	   this.count = num;
   }
   public int getcount() {
	   return count;
   }
   public void setvalue(int num) {
	   this.t_value = num;
   }
   public int getvalue() {
	   return t_value;
   }

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
}