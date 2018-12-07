package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Font;

public class Login extends JFrame
{
   private JTextField tf;
   private JPasswordField pf;
   public static JButton loginButton;
   JLabel loginText = new JLabel();
   private String protocol = "a2";
   private static final String loginFail = "log-in fail.";
   private String accessID;

   public Login() 
   {
      JPanel idPanel = new JPanel();
      idPanel.setForeground(UIManager.getColor("Button.disabledText"));
      idPanel.setBackground(SystemColor.window);
      JPanel passPanel = new JPanel();
      passPanel.setForeground(UIManager.getColor("ToolTip.background"));
      tf = new JTextField(12);
      pf = new JPasswordField(10);

      Container contentPane = getContentPane(); 
      contentPane.setBackground(Color.GRAY); 
      contentPane.setLayout(new FlowLayout()); 

      JLabel idLabel = new JLabel("ID : ");
      idLabel.setFont(new Font("YuKyokasho Yoko", Font.PLAIN, 11));
      JLabel passLabel = new JLabel("PASSWORD : ");
      passLabel.setFont(new Font("YuKyokasho Yoko", Font.PLAIN, 11));
      loginButton = new JButton("LOGIN");
      loginButton.setFont(new Font("YuKyokasho Yoko", Font.PLAIN, 11));
      loginButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) 
      {
         //PROTOCOL 합치는 과정
         String in = (tf.getText()).concat("|");
         String pw = new String (pf.getPassword());
         String info = in.concat(pw);

         protocol = protocol.concat(info);
         
         socketStarting();
      }});
      idPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

      idPanel.add(idLabel);
      idPanel.add(tf);

      passPanel.add(passLabel);
      passPanel.add(pf);

      getContentPane().add(idPanel);
      getContentPane().add(passPanel);
      getContentPane().add(loginButton);
      getContentPane().add(loginText);

      getContentPane().setLayout(new FlowLayout());

      setTitle("LOGIN");
      setSize(300, 200);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }

   public void socketStarting()
   {
      // TODO Auto-generated method stub      
      Socket sock = null;
      BufferedReader read;
      DataOutputStream write;
      String recv_str = "";

      try 
      {
         sock = new Socket("127.0.0.1", 777);

         read = new BufferedReader(new InputStreamReader(sock.getInputStream()));
         write = new DataOutputStream(sock.getOutputStream());

         System.out.println("*************** Connect Success ***************");       

         // 서버에게 문자열 값을 전달하는과정
         write.writeBytes(protocol + "\n");
         recv_str = read.readLine();
         
         if (recv_str.equals(loginFail)) {
        	 tf.setText("");
        	 pf.setText("");
        	 return;
         }
         else
        	 accessID = recv_str;
      }
      catch (IOException e) 
      {
         e.printStackTrace();
      }
   }
   
   public String getAccessID() {
	   return accessID;
   }
}