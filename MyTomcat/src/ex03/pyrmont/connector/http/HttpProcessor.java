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
		String method = new String(requestLine.method, 0, requestLine.methodEnd);
		String uri = null;
		String protocol = new String(requestLine.protocol, 0, requestLine.protocolEnd);
		
		if(method.length() < 1){
			throw new ServletException("Missing HTTP request method");
		}else if(requestLine.uriEnd < 1){
			throw new ServletException("Missing HTTP request URI");
		}
		
		int question = requestLine.indexOf("?");
		if(question >= 0){
			request.setQureyString(new String(requestLine.uri, question + 1, requestLine.uriEnd - question -1));
			uri = new String(requestLine.uri, 0, question);
		}else{
			request.setQureyString(null);
			uri = new String(requestLine.uri, 0, requestLine.uriEnd);
		}
		
		//判断是否是一个绝对地址的url
		if(!uri.startsWith("/")){
			int pos = uri.indexOf("://");
			if(pos != -1){//证明是绝对地址
				pos = uri.indexOf("/", pos + 3);//找到http://后的第一个/的位置
				if(pos == -1){
					uri = "";
				}else{
					uri = uri.substring(pos);
				}
			}
		}
		
		String match = ";jsessionid";
		int semicolon = uri.indexOf(match);//找到jsessionid的位置
		if(semicolon >= 0){
			//从jsessionid开始向后截取
			String rest = uri.substring(semicolon + match.length());
			//在后面的uri中找到第一个；的位置
			int semicolon2 = rest.indexOf(";");
			if(semicolon2 > 0){
				request.setRequestedSessionId(rest.substring(0, semicolon2));
				rest = rest.substring(semicolon2);
			}else{//不存在；
				request.setRequestedSessionId(rest);
				rest = "";
			}
			request.setRequestedSessionURL(true);
			uri = uri.substring(0, semicolon) + rest;//在uri中过滤掉jsessionid
		}else{
			request.setRequestedSessionURL(false);
			request.setRequestedSessionId(null);
		}
		//String normalizedUri = 
				
		((HttpRequest) request).setMethod(method);
	}
}
