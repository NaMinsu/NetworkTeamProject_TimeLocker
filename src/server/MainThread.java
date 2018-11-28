import java.io.*;
import java.net.*;
import java.sql.*;

public class MainThread implements Runnable {
	Socket cSocket;
	Connection dbcon;
	BufferedReader inFromClient;
	DataOutputStream outToClient;
	String inputData, outputData;
	
	public MainThread(Socket connection) {
		cSocket = connection;
	}
	
	@Override
	public void run() {
		outputData = null;
		
		try {
			// read data
			inFromClient = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
			outToClient = new DataOutputStream(cSocket.getOutputStream());
			inputData = inFromClient.readLine();
			
			// open database
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost/timeLocker_DB";
				String user = "root", password = "";
				dbcon = DriverManager.getConnection(url, user, password);
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
			int operatorIndex;
			String accessID;
			
			switch (flag) {
			case 'a': // Login Operation
				String id, psw;
				int operatorIndex = inputData.indexOf('|');
				id = inputData.substring(2, operatorIndex);
				psw = inputData.substring(operatorIndex + 1);
				boolean result = logIn(id, psw);
				// If login success, send id to client
				if (result)
					outputData = id;
				// If fail, send fail string to client
				else
					outputData = "log-in fail.";
				outToClient.writeBytes(outputData);
			case 'b': // search operation
				String[] address = new String[dataNum - 1];
				int[] operatorIndices = new int[dataNum - 1];
				operatorIndices[0] = inputData.indexOf('|');
				for (int i = 1; i < dataNum - 1; i++)
					operatorIndices[i] = inputData.substring(operatorIndices[i-1]).indexOf('|');
				accessID = inputData.substring(2, operatorIndices[0]);
				for (int i = 0; i < dataNum - 3; i++)
					address[i] = inputData.substring(operatorIndices[i] + 1, operatorIndices[i+1]);
				address[3] = inputData.substring(operatorIndices[dataNum - 2]);
				searchPCRoomList(accessID, address);
				break;
			case 'c': // exchange operation (point to time)
				operatorIndex = inputData.indexOf('|');
				accessID = inputData.substring(2, operatorIndex);
				int exchangePoint = Integer.parseInt(inputData.substring(operatorIndex + 1));
				pointToTime(accessID, exchangePoint);
				break;
			case 'd': // exchange operation (time to point)
				
				// TODO: make exchanging algorithm (time to point)
				
				break;
			default:
				outputData = "This option is unavailable.";
				outToClient.writeBytes(outputData);
			}
		
			// close database
			try {
				if (dbcon != null && !dbcon.isClosed())
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* parameter: String id, String password
	 * operation: check login information
	 * return: true if login success */
	private boolean logIn(String id, String password) {
		boolean isSuccess = false;
		
		try {
		Statement stmt = dbcon.createStatement();
		ResultSet rs = null;
		
		String sql = "select ID, PASSWORD from USERS where "
				+ "ID = '" + id + "' and password = '" + password + "'";
		rs = stmt.executeQuery(sql);
		rs.last();
		int count = rs.getRow();
		if (count == 1)
			isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isSuccess;
	}
	
	private void timeToPoint(String aid, int time) {
		int exchangeFee;
		int plusPoint;
		
		// TODO: make change time to point operation
		try {
			Statement stmt = dbcon.createStatement();
			String update = "update REGISTERED set POINT ";
			String sql = "";
			rs = stmt.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void pointToTime(String aid, int point) {
		int plusTime;
		
		// TODO: make change point to time operation
		try {
			Statement stmt = dbcon.createStatement();
			String update = "update REGISTERED set LEFTTIME ";
			String sql = "";
			rs = stmt.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void searchPCRoomList(String aid, String[] address) {
		
		// TODO: make search PCRoom by address operation
		try {
			Statement stmt = dbcon.createStatement();
			ResultSet rs = null;
			
			String[] Acon = new String[4];
			Acon[0] = "DO = '" + address[0] + "'";
			Acon[1] = "SI = '" + address[1] + "'";
			Acon[2] = "GU = '" + address[2] + "'";
			Acon[3] = "DONG = '" + address[3] + "'";
			String condition = Acon[0] + " and " + Acon[1] + " and "
					+ Acon[2] + " and " + Acon[3];
			String sql = "select NAME, LEFTTIME, POINT from PCROOM natural join "
					+ "(select PCR_ID, LEFTTIME, POINT from REGISTERED"
					+ " where USER_ID = '" + aid + "') where " + condition;
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String name = rs.getString(1);
				int leftTime = rs.getInt(2);
				int point = rs.getInt(3);
				
				outputData = name + "\t" + leftTime + "\t" + point +"\n";
				outToClient.writeBytes(outputData);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
