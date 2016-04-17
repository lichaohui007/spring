
package ex03.pyrmont.connector.http;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tomcat.util.res.StringManager;

public class SocketInputStream extends InputStream{

	private static final byte CR = (byte) '\r'; //回车
	private static final byte LF = (byte) '\n'; //换行
	private static final byte SP = (byte) ' ';
	private static final byte HT = (byte) '\t';
	private static final byte COLON = (byte) ':';
	private static final int LC_OFFSET = 'A' - 'a';//澶у皬鍐欎箣闂寸殑宸�
	protected static StringManager sm = StringManager.getManager(Constants.Package);
	
	protected byte[] buf; //鍒濆缂撳啿鍖�	protected int count; //鍓╀綑鐨勬湁鏁堝瓧鑺�	protected int pos;  //褰撴湡鍦ㄧ紦鍐插尯涓殑浣嶇疆
	protected int count;
	protected int pos;
	protected InputStream is;
	
	SocketInputStream(InputStream is, int bufferSize){
		this.is = is;
		buf = new byte[bufferSize];
	}
	
	public void readRequestLine(HttpRequestLine requestLine) throws IOException{
		if(requestLine.methodEnd != 0)
			requestLine.recycle();
		int chr = 0;
		do{
			try{
				chr = read();
			}catch(Exception e){
				chr = -1;
			}
		}while((chr == CR) || (chr == LF));
		if(chr == -1)
			throw new EOFException("requestStream.readline.error");
		pos--;
		
		//璇诲彇鏂规硶
		int maxRead = requestLine.method.length;
		int readStart = pos;
		int readCount = 0;
		
		boolean space = false;
		
		while(!space){
			if(readCount >= maxRead){
				if((2 * maxRead) <= HttpRequestLine.MAX_MEHTOD_SIZE){
					char[] newBuffer = new char[2 * maxRead];
					System.arraycopy(requestLine.method, 0, newBuffer, 0, maxRead);
					requestLine.method = newBuffer;
					maxRead = requestLine.method.length;
				}else{
					throw new IOException(sm.getString("requestStream.readline.toolong"));
				}
			}
			
			if(pos >= count){
				int val = read();
				if(val == -1){
					throw new IOException(sm.getString("requestStream.readline.error"));
				}
				pos = 0;
				readStart = 0;
			}
			if(buf[pos] == SP){
				space = true;
			}
			requestLine.method[readCount] = (char) buf[pos];
			readCount++;
			pos++;
		}//while 寰幆缁撴潫
		requestLine.methodEnd = readCount - 1;
		
		//璇诲彇URI
		maxRead = requestLine.uri.length;//对象实例化时,给的具体的值
		readStart = pos;
		readCount = 0; // 局部变量
		
		space = false;
		boolean eol = false;
		
		while(!space){
			if(readCount >= maxRead){
				if((2 * maxRead) <= HttpRequestLine.MAX_URI_SIZE){
					char[] newBuffer = new char[2 * maxRead];
					System.arraycopy(requestLine.uri, 0, newBuffer, 0, maxRead);
					requestLine.uri = newBuffer;
					maxRead = requestLine.uri.length;
				}else{
					throw new IOException(sm.getString("requestStream.readline.toolong"));
				}
			}
			
			if(pos >= count){
				int val = read();
				if(val == -1)
					throw new IOException(sm.getString("requestStream.readline.error"));
				pos = 0;
				readStart = 0;
			}
			if(buf[pos] == SP){
				space = true;
			}else if((buf[pos] == CR) || (buf[pos] == LF)){
				eol = true;
				space = true;
			}
			requestLine.uri[readCount] = (char) buf[pos];
			readCount++;
			pos++;
		}//while
		
		requestLine.uriEnd = readCount - 1;
		
		//璇诲彇鍗忚
		maxRead = requestLine.protocol.length;
		readStart = pos;
		readCount = 0;
		
		while(!eol){
			if(readCount >= maxRead){
				if((2 * maxRead) <= HttpRequestLine.MAX_PROTOCOL_SIZE){
					char[] newBuffer = new char[2 * maxRead];
					System.arraycopy(requestLine.protocol, 0, newBuffer, 0, maxRead);
					//鏁扮粍閲嶆柊璧嬪�
					requestLine.protocol = newBuffer;
					maxRead = requestLine.protocol.length;
				}else{
					throw new IOException(sm.getString("requestStream.readline.toolong"));
				}
			}
			
			if(pos > count){
				int val = read();
				if(val == -1)
					throw new IOException(sm.getString("requestStream.readline.error"));
				pos = 0;
				readStart = 0;
			}
			
			if(buf[pos] == CR){
				
			}else if(buf[pos] == LF){
				eol = true;
			}else{
				requestLine.protocol[readCount] = (char) buf[pos];
				readCount++;
			}
			pos++;
		}
		requestLine.protocolEnd = readCount;
	}
	
