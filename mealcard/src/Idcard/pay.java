package Idcard;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class pay {

	private JFrame frame;
	private JTextField textField;
	private String Sno;
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pay window = new pay();
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
	public pay(String a) {
		this.Sno=a;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u5145\u503C");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u5145\u503C\u91D1\u989D\uFF1A");
		lblNewLabel.setBounds(55, 79, 128, 46);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(150, 92, 96, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int money = Integer.parseInt(textField.getText());
				try { 
					 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			     	 Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mealcard");  
			     	 CallableStatement CS = con.prepareCall("{call pay(?,?)}");
			         CS.setInt(1, money);
			         CS.setString(2, Sno);
			         CS.execute();
			         JOptionPane.showMessageDialog(null, "充值成功", "充值成功", JOptionPane.ERROR_MESSAGE);
				 } catch (Exception e) {   
			    	  e.printStackTrace();
			    	  
				     }
			}
		});
		btnNewButton.setBounds(55, 135, 88, 22);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IDcard(Sno);
			}
		});
		btnNewButton_1.setBounds(158, 134, 88, 23);
		frame.getContentPane().add(btnNewButton_1);
		frame.setVisible(true);
	}
}
