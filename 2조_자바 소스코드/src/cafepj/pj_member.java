package cafepj;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class pj_member extends JFrame {
	Statement stmt;
	ResultSet rs;
	String query;
	Connection con = new DB_connect().makeConnection();
	List<String> orderlist = null;
	String now_user = Login.getid();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) throws Exception {
		pj_member window = new pj_member();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	
	public pj_member() throws SQLException {

		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("img/투썸로고.PNG")));
		setTitle("회원");
		setBounds(100, 100, 332, 610);
		setLocationRelativeTo(null);
        setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/login.png")));
		lblNewLabel.setBounds(0, 0, 320, 110);
		getContentPane().add(lblNewLabel);
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel1.setBackground(new Color(164, 43, 63));
		panel1.setBounds(10, 130, 300, 142);
		getContentPane().add(panel1);
		panel1.setLayout(null);
		
		JPanel panel1_1 = new JPanel();
		panel1_1.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel1_1.setBackground(new Color(164, 43, 63));
		panel1_1.setBounds(0, 0, 300, 40);
		panel1.add(panel1_1);
		panel1_1.setLayout(null);
		
		JLabel lblNewLabel1_1_1 = new JLabel("개인정보");
		lblNewLabel1_1_1.setBounds(10, 3, 95, 30);
		lblNewLabel1_1_1.setForeground(Color.WHITE);
		lblNewLabel1_1_1.setFont(new Font("함초롬돋움", Font.BOLD, 17));
		panel1_1.add(lblNewLabel1_1_1);
		
		JButton btnNewButton1_1_1 = new JButton("수정");
		btnNewButton1_1_1.setBackground(Color.DARK_GRAY);
		btnNewButton1_1_1.setForeground(Color.WHITE);
		btnNewButton1_1_1.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
		btnNewButton1_1_1.setBounds(230, 8, 60, 23);
		panel1_1.add(btnNewButton1_1_1);
		
		JPanel panel1_2 = new JPanel();
		panel1_2.setBorder(new LineBorder(new Color(64, 64, 64), 0));
		panel1_2.setBackground(new Color(169, 41, 62));
		panel1_2.setBounds(2, 42, 296, 98);
		panel1.add(panel1_2);
		panel1_2.setLayout(null);
		
		JLabel lblNewLabel1_2_1 = new JLabel("이름:");
		lblNewLabel1_2_1.setForeground(Color.WHITE);
		lblNewLabel1_2_1.setFont(new Font("함초롬돋움", Font.BOLD, 15));
		lblNewLabel1_2_1.setBounds(18, 3, 50, 25);
		panel1_2.add(lblNewLabel1_2_1);
		
		JLabel lblNewLabel1_2_2 = new JLabel("생년월일:");
		lblNewLabel1_2_2.setForeground(Color.WHITE);
		lblNewLabel1_2_2.setFont(new Font("함초롬돋움", Font.BOLD, 15));
		lblNewLabel1_2_2.setBounds(18, 33, 70, 25);
		panel1_2.add(lblNewLabel1_2_2);
		
		JLabel lblNewLabel1_2_3 = new JLabel("전화번호:");
		lblNewLabel1_2_3.setForeground(Color.WHITE);
		lblNewLabel1_2_3.setFont(new Font("함초롬돋움", Font.BOLD, 15));
		lblNewLabel1_2_3.setBounds(18, 63, 70, 25);
		panel1_2.add(lblNewLabel1_2_3);
		
		JTextField lblNewLabel_name = new JTextField();
		lblNewLabel_name.setEditable(false);
		lblNewLabel_name.setHorizontalAlignment(JTextField.CENTER);
		lblNewLabel_name.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_name.setForeground(Color.BLACK);
		lblNewLabel_name.setFont(new Font("함초롬돋움", Font.BOLD, 15));
		lblNewLabel_name.setBounds(70, 3, 90, 25);
		panel1_2.add(lblNewLabel_name);
		
		JTextField lblNewLabel_birth = new JTextField();
		lblNewLabel_birth.setEditable(false);
		lblNewLabel_birth.setHorizontalAlignment(JTextField.CENTER);
		lblNewLabel_birth.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_birth.setForeground(Color.BLACK);
		lblNewLabel_birth.setFont(new Font("함초롬돋움", Font.BOLD, 15));
		lblNewLabel_birth.setBounds(95, 33, 150, 25);
		panel1_2.add(lblNewLabel_birth);
		
		JTextField lblNewLabel_phone = new JTextField();
		lblNewLabel_phone.setEditable(false);
		lblNewLabel_phone.setHorizontalAlignment(JTextField.CENTER);
		lblNewLabel_phone.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_phone.setForeground(Color.BLACK);
		lblNewLabel_phone.setFont(new Font("함초롬돋움", Font.BOLD, 15));
		lblNewLabel_phone.setBounds(95, 63, 150, 25);
		panel1_2.add(lblNewLabel_phone);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(164, 43, 63));
		panel2.setForeground(new Color(255, 255, 255));
		panel2.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel2.setBounds(10, 292, 300, 232);
		getContentPane().add(panel2);
		panel2.setLayout(null);
		
		JPanel panel2_1 = new JPanel();
		panel2_1.setBackground(new Color(164, 43, 63));
		panel2_1.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		panel2_1.setBounds(0, 0, 300, 40);
		panel2.add(panel2_1);
		panel2_1.setLayout(null);
		
		JLabel lblNewLabel2_1_1 = new JLabel("최근주문내역");
		lblNewLabel2_1_1.setBounds(10, 3, 100, 30);
		lblNewLabel2_1_1.setForeground(Color.WHITE);
		lblNewLabel2_1_1.setFont(new Font("함초롬돋움", Font.BOLD, 17));
		panel2_1.add(lblNewLabel2_1_1);
		
		JPanel panel2_2 = new JPanel();
		panel2_2.setBackground(Color.DARK_GRAY);
		panel2_2.setBounds(10, 50, 280, 30);
		panel2.add(panel2_2);
		panel2_2.setLayout(null);
		
		JLabel lblNewLabel2_2_1 = new JLabel("주문일");
		lblNewLabel2_2_1.setForeground(Color.WHITE);
		lblNewLabel2_2_1.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		lblNewLabel2_2_1.setBounds(15, 7, 50, 15);
		panel2_2.add(lblNewLabel2_2_1);
		
		JLabel lblNewLabel2_2_2 = new JLabel("주문 품목");
		lblNewLabel2_2_2.setForeground(Color.WHITE);
		lblNewLabel2_2_2.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		lblNewLabel2_2_2.setBounds(82, 7, 55, 15);
		panel2_2.add(lblNewLabel2_2_2);
		
		JLabel lblNewLabel2_2_3 = new JLabel("지점");
		lblNewLabel2_2_3.setForeground(Color.WHITE);
		lblNewLabel2_2_3.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		lblNewLabel2_2_3.setBounds(200, 7, 30, 15);
		panel2_2.add(lblNewLabel2_2_3);
		
		JPanel panel2_3 = new JPanel();
		panel2_3.setBackground(Color.LIGHT_GRAY);
		panel2_3.setBounds(10, 90, 280, 132);
		panel2.add(panel2_3);
		panel2_3.setLayout(null);
		
		JLabel orderlbl1_1 = new JLabel();
		orderlbl1_1.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		orderlbl1_1.setBounds(0, 0, 65, 26);
		panel2_3.add(orderlbl1_1);
		
		JLabel orderlbl2_1 = new JLabel();
		orderlbl2_1.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		orderlbl2_1.setBounds(0, 26, 65, 26);
		panel2_3.add(orderlbl2_1);
		
		JLabel orderlbl3_1 = new JLabel();
		orderlbl3_1.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		orderlbl3_1.setBounds(0, 52, 65, 26);
		panel2_3.add(orderlbl3_1);
		
		JLabel orderlbl4_1 = new JLabel();
		orderlbl4_1.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		orderlbl4_1.setBounds(0, 78, 65, 26);
		panel2_3.add(orderlbl4_1);
		
		JLabel orderlbl5_1 = new JLabel();
		orderlbl5_1.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		orderlbl5_1.setBounds(0, 104, 65, 26);
		panel2_3.add(orderlbl5_1);
		
		JLabel orderlbl1_2 = new JLabel();
		orderlbl1_2.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		orderlbl1_2.setHorizontalAlignment(JLabel.CENTER);
		orderlbl1_2.setBounds(65, 0, 85, 26);
		panel2_3.add(orderlbl1_2);
		
		JLabel orderlbl2_2 = new JLabel();
		orderlbl2_2.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		orderlbl2_2.setHorizontalAlignment(JLabel.CENTER);
		orderlbl2_2.setBounds(65, 26, 85, 26);
		panel2_3.add(orderlbl2_2);
		
		JLabel orderlbl3_2 = new JLabel();
		orderlbl3_2.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		orderlbl3_2.setHorizontalAlignment(JLabel.CENTER);
		orderlbl3_2.setBounds(65, 52, 85, 26);
		panel2_3.add(orderlbl3_2);
		
		JLabel orderlbl4_2 = new JLabel();
		orderlbl4_2.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		orderlbl4_2.setHorizontalAlignment(JLabel.CENTER);
		orderlbl4_2.setBounds(65, 78, 85, 26);
		panel2_3.add(orderlbl4_2);
		
		JLabel orderlbl5_2 = new JLabel();
		orderlbl5_2.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		orderlbl5_2.setHorizontalAlignment(JLabel.CENTER);
		orderlbl5_2.setBounds(65, 104, 85, 26);
		panel2_3.add(orderlbl5_2);
		
		JLabel orderlbl1_3 = new JLabel();
		orderlbl1_3.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		orderlbl1_3.setBounds(150, 0, 130, 26);
		panel2_3.add(orderlbl1_3);
		
		JLabel orderlbl2_3 = new JLabel();
		orderlbl2_3.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		orderlbl2_3.setBounds(150, 26, 130, 26);
		panel2_3.add(orderlbl2_3);
		
		JLabel orderlbl3_3 = new JLabel();
		orderlbl3_3.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		orderlbl3_3.setBounds(150, 52, 130, 26);
		panel2_3.add(orderlbl3_3);
		
		JLabel orderlbl4_3 = new JLabel();
		orderlbl4_3.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		orderlbl4_3.setBounds(150, 78, 130, 26);
		panel2_3.add(orderlbl4_3);
		
		JLabel orderlbl5_3 = new JLabel();
		orderlbl5_3.setFont(new Font("함초롬돋움", Font.BOLD, 12));
		orderlbl5_3.setBounds(150, 104, 130, 26);
		panel2_3.add(orderlbl5_3);
		
		JButton btnNewButton = new JButton("뒤로가기");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
		btnNewButton.setBounds(215, 534, 93, 30);
		getContentPane().add(btnNewButton);
		
		query = String.format(("SELECT * FROM user where user_id='%s'"), now_user);
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		while(rs.next()) {
			lblNewLabel_name.setText(rs.getString("user_name"));
			lblNewLabel_birth.setText(rs.getString("user_birth"));
			lblNewLabel_phone.setText(rs.getString("user_phone"));
		}
		
		query = String.format(("select O.order_date, C.menu_name, O.place_name from orders O, cart C where O.order_number = C.order_number and O.user_id = '%s' order by O.order_date desc"), now_user);
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		try {
			rs.next();
			orderlbl1_1.setText(rs.getString(1));
			orderlbl1_2.setText(rs.getString(2));
			orderlbl1_3.setText(rs.getString(3));
			rs.next();
			orderlbl2_1.setText(rs.getString(1));
			orderlbl2_2.setText(rs.getString(2));
			orderlbl2_3.setText(rs.getString(3));
			rs.next();
			orderlbl3_1.setText(rs.getString(1));
			orderlbl3_2.setText(rs.getString(2));
			orderlbl3_3.setText(rs.getString(3));
			rs.next();
			orderlbl4_1.setText(rs.getString(1));
			orderlbl4_2.setText(rs.getString(2));
			orderlbl4_3.setText(rs.getString(3));
			rs.next();
			orderlbl5_1.setText(rs.getString(1));
			orderlbl5_2.setText(rs.getString(2));
			orderlbl5_3.setText(rs.getString(3));
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		btnNewButton1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new date();
				
			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				try {
					new place_select();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
			}
		});		
		setVisible(true);
	}
}
