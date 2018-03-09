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
      		 Sno = JOptionPane.showInputDialog("���������ѧ������.");
  			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
  	     	 Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;integratedSecurity=true;DatabaseName=mealcard");  
  	     	 CallableStatement CS = con.prepareCall("{call foodMenu(?)}");
  	         CS.setString(1, Rid);
  	         CS.execute();
  	         ResultSet resultSet=CS.executeQuery();
  	         DefaultTableModel model=new DefaultTableModel();
  	         model.setColumnIdentifiers(new Object[]{"ʳ����","�������","ʳ������","ʳ�����","�۸�","���"});
  	         model.addRow(new Object[]{"ʳ����","�������","ʳ������","ʳ�����","�۸�","���"});
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
  	                //��ӡ��������к���
  	               
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

  	      	         JOptionPane.showMessageDialog(null, "��ͳɹ�", "��ͳɹ�", JOptionPane.INFORMATION_MESSAGE);
  	      		 } catch (Exception d) {   
  	      	    	  d.printStackTrace();
   	      	         JOptionPane.showMessageDialog(null, "���ʧ��", "���ʧ��", JOptionPane.INFORMATION_MESSAGE); 
  	      		     }
  	            }

  	        };
  	        
  	        //���ñ�����Ⱦ��   	 		
  	        table = new JTable(model);
  	        table.getColumnModel().getColumn(5).setCellRenderer(new MyButtonRender());
  	        MyButtonEditor editor = new MyButtonEditor(e);
  	        table.getColumnModel().getColumn(5).setCellEditor(editor);
			table.setBounds(10, 10, 414, 241);
  	 		frame.getContentPane().add(table);
  	 		
  	        frame.setSize(427, 329);
  	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	        frame.setVisible(true);
  	         JOptionPane.showMessageDialog(null, "��ѯ�ɹ�", "��ѯ�ɹ�", JOptionPane.INFORMATION_MESSAGE);
  		 } catch (Exception b) {   
  	    	  b.printStackTrace();
  	    	JOptionPane.showMessageDialog(null, "��ѯʧ��", "��ѯʧ��", JOptionPane.INFORMATION_MESSAGE);
  		     }
      	frame.setVisible(true);
	}
}

class MyButtonRender implements TableCellRenderer{

    private JButton button;

    public MyButtonRender() {
        button = new JButton("���");
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
        button = new MyButton("���");
        button.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {
            //��������Զ�����¼�������
            	
                event.invoke(e);
            }
        });
    }
    public MyButtonEditor(MyEvent e) {
        this();
        this.event = e;
    }
    /*
    ��д�༭������������һ����ť��JTable
    */
      @Override
  public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
//      setClickCountToStart(1);
//�����������İ�ť���ڵ��к��зŽ�button����
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

