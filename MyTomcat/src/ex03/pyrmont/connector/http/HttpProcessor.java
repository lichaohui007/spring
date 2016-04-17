package ex03.pyrmont.connector.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.servlet.ServletException;

import org.apache.catalina.util.RequestUtil;
import org.apache.tomcat.util.res.StringManager;

import ex03.pyrmont.ServletProcessor;
import ex03.pyrmont.StaticResourcePrecessor;

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
			//鑾峰彇socket	杈撳叆娴�
			input = new SocketInputStream(socket.getInputStream(), 2048);
			output = socket.getOutputStream();
			request = new HttpRequest(input);
			
			response = new HttpResponse(output);
			response.setRequest(request);
			
			response.setHeader("Server", "Pyrmont Servlet Container");
			parseRequest(input, output);
			parseHeaders(input);
			
			if(request.getRequestURI().startsWith("/servlet")){
				ServletProcessor processor = new ServletProcessor();
				processor.process(request, response);
			}else{
				StaticResourcePrecessor processor =  new StaticResourcePrecessor();
				processor.process(request, response);
			}
			
			socket.close();
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
		
		//鍒ゆ柇鏄惁鏄竴涓粷瀵瑰湴鍧�殑url
		if(!uri.startsWith("/")){
			int pos = uri.indexOf("://");
			if(pos != -1){//璇佹槑鏄粷瀵瑰湴鍧�
				pos = uri.indexOf("/", pos + 3);//鎵惧埌http://鍚庣殑绗竴涓�鐨勪綅缃�
				if(pos == -1){
					uri = "";
				}else{
					uri = uri.substring(pos);
				}
			}
		}
		
		String match = ";jsessionid";
		int semicolon = uri.indexOf(match);//鎵惧埌jsessionid鐨勪綅缃�
		if(semicolon >= 0){
			//浠巎sessionid寮�鍚戝悗鎴彇
			String rest = uri.substring(semicolon + match.length());
			//鍦ㄥ悗闈㈢殑uri涓壘鍒扮涓�釜锛涚殑浣嶇疆
			int semicolon2 = rest.indexOf(";");
			if(semicolon2 > 0){
				request.setRequestedSessionId(rest.substring(0, semicolon2));
				rest = rest.substring(semicolon2);
			}else{//涓嶅瓨鍦紱
				request.setRequestedSessionId(rest);
				rest = "";
			}
			request.setRequestedSessionURL(true);
			uri = uri.substring(0, semicolon) + rest;//鍦╱ri涓繃婊ゆ帀jsessionid
		}else{
			request.setRequestedSessionURL(false);
			request.setRequestedSessionId(null);
		}
		//String normalizedUri = 
				
		((HttpRequest) request).setMethod(method);
	}
}
