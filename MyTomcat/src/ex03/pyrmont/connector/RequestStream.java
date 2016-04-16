package ex03.pyrmont.connector;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;

//import org.apache.catalina.tribes.util.StringManager;





import org.apache.catalina.util.StringManager;

import ex03.pyrmont.connector.http.Constants;
import ex03.pyrmont.connector.http.HttpRequest;

public class RequestStream extends ServletInputStream{

	protected boolean closed = false;
	
	protected int count = 0;
	
	protected int length = -1;
	
	protected static StringManager sm = StringManager.getManager(Constants.Package);
	
	protected InputStream stream = null;
	
	public RequestStream(HttpRequest request) {
		// TODO Auto-generated constructor stub
		super();
		closed = false;
		count = 0;
		length = request.getContentLength();
		stream = request.getStream();
	}
	
	public void colse() throws IOException{
		if(closed){
			throw new IOException(sm.getString("requestStream.close.closed"));
		}
		if(length > 0){
			while(count < length){
				int b = read();
				if(b < 0)
					break;
			}
		}
		closed = true;
	}
	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		if(closed)
			throw new IOException(sm.getString("requestStream.read.closed"));
		if((length >= 0) && (count >= length))
			return (-1);
		int b = stream.read();
		if(b >= 0)
			count++;
		return (b);
	}

}
