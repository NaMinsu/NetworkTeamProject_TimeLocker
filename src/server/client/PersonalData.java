package client;
import java.awt.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PersonalData extends JFrame
{
	private JTable dataset;
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
            	PersonalData frame = new PersonalData();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public PersonalData() {
      setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\WORK\\\uAC00\uCC9C\uB300\uD559\uAD50\\2-2\\\uCEF4\uD4E8\uD130\uB124\uD2B8\uC6CC\uD06C \uBC0F \uC2E4\uC2B5(\uC774\uC8FC\uD615 \uAD50\uC218\uB2D8)\\Team Project\\Term project\\icon\\point.png"));
      setTitle("TimeLocker");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 450, 430);
      setLocationRelativeTo(null);   //창을 가운데에 뜨게함.
      setResizable(false);   //size변경하지 못하게 함.
      getContentPane().setLayout(null);

      JPanel MyPoint = new JPanel();
      MyPoint.setBackground(new Color(119, 136, 153));
      MyPoint.setBounds(0, 0, 444, 401);
      getContentPane().add(MyPoint);
      MyPoint.setLayout(null);

      JLabel lblMyPoint = new JLabel("My Point");
      lblMyPoint.setFont(new Font("Arial Black", Font.PLAIN, 15));
      lblMyPoint.setBounds(6, 10, 103, 18);
      MyPoint.add(lblMyPoint);

      JButton GoBack = new JButton("Go Back");
      GoBack.setBounds(12, 368, 97, 23);
      MyPoint.add(GoBack);

      JButton UsePoint = new JButton("Use the Point");
      UsePoint.setBounds(314, 368, 118, 23);
      MyPoint.add(UsePoint);
      
      dataset = new JTable();
      dataset.setBackground(new Color(105, 105, 105));
      dataset.setModel(new DefaultTableModel(
      	new Object[][] {
      		{"3POP", "01:40"},
      		{"Z", "8:10"},
      		{"Gold Crown", "00:08"},
      		{null, null},
      		{null, null},
      	},
      	new String[] {
      		"PC Room", "Remain Time"
      	}
      ));
      dataset.setBounds(12, 356, 420, -318);
      MyPoint.add(dataset);

   }
}