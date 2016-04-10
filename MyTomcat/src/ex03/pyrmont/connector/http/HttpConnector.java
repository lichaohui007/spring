package ex03.pyrmont.connector.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpConnector implements Runnable{

	boolean stopped;
	private String schema = "http";
	
	public String getShema(){
		return schema;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		int port = 8080;
		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		while(!stopped){
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
			//Http
		}
	}

}
