package cafepj;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class errorPage extends JFrame{
	JPanel errorpanel = new JPanel();
	
	public errorPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 100);
		setLocationRelativeTo(null);
		setTitle("ERROR");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/����ΰ�.PNG"));
		JLabel lb1 = new JLabel("�ùٸ� ���� �Է��ϼ���!");
		JButton bt1 = new JButton("Ȯ��");
		bt1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		setLayout(new FlowLayout());
		add(lb1);
		add(bt1);
		setVisible(true);
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new errorPage();
	}

}
