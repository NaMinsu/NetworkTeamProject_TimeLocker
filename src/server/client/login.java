package client;


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
//import com.sun.prism.paint.Color;


public class login extends JFrame implements ActionListener 
{
 private String id = "hong";
 private String password = "123123";
 private JTextField tf;
 private JPasswordField pf;
 private JButton login;
 JLabel loginText = new JLabel();
 private boolean isLogin = false;

 public login() {
  JPanel idPanel = new JPanel();
  idPanel.setForeground(UIManager.getColor("Button.disabledText"));
  idPanel.setBackground(SystemColor.window);
  JPanel passPanel = new JPanel();
  tf = new JTextField(12);
  pf = new JPasswordField(10);
  //loginText.setBackground(Color.RED);
  Container contentPane = getContentPane(); 
	contentPane.setBackground(Color.DARK_GRAY); 
	contentPane.setLayout(new FlowLayout()); 


  
  JLabel idLabel = new JLabel("ID : ");
  idLabel.setFont(new Font("YuKyokasho Yoko", Font.PLAIN, 11));
  JLabel passLabel = new JLabel("PASSWORD : ");
  passLabel.setFont(new Font("YuKyokasho Yoko", Font.PLAIN, 11));
  login = new JButton("LOGIN");
  login.setFont(new Font("YuKyokasho Yoko", Font.PLAIN, 11));
  login.addActionListener(this);
  idPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
  
  idPanel.add(idLabel);
  idPanel.add(tf);

  passPanel.add(passLabel);
  passPanel.add(pf);

  getContentPane().add(idPanel);
  getContentPane().add(passPanel);
  getContentPane().add(login);
  getContentPane().add(loginText);
  
  getContentPane().setLayout(new FlowLayout());

  setTitle("LOGIN");
  setSize(300, 200);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  setVisible(true);
 }

 
 @Override
 public void actionPerformed(ActionEvent e) 
 {
  // TODO Auto-generated method stub
  if (e.getSource() == login) 
  {
   try {
    if (id.equals(tf.getText()) && password.equals(pf.getText()))
     isLogin = true;
    else
     isLogin = false;
    if (isLogin) {
     loginText.setText("hh.");
    } else {
     loginText.setText("g");
    }
   } catch (Exception e1) {
    System.out.println("false");
   }
  }
 }

 
 public static void main(String[] args)throws IOException
 {
  // TODO Auto-generated method stub
    login my = new login();
    
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	Socket sock = null; // 접속할 소켓
	DataInputStream read; // READ 스트림
	DataOutputStream write; // WRITE 스트림

	String send_str = "", recv_str = ""; // 서버에게 보낼 문자열

	try {
		sock = new Socket("127.0.0.1", 60000); //일단은 그냥 나랑 연결하게 

		// 소켓과 스트림결합
		read = new DataInputStream(sock.getInputStream()); // READ STREAM 연결
		write = new DataOutputStream(sock.getOutputStream()); // WRITE STREAM 연결

		System.out.println("*************** 접속성공! ***************");
		System.out.println("********* quit, exit 입력시 종료 ********");

	} catch (IOException e) {
		e.printStackTrace();
	}
 }
} 

