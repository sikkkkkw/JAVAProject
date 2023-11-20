package cafepj;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class place_select extends JFrame  { 
	JPanel panel;
	JLabel lblNewLabel_1;
	Connection con = new DB_connect().makeConnection();
	String query;
	Statement stmt;
	ResultSet rs;
	List<String> maplist = null;
	static String map;
	ImageIcon icon;
	Object locate = "지점 선택";
	static int order_number;
	
	Date now = new Date();
	SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = today.format(now);
	int ins;
	String now_user = Login.getid();
	
	public void set_db() throws Exception{
		try {
			query = String.format("INSERT INTO orders values (%d, null, '%s', '%s', '%s')", getorder_number(), nowTime, now_user, locate);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ins = stmt.executeUpdate(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void  get_order_number() throws Exception{
		try {
			query = String.format("select order_number from orders ORDER BY order_number ASC");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(query);
			rs.last();
			setorder_number(rs.getInt(1) + 1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void maplist() throws Exception{
		query = "SELECT place_name FROM place where place_status ='OPEN'";
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rs = stmt.executeQuery(query);
		maplist = new ArrayList<String>();
		maplist.add((String) locate);
		while(rs.next()) {
			maplist.add(rs.getString(1));
		}
	}
	
      public place_select() throws Exception {
      	getContentPane().setBackground(Color.WHITE);
         setTitle("지점 선택");
         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("img/투썸로고.PNG")));
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(450, 500);
         setLocationRelativeTo(null);
         setResizable(false);
         getContentPane().setLayout(null);

         JLabel lblNewLabel = new JLabel("매장 선택");
         lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 30));
         lblNewLabel.setBounds(131, 17, 174, 81);
         getContentPane().add(lblNewLabel);
         
         JPanel panel_1 = new JPanel();
         panel_1.setBackground(Color.WHITE);
         panel_1.setBounds(51, 160, 330, 215);
         
         JLabel lblNewLabel_1 = new JLabel("");
         lblNewLabel_1.setBounds(0, 0, 330, 215);
         panel_1.add(lblNewLabel_1);
         getContentPane().add(panel_1);
         
         
         maplist();
         JComboBox<String> comboBox = new JComboBox<String>(maplist.toArray(new String[maplist.size()]));
         comboBox.setFont(new Font("함초롬돋움", Font.ITALIC, 12));
         comboBox.setBackground(Color.WHITE);
         comboBox.setBounds(51, 108, 333, 23);
         comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				locate = (String)comboBox.getSelectedItem();
				if (locate != "지점 선택") {
					lblNewLabel_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource(String.format("map/%s.jpg", locate))));
				} else {
					lblNewLabel_1.setIcon(new ImageIcon());
				}
				map = (String) locate;
		}});
         getContentPane().add(comboBox);
         
         JButton btnNewButton = new JButton("회원 정보");
         btnNewButton.setBackground(Color.DARK_GRAY);
         btnNewButton.setForeground(Color.WHITE);
         btnNewButton.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
         btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new pj_member();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				setVisible(false);
			}
         });
         btnNewButton.setBounds(27, 402, 123, 23);
         getContentPane().add(btnNewButton);

         
         JButton btnNewButton_1 = new JButton("뒤로 가기");
         btnNewButton_1.setBackground(Color.DARK_GRAY);
         btnNewButton_1.setForeground(Color.WHITE);
         btnNewButton_1.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
         btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	new Login();
            	setVisible(false);
            }
         });
         btnNewButton_1.setBounds(200, 402, 97, 23);
         getContentPane().add(btnNewButton_1);
         
         JButton btnNewButton_2 = new JButton("지점 선택");
         btnNewButton_2.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
         btnNewButton_2.setForeground(Color.WHITE);
         btnNewButton_2.setBounds(321, 402, 95, 23);
         getContentPane().add(btnNewButton_2);
         btnNewButton_2.setBackground(new Color(220, 20, 60));
         btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(locate == maplist.get(0)) {
					System.out.println("지점 선택하세요"); //오류창으로 변경가능
				} else {
					try {
				    	get_order_number();
						set_db();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					dispose();
					try {
						new menu_select();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
								
		}});         
         setVisible(true);
         }
      
      public static void main(String[] args) throws Exception {
         place_select A = new place_select();
         }
      
      public static String getmap() {
  		return map;
  	}
      public void setorder_number(int order_number) {
  		this.order_number = order_number;
  	}
  	public static int getorder_number() {
  		return order_number;
  	}
  	
      }
