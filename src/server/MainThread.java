import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class MainThread implements Runnable {
	Socket cSocket;
	Connection dbcon = null;
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
			// debugging
			System.out.println(inputData);
			
			// open database
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost/timeLocker_DB";
				String user = "root", password = "zmfhzjm0";
				dbcon = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			// main operation
			char flag = inputData.charAt(0);
			int dataNum = Integer.parseInt(inputData.substring(1, 2));
			// debugging
			System.out.println(flag + " " + dataNum);
			int operatorIndex;
			int[] operatorIndices;
			String accessID;
			String[] pcrNames;
			int k, workload;
			
			switch (flag) {
			case 'a': // login operation
				String id, psw;
				operatorIndex = inputData.indexOf('|');
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
				break;
			case 'b': // search operation
				accessID = inputData.substring(2);
				searchPCRoomList(accessID);
				break;
			case 'c': // exchange operation (point to time)			
				workload = (dataNum - 2) / 2;
				pcrNames = new String[workload];
				int[] points = new int[workload];
				k = 1;
				operatorIndices = new int[dataNum - 1];
				operatorIndices[0] = inputData.indexOf("|");
				for (int i = 1; i < dataNum - 1; i++)
					operatorIndices[i] = inputData.substring(operatorIndices[i-1] + 1).indexOf("|")
					+ operatorIndices[i - 1] + 1;
				accessID = inputData.substring(2, operatorIndices[0]);
				String targetName = inputData.substring(operatorIndices[0] + 1, operatorIndices[1]);
				for (int i = 0; i < workload; i++) {
					if (i != workload - 1) {
						pcrNames[i] = inputData.substring(operatorIndices[k] + 1, operatorIndices[k + 1]);
						k++;
						points[i] = Integer.parseInt(inputData.substring(operatorIndices[k] + 1, operatorIndices[k + 1]));
						k++;
					}
				}
				pcrNames[workload - 1] = inputData.substring(operatorIndices[k] + 1, operatorIndices[k + 1]);
				points[workload - 1] = Integer.parseInt(inputData.substring(operatorIndices[k + 1] + 1));
				pointToTime(accessID, targetName, pcrNames, points);
				outToClient.writeBytes(outputData);
				break;
			case 'd': // exchange operation (time to point)
				workload = (dataNum - 1) / 2;
				pcrNames = new String[workload];
				int[] times = new int[workload];
				k = 0;
				operatorIndices = new int[dataNum - 1];
				operatorIndices[0] = inputData.indexOf("|");
				for (int i = 1; i < dataNum - 1; i++)
					operatorIndices[i] = inputData.substring(operatorIndices[i-1] + 1).indexOf("|")
					+ operatorIndices[i - 1] + 1;
				accessID = inputData.substring(2, operatorIndices[0]);
				for (int i = 0; i < workload; i++) {
					if (i != workload - 1) {
						pcrNames[i] = inputData.substring(operatorIndices[k] + 1, operatorIndices[k + 1]);
						k++;
						times[i] = Integer.parseInt(inputData.substring(operatorIndices[k] + 1, operatorIndices[k + 1]));
						k++;
					}
				}
				pcrNames[workload - 1] = inputData.substring(operatorIndices[k] + 1, operatorIndices[k + 1]);
				times[workload - 1] = Integer.parseInt(inputData.substring(operatorIndices[k + 1] + 1));
				timeToPoint(accessID, pcrNames, times);
				outToClient.writeBytes(outputData);
				break;
			default:
				outputData = "This option is unavailable.";
				outToClient.writeBytes(outputData);
			}
		
			// close database
			try {
				if (dbcon != null && !dbcon.isClosed())
					dbcon.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* protocol code: a
	 * parameter: String id, String password
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
	
	/* protocol code: d
	 * parameter: id, pcroom names, times
	 * operation: exchange time to point 
	 * return: String success if operation success */
	private void timeToPoint(String aid, String[] pcNames, int[] times) {
		int plusPoint = 0;
		
		Statement stmt = null;
		ResultSet rs = null;
		try {
			for (int i = 0; i < times.length; i++) {
				int pcr_id = 0;
				plusPoint = times[i];
				stmt = dbcon.createStatement();
				String sql = "select ID from PCROOM where NAME = '"
						+ pcNames[i] + "'";
				rs = stmt.executeQuery(sql);
				if (rs.next())
					pcr_id = rs.getInt(1);
				
				stmt = dbcon.createStatement();
				String update = "update REGISTERED set LEFTTIME = LEFTTIME - "
						+ times[i] + " where PCR_ID = " + pcr_id
						+ " and USER_ID = '" + aid + "'";
				stmt.executeUpdate(update);
				
				stmt = dbcon.createStatement();
				update = "update REGISTERED set POINT = POINT + " + plusPoint
						+ " where PCR_ID = " + pcr_id
						+ " and USER_ID = '" + aid + "'";
				int count = stmt.executeUpdate(update);
				if (count == 1)
					outputData = "success";
				else {
					outputData = "fail";
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* protocol code: c
	 * parameter: id, target pcroom name, source pcroom names, points
	 * operation: exchange points to time of target pcroom 
	 * return: String success if update succress */
	private void pointToTime(String aid, String target, String[] pcrNames, int[] points) {
		int target_id = 0;
		int[] source_id = new int[points.length];
		int plusTime = 0;
		for (int i = 0; i < points.length; i++)
			plusTime += points[i] - feePolicy(pcrNames[i], target);
		
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = dbcon.createStatement();
			String sql = "select ID from PCROOM where NAME = '" + target + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next())
				target_id = rs.getInt(1);
			
			for (int i = 0; i < points.length; i++) {
				stmt = dbcon.createStatement();
				sql = "select ID from PCROOM where NAME = '" + pcrNames[i] +"'";
				rs = stmt.executeQuery(sql);
				if (rs.next())
					source_id[i] = rs.getInt(1);
				
				stmt = dbcon.createStatement();
				String update = "update REGISTERED set POINT = POINT - "
						+ points[i] + " where PCR_ID = " + source_id[i]
						+ " and USER_ID = '" + aid + "'";
				int counter = stmt.executeUpdate(update);
				if (counter == 1)
					outputData = "success";
				else
					outputData = "fail";
			}
			
			stmt = dbcon.createStatement();
			String update = "update REGISTERED set LEFTTIME = LEFTTIME + "
					+ plusTime + " where PCR_ID = " + target_id
					+ " and USER_ID = '" + aid + "'";
			int count = stmt.executeUpdate(update);
			if (count == 1)
				outputData = "success";
			else
				outputData = "fail";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/* protocol code: b
	 * parameter: user id
	 * operation: search PCRoom info for user
	 * return: array of PCRoom name, left time, point */
	private void searchPCRoomList(String aid) {
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			Statement stmt = dbcon.createStatement();
			ResultSet rs = null;
			
			String sql = "select NAME, LEFTTIME, POINT from PCROOM join REGISTERED "
					+ "on PCROOM.ID = REGISTERED.PCR_ID where "+
					"REGISTERED.USER_ID = '" + aid + "'";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String name = rs.getString(1);
				int leftTime = rs.getInt(2);
				int point = rs.getInt(3);
				
				String temp = name + "\t" + leftTime + "\t" + point;
				list.add(temp);
			}
			
			outputData = "";
			for (int i = 0; i < list.size(); i++) {
				outputData = outputData + list.get(i);
				if (i != list.size() - 1)
					outputData = outputData + ":";
			}
			
			outToClient.writeBytes(outputData);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* protocol code: none
	 * parameter: pcroom name, target pcroom name
	 * operation: calculate fee by each pcroom
	 * return: fee */
	private double feePolicy(String pcrName, String targetName) {
		double fee = 0;
		double temp = 0;
		
		int franchise_source = 0, franchise_destination = 0;
		
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = dbcon.createStatement();
			String sql = "select FRANCHISE from PCROOM where NAME = '"
					+ pcrName + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next())
				franchise_source = rs.getInt(1);
			System.out.println("Source: " + franchise_source);
			
			stmt = dbcon.createStatement();
			sql = "select FRANCHISE from PCROOM where NAME = '"
					+ targetName + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next())
				franchise_destination = rs.getInt(1);
			System.out.println("Destination: " + franchise_destination);
			
			if (franchise_source != franchise_destination) {
				stmt = dbcon.createStatement();
				sql = "select FEE from PCROOM where FRANCHISE = "
						+ franchise_destination;
				rs = stmt.executeQuery(sql);
				if (rs.next())
					temp = rs.getDouble(1);
				System.out.println("temp: " + temp);
				fee = temp / 100.0;
				System.out.println("fee: " + fee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return fee;
	}
}
