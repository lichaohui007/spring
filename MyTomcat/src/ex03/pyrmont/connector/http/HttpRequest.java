package ex03.pyrmont.connector.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.util.Enumerator;
import org.apache.catalina.util.ParameterMap;
import org.apache.catalina.util.RequestUtil;

import ex03.pyrmont.connector.RequestStream;

public class HttpRequest implements HttpServletRequest{

	private String contentType;
	private int contentLength;
	private InetAddress inetAddress;
	private InputStream input;
	private String method;
	private String protocol;
	private String queryString;
	private String requestURI;
	private String serverName;
	private int serverPort;
	private Socket socket;
	private boolean requestedSessionCookie;
	private String requestedSessionId;
	private boolean requestedSessionURL;
	
	protected HashMap attributes = new HashMap();
	protected String authorization = null;
	protected String contextPath = "";
	protected ArrayList cookies = new ArrayList();
	protected static ArrayList empty = new ArrayList();
	
	protected SimpleDateFormat[] formats = {
			new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz",Locale.US),
			new SimpleDateFormat("EEEEEE dd-MM-yy HH:mm:ss zzz", Locale.US),
			new SimpleDateFormat("EEE MMMM d HH:mm:ss yyyy", Locale.US)
	};
	
	protected HashMap headers = new HashMap();
	
	protected ParameterMap parameters = null;
	
	protected boolean parsed = false;
	protected String pathInfo = null;
	
	protected BufferedReader reader = null;
	
	protected ServletInputStream stream =  null;
	
	public HttpRequest(InputStream input){
		this.input = input;
	}
	
	public void addHeader(String name, String value){
		name = name.toLowerCase();
		synchronized (headers) {
			ArrayList values = (ArrayList) headers.get(name);
			if(values == null){
				values = new ArrayList();
				headers.put(name, values);
			}
			values.add(value);
		}
	}
	
	protected void parseParameters(){
		if(parsed)//对象的共享变量
			return;
		ParameterMap results =  parameters;
		if(results == null)
			results = new ParameterMap();
		results.setLocked(false);
		String encoding =  getCharacterEncoding();
		if(encoding == null)
			encoding = "ISO-8859-1";
		String queryString =  getQueryString();
		try {
			RequestUtil.parseParameters(results, queryString, encoding);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String contentType = getContentType();
		if(contentType == null)
			contentType = "";
		int semicolon = contentType.indexOf(';');
		if(semicolon > 0){
			contentType = contentType.substring(0, semicolon).trim();
		}else{
			contentType = contentType.trim();
		}
		
		if("POST".equals(getMethod()) && (getContentLength() > 0) && "application/x-www-form-urlencoded".equals(contentType)){
			try{
				int max = getContentLength();
				int len = 0;
				byte[] buf = new byte[getContentLength()];
				ServletInputStream is = getInputStream();
				
				while(len < max){
					int next = is.read(buf, len, max - len);
					if(next < 0){
						break;
					}
					len += next;
				}
				is.close();
				if(len < max){
					throw new RuntimeException("Content length mismathch");
				}
				RequestUtil.parseParameters(results, buf, encoding);
			}catch(Exception e){
				throw new RuntimeException("Content read fail");
			}
			
		}
		
		results.setLocked(true);
		parsed = true;
		parameters = results;
	}
	
	public void addCookie(Cookie cookie){
		synchronized (cookies) {
			cookies.add(cookie);
		}
	}
	
	public ServletInputStream createInputStream() throws IOException{
		return (new RequestStream(this));
	}
	
	public InputStream getStream(){
		return input;
	}
	
	public void setContentLength(int length){
		this.contentLength = length;
	}
	
	public void setContentType(String type){
		this.contentType = type;
	}
	
	public void setInet(InetAddress inetAddress){
		this.inetAddress = inetAddress;
	}
	
	public void setContextPath(String path){
		if(path == null)
			this.contextPath = "";
		else
			this.contextPath = path;
		
	}
	
	public void setMethod(String method){
		this.method = method;
	}
	
	public void setPathInfo(String path){
		this.pathInfo = path;
	}
	
	public void setProtocol(String protocol){
		this.protocol = protocol;
	}
	
	public void setQureyString(String queryString){
		this.queryString = queryString;
	}
	
	public void setRequestURI(String requestURI){
		this.requestURI = requestURI;
	}
	
	public void setServerName(String name){
		this.serverName = name;
	}
	
	public void setServerPort(int port){
		this.serverPort = port;
	}
	
	public void setSocket(Socket socket){
		this.socket = socket;
	}
	
	public void setRequestedSessionCookie(boolean flag){
		this.requestedSessionCookie = flag;
	}
	
	public void serRequestedSessionId(String requestedSessionId){
		this.requestedSessionId = requestedSessionId;
	}
	
	public void setRequestedSessionURL(boolean flag){
		this.requestedSessionURL = flag;
	}
	@Override
	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		synchronized (attributes) {
			return (attributes.get(name));
		}
		
	}

	@Override
	public Enumeration getAttributeNames() {
		// TODO Auto-generated method stub
		synchronized (attributes) {
			return (new Enumerator(attributes.keySet()));
		}
		//return null;
	}

	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getContentLength() {
		// TODO Auto-generated method stub
		return contentLength;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return contentType;
	}

	/*@Override
	public ServletInputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration getLocales() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParameter(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map getParameterMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration getParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getParameterValues(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRealPath(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getServerPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeAttribute(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAttribute(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCharacterEncoding(String arg0)
			throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAuthType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cookie[] getCookies() {
		// TODO Auto-generated method stub
		synchronized (cookies) {
			if(cookies.size() < 1)
				return (null);
			Cookie[] results = new Cookie[cookies.size()];
			return (Cookie[]) cookies.toArray(results);
		}
		//return null;
	}

	@Override
	public long getDateHeader(String name) {
		// TODO Auto-generated method stub
		String value = getHeader(name);
		return 0;
	}

	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		name = name.toLowerCase();
		synchronized (headers) {
			ArrayList values = (ArrayList) headers.get(name);
			if(values != null)
				return (String) values.get(0);
			else
				return null;
		}
		//return ;
	}

	@Override
	public Enumeration getHeaderNames() {
		// TODO Auto-generated method stub
		synchronized (headers) {
			return (new Enumerator(headers.keySet()));
		}
		//return null;
	}

	@Override
	public Enumeration getHeaders(String name) {
		// TODO Auto-generated method stub
		name = name.toLowerCase();
		synchronized (headers) {
			ArrayList values = (ArrayList) headers.get(name);
			
		}
		return null;
	}

	@Override
	public int getIntHeader(String arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPathInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPathTranslated() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRemoteUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuffer getRequestURL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestedSessionId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpSession getSession(boolean arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRequestedSessionIdFromCookie() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromURL() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromUrl() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUserInRole(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
}