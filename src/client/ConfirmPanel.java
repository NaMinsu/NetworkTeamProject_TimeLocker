package main;

import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class ConfirmPanel extends JFrame{

   public static JCheckBox[] cb = new JCheckBox[100];   //checkbox 100개 한정.
   public JButton ConfirmButton;
   private JPanel ConfirmPanel;
   public String accessID;
   private String protocol_b = "b1";
   private String protocol_c = "c";
   private ArrayList<String[]> datamap;
   JScrollPane checkbox;
   private final Object [] colNames = {"CheckBox","PCRoom","Remain Time","Point"};
   public Object [][] datas;
   private JTable table;
   public DefaultTableModel dtm;
   public ArrayList<Integer> selected;
   public boolean updated = false;
   private static final String code_suc = "success";

   public ConfirmPanel() {
      setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\WORK\\\uAC00\uCC9C\uB300\uD559\uAD50\\2-2\\\uCEF4\uD4E8\uD130\uB124\uD2B8\uC6CC\uD06C \uBC0F \uC2E4\uC2B5(\uC774\uC8FC\uD615 \uAD50\uC218\uB2D8)\\Team Project\\Term project\\icon\\point.png"));
      setTitle("TimeLocker");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 443, 424);   //size 수정해야됨.
      setLocationRelativeTo(null);   //창을 가운데에 뜨게함.
      setResizable(false);   //size변경하지 못하게 함.
      getContentPane().setLayout(null);

      ConfirmPanel = new JPanel();
      ConfirmPanel.setBackground(UIManager.getColor("Button.background"));
      ConfirmPanel.setBounds(0, 0, 444, 401);
      getContentPane().add(ConfirmPanel);
      ConfirmPanel.setLayout(null);

      JLabel lblMyPoint = new JLabel("My Point");
      lblMyPoint.setFont(new Font("Arial Black", Font.PLAIN, 15));
      lblMyPoint.setBounds(6, 10, 103, 18);
      ConfirmPanel.add(lblMyPoint);

      ConfirmButton = new JButton("Confirm");
      ConfirmButton.setBounds(296, 368, 136, 23);
      ConfirmPanel.add(ConfirmButton);
      
      dtm = new DefaultTableModel(null, colNames);
      table = new JTable(dtm);
      table.getColumn("CheckBox").setCellRenderer(dcr);
      JCheckBox box = new JCheckBox();
      box.setHorizontalAlignment(JLabel.CENTER);
      table.getColumn("CheckBox").setCellEditor(new DefaultCellEditor(box));

      checkbox = new JScrollPane(table);   //수정함
      checkbox.setBounds(6, 80, 426, 278);
      ConfirmPanel.add(checkbox);

      JLabel lblTime = new JLabel("Time");
      lblTime.setBounds(212, 55, 57, 15);
      ConfirmPanel.add(lblTime);

      JLabel lblPoint = new JLabel("Point");
      lblPoint.setBounds(335, 55, 57, 15);
      ConfirmPanel.add(lblPoint);

      JLabel lblPcRoom = new JLabel("PC Room");
      lblPcRoom.setBounds(87, 55, 57, 15);
      ConfirmPanel.add(lblPcRoom);
   }

   public void putResult(ItemEvent e)
   {
      JCheckBox ck = (JCheckBox)e.getSource();
      if(e.getStateChange() == ItemEvent.SELECTED)
      {
         System.out.println(ck.getText() + ": Selected");
      }
      else if(e.getStateChange() == ItemEvent.DESELECTED)
      {
         System.out.println(ck.getText() + ": Not Selected");
      }
   }

   public void uploadList() {
      try {
         Socket sck = new Socket("127.0.0.1", 777);
         BufferedReader read;
         DataOutputStream write;
         datamap = new ArrayList<String[]>();

         read = new BufferedReader(new InputStreamReader(sck.getInputStream()));
         write = new DataOutputStream(sck.getOutputStream());

         protocol_b = protocol_b + accessID;
         System.out.println(protocol_b);
         write.writeBytes(protocol_b + "\n");
         String output = read.readLine();
         System.out.println(output);

         String[] linebyline = output.split(":");
         for (int i = 0; i < linebyline.length; i++) {
            String[] temp = linebyline[i].split("\t");
            datamap.add(temp);
         }

         sck.close();
         protocol_b = "b1";
      } catch (UnknownHostException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public void updateList()
   {
	  ConfirmPanel.remove(checkbox);
      dtm = null;
	  
	  uploadList();
	  
      datas = new Object[datamap.size()][4];

      String[] temp = new String[3];   //수정함
      String[] name = new String[datamap.size()];
      String[] time = new String[datamap.size()];
      String[] point = new String[datamap.size()];
      for (int i = 0; i < datamap.size(); i++) {
         temp = datamap.get(i);
         name[i] = temp[0];
         time[i] = temp[1];
         point[i] = temp[2];
      }

      //수정함
      for(int i=0;i<datamap.size();i++)
      {
         datas[i][0] = false;
         datas[i][1] = name[i];
         datas[i][2] = time[i];
         datas[i][3] = point[i];
      }
	  
      dtm = new DefaultTableModel(datas, colNames);
      //DefaultTableModel dtm = new DefaultTableModel(0,0);
      table = new JTable(dtm);
      table.getColumn("CheckBox").setCellRenderer(dcr);
      JCheckBox box = new JCheckBox();
      box.addItemListener(new ItemListener() {
    	  public void itemStateChanged(ItemEvent e) {
    	  	putResult(e);
    	  }
      });
      box.setHorizontalAlignment(JLabel.CENTER);
      table.getColumn("CheckBox").setCellEditor(new DefaultCellEditor(box));
      
      checkbox = new JScrollPane(table);   //수정함
      checkbox.setBounds(6, 80, 426, 278);
      ConfirmPanel.add(checkbox);
   }
   
   public void buyTime() {
	   int count = 0;
	   int targetIndex = 0;
	   
	   for (int i = 0; i < table.getRowCount(); i++) {
	    	if (table.getValueAt(i, 0).equals(true)) {
	    		targetIndex = i;
	    		count++;
	    	}
	   }
	   
	   if (count == 0) {
		   JOptionPane.showMessageDialog(null, "You should select only one PC-Room!");
		   return;
	   }
	   else if (count > 1) {
		   JOptionPane.showMessageDialog(null, "You should select only one PC-Room!");
		   return;
	   }
	   
	   int dataNum = 2 + selected.size() * 2;
	   protocol_c = protocol_c + dataNum + accessID + "|"
			   + table.getValueAt(targetIndex, 1);
	   for (int i = 0; i < selected.size(); i++) {
		   int index = selected.get(i);
		   String name = (String)table.getValueAt(index, 1);
		   String point = (String)table.getValueAt(index, 3);
		   protocol_c = protocol_c + "|" + name + "|" + point;
	   }
	   
	   try {
		Socket sck = new Socket("127.0.0.1", 777);
		BufferedReader read;
	    DataOutputStream write;
	       
	    read = new BufferedReader(new InputStreamReader(sck.getInputStream()));
	    write = new DataOutputStream(sck.getOutputStream());
	       
	    write.writeBytes(protocol_c + "\n");
	    String code = read.readLine();
		
	    if (code.equals(code_suc))
	    	updated = true;
	    protocol_c = "c";
	    
		sck.close();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }

   DefaultTableCellRenderer dcr = new DefaultTableCellRenderer()
   {
      public Component getTableCellRendererComponent  // 셀렌더러
      (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
      {
         JCheckBox box= new JCheckBox();
         box.setSelected(((Boolean)value).booleanValue());  
         box.setHorizontalAlignment(JLabel.CENTER);
         return box;
      }
   };
}