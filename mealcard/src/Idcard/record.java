package Idcard;

import java.awt.EventQueue;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class record {

	private JFrame frame;
	private JTable table;
	private String Sno;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					record window = new record("2014");
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
	public record(String a) {
		this.Sno=a;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u6D88\u8D39\u8BB0\u5F55");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		try { 
			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
	     	 Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mealcard");  
	     	 CallableStatement CS = con.prepareCall("{call id_record(?)}");
	         CS.setString(1, Sno);
	         CS.execute();
	         ResultSet resultSet=CS.executeQuery();
	         DefaultTableModel model=new DefaultTableModel();
	         model.setColumnIdentifiers(new Object[]{"消费编号","学号","餐厅号","食物编号","价格","消费时间"});
	         model.addRow(new Object[]{"消费编号","学号","餐厅号","食物编号","价格","消费时间"});
	         while(resultSet.next()){
	             int Cid=resultSet.getInt(1);
	             String Sno=resultSet.getString(2);
	             String Rid=resultSet.getString(3);    
	             String Fid=resultSet.getString(4);    
	             double CPrice=resultSet.getDouble(5);
	             String CDate=resultSet.getString(6);
	             model.addRow(new Object[]{Cid,Sno,Rid,Fid,CPrice,CDate});	
	         }
	 		table = new JTable(model);
			table.setBounds(10, 10, 414, 241);
			frame.getContentPane().add(table);
	         JOptionPane.showMessageDialog(null, "查询成功", "查询成功", JOptionPane.INFORMATION_MESSAGE);
		 } catch (Exception e) {   
	    	  e.printStackTrace();
	    	  
		     }
		frame.setVisible(true);
		
	}
}
