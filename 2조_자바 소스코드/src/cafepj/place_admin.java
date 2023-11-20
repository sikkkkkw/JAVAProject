package cafepj;

import java.awt.Color;
import java.awt.Font;
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

public class place_admin extends JFrame  { 
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
	
	private void maplist() throws Exception{
		query = "SELECT place_name FROM place";
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		maplist = new ArrayList<String>();
		maplist.add((String) locate);
		while(rs.next()) {
			maplist.add(rs.getString(1));
		}
	}
	
      public place_admin() throws Exception {
         setTitle("지점 선택");
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setSize(450, 500);
         setResizable(false);
         getContentPane().setLayout(null);
         
         JLabel lblNewLabel = new JLabel("매장 선택");
         lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
         lblNewLabel.setFont(new Font("나눔고딕", Font.BOLD, 30));
         lblNewLabel.setBounds(131, 17, 174, 81);
         getContentPane().add(lblNewLabel);
         
         JPanel panel_1 = new JPanel();
         panel_1.setBounds(51, 160, 330, 215);
         
         JLabel lblNewLabel_1 = new JLabel("");
         lblNewLabel_1.setBounds(0, 0, 330, 215);
         panel_1.add(lblNewLabel_1);
         getContentPane().add(panel_1);
         
         
         maplist();
         JComboBox<String> comboBox = new JComboBox<String>(maplist.toArray(new String[maplist.size()]));
         comboBox.setBounds(51, 108, 333, 23);
         comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				locate = (String)comboBox.getSelectedItem();
				lblNewLabel_1.setIcon(new ImageIcon(String.format("map/%s.jpg", locate)));
				map = (String) locate;
		}});
         getContentPane().add(comboBox);
         
         JButton btnNewButton_1 = new JButton("뒤로 가기");
         btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	//new login();
            }
         });
         btnNewButton_1.setBounds(200, 402, 97, 23);
         getContentPane().add(btnNewButton_1);
         
         JButton btnNewButton_2 = new JButton("지점 선택");
         btnNewButton_2.setForeground(Color.WHITE);
         btnNewButton_2.setBounds(321, 402, 95, 23);
         getContentPane().add(btnNewButton_2);
         btnNewButton_2.setBackground(Color.red);
         btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(locate == maplist.get(0)) {
					System.out.println("지점 선택하세요"); //오류창으로 변경가능
				} else {
					
					dispose();
				}
								
		}});         
         setVisible(true);
         }
      
      public static void main(String[] args) throws Exception {
         place_admin A = new place_admin();
         }
      
      public static String getmap() {
  		return map;
  	}
  	
      }
