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
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class order_list extends JFrame implements ActionListener{
	payment_select paysel = new payment_select();
	private JPanel contentPane;
	JPanel p = new JPanel();
	String payment = paysel.getpay();
	
	
	JButton exit_button = new JButton("나가기");
	int order_number = place_select.getorder_number();
	Connection con = new DB_connect().makeConnection();
	String query;
	Statement stmt;
	ResultSet rs;
	int count; //카트 개수
	int order_value = 0; //결제 금액
	int cart_count = 0;
	int ins;
	String cart_temperature, menu_name, cart_number;
	String temp = null;
	int cart_value;
	/**
	 * Launch the application.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		order_list frame = new order_list();
	}
	
	private void orderlist() throws Exception{
		query = String.format("SELECT order_value FROM orders where order_number = %d", order_number);
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rs = stmt.executeQuery(query);
		rs.next();
		order_value = rs.getInt(1);
		
		
		query = String.format("select count(*)  from cart where order_number = %d", order_number);
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rs = stmt.executeQuery(query);
		rs.next();
		count = rs.getInt(1);
	}
	public void settotal(int value) throws Exception{
		query = String.format("UPDATE orders set order_value = %d WHERE order_number = %d", value, order_number);
		stmt = con.createStatement();
		ins = stmt.executeUpdate(query);
	}
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public order_list() throws Exception {
		paysel.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 480);
		setTitle("주문내역");
		setLocationRelativeTo(null);
        setResizable(false);
	    setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("img/투썸로고.PNG")));
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		JButton b = new JButton();
		b.addActionListener(null);
		p.add(b);
		getContentPane().add(p);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 504, 441);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("주문 내역");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 504, 56);
		lblNewLabel.setBackground(new Color(220, 20, 60));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panel.add(lblNewLabel);
		
		orderlist();
		JLabel lblNewLabel_1 = new JLabel(String.format("주문번호는 %d입니다.", order_number));
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setBorder(new LineBorder((new Color(220, 20, 60))));
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 24));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(60, 90, 400, 40);
		panel.add(lblNewLabel_1);
		
		
		JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(60, 140, 400, 190);
		panel.add(scrollPane);
		
		JPanel borderlaoutpanel = new JPanel();
        scrollPane.setViewportView(borderlaoutpanel);
        borderlaoutpanel.setLayout(new BorderLayout(0, 0));

        JPanel columnpanel = new JPanel();
        borderlaoutpanel.add(columnpanel, BorderLayout.CENTER);
        columnpanel.setLayout(new GridLayout(0,1));
        columnpanel.setBackground(Color.WHITE);
        
        query = String.format("select cart_temperature, menu_name, cart_number , cart_value  from cart where order_number = %d", order_number);
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rs = stmt.executeQuery(query);
        rs.last();
        int row_count = rs.getRow();
        rs.beforeFirst();
        
        int final_value = 0;
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
            j1.setBounds(15, 10, 60, 30);
			j1.setHorizontalAlignment(SwingConstants.CENTER);
			rowPanel.add(j1);
			
			JLabel j2 = new JLabel(String.format("%s", rs.getString(2)));
			j2.setHorizontalAlignment(SwingConstants.CENTER);
			j2.setBounds(90, 10, 110, 30);
			j1.setHorizontalAlignment(SwingConstants.CENTER);
			rowPanel.add(j2);
			
			JLabel j3 = new JLabel(String.format("%d개", rs.getInt(3)));
			j3.setHorizontalAlignment(SwingConstants.CENTER);
			j3.setBounds(220, 10, 40, 30);
			j1.setHorizontalAlignment(SwingConstants.CENTER);
			rowPanel.add(j3);
			
			JLabel j4 = new JLabel(String.format("%d원", rs.getInt(4)));
			j4.setHorizontalAlignment(SwingConstants.CENTER);
			j4.setBounds(280, 10, 80, 30);
			j1.setHorizontalAlignment(SwingConstants.CENTER);
			rowPanel.add(j4);
			final_value += rs.getInt(4) * rs.getInt(3);
	}	
		
		JLabel lblNewLabel_2 = new JLabel("  결제 방식        " + payment);
		lblNewLabel_2.setBounds(60, 340, 220, 40);
		lblNewLabel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblNewLabel_2.setFont(new Font("함초롬돋움", Font.BOLD, 15));
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(String.format("  금액                %s 원", final_value));
		lblNewLabel_3.setBounds(60, 383, 220, 40);
		lblNewLabel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblNewLabel_3.setFont(new Font("함초롬돋움", Font.BOLD, 15));
		panel.add(lblNewLabel_3);
		settotal(final_value);
		
		exit_button.setBounds(360, 383, 100, 37);
		exit_button.setForeground(Color.WHITE);
		exit_button.setBackground(Color.DARK_GRAY);
		exit_button.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 12));
		exit_button.addActionListener(this);
		panel.add(exit_button);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit_button) {
			dispose();
			try {
				new place_select();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
