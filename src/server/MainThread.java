import java.io.*;
import java.net.*;
import java.sql.*;

public class MainThread implements Runnable {
	Socket cSocket;
	Connection dbcon;
	
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
				dbcon = con;
				System.out.println(con);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			// main operation
			// TODO: make main operation of all function
			char flag = inputData.charAt(0);
			int dataNum = Integer.parseInt(inputData.substring(1, 2));
			
			switch (flag) {
			case 'a': // Login Operation
				String id, psw;
				int operatorIndex = inputData.indexOf('|');
				id = inputData.substring(2, operatorIndex);
				psw = inputData.substring(operatorIndex + 1);
				boolean result = logIn(id, psw);
				if (result)
					outputData = "log-in success.";
				else
					outputData = "log-in fail.";
				break;
			// TODO: make another operation code
			default:
				outputData = "This option is unavailable.";
			}
		
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
	
	private boolean logIn(String id, String password) {
		boolean success = false;
		
		try {
		Statement stmt = dbcon.createStatement();
		ResultSet rs = null;
		
		String sql = "select ID, PASSWORD from USERS where "
				+ "ID = '" + id + "' and password = '" + password + "'";
		rs = stmt.executeQuery(sql);
		rs.last();
		int count = rs.getRow();
		if (count == 1)
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
