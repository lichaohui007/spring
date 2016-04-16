package ex03.pyrmont.connector.http;

import java.io.OutputStream;
import java.net.Socket;

import org.apache.tomcat.util.res.StringManager;

public class HttpProcessor {

	private HttpConnector connector = null;
	private HttpRequest request;
	private HttpRequestLine requestLine = new HttpRequestLine();
	private HttpResponse response;
	
	protected String method = null;
	protected String queryString = null;
	
	protected StringManager sm = StringManager.getManager("ex03.pyrmont.connector.http");
	public HttpProcessor(HttpConnector connector) {
		// TODO Auto-generated constructor stub
		this.connector = connector;
	}
	
	public void process(Socket socket){
		SocketInputStream input = null;
		OutputStream output = null;
		
		try{
			input = new SocketInputStream(socket.getInputStream(), 2048);
			output = socket.getOutputStream();
			request = new HttpRequest(input);
			
			response = new HttpResponse(output);
			response.
		}catch(Exception e){
			
		}
	}
}
