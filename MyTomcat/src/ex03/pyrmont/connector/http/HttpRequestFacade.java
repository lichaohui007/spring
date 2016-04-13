package ex03.pyrmont.connector.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpRequestFacade implements HttpServletRequest{

	private HttpServletRequest request;
	
	public HttpRequestFacade(HttpRequest request) {
		// TODO Auto-generated constructor stub
		this.request = request;
	}
	@Override
	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		return request.getAttribute(name);
	}

	@Override
	public Enumeration getAttributeNames() {
		// TODO Auto-generated method stub
		return request.getAttributeNames();
	}

	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return request.getCharacterEncoding();
	}

	@Override
	public int getContentLength() {
		// TODO Auto-generated method stub
		return request.getContentLength();
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return request.getContentType();
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return request.getInputStream();
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return request.getLocale();
	}

	@Override
	public Enumeration getLocales() {
		// TODO Auto-generated method stub
		return request.getLocales();
	}

	@Override
	public String getParameter(String arg0) {
		// TODO Auto-generated method stub
		return request.getParameter(arg0);
	}

	@Override
	public Map getParameterMap() {
		// TODO Auto-generated method stub
		return request.getParameterMap();
	}

	@Override
	public Enumeration getParameterNames() {
		// TODO Auto-generated method stub
		return request.getParameterNames();
	}

	@Override
	public String[] getParameterValues(String arg0) {
		// TODO Auto-generated method stub
		return request.getParameterValues(arg0);
	}

	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return request.getProtocol();
	}

	@Override
	public BufferedReader getReader() throws IOException {
		// TODO Auto-generated method stub
		return request.getReader();
	}

	@Override
	public String getRealPath(String arg0) {
		// TODO Auto-generated method stub
		return request.getRealPath(arg0);
	}

	@Override
	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return request.getRemoteAddr();
	}

	@Override
	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return request.getRemoteHost();
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String arg0) {
		// TODO Auto-generated method stub
		return request.getRequestDispatcher(arg0);
	}

	@Override
	public String getScheme() {
		// TODO Auto-generated method stub
		return request.getScheme();
	}

	@Override
	public String getServerName() {
		// TODO Auto-generated method stub
		return request.getServerName();
	}

	@Override
	public int getServerPort() {
		// TODO Auto-generated method stub
		return request.getServerPort();
	}

	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeAttribute(String arg0) {
		// TODO Auto-generated method stub
		request.removeAttribute(arg0);
	}

	@Override
	public void setAttribute(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		request.setAttribute(arg0, arg1);
	}

	@Override
	public void setCharacterEncoding(String arg0)
			throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(arg0);
	}

	@Override
	public String getAuthType() {
		// TODO Auto-generated method stub
		return request.getAuthType();
	}

	@Override
	public String getContextPath() {
		// TODO Auto-generated method stub
		return request.getContextPath();
	}

	@Override
	public Cookie[] getCookies() {
		// TODO Auto-generated method stub
		return request.getCookies();
	}

	@Override
	public long getDateHeader(String arg0) {
		// TODO Auto-generated method stub
		return request.getDateHeader(arg0);
	}

	@Override
	public String getHeader(String arg0) {
		// TODO Auto-generated method stub
		return request.getHeader(arg0);
	}

	@Override
	public Enumeration getHeaderNames() {
		// TODO Auto-generated method stub
		return request.getHeaderNames();
	}

	@Override
	public Enumeration getHeaders(String arg0) {
		// TODO Auto-generated method stub
		return request.getHeaders(arg0);
	}

	@Override
	public int getIntHeader(String arg0) {
		// TODO Auto-generated method stub
		return request.getIntHeader(arg0);
	}

	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return request.getMethod();
	}

	@Override
	public String getPathInfo() {
		// TODO Auto-generated method stub
		return request.getPathInfo();
	}

	@Override
	public String getPathTranslated() {
		// TODO Auto-generated method stub
		return request.getPathTranslated();
	}

	@Override
	public String getQueryString() {
		// TODO Auto-generated method stub
		return request.getQueryString();
	}

	@Override
	public String getRemoteUser() {
		// TODO Auto-generated method stub
		return request.getRemoteUser();
	}

	@Override
	public String getRequestURI() {
		// TODO Auto-generated method stub
		return request.getRequestURI();
	}

	@Override
	public StringBuffer getRequestURL() {
		// TODO Auto-generated method stub
		return request.getRequestURL();
	}

	@Override
	public String getRequestedSessionId() {
		// TODO Auto-generated method stub
		return request.getRequestedSessionId();
	}

	@Override
	public String getServletPath() {
		// TODO Auto-generated method stub
		return request.getServletPath();
	}

	@Override
	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return request.getSession();
	}

	@Override
	public HttpSession getSession(boolean arg0) {
		// TODO Auto-generated method stub
		return request.getSession(arg0);
	}

	@Override
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return request.getUserPrincipal();
	}

	@Override
	public boolean isRequestedSessionIdFromCookie() {
		// TODO Auto-generated method stub
		return request.isRequestedSessionIdFromCookie();
	}

	@Override
	public boolean isRequestedSessionIdFromURL() {
		// TODO Auto-generated method stub
		return request.isRequestedSessionIdFromURL();
	}

	@Override
	public boolean isRequestedSessionIdFromUrl() {
		// TODO Auto-generated method stub
		return request.isRequestedSessionIdFromUrl();
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		// TODO Auto-generated method stub
		return request.isRequestedSessionIdValid();
	}

	@Override
	public boolean isUserInRole(String arg0) {
		// TODO Auto-generated method stub
		return request.isUserInRole(arg0);
	}

}