	public void readHeader(HttpHeader header) throws IOException{
		if(header.nameEnd != 0)
			header.recycle();
		int chr = read();
		
		//鏌ユ壘绌鸿
		if((chr == CR) || (chr == LF)){
			if(chr == CR)
				read();
			header.nameEnd = 0;
			header.valueEnd = 0;
			return;
		}else{
			pos--;
		}
		
		//璇诲彇header鐨刵ame
		int maxRead = header.name.length;
		int readStart = pos;
		int readCount = 0;
		
		boolean colon = false;
		
		if(!colon){
			if(readCount >= maxRead){
				if((2 * maxRead) <= HttpHeader.MAX_NAME_SIZE){
					char[] newBuffer = new char[2 * maxRead];
					System.arraycopy(header.name, 0, newBuffer, 0, maxRead);
					header.name = newBuffer;
					maxRead = header.name.length;
				}else{
					throw new IOException(sm.getString("requestStream.readline.toolong"));
				}
			}
			if(pos >= count){
				int val = read();
				if(val == -1){
					throw new IOException(sm.getString("requestStream.readline.error"));
				}
				pos = 0;
				readStart = 0;
			}
			
			if(buf[pos] == COLON){
				colon = true;
			}
			
			char val = (char) buf[pos];
			if((val > 'A' && (val <= 'Z'))){
				val = (char) (val - LC_OFFSET);
			}
			
			header.name[readCount] = val;
			readCount++;
			pos++;
		}
		
		header.nameEnd = readCount - 1;
		readStart = pos;
		readCount = 0;
		
		int crPos = -2;
		
		boolean eol = false;
		boolean validLine = true;
		
		while(validLine){
			boolean space = true;
			while(space){
				if(pos >= count){
					int val = read();
					if(val == -1)
						throw new IOException(sm.getString("requestStream.readline.errot"));
					pos = 0;
					readStart = 0;
				}
				if((buf[pos] == SP) || (buf[pos] == HT)){
					pos++;
				}else{
					space = false;
				}
			}//while(space)
			while(!eol){
				if(readCount >= maxRead){
					if((2 * maxRead <= HttpHeader.MAX_VALUE_SIZE)){
						char[] newBuffer = new char[2 * maxRead];
						System.arraycopy(header.value, 0, newBuffer, 0, maxRead);
						header.value = newBuffer;
						maxRead = header.value.length;
					}else{
						throw new IOException(sm.getString("request.readline.toolong"));
					}
				}
				
				if(pos >= count){
					int val = read();
					if(val == -1)
						throw new IOException(sm.getString("request.readline.error"));
					pos = 0;
					readStart = 0;
				}
				
				if(buf[pos] == CR){
					
				}else if(buf[pos] == LF){
					eol = true;
				}else{
					int ch = buf[pos] & 0xff;
					header.value[readCount] = (char) ch;
					readCount++;
				}
				pos++;
			}
			int nextChr = read();
			if((nextChr != SP) && (nextChr != HT)){
				pos--;
				validLine = false;
			}else{
				eol = false;
				if(readCount >= maxRead){
					if((2 * maxRead) <= HttpHeader.MAX_VALUE_SIZE){
						char[] newBuffer = new char[2 * maxRead];
						System.arraycopy(header.value, 0, newBuffer, 0, maxRead);
						header.value = newBuffer;
						maxRead = header.value.length;
					}else{
						throw new IOException(sm.getString("requestStream.readline.toolong"));
					}
				}//if(readCount >= maxRead)
				header.value[readCount] = ' ';
				readCount++;
			}
		}//while(validLine)
		header.valueEnd = readCount;
		
	}
	
	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		if(pos >= count){
			fill();
			if(pos >= count)
				return -1;
		}
		//杩斿洖褰撳墠鐨勫瓧鑺傚�
		return buf[pos++] & 0xff;
	}

	protected void fill() throws IOException{
		pos = 0;
		count = 0;
		//灏嗘祦涓殑鏁版嵁璇诲埌buffer涓�		
		int nRead = is.read(buf, 0, buf.length);//杩斿洖瀹為檯璇诲彇鐨勫瓧鑺傛暟
		if(nRead > 0){
			//杩斿洖璇诲彇鐨勫瓧鑺傛暟
			count = nRead;
		}
			
	}
}

