package ex03.pyrmont.connector;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletOutputStream;

import ex02.pyrmont.Constants;
import ex03.pyrmont.connector.http.HttpResponse;

public class ResponseStream extends ServletOutputStream{

	protected boolean closed = false;
	
	protected boolean commit = false;
	
	protected int count = 0;
	
	protected int length = -1;
	
	protected HttpResponse response;

	protected OutputStream stream = null;
	
	public ResponseStream(HttpResponse response) {
		// TODO Auto-generated constructor stub
		super();
		closed = false;
		commit = false;
		count = 0;
		this.response = response;
	}
	@Override
	public void write(int b) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	public boolean getCommit(){
		return this.commit;
	}
	
	public void setCommit(boolean commit){
		this.commit = commit;
	}
	
	public void close() throws IOException{
		if(closed)
				throw new IOException("responseStream.close.closed");
		response.f
	}
}
