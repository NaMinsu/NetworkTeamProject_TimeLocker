import java.io.*;
import java.net.*;
import java.sql.*;

public class MainThread implements Runnable {
	Socket cSocket;
	
	public MainThread(Socket connection) {
		cSocket = connection;
	}
	
	@Override
	public void run() {
		String inputData, outputData = null;
		
		try {
			// read data
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(cSocket.getOutputStream());
			inputData = inFromClient.readLine();
			
			// open database
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost/timeLocker_DB";
				String user = "root", password = "";
				con = DriverManager.getConnection(url, user, password);
				System.out.println(con);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			// main operation
			// TODO: make main operation of all function
		
			// close database
			try {
				if (con != null && !con.isClosed())
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// write output
			outToClient.writeBytes(outputData);
			cSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private bool logIn(String id, String password) {
		bool success = false;
		
		// TODO: make log-in operation
		
		return success;
	}
	
	private int timeToPoint(int time) {
		int point = 0;
		
		// TODO: make change time to point operation
		
		return point;
	}
	
	private int pointToTime(int point) {
		int time = 0;
		
		// TODO: make change point to time operation
		
		return time;
	}
	
	private String[] searchPCRoomList(String[] address) {
		String[] list = null;
		
		// TODO: make search PCRoom by address operation
		
		return list;
	}
}
