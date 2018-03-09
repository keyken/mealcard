package Restaurant;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

import Idcard.pay;

public class restaurant {

	private JFrame frame;
	private String Rid;
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					restaurant window = new restaurant("1");
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
	public restaurant(String id) {
		this.Rid=id;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u9910\u5385\u7BA1\u7406");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu_1 = new JMenu("\u70B9\u9910");
		menuBar.add(mnNewMenu_1);
		mnNewMenu_1.addMenuListener(
	            new MenuListener(){
	                public void menuCanceled(MenuEvent e){
	                   
	                }
	                public void menuDeselected(MenuEvent e){
	                  
	                } 
	                public void menuSelected(MenuEvent e){
		            	new order(Rid);
	                }
	        }); 
		JMenu mnNewMenu = new JMenu("\u9910\u5385\u7BA1\u7406");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u98DF\u54C1\u5F55\u5165");
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
         		 String Fid;
          		 Fid = JOptionPane.showInputDialog("请输入要录入的食品编号.");
         		 String Rid;
          		 Rid = JOptionPane.showInputDialog("请输入要录入的餐厅编号.");
         		 String Fname;
          		 Fname = JOptionPane.showInputDialog("请输入要录入的食物名称.");
         		 String Sort;
          		 Sort = JOptionPane.showInputDialog("请输入要录入的食物分类.");
         		 double Price;
          		 Price = Double.parseDouble(JOptionPane.showInputDialog("请输入要录入的食物价格."));
          		try { 
       			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
       	     	 Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mealcard");  
       	     	 CallableStatement CS = con.prepareCall("{call foodInsert(?,?,?,?,?)}");
       	         CS.setString(1, Fid);
       	         CS.setString(2, Rid);
       	         CS.setString(3, Fname);
       	         CS.setString(4, Sort);
       	         CS.setDouble(5, Price);
       	         CS.execute();
       	         JOptionPane.showMessageDialog(null, "食物录入成功", "食物录入成功", JOptionPane.INFORMATION_MESSAGE);
       		 } catch (Exception q) {   
       	    	  q.printStackTrace();
        	      JOptionPane.showMessageDialog(null, "食物录入失败", "食物录入失败", JOptionPane.ERROR_MESSAGE);	  
       		     }
            }
        });
		
		JMenuItem menuItem = new JMenuItem("\u98DF\u54C1\u66F4\u65B0");
		mnNewMenu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u9910\u5385\u5F55\u5165");
		mnNewMenu.add(menuItem_1);
		frame.setVisible(true);
	}
}
