package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public abstract class MainClient extends JFrame implements ActionListener
{
   public static String accessID;
   public static Login loginPage;
   public static PointPanel pointPage;
   public static ConfirmPanel confirmPage;

   public static void main(String[] args)
   {
      loginPage = new Login();
      pointPage = new PointPanel();
      confirmPage = new ConfirmPanel();

      loginPage.setVisible(true);

      //button actionListener
      //loginPage
      loginPage.loginButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            //PROTOCOL 합치는 과정
            String in = (loginPage.tf.getText()).concat("|");
            String pw = new String (loginPage.pf.getPassword());
            String info = in.concat(pw);

            loginPage.protocol = loginPage.protocol.concat(info);

            loginPage.socketStarting();

            if (loginPage.isLogIn()) {
            	accessID = loginPage.getAccessID();
            	pointPage.accessID = accessID;
            	confirmPage.accessID = accessID;
            	
            	pointPage.updateList();
            	
            	loginPage.setVisible(false);
                pointPage.setVisible(true);
                confirmPage.setVisible(false);
            }
         }
      });

      //pointPage
      pointPage.GoBack.addActionListener(new ActionListener() 
      {
         @Override
         public void actionPerformed(ActionEvent arg0)
         {
            loginPage.setVisible(true);
            pointPage.setVisible(false);
            confirmPage.setVisible(false);
         }
      });

      pointPage.PointToTime.addActionListener(new ActionListener() 
      {
         public void actionPerformed(ActionEvent arg0)
         {
        	if (pointPage.setSelected()) { 
        	    confirmPage.selected = pointPage.selected;
        	    confirmPage.updateList();
        		loginPage.setVisible(false);
                pointPage.setVisible(false);
                confirmPage.setVisible(true);
        	}
         }
      });

      pointPage.TimeToPoint.addActionListener(new ActionListener() 
      {
         public void actionPerformed(ActionEvent arg0)
         {
            pointPage.pointExchanger();
            if (pointPage.updated) {
            	pointPage.updated = false;
            	pointPage.setVisible(false);
            	pointPage.updateList();
            	pointPage.setVisible(true);
            }
         }
      });
      
      confirmPage.ConfirmButton.addActionListener(new ActionListener() 
      {
         public void actionPerformed(ActionEvent arg0)
         {
        	 confirmPage.buyTime();
        	 if (confirmPage.updated) {
        		 confirmPage.updated = false;
        		 pointPage.updateList();
        		 loginPage.setVisible(false);
        		 pointPage.setVisible(true);
        		 confirmPage.setVisible(false);
        	 }
         }
      });
   }
}
