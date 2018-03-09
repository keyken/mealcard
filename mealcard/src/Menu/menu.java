package Menu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import Idcard.IDcard;
import Restaurant.restaurant;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class menu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu window = new menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 */
	public menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u67E5\u8BE2");
		frame.setBounds(100, 100, 495, 308);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("\u996D\u5361\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Sno;
				Sno = JOptionPane.showInputDialog("请输入你的学生卡号.");
				 try { 
					 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			     	 Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mealcard");  
			     	 CallableStatement CS = con.prepareCall("{?=call approve(?)}");
			     	 CS.registerOutParameter(1, Types.INTEGER);
			         CS.setString(2, Sno);
			         CS.execute();
			     	 if(CS.getInt(1)==1){
			     		JOptionPane.showMessageDialog(null, "登陆成功", "登陆成功", JOptionPane.ERROR_MESSAGE);
						new IDcard(Sno);
			     	 }
			     	 else{
						 JOptionPane.showMessageDialog(null, "登录失败", "没有此学生卡号", JOptionPane.ERROR_MESSAGE);
			     	 }
				 } catch (Exception e) {   
			    	  e.printStackTrace();
				     }
			}
		});
		btnNewButton.setBounds(61, 79, 136, 63);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u9910\u5385\u67E5\u8BE2");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Rid;
				Rid = JOptionPane.showInputDialog("请输入你的餐厅号.");
				 try { 
					 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			     	 Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mealcard");  
			     	 CallableStatement CS = con.prepareCall("{?=call RS_approve(?)}");
			     	 CS.registerOutParameter(1, Types.INTEGER);
			         CS.setString(2, Rid );
			         CS.execute();
			     	 if(CS.getInt(1)==1){
			     		JOptionPane.showMessageDialog(null, "登陆成功", "登陆成功", JOptionPane.ERROR_MESSAGE);
						new  restaurant(Rid);
			     	 }
			     	 else{
						 JOptionPane.showMessageDialog(null, "登录失败", "没有此餐厅号", JOptionPane.ERROR_MESSAGE);
			     	 }
				 } catch (Exception c) {   
			    	  c.printStackTrace();
				     }
			}
		});
		btnNewButton_1.setBounds(254, 79, 136, 63);
		frame.getContentPane().add(btnNewButton_1);
		frame.setVisible(true);
	}
}
