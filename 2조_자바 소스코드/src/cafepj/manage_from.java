package cafepj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;



class DBConnection{
	Connection con;
	Statement stmt;
	ResultSet rs;
	
	public DBConnection()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafe?serverTimezone=UTC","root","root");
			stmt = con.createStatement();
		}
		
		catch(Exception e)
		{
			System.out.println("데이터베이스 연결 오류 : "+ e.getMessage());
		}
	}
}


public class manage_from {

	static String daily_sum;
	static String monthly_sum;
	public JFrame manage_Frame;
	private JTextField status_text;
	public String status;
	Map<String, Integer> data = new HashMap<>();
	String type[] = {"ade","coffee","dessert","tea"}; 
	ArrayList<String> list = new ArrayList<String>();
	pj_calendar pjc = new pj_calendar();
	String startdate = pjc.getSTdate();
	String enddate = pjc.getEDdate();
	String start_date_field, end_date_field;
	String place_name = place_admin.getmap();
	int tempsum = 0;
	
	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		new manage_from();

	}
	
	/**
	 * Create the application.
	 */
	public manage_from() {
		try {
			initialize();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void gap_calc(String start, String end) {
		String sql_gap = String.format("select menu.menu_type,count(menu.menu_type) from menu, orders, cart where orders.order_number = cart.order_number and cart.menu_name = menu.menu_name and orders.order_date between '%s' and '%s' and orders.place_name ='%s' group by menu.menu_type;",start,end,place_name);
		for(int i = 0 ; i < type.length ; i ++ ) {
			data.put(type[i],0);
		}
		
		DBConnection dbc = new DBConnection();
		try {
			dbc.rs = dbc.stmt.executeQuery(sql_gap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		try {
			for(String t : type) {
		         list.add(t);
		    }
			while(dbc.rs.next()){ // rs.next() 메소드는 다음행이 없는 경우 false리턴
				String id = dbc.rs.getString(1); 
				int count = dbc.rs.getInt(2); 
				System.out.println(id + " " + count);
				data.put(id, count);
				list.remove(dbc.rs.getString(1)); 
			}
			for(String t : list) {
		         data.put(t, 0);
		      }
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		
		pjc.setVisible(false);
		
		System.out.print(startdate + " - " + enddate);
		DBConnection dbc = new DBConnection();
		LocalDate CT = LocalDate.now();
		System.out.println("\n" + getmaps() + "123");
		String sql = String.format("SELECT sum(order_value) FROM orders where order_date = '%s' and place_name = '%s';",CT.toString(),getmaps());
		System.out.println(sql);
		dbc.rs = dbc.stmt.executeQuery(sql);
		dbc.rs.next();
		tempsum = dbc.rs.getInt(1);
		daily_sum = Integer.toString(tempsum);
		System.out.println("당일매출 : " + daily_sum);
		
		String temp_month = CT.getYear()+"-"+CT.getMonthValue();
		String start_m = temp_month + "-" + 1;
		String end_m = temp_month +"-"+CT.lengthOfMonth();
		sql = String.format("SELECT order_value FROM orders where place_name = '%s' and order_date between '%s' and '%s';",getmaps(),start_m,end_m);
		dbc.rs = dbc.stmt.executeQuery(sql);
		tempsum = 0;
		while(dbc.rs.next()) {
			System.out.println(dbc.rs.getInt("order_value"));
			tempsum += dbc.rs.getInt("order_value");
		}
		monthly_sum = Integer.toString(tempsum);
		System.out.println("월매출 : " + monthly_sum);
		
		
		chart_panel cp = new chart_panel();
		cp.setBackground(new Color(240, 255, 255));
		
		manage_Frame = new JFrame();
		manage_Frame.setBackground(new Color(254, 255, 255));
		manage_Frame.setTitle("MANAGE");
		manage_Frame.setResizable(false);
		manage_Frame.setBounds(100, 100, 450, 350);
		manage_Frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("img/투썸로고.PNG")));
		manage_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel default_panel = new JPanel();
		manage_Frame.getContentPane().add(default_panel, BorderLayout.NORTH);
		default_panel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel info_panel = new JPanel();
		default_panel.add(info_panel);
		info_panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel adminPanel = new JPanel();
		adminPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		adminPanel.setBackground(Color.WHITE);
		info_panel.add(adminPanel);
		
		JLabel admin_label = new JLabel("<ADMIN>");
		adminPanel.add(admin_label);
		admin_label.setHorizontalAlignment(SwingConstants.CENTER);
		admin_label.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 16));
		
		status_text = new JTextField();
		status_text.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
		adminPanel.add(status_text);
		status_text.setEditable(false);
		status_text.setHorizontalAlignment(SwingConstants.CENTER);
		status_text.setColumns(10);
		
		JPanel locationPanel = new JPanel();
		locationPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		locationPanel.setBackground(Color.WHITE);
		info_panel.add(locationPanel);
		
		JLabel lblNewLabel = new JLabel(place_name);
		locationPanel.add(lblNewLabel);
		
		JPanel summary_price_panel = new JPanel();
		default_panel.add(summary_price_panel);
		summary_price_panel.setLayout(new GridLayout(0, 2, 0, 0));
		summary_price_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel daily_txt = new JLabel("당일 매출");
		daily_txt.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
		daily_txt.setBackground(Color.WHITE);
		daily_txt.setHorizontalAlignment(JLabel.CENTER);
		summary_price_panel.add(daily_txt);
		
		JTextField daily_area = new JTextField();
		daily_area.setBackground(Color.WHITE);
		daily_area.setEditable(false);
		daily_area.setText(manage_from.daily_sum+"원");
		summary_price_panel.add(daily_area);
		
		JLabel monthly_txt = new JLabel("당월 매출");
		monthly_txt.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
		monthly_txt.setBackground(Color.WHITE);
		monthly_txt.setHorizontalAlignment(JLabel.CENTER);
		summary_price_panel.add(monthly_txt);
		
		JTextField monthly_area = new JTextField();
		monthly_area.setBackground(Color.WHITE);
		monthly_area.setEditable(false);
		monthly_area.setText(manage_from.monthly_sum+"원");
		summary_price_panel.add(monthly_area);
		
		JPanel under_main = new JPanel();
		under_main.setBackground(Color.WHITE);
		manage_Frame.getContentPane().add(under_main, BorderLayout.CENTER);
		under_main.setLayout(null);
		
		JPanel button_panel = new JPanel();
		button_panel.setBounds(10, 6, 214, 53);
		under_main.add(button_panel);
		button_panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton open_B = new JButton("open");
		open_B.setForeground(Color.WHITE);
		open_B.setBackground(Color.DARK_GRAY);
		open_B.setFont(new Font("Arial", Font.BOLD, 12));
		open_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status_text.setText("OPEN");
				status = "OPEN";
				String sql = String.format("update place set place_status = '%s' where place_name ='%s';", status, place_name);
				try {
					int r = dbc.stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_panel.add(open_B);
		
		JButton close_B = new JButton("close");
		close_B.setForeground(Color.WHITE);
		close_B.setBackground(Color.DARK_GRAY);
		close_B.setFont(new Font("Arial", Font.BOLD, 12));
		close_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status_text.setText("CLOSE");
				status = "CLOSE";
				String sql = String.format("update place set place_status = '%s' where place_name ='%s';", status, place_name);
				try {
					int r = dbc.stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_panel.add(close_B);
		
		JButton rest_B = new JButton("rest");
		rest_B.setForeground(Color.WHITE);
		rest_B.setBackground(Color.DARK_GRAY);
		rest_B.setFont(new Font("Arial", Font.BOLD, 12));
		rest_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status_text.setText("REST");
				status = "REST";
				String sql = String.format("update place set place_status = '%s' where place_name ='%s';", status, place_name);
				try {
					int r = dbc.stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		button_panel.add(rest_B);
		
		
		
		cp.setBorder(new LineBorder(new Color(0, 0, 0)));
		cp.setBounds(16, 97, 320, 110);
		under_main.add(cp);
		cp.setLayout(null);
		
		JButton edit_button = new JButton("edit");
		edit_button.setForeground(Color.WHITE);
		edit_button.setBackground(new Color(178, 34, 34));
		edit_button.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
		edit_button.setBounds(348, 107, 84, 40);
		edit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new date1();
				
			}
		});
		under_main.add(edit_button);
		
		JButton back_B = new JButton("back");
		back_B.setBackground(Color.DARK_GRAY);
		back_B.setForeground(Color.WHITE);
		back_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login();
				manage_Frame.dispose();
			}
		});
		back_B.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
		back_B.setBounds(348, 167, 84, 40);
		under_main.add(back_B);
		
		JLabel coffee_label = new JLabel("디저트");
		coffee_label.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
		coffee_label.setHorizontalAlignment(SwingConstants.CENTER);
		coffee_label.setBounds(250, 215, 61, 16);
		under_main.add(coffee_label);
		
		JLabel tea_label = new JLabel("차");
		tea_label.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
		tea_label.setHorizontalAlignment(SwingConstants.CENTER);
		tea_label.setBounds(40, 215, 61, 16);
		under_main.add(tea_label);
		
		JLabel ade_label = new JLabel("에이드");
		ade_label.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
		ade_label.setHorizontalAlignment(SwingConstants.CENTER);
		ade_label.setBounds(110, 215, 61, 16);
		under_main.add(ade_label);
		
		JLabel dessert_label = new JLabel("커피");
		dessert_label.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
		dessert_label.setHorizontalAlignment(SwingConstants.CENTER);
		dessert_label.setBounds(180, 215, 60, 16);
		under_main.add(dessert_label);
		
		JButton cofirm_bt = new JButton("확인");
		cofirm_bt.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
		cofirm_bt.setBackground(Color.DARK_GRAY);
		cofirm_bt.setForeground(Color.WHITE);
		cofirm_bt.setBounds(346, 62, 84, 29);
		under_main.add(cofirm_bt);
		
		JButton select_date = new JButton("날짜 선택");
		select_date.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
		select_date.setForeground(Color.WHITE);
		select_date.setBackground(Color.DARK_GRAY);
		select_date.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				manage_Frame.dispose();
				pj_calendar cbar = new pj_calendar();
			}
		});
		select_date.setBounds(236, 62, 100, 29);
		under_main.add(select_date);
		
		JLabel start_label = new JLabel(startdate);
		start_label.setBounds(20, 71, 100, 16);
		under_main.add(start_label);
		
		JLabel end_label = new JLabel(enddate);
		end_label.setBounds(155, 71, 100, 16);
		under_main.add(end_label);
		
		JButton place_choose = new JButton("지점 선택");
		place_choose.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
		place_choose.setBackground(new Color(178, 34, 34));
		place_choose.setForeground(Color.WHITE);
		place_choose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new place_admin();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		place_choose.setBounds(284, 21, 117, 29);
		under_main.add(place_choose);
		cofirm_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				manage_Frame.revalidate();
				if(startdate!=null && enddate!=null) {
					gap_calc(startdate, enddate);
					cp.h1 = data.get("tea");
					cp.h2 = data.get("ade");
					cp.h3 = data.get("coffee");
					cp.h4 = data.get("dessert");
					System.out.println(cp.h1+" "+cp.h2+" "+cp.h3+" "+cp.h4);
					cp.rerepaint();
					manage_Frame.revalidate();
				}else {
					new errorPage();
				}
			}
		});

		manage_Frame.setVisible(true);
		

	}
	public String getmaps() {
		return place_name;
	}
	
}
