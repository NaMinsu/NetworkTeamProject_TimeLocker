package client.client;

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
import java.awt.event.ActionLister;

//import com.sun.prism.paint.Color;

public class Login extends JFrame implements ActionListener 
{
   private String id = "hong";
   private String password = "123123";
   private JTextField tf;
   private JPasswordField pf;
   public static JButton loginButton ;
   JLabel loginText = new JLabel();
   private boolean isLogin = false;

  
   
   private static String protocol = "a2";

   
   public Login() 
   {
      JPanel idPanel = new JPanel();
      idPanel.setForeground(UIManager.getColor("Button.disabledText"));
      idPanel.setBackground(SystemColor.window);
      JPanel passPanel = new JPanel();
      passPanel.setForeground(UIManager.getColor("ToolTip.background"));
      tf = new JTextField(12);
      pf = new JPasswordField(10);
      //loginText.setBackground(Color.RED);
      Container contentPane = getContentPane(); 
      contentPane.setBackground(Color.GRAY); 
      contentPane.setLayout(new FlowLayout()); 

      JLabel idLabel = new JLabel("ID : ");
      idLabel.setFont(new Font("YuKyokasho Yoko", Font.PLAIN, 11));
      JLabel passLabel = new JLabel("PASSWORD : ");
      passLabel.setFont(new Font("YuKyokasho Yoko", Font.PLAIN, 11));
      loginButton = new JButton("LOGIN");
      loginButton.setFont(new Font("YuKyokasho Yoko", Font.PLAIN, 11));
      loginButton.addActionListener(this);
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

   @Override
   public void actionPerformed(ActionEvent e) 
   {
 
      String in = (tf.getText()).concat("|");
      String pw = new String (pf.getPassword());
      String info = in.concat(pw);
      
      protocol = protocol.concat(info);
      
      System.out.println(protocol);
   }

   public void socketStarting()
   {
      // TODO Auto-generated method stub
      Login my = new Login(); 
  

      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

      
      Socket sock = null;
      DataInputStream read;
      DataOutputStream write;

      String send_str = "", recv_str = "";
      
      try 
      {
         sock = new Socket("127.0.0.1", 777);
         read = new DataInputStream(sock.getInputStream());
         write = new DataOutputStream(sock.getOutputStream());

         
         System.out.println("*************** Connect Success ***************");
     
         
        
				protocol = in.readLine();
				
				try {
					write.writeUTF(protocol);
					
				} catch (Exception e) {
				}
				
				try {
					recv_str = read.readUTF();
					
					while(recv_str.equals("log-in fail."))
					{
					
						tf=null;
						pf=null;
						
						
						
						
	
					}
			        	 
				} catch (Exception e) {
				}
				

         
      }catch (IOException e) 
      {
         e.printStackTrace();
      }
     // return recv_str;
     
   
   }
   
}

  
