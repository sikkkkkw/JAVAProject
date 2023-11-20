package cafepj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class menu_select extends JFrame {
	
	Connection con = new DB_connect().makeConnection();
	String query;
	String menu_name = null; 
	String menu_type = "coffee";
	Statement stmt;
	ResultSet rs;
	int type_count = 0;
	int menu_value = 0;
	int order_number = place_select.getorder_number();
	place_select mapchose = new place_select();
	String locate = mapchose.getmap();
	static String map = null;
	
	int type_n = 0;
	
	int ins;
	static String menu;
	
	JPanel rowPanel = new JPanel();
    JPanel columnpanel = new JPanel();
	
	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws Exception {
		new menu_select();
	}

	/**
	 * Create the application.
	 */	
	
	public void type_db() throws Exception{
		try {
			query = String.format("select menu_name from menu where menu_type = '%s'", menu_type);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(query);
	        rs.last();
	        type_count = rs.getRow();
	        rs.beforeFirst();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void del_db() throws Exception{
		try {
			query = String.format("DELETE FROM orders WHERE order_number = %s", order_number);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ins = stmt.executeUpdate(query);
	        rs.last();
	        type_count = rs.getRow();
	        rs.beforeFirst();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public menu_select() throws Exception {
		mapchose.setVisible(false);
		setTitle("메뉴");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("img/투썸로고.PNG")));
		setBounds(100, 100, 450, 450);
		setLocationRelativeTo(null);
        setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 434, 411);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(12, 10, 410, 57);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("장바구니");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
					new Shopping();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(new Color(178, 34, 34));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
		btnNewButton.setBounds(294, 10, 104, 23);
		
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel(locate);
		lblNewLabel.setFont(new Font("HY견고딕", Font.ITALIC, 12));
		lblNewLabel.setBounds(126, 10, 158, 15);
		panel_1.add(lblNewLabel);
		
		
		
		
		JLabel lblNewLabel_1 = new JLabel();
		String now_user = Login.getid();
		query = String.format(("select user_name from user where user_id = '%s'"), now_user);
	      stmt = con.createStatement();
	      rs = stmt.executeQuery(query);
	      try {
	    	  rs.next();
	    	  lblNewLabel_1.setText(rs.getString(1) + "님 반가워요");
	      } catch (Exception e) {
	    	  e.getStackTrace();
	      }
		lblNewLabel_1.setFont(new Font("HY견고딕", Font.ITALIC, 12));
		lblNewLabel_1.setBounds(138, 32, 123, 15);
		panel_1.add(lblNewLabel_1);
		
		JButton btnBack = new JButton("뒤로가기 ");
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
		btnBack.setBackground(Color.DARK_GRAY);
		btnBack.setBounds(12, 11, 104, 23);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					del_db();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				dispose();
				try {
					new place_select();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}});
		panel_1.add(btnBack);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 67, 114, 334);
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(128, 67, 294, 334);
		panel.add(scrollPane);
		
		JPanel borderlaoutpanel = new JPanel();
		borderlaoutpanel.setBackground(Color.WHITE);
        scrollPane.setViewportView(borderlaoutpanel);
        borderlaoutpanel.setLayout(new BorderLayout(0, 0));

        borderlaoutpanel.add(columnpanel, BorderLayout.CENTER);
        columnpanel.setLayout(new GridLayout(0,1,1,1));
        columnpanel.setBackground(Color.WHITE);
		
		JButton btnNewButton_2 = new JButton("Coffee");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_type = "coffee";
				type_n = 0;
				setmenu();
			}
		});
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(new Color(178, 34, 34));
		btnNewButton_2.setFont(new Font("Arial Black", Font.ITALIC, 12));
		panel_2.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Ade");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_type = "ade";
				type_n = 0;
				setmenu();
			}
			});
		btnNewButton_3.setBackground(Color.DARK_GRAY);
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setFont(new Font("Arial Black", Font.ITALIC, 12));
		panel_2.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Tea");
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_type = "tea";
				type_n = 0;
				setmenu();
			}
		});
		btnNewButton_4.setBackground(new Color(178, 34, 34));
		btnNewButton_4.setFont(new Font("Arial Black", Font.ITALIC, 12));
		panel_2.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("dessert");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_type = "dessert";
				type_n = 1;
				setmenu();
			}
		});
		btnNewButton_5.setBackground(Color.DARK_GRAY);
		btnNewButton_5.setForeground(Color.WHITE);
		btnNewButton_5.setFont(new Font("Arial Black", Font.ITALIC, 12));
		panel_2.add(btnNewButton_5);
        
        setmenu();
		setVisible(true);
	}
	
	public void setmenu() {
		try {
			type_db();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		columnpanel.removeAll();
		try {
			query = String.format("select menu_name, menu_value from menu where menu_type = '%s'", menu_type);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        for(int i = 0; i < type_count; i ++) {
        	try {
				rs.next();
				rowPanel = new JPanel();
	            rowPanel.setPreferredSize(new Dimension(275,80));
	            rowPanel.setBackground(Color.white);
	            rowPanel.setBorder(new LineBorder(Color.BLACK));
	            columnpanel.add(rowPanel);
	            rowPanel.setLayout(null);
	            
	            JLabel lblNewLabel_2 = new JLabel();
	            lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
	            lblNewLabel_2.setBounds(0, 0, 80, 80);
		        lblNewLabel_2.setIcon(new ImageIcon(getClass().getClassLoader().getResource(String.format("menu/%s.gif", rs.getString(1)))));


	            rowPanel.add(lblNewLabel_2);
	           
	            
	            JLabel lblNewLabel_3_1 = new JLabel(String.format("%d 원", rs.getInt(2)));
	            lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.LEFT);
	            lblNewLabel_3_1.setBounds(92, 45, 75, 30);
	            rowPanel.add(lblNewLabel_3_1);
	            
	            JButton btnNewButton_1 = new JButton(rs.getString(1));
	            btnNewButton_1.setBounds(92, 10, 140, 30);
	            btnNewButton_1.setBackground(new Color(178, 34, 34));
	            btnNewButton_1.setForeground(Color.white);
	            btnNewButton_1.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
	            btnNewButton_1.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				setmenu(e.getActionCommand());
	    				if (type_n == 0) {
	    					try {
								new Option();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	    				} else {
	    					try {
	    						query = String.format("select menu_value from menu where menu_name = '%s'", getmenu());
	    						stmt = con.createStatement();
	    						rs = stmt.executeQuery(query);
	    						rs.next();
	    						int dessert_v = rs.getInt(1);
	    						
	    		              	query = String.format("insert into cart(cart_value, cart_temperature, cart_syrup ,cart_size, cart_shot, cart_number, menu_name, order_number) values(%d,null,null,null,null,1,'%s',%d)", dessert_v, menu, order_number);
	    		  				stmt = con.createStatement();
	    		  				ins = stmt.executeUpdate(query);
	    		  			} catch (SQLException e1) {
	    		  				// TODO Auto-generated catch block
	    		  				e1.printStackTrace();
	    		  			}
	    				}
	    			}
	    		});
	       
	            rowPanel.add(btnNewButton_1);
	            revalidate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
	}
	public void setmenu(String menu) {
		this.menu = menu;
	}
	public static String getmenu() {
		return menu;
	}
	
	public void setmap(String map) { //옵션 선택에서 맵이름 호출을 위해 맵이름 설정
		this.map = map;
	}
	public static String getmap() { //옵션 선택에서 맵이름 불러 DB에 넣기 위함
		return map;
	}
	
}
