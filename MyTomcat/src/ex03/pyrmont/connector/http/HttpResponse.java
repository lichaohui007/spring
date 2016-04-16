package ex03.pyrmont.connector.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class HttpResponse implements HttpServletResponse{

	private static final int BUFFER_SIZE = 1024;
	HttpRequest request;
	OutputStream output;
	PrintWriter writer;
	protected byte[] buffer = new byte[BUFFER_SIZE];
	protected int bufferCount = 0;
	protected boolean committed = false;
	protected int contentCount = 0;
	protected int contentLength = -1;
	protected String contentType = null;
	protected String encoding = null;
	protected ArrayList cookies = new ArrayList();
	protected HashMap headers = new HashMap();
	protected final SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);

	protected String message = getStatusMessage(HttpServletResponse.SC_OK);	

	protected int status = HttpServletResponse.SC_OK;
	
	public HttpResponse(OutputStream output) {
		// TODO Auto-generated constructor stub
		this.output = output;
	}
	//灏唕esponse鍜宧eaders鏀惧叆鍒拌緭鍑烘祦涓�
	public void finishResponse(){
		if(writer != null){
			writer.flush();
			writer.close();
		}
	}
	
	
	public int getContentLength(){
		return contentLength;
	}
	
	public String getContentType(){
		return contentType;
	}
	
	protected String getProtocol(){
			return request.getProtocol();
	}
	
	protected String getStatusMessage(int status){
		switch (status) {
		 case SC_OK:
		        return ("OK");
		      case SC_ACCEPTED:
		        return ("Accepted");
		      case SC_BAD_GATEWAY:
		        return ("Bad Gateway");
		      case SC_BAD_REQUEST:
		        return ("Bad Request");
		      case SC_CONFLICT:
		        return ("Conflict");
		      case SC_CONTINUE:
		        return ("Continue");
		      case SC_CREATED:
		        return ("Created");
		      case SC_EXPECTATION_FAILED:
		        return ("Expectation Failed");
		      case SC_FORBIDDEN:
		        return ("Forbidden");
		      case SC_GATEWAY_TIMEOUT:
		        return ("Gateway Timeout");
		      case SC_GONE:
		        return ("Gone");
		      case SC_HTTP_VERSION_NOT_SUPPORTED:
		        return ("HTTP Version Not Supported");
		      case SC_INTERNAL_SERVER_ERROR:
		        return ("Internal Server Error");
		      case SC_LENGTH_REQUIRED:
		        return ("Length Required");
		      case SC_METHOD_NOT_ALLOWED:
		        return ("Method Not Allowed");
		      case SC_MOVED_PERMANENTLY:
		        return ("Moved Permanently");
		      case SC_MOVED_TEMPORARILY:
		        return ("Moved Temporarily");
		      case SC_MULTIPLE_CHOICES:
		        return ("Multiple Choices");
		      case SC_NO_CONTENT:
		        return ("No Content");
		      case SC_NON_AUTHORITATIVE_INFORMATION:
		        return ("Non-Authoritative Information");
		      case SC_NOT_ACCEPTABLE:
		        return ("Not Acceptable");
		      case SC_NOT_FOUND:
		        return ("Not Found");
		      case SC_NOT_IMPLEMENTED:
		        return ("Not Implemented");
		      case SC_NOT_MODIFIED:
		        return ("Not Modified");
		      case SC_PARTIAL_CONTENT:
		        return ("Partial Content");
		      case SC_PAYMENT_REQUIRED:
		        return ("Payment Required");
		      case SC_PRECONDITION_FAILED:
		        return ("Precondition Failed");
		      case SC_PROXY_AUTHENTICATION_REQUIRED:
		        return ("Proxy Authentication Required");
		      case SC_REQUEST_ENTITY_TOO_LARGE:
		        return ("Request Entity Too Large");
		      case SC_REQUEST_TIMEOUT:
		        return ("Request Timeout");
		      case SC_REQUEST_URI_TOO_LONG:
		        return ("Request URI Too Long");
		      case SC_REQUESTED_RANGE_NOT_SATISFIABLE:
		        return ("Requested Range Not Satisfiable");
		      case SC_RESET_CONTENT:
		        return ("Reset Content");
		      case SC_SEE_OTHER:
		        return ("See Other");
		      case SC_SERVICE_UNAVAILABLE:
		        return ("Service Unavailable");
		      case SC_SWITCHING_PROTOCOLS:
		        return ("Switching Protocols");
		      case SC_UNAUTHORIZED:
		        return ("Unauthorized");
		      case SC_UNSUPPORTED_MEDIA_TYPE:
		        return ("Unsupported Media Type");
		      case SC_USE_PROXY:
		        return ("Use Proxy");
		      case 207:       // WebDAV
		        return ("Multi-Status");
		      case 422:       // WebDAV
		        return ("Unprocessable Entity");
		      case 423:       // WebDAV
		        return ("Locked");
		      case 507:       // WebDAV
		        return ("Insufficient Storage");
		      default:
		        return ("HTTP Response Status " + status);
		}
	}

	public OutputStream getStream(){
		return this.output;
	}
	
	protected void sendHeaders() throws IOException{
		if(isCommitted())
			return;
		OutputStreamWriter osr = null;
		try{

			osr = new OutputStreamWriter(getStream(), getCharacterEncoding());
		}catch(Exception e){
			osr = new OutputStreamWriter(getStream());
		}
		final PrintWriter outputWriter = new PrintWriter(osr);
		//鍝嶅墠娈靛搷搴旂姸鎬�

		outputWriter.print(this.getProtocol());
		outputWriter.print(" ");
		outputWriter.print(status);
		if(message != null){
			outputWriter.print(" ");
			outputWriter.print(message);
		}
		outputWriter.print("\r\n");
		if(getContentType() != null){
			outputWriter.print("Content-Type: " + getContentType() + "\r\n");

		}
		
		if(getContentLength() >= 0){
			outputWriter.print("Content-Length: " + getContentLength() + "\r\n");
		}
		//閬嶅巻headers  map+ list

		synchronized (headers) {
			Iterator names = headers.keySet().iterator();
			while(names.hasNext()){
				String name = (String) names.next();
				ArrayList values = (ArrayList) headers.get(name);
				Iterator items = values.iterator();
				while(items.hasNext()){
					String value = (String) items.next();
					outputWriter.print(name);
					outputWriter.print(": ");
					outputWriter.print(value);

					outputWriter.print("\r\n");

				}
			}
		}
		
		synchronized (cookies) {
			Iterator items = cookies.iterator();
			while(items.hasNext()){
				Cookie cookie = (Cookie) items.next();

				outputWriter.print(cookie.getName());
				outputWriter.print(": ");
				outputWriter.print(cookie.getValue());
				outputWriter.print("\r\n");
			}
		}
		
		outputWriter.print("\r\n");

		outputWriter.flush();
		committed = true;
	}
	
	public void setRequest(HttpRequest request){

		this.request = request;

	}
	
	public void sendStaticResource() throws IOException{
		byte[] bytes = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
		try{
			File file = new File(Constants.WEB_ROOT, request.getRequestURI());

			//鐢熸垚闈欐�璧勬簮娴�
			fis = new FileInputStream(file);
			//灏濊瘯鍚慴uffer涓粠0寮�鏉ュ姞杞絙uffersize鐨勫瓧鑺�
			int ch = fis.read(bytes, 0 , BUFFER_SIZE);
			while(ch != -1){
				//buffer鍚戣緭鍑烘祦涓啓鏁版嵁  buffer灏濊瘯灏哻h涓瓧鑺傚啓鍏ュ埌娴佷腑
				output.write(bytes, 0, ch);
				ch = fis.read(bytes, 0, BUFFER_SIZE);
			}
		}catch(Exception e){
			 String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
				        "Content-Type: text/html\r\n" +
				        "Content-Length: 23\r\n" +
				        "\r\n" +
				        "<h1>File Not Found</h1>";
			 output.write(errorMessage.getBytes());

		}finally{
			if(fis != null)
				fis.close();
		}
	}
	
	public void write(int b) throws IOException{
		if(bufferCount >= buffer.length)
			flushBuffer();

		buffer[bufferCount++] = (byte) b;

		contentCount++;
	}
	
	public void write(byte[] b) throws IOException{

		write(b, 0, b.length);
	}
	
	public void write(byte[]b, int off, int len) throws IOException{
		if(len == 0)
			return;
		if(len <= (buffer.length - bufferCount)){
			System.arraycopy(b, off, buffer, bufferCount, len);
			bufferCount  += len;
			contentCount += len;
			return;
		}
		
		flushBuffer();
		int iterations = len / buffer.length;
		int leftoverStart = iterations * buffer.length;
		int leftoverLen = len - leftoverStart;
		//姣忔鍚戞祦涓啓鍏uffer.length涓瓧鑺�
		for(int i = 0; i < iterations; i++){
			write(b, off + (i * buffer.length), buffer.length);
			if(leftoverLen > 0)
				write(b, off + leftoverStart, leftoverLen);

		}
	}
	@Override
	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetBuffer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBufferSize(int arg0) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void setContentLength(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContentType(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLocale(Locale arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCookie(Cookie cookie) {
		// TODO Auto-generated method stub
		if(isCommitted())
			return;
		synchronized (cookies) {
			cookies.add(cookie);
		}
	}

	@Override
	public void addDateHeader(String name, long value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addHeader(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addIntHeader(String arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsHeader(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String encodeRedirectURL(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeRedirectUrl(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeURL(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeUrl(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void sendError(int arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendError(int arg0, String arg1) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendRedirect(String arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDateHeader(String arg0, long arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeader(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIntHeader(String arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStatus(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStatus(int arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}
}
