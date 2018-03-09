package Login;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import Menu.menu;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class login1 {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login1 window = new login1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u9910\u5385\u767B\u9646\u7CFB\u7EDF");
		frame.setBounds(100, 100, 477, 328);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(186, 101, 109, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setBounds(129, 104, 53, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setBounds(128, 136, 54, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(186, 133, 109, 21);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int acount =Integer.parseInt(textField.getText()); 
				String password =passwordField.getText();
				 try { 
					 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			     	 Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mealcard");  
			     	 CallableStatement CS = con.prepareCall("{?=call login_in(?,?)}");
			     	 CS.registerOutParameter(1, Types.INTEGER);
			         CS.setInt(2, acount);
			         CS.setString(3, password);
			         CS.execute();
			     	 if(CS.getInt(1)==1){
			     		JOptionPane.showMessageDialog(null, "µÇÂ½³É¹¦", "µÇÂ½³É¹¦", JOptionPane.ERROR_MESSAGE);
						new menu();
			     	 }
			     	 else{
						 JOptionPane.showMessageDialog(null, "ÃÜÂë´íÎó", "µÇÂ¼Ê§°Ü", JOptionPane.ERROR_MESSAGE);
			     	 }
				 } catch (Exception e) {   
			    	  e.printStackTrace();
				     }
				 
			}
		});
		btnNewButton.setBounds(129, 163, 65, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u9000\u51FA");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(232, 163, 65, 23);
		frame.getContentPane().add(btnNewButton_1);
		

	}
}
