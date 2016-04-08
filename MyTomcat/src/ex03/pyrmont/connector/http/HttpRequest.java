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
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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
	private String requestSessionId;
	private boolean requestedSessionURL;
	
	protected HashMap attributes = new HashMap();
	protected String authorization = null;
	protected String contextPath = "";
	protected ArrayList cookies = new ArrayList();
	
	protected static ArrayList empty = new ArrayList();
	
	protected SimpleDateFormat[] formats = { 
			new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US),
			new SimpleDateFormat("EEEEEE, dd-MMM-yy HH:mm:ss zzz", Locale.US),
			new SimpleDateFormat("EEE MMMM d HH:mm:ss yyyy", Locale.US)
	}; 
	
	protected HashMap headers = new HashMap();
	
	protected ParameterMap parameters = null;
	
	protected boolean parsed = false;
	
	protected String pathInfo = null;
	
	protected BufferedReader reader = null;
	
	protected ServletInputStream stream = null;
	
	public HttpRequest(InputStream input){
		this.input = input;
	}
	
	public void addHeader(String name, String value){
		name = name.toLowerCase();
		//map + list
		synchronized (headers) {
			ArrayList values = (ArrayList)headers.get(name);
			if(values == null){
				values = new ArrayList();
				headers.put(name, values);
			}
			values.add(value);
		}
	}
	
	protected void parseParameters(){
		if(parsed)
			return;
		ParameterMap results = parameters;
		if(results == null)
			results = new ParameterMap();
		results.setLocked(false);
		String encoding = getCharacterEncoding();
		if(encoding == null)
			encoding = "ISO-8859-1";
		String queryString = getQueryString();
		try{
			RequestUtil.parseParameters(results, queryString, encoding);
		}catch(Exception e){
			;
		}
		String contentType = getContentType();
		if(contentType == null)
			contentType = "";
		int semicolon = contentType.indexOf(";");
		if(semicolon >= 0){
			contentType = contentType.substring(0,semicolon).trim();
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
					//读取max-len个字节的数据到字节数组byte中，初始偏移量为len
					int next = is.read(buf, len, max-len);
					if(next < 0){
						break;
					}
					len +=next;
				}
				is.close();
				if(len < max){
					throw new RuntimeException("Content length mismatch");
				}
				//将results进行
				RequestUtil.parseParameters(results, buf, encoding);;
			}catch(Exception e){
				;
			}
		}
		
		results.setLocked(true);
		parsed = true;
		parameters = results;
	}
	
	public ServletInputStream createInputStream() throws IOException{
		return (new RequestStream(this));
	}
	public void addCookie(Cookie cookie){
		synchronized (cookies) {
			cookies.add(cookie);
		}
	}
	
	public InputStream getStream(){
		return input;
	}
	
	public void setContentLength(int length){
		this.contentLength = length;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public void setInet(InetAddress inetAddress){
		this.inetAddress = inetAddress;
	}
	
	public void serContextPath(String path){
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
	
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}
	
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	 
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public AsyncContext getAsyncContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAttribute(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DispatcherType getDispatcherType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLocalAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLocalPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<Locale> getLocales() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParameter(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<String> getParameterNames() {
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
	public int getRemotePort() {
		// TODO Auto-generated method stub
		return 0;
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
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAsyncStarted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAsyncSupported() {
		// TODO Auto-generated method stub
		return false;
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
	public AsyncContext startAsync() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AsyncContext startAsync(ServletRequest arg0, ServletResponse arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean authenticate(HttpServletResponse arg0) throws IOException,
			ServletException {
		// TODO Auto-generated method stub
		return false;
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
		return null;
	}

	@Override
	public long getDateHeader(String arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getHeader(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<String> getHeaders(String arg0) {
		// TODO Auto-generated method stub
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
	public Part getPart(String arg0) throws IOException, IllegalStateException,
			ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Part> getParts() throws IOException,
			IllegalStateException, ServletException {
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

	@Override
	public void login(String arg0, String arg1) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logout() throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
