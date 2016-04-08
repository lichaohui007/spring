package ex02.pyrmont;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

public class ResponseFacade implements ServletResponse{

	private ServletResponse response;
	
	public ResponseFacade(Response response) {
		// TODO Auto-generated constructor stub
		this.response = response;
	}
	@Override
	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub
		response.flushBuffer();
	}

	@Override
	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return response.getCharacterEncoding();
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return response.getContentType();
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return response.getLocale();
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return response.getOutputStream();
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		// TODO Auto-generated method stub
		return response.getWriter();
	}

	@Override
	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		response.reset();
	}

	@Override
	public void resetBuffer() {
		// TODO Auto-generated method stub
		response.resetBuffer();
	}

	@Override
	public void setBufferSize(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCharacterEncoding(String arg0) {
		// TODO Auto-generated method stub
		response.setCharacterEncoding(arg0);
	}

	@Override
	public void setContentLength(int arg0) {
		// TODO Auto-generated method stub
		response.setContentLength(arg0);
	}

	@Override
	public void setContentType(String arg0) {
		// TODO Auto-generated method stub
		response.setContentType(arg0);
	}

	@Override
	public void setLocale(Locale arg0) {
		// TODO Auto-generated method stub
		response.setLocale(arg0);
	}

}
