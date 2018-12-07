package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public abstract class MainClient extends JFrame implements ActionListener
{
   public static String accessID;
   public static Login loginPage;
   public static SearchPCRoomPanel searchPage;
   public static PointPanel pointPage;
   public static ChangePointPanel changePage;
   public static FinalPanel finalPage;

   public static void main(String[] args)
   {
      loginPage = new Login();
      pointPage = new PointPanel();
      searchPage = new SearchPCRoomPanel();
      changePage = new ChangePointPanel();
      finalPage = new FinalPanel();

      loginPage.setVisible(true);
      
      //button actionListener
      //loginPage
      // TODO: making login
     loginPage.loginButton.addActionListener(new ActionListener() {
    	 @Override
    	 public void actionPerformed(ActionEvent e) {
    	 accessID = loginPage.getAccessID();
    	 loginPage.setVisible(false);
         pointPage.setVisible(true);
         searchPage.setVisible(false);
         changePage.setVisible(false);
//         finalPage.setVisible(false);
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
            searchPage.setVisible(false);
            changePage.setVisible(false);
//            finalPage.setVisible(false);
         }
      });
      
      pointPage.UsePoint.addActionListener(new ActionListener() 
      {
         public void actionPerformed(ActionEvent arg0)
         {
            loginPage.setVisible(false);
            pointPage.setVisible(false);
            searchPage.setVisible(true);
            changePage.setVisible(false);
//            finalPage.setVisible(false);
         }
      });
      
      //searchPage
      searchPage.BackToPreviousPage.addActionListener(new ActionListener() 
      {
         public void actionPerformed(ActionEvent arg0)
         {
            loginPage.setVisible(false);
            pointPage.setVisible(true);
            searchPage.setVisible(false);
            changePage.setVisible(false);
//            finalPage.setVisible(false);
         }
      });
      
      searchPage.GoToNextPage.addActionListener(new ActionListener() 
      {
         public void actionPerformed(ActionEvent arg0)
         {
            loginPage.setVisible(false);
            pointPage.setVisible(false);
            searchPage.setVisible(false);
            changePage.setVisible(true);
//            finalPage.setVisible(false);
         }
      });
      
      //changePage
      changePage.ChangePoint.addActionListener(new ActionListener() 
      {
         public void actionPerformed(ActionEvent arg0)
         {
            loginPage.setVisible(false);
            pointPage.setVisible(false);
            searchPage.setVisible(false);
            changePage.setVisible(false);
//            finalPage.setVisible(true);
         }
      });
   }
}