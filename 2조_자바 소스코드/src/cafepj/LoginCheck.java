package cafepj;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class LoginCheck extends JFrame implements ActionListener{
	
	Statement stmt;
	ResultSet rs;
	String query;
	JButton btnNewButton = new JButton();
	Connection con = new DB_connect().makeConnection();
	String now_user = Login.getid();
	
	public static void main(String[] args) throws SQLException {
		new LoginCheck();
	}

   public LoginCheck() throws SQLException {
      getContentPane().setBackground(Color.WHITE);
      getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
      
      JPanel panel = new JPanel();
      panel.setBackground(Color.WHITE);
      getContentPane().add(panel);
      panel.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("");
      lblNewLabel.setBounds(83, 44, 328, 33);
      lblNewLabel.setFont(new Font("«‘√ ∑“µ∏øÚ", Font.BOLD | Font.ITALIC, 25));
      panel.add(lblNewLabel);
      
      query = String.format(("select user_name from user where user_id = '%s'"), now_user);
      stmt = con.createStatement();
      rs = stmt.executeQuery(query);
      try {
    	  rs.next();
    	  lblNewLabel.setText(rs.getString(1) + "¥‘, π›∞©Ω¿¥œ¥Ÿ !");
      } catch (Exception e) {
    	  e.getStackTrace();
      }
      
      JPanel panel_1 = new JPanel();
      panel_1.setBackground(Color.WHITE);
      getContentPane().add(panel_1);
      
      btnNewButton = new JButton("»Æ¿Œ");
      btnNewButton.addActionListener(this);
      
      btnNewButton.setForeground(Color.WHITE);
      btnNewButton.setBackground(Color.DARK_GRAY);
      btnNewButton.setFont(new Font("«‘√ ∑“µ∏øÚ", Font.ITALIC, 15));
      panel_1.add(btnNewButton);
      setTitle("∑Œ±◊¿Œ");
      setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("img/≈ıΩÊ∑Œ∞Ì.PNG")));
      setBounds(500, 300, 450, 210);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      
   }


   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == btnNewButton) {
         dispose();
         try {
        	if (now_user.equals("admin")) {
        		new manage_from();
        	} else
			new place_select();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
      }
   }
}