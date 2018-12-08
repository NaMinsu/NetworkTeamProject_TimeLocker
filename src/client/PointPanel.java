package main;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.*;
import java.util.*;
import javax.swing.ImageIcon;


public class PointPanel extends JFrame
{
   public static JButton GoBack;
   public static JButton PointToTime;
   public static JButton TimeToPoint;
   public static JCheckBox[] cb = new JCheckBox[100];   //checkbox 100개 한정.
   private JPanel MyPoint;
   public static String accessID;
   private String protocol_b = "b1";
   private String protocol_d = "d";
   private ArrayList<String[]> datamap;
   JScrollPane checkbox;
   private final Object [] colNames = {"CheckBox","PCRoom","Remain Time","Point"};
   public Object [][] datas;
   private JTable table;
   public DefaultTableModel dtm;
   private static final String code_suc = "success";
   public ArrayList<Integer> selected;
//   private static final String code_fail = "fail";
   public boolean updated = false;

   public PointPanel() 
   {
      setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\WORK\\\uAC00\uCC9C\uB300\uD559\uAD50\\2-2\\\uCEF4\uD4E8\uD130\uB124\uD2B8\uC6CC\uD06C \uBC0F \uC2E4\uC2B5(\uC774\uC8FC\uD615 \uAD50\uC218\uB2D8)\\Team Project\\Term project\\icon\\point.png"));
      setTitle("TimeLocker");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 450, 430);
      setLocationRelativeTo(null);   //창을 가운데에 뜨게함.
      setResizable(false);   //size변경하지 못하게 함.
      getContentPane().setLayout(null);

      MyPoint = new JPanel();
      MyPoint.setBackground(new Color(255, 239, 213));
      MyPoint.setBounds(0, 0, 444, 401);
      getContentPane().add(MyPoint);
      MyPoint.setLayout(null);

      JLabel lblMyPoint = new JLabel("My Point");
      lblMyPoint.setForeground(new Color(0, 0, 0));
      lblMyPoint.setBackground(new Color(240,240,240));
      lblMyPoint.setFont(new Font("STIXSizeTwoSym", Font.PLAIN, 19));
      lblMyPoint.setBounds(6, 20, 103, 54);
      MyPoint.add(lblMyPoint);

      GoBack = new JButton("Go Back");
      GoBack.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      	}
      });
      GoBack.setIcon(new ImageIcon("back2.png"));
      GoBack.setForeground(new Color(47, 79, 79));
      GoBack.setBackground(new Color(255, 255, 255));
      GoBack.setBounds(341, 45, 84, 28);
      MyPoint.add(GoBack);

      PointToTime = new JButton("Point to Time");
      PointToTime.setBounds(296, 368, 136, 23);
      MyPoint.add(PointToTime);

      TimeToPoint = new JButton("Time to Point");
      TimeToPoint.setBounds(137, 368, 147, 23);
      MyPoint.add(TimeToPoint);

      //수정함
      dtm = new DefaultTableModel(null, colNames);
      table = new JTable(dtm);
      table.getColumn("CheckBox").setCellRenderer(dcr);
      JCheckBox box = new JCheckBox();
      box.setHorizontalAlignment(JLabel.CENTER);
      table.getColumn("CheckBox").setCellEditor(new DefaultCellEditor(box));

      checkbox = new JScrollPane(table);   //수정함
      checkbox.setBounds(6, 80, 432, 278);
      MyPoint.add(checkbox);
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

   public boolean setSelected() {
	   selected = new ArrayList<Integer>();
	   
	   for (int i = 0; i < table.getRowCount(); i++) {
	    	if (table.getValueAt(i, 0).equals(true))
	    		selected.add(i);
	   }
	   
	   boolean isInError = false;
	   System.out.println(selected.size());
	   for (int i = 0; i < selected.size(); i++) {
		   int index = selected.get(i);
		   if (table.getValueAt(index, 3).equals("0"))
			   isInError = true;
	   }
	   
	   if (isInError) {
		   JOptionPane.showMessageDialog(null, "You have no point!");
		   return false;
	   }
	   else
		   return true;
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
     MyPoint.remove(checkbox);
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
     
      //table.setForeground(new Color(0).yellow);
      //table.setBackground(new Color(0).yellow);
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
      MyPoint.add(checkbox);
   }
   
   public void pointExchanger() {
      int dataNum = 1;
      selected = new ArrayList<Integer>();
      
      for (int i = 0; i < table.getRowCount(); i++) {
          if (table.getValueAt(i, 0).equals(true)) {
             selected.add(i);
             dataNum += 2;
          }
       }
      
      protocol_d = protocol_d + dataNum + accessID;
      boolean isInError = false;
      for (int i = 0; i < selected.size(); i++) {
         int index = selected.get(i);
         String name = (String)table.getValueAt(index, 1);
         String time = (String)table.getValueAt(index, 2);
         if (time.equals("0"))
            isInError = true;
         protocol_d = protocol_d + "|" + name + "|" + time;
      }
      
      if (isInError) {
         JOptionPane.showMessageDialog(null, "You have no time!");
         protocol_d = "d";
         return;
      }
      
      try {
         Socket sck = new Socket("127.0.0.1", 777);
         BufferedReader read;
          DataOutputStream write;
          
          read = new BufferedReader(new InputStreamReader(sck.getInputStream()));
          write = new DataOutputStream(sck.getOutputStream());
          
          write.writeBytes(protocol_d + "\n");
          String code = read.readLine();
          
          if (code.equals(code_suc)) {
             updated = true;
          }
          
          protocol_d = "d";
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
         box.setBackground(new Color(0).yellow);
         return box;
      }
   };
}