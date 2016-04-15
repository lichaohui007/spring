package ex03.pyrmont.connector;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ResponseWriter extends PrintWriter{

	public ResponseWriter(OutputStreamWriter writer) {
		// TODO Auto-generated constructor stub
		super(writer);
	}

	public void print(boolean b){
		super.print(b);
		super.flush();
	}
	
	public void print(char c){
		super.print(c);
		super.flush();
	}
	
	public void print(char[] ca){
		super.print(ca);
		super.flush();
		
	}
	
	public void print(double d){
		super.print(d);
		super.flush();
	}
	
	public void print(float f){
		super.print(f);
		super.flush();
	}
	
	public void print(int i){
		super.print(i);
		super.flush();
	}
}
