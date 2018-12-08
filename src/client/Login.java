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
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Component;


public class Login extends JFrame
{
   public JTextField tf;
   public JPasswordField pf;
   public static JButton loginButton;
   JLabel loginText = new JLabel();
   public String protocol = "a2";
   private static final String loginFail = "log-in fail.";
   private String accessID;
   private boolean isLoggingOn = false;


   public Login() 
   {
   	getContentPane().setForeground(new Color(253, 245, 230));
      //setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\WORK\\\uAC00\uCC9C\uB300\uD559\uAD50\\2-2\\\uCEF4\uD4E8\uD130\uB124\uD2B8\uC6CC\uD06C \uBC0F \uC2E4\uC2B5(\uC774\uC8FC\uD615 \uAD50\uC218\uB2D8)\\Team Project\\Term project\\icon\\point.png"));
      setTitle("TimeLocker");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(0, 0, 444, 401);   //size 수정해야됨.
      setLocationRelativeTo(null);   //창을 가운데에 뜨게함.
      //setResizable(false);   //size변경하지 못하게 함.
      getContentPane().setLayout(null);

      Container contentPane = getContentPane(); 
      contentPane.setBackground(new Color(255, 255, 255));
      getContentPane().setLayout(null);
      getContentPane().setLayout(null);
      JPanel idPanel = new JPanel();
      idPanel.setBounds(109, 131, 170, 39);
      idPanel.setForeground(new Color(253, 245, 230));
      idPanel.setBackground(new Color(255, 239, 213));
      tf = new JTextField(12);
      tf.setBounds(6, 6, 158, 26);
      idPanel.setLayout(null);
      idPanel.add(tf);
      
      JLabel lblNewLabel = new JLabel("");
      lblNewLabel.setBounds(155, 18, 97, 131);
      getContentPane().add(lblNewLabel);
      lblNewLabel.setIcon(new ImageIcon("sandglass.png"));
      
      getContentPane().add(idPanel);
      JPanel passPanel = new JPanel();
      passPanel.setBounds(109, 174, 170, 39);
      passPanel.setBackground(new Color(255, 239, 213));
      passPanel.setForeground(UIManager.getColor("ToolTip.background"));
      pf = new JPasswordField(12);
      pf.setBounds(6, 6, 158, 27);
      passPanel.setLayout(null);

      passPanel.add(pf);
      getContentPane().add(passPanel);
      loginButton = new JButton("LOGIN");
      loginButton.setBackground(new Color(192, 192, 192));
      loginButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });
      loginButton.setBounds(155, 225, 81, 29);
      loginButton.setFont(new Font("YuKyokasho Yoko", Font.PLAIN, 11));
      loginButton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      getContentPane().add(loginButton);
      loginText.setBounds(243, 70, 0, 0);
      getContentPane().add(loginText);

      JLabel idLabel = new JLabel("ID : ");
      getContentPane().add(idLabel);
      idLabel.setBounds(76, 145, 30, 17);
      idLabel.setFont(new Font("YuKyokasho Yoko", Font.PLAIN, 11));
      JLabel passLabel = new JLabel("PW : ");
      passLabel.setBounds(76, 184, 30, 17);
      getContentPane().add(passLabel);
      passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      passLabel.setFont(new Font("YuKyokasho Yoko", Font.PLAIN, 11));
      
      JLabel lblTimelocker = new JLabel("TimeLocker");
      lblTimelocker.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
      lblTimelocker.setBounds(141, 29, 105, 16);
      getContentPane().add(lblTimelocker);

      setTitle("LOGIN");
      setSize(400, 300);
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
            isLoggingOn = false;

            return;
         }
         else {
            protocol="a2";
            accessID = recv_str;
            isLoggingOn = true;

         }
      }
      catch (IOException e) 
      {
         e.printStackTrace();
      }
   }

   public String getAccessID() {
      return accessID;
   }

   public boolean isLogIn() {
      return isLoggingOn;
   }
}
