package Restaurant;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class order {

	private JFrame frame;
	private JTable table;
	private String Rid;
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					order window = new order("1");
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
	public order(String a) {
		this.Rid=a;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

      	try { 
      		 String Sno;
      		 Sno = JOptionPane.showInputDialog("请输入你的学生卡号.");
  			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
  	     	 Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mealcard");  
  	     	 CallableStatement CS = con.prepareCall("{call foodMenu(?)}");
  	         CS.setString(1, Rid);
  	         CS.execute();
  	         ResultSet resultSet=CS.executeQuery();
  	         DefaultTableModel model=new DefaultTableModel();
  	         model.setColumnIdentifiers(new Object[]{"食物编号","餐厅编号","食物名称","食物类别","价格","点餐"});
  	         model.addRow(new Object[]{"食物编号","餐厅编号","食物名称","食物类别","价格","点餐"});
  	         while(resultSet.next()){
  	             String Fid=resultSet.getString(1);
  	             String Rid=resultSet.getString(2);    
  	             String Fname=resultSet.getString(3);    
  	             String Sort=resultSet.getString(4);
  	             double Price=resultSet.getDouble(5);
  	             model.addRow(new Object[]{Fid,Rid,Fname,Sort,Price});
  	             
  	         }
  	       MyEvent e = new MyEvent() {
  	            @Override
  	            public void invoke(ActionEvent e) {
  	                MyButton button = (MyButton)e.getSource();
  	                //打印被点击的行和列
  	               
  	                String Fid = table.getValueAt(button.getRow(), 0).toString();

  	                System.out.println(Fid);

  	                try { 
  	      			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
  	      	     	 Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mealcard");  
  	      	     	 CallableStatement CS1 = con.prepareCall("{call ordering(?,?,?)}");
  	      	     	 CS1.setString(1, Fid);
  	      	         CS1.setString(2, Sno);
  	      	         CS1.setString(3, Rid);
  	      	         CS1.execute();

  	      	         JOptionPane.showMessageDialog(null, "点餐成功", "点餐成功", JOptionPane.INFORMATION_MESSAGE);
  	      		 } catch (Exception d) {   
  	      	    	  d.printStackTrace();
   	      	         JOptionPane.showMessageDialog(null, "点餐失败", "点餐失败", JOptionPane.INFORMATION_MESSAGE); 
  	      		     }
  	            }

  	        };
  	        
  	        //设置表格的渲染器   	 		
  	        table = new JTable(model);
  	        table.getColumnModel().getColumn(5).setCellRenderer(new MyButtonRender());
  	        MyButtonEditor editor = new MyButtonEditor(e);
  	        table.getColumnModel().getColumn(5).setCellEditor(editor);
			table.setBounds(10, 10, 414, 241);
  	 		frame.getContentPane().add(table);
  	 		
  	        frame.setSize(427, 329);
  	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	        frame.setVisible(true);
  	         JOptionPane.showMessageDialog(null, "查询成功", "查询成功", JOptionPane.INFORMATION_MESSAGE);
  		 } catch (Exception b) {   
  	    	  b.printStackTrace();
  	    	JOptionPane.showMessageDialog(null, "查询失败", "查询失败", JOptionPane.INFORMATION_MESSAGE);
  		     }
      	frame.setVisible(true);
	}
}

class MyButtonRender implements TableCellRenderer{

    private JButton button;

    public MyButtonRender() {
        button = new JButton("点餐");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        return button;
    }
}

class MyButtonEditor extends DefaultCellEditor{

    private MyButton button;

    private MyEvent event;

    public MyButtonEditor() {
        super(new JTextField());
        button = new MyButton("点餐");
        button.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {
            //这里调用自定义的事件处理方法
            	
                event.invoke(e);
            }
        });
    }
    public MyButtonEditor(MyEvent e) {
        this();
        this.event = e;
    }
    /*
    重写编辑器方法，返回一个按钮给JTable
    */
      @Override
  public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
//      setClickCountToStart(1);
//将这个被点击的按钮所在的行和列放进button里面
        button.setRow(row);
        button.setColumn(column);
        return button;
    }
}

class MyButton extends JButton{

    private int row;

    private int column;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public MyButton() {

    }

    public MyButton(String name) {
        super(name);
    }

}

abstract class MyEvent{
	public abstract void invoke(ActionEvent e);
}

