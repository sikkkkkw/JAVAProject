package cafepj;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class chart_panel extends JPanel {
	
	int h1=0, h2=0, h3=0, h4=0;
	int sum = h1 + h2 + h3 + h4;
	
	JLabel lblNewLabel_1 = new JLabel(Integer.toString(h2));
	JLabel lblNewLabel_2 = new JLabel(Integer.toString(h2));
	JLabel lblNewLabel_3 = new JLabel(Integer.toString(h3));
	JLabel lblNewLabel_4 = new JLabel(Integer.toString(h4));

	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(35, 110-h1, 40, h1);
		g.fillRect(105, 110-h2, 40, h2);
		g.fillRect(175, 110-h3, 40, h3);
		g.fillRect(245, 110-h4, 40, h4);
		/*
		 * g.fillRect(30, 100, 25, 10);
		 * g.fillRect(86, 76, 25, 40);
		 * g.fillRect(146, 86, 25, 20);
		 */
		
		lblNewLabel_1.setBounds(50, 70-h1, 20, 20);
		lblNewLabel_1.setText(Integer.toString(h1));
		add(lblNewLabel_1);
		
		
		lblNewLabel_2.setBounds(120, 70-h2, 20, 20);
		lblNewLabel_2.setText(Integer.toString(h2));
		add(lblNewLabel_2);
		
		
		lblNewLabel_3.setBounds(190, 70-h3, 20, 20);
		lblNewLabel_3.setText(Integer.toString(h3));
		add(lblNewLabel_3);
		
		
		lblNewLabel_4.setBounds(260, 70-h4, 20, 20);
		lblNewLabel_4.setText(Integer.toString(h4));
		add(lblNewLabel_4);
	}
	/**
	 * Create the panel.
	 */
	public chart_panel() {
		setBackground(new Color(255, 247, 253));
		this.setSize(200, 100);
		setLayout(null);
		repaint();
		this.setVisible(true);
		
	}
	public void rerepaint() {
		repaint();
	}
	
	
}
