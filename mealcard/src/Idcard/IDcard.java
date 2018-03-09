package Idcard;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

public class IDcard {

	private JFrame frame;
	private String Sno;
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IDcard window = new IDcard("2014");
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
	public IDcard(String a) {
		Sno=a;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u996D\u5361");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u996D\u5361\u7BA1\u7406");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u5145\u503C");
		menu.add(menuItem);
		 menuItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	new pay(Sno);
	            }
	        });
		
		JMenuItem menuItem_1 = new JMenuItem("\u6D88\u8D39\u8BB0\u5F55");
		menu.add(menuItem_1);
		menu.add(menuItem_1);
		menuItem_1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	new record(Sno);
	            }
	        });
		frame.setVisible(true);
	}

}
