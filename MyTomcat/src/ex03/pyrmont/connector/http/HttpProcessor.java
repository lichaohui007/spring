package ex03.pyrmont.connector.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.servlet.ServletException;

import org.apache.catalina.util.RequestUtil;
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
			//获取socket	输入流
			input = new SocketInputStream(socket.getInputStream(), 2048);
			output = socket.getOutputStream();
			request = new HttpRequest(input);
			
			response = new HttpResponse(output);
			response.setRequest(request);
			
			response.setHeader("Server", "Pyrmont Servlet Container");
			
			
		}catch(Exception e){
			
		}
	}
	
	private void parseHeaders(SocketInputStream input) throws IOException, ServletException{
		while(true){
			HttpHeader header = new HttpHeader();
			input.readHeader(header);
			if(header.nameEnd == 0){
				if(header.valueEnd == 0){
					return;
				}else{
					throw new ServletException(sm.getString("httpProcessor.parseHeaders.colon"));
				}
			}
			String name = new String(header.name, 0, header.nameEnd);
			String value = new String(header.value, 0, header.valueEnd);
			request.addHeader(name, value);
			if(name.equals("cookie")){
				//Cookie[] cookies = RequestUtil.pa
			}else if(name.equals("content-length")){
				int n = -1;
				try{
					n = Integer.parseInt(value);
				}catch(Exception e){
					throw new ServletException(sm.getString("httpProcessor.parseHeaders.contentLength"));
				}
				request.setContentLength(n);
			}else if(name.equals("content-type")){
				request.setContentType(value);
			}
		}//while(true)
	}
	
	private void parseRequest(SocketInputStream input, OutputStream output) throws IOException,ServletException{
		input.readRequestLine(requestLine);
		
	}
}
