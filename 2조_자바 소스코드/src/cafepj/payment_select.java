package cafepj;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class payment_select extends JFrame implements ActionListener {
	JPanel contentPane;
	JPanel pay;
	JButton button = new JButton();
	JButton button1 = new JButton();
	static String payment = null;
	
	public static void main(String[] args) {
		payment_select frame = new payment_select();
	}

	public payment_select() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("결제 방식 선택");
		setLocationRelativeTo(null);
        setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("img/투썸로고.PNG")));
		setBounds(100, 100, 460, 300);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(20, 20, 400, 220);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		button.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/kakaopay.png")));
		button.setBounds(25, 45, 150, 150);
		button.addActionListener(this);
		panel.add(button);
		
		button1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("img/samsungpay.png")));
		button1.setBounds(220, 45, 150, 150);
		button1.addActionListener(this);
		panel.add(button1);
		
		JLabel lblNewLabel = new JLabel("< 결 제 >");
		lblNewLabel.setFont(new Font("함초롬돋움", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel.setBounds(0, 0, 105, 35);
		panel.add(lblNewLabel);
		setVisible(true);
		System.out.println();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			payment = "<카카오페이>";
			dispose();
			try {
				new order_list();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if(e.getSource() == button1){
			payment = "<삼성페이>";
			dispose();
			try {
				new order_list();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public static String getpay() {
		return payment;
	}
}
