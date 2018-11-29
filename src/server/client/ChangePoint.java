package client;
import java.awt.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ChangePoint extends JFrame
{
	private JTable dataset;
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
            	ChangePoint frame = new ChangePoint();
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
   public ChangePoint() 
   {
      setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\WORK\\\uAC00\uCC9C\uB300\uD559\uAD50\\2-2\\\uCEF4\uD4E8\uD130\uB124\uD2B8\uC6CC\uD06C \uBC0F \uC2E4\uC2B5(\uC774\uC8FC\uD615 \uAD50\uC218\uB2D8)\\Team Project\\Term project\\icon\\point.png"));
      setTitle("TimeLocker");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 450, 430);
      setLocationRelativeTo(null);   //â�� ����� �߰���.
      setResizable(false);   //size�������� ���ϰ� ��.
      getContentPane().setLayout(null);

      JPanel ChangeoverPoint = new JPanel();
      ChangeoverPoint.setBounds(0, 0, 444, 401);
      getContentPane().add(ChangeoverPoint);
      ChangeoverPoint.setLayout(null);
      ChangeoverPoint.setVisible(false);   //ó������ �� ���̰� �ϱ�

      JButton ChangePoint = new JButton("Change the Point");
      ChangePoint.setBounds(12, 368, 145, 23);
      ChangeoverPoint.add(ChangePoint);

      JLabel lblChangeThePoint = new JLabel("Change the Point");
      lblChangeThePoint.setFont(new Font("Arial Black", Font.PLAIN, 15));
      lblChangeThePoint.setBounds(12, 10, 157, 18);
      ChangeoverPoint.add(lblChangeThePoint);
  }
}