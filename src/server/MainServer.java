import java.net.*;
import java.io.IOException;

public class MainServer {
	public static void main(String[] args) throws IOException {
		ServerSocket svsck = new ServerSocket(777);
		// System.out.println("Server starts running...");
		
		while(true) {
			Socket connection = svsck.accept();
			if (connection.isConnected()) {
				MainThread mt = new MainThread(connection);
				Thread t = new Thread(mt);
				t.start();
			}
		}
	}
}
