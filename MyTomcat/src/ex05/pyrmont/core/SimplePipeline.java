package ex05.pyrmont.core;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Valve;
import org.apache.catalina.ValveContext;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;

public class SimplePipeline implements Pipeline{

	protected Valve basic = null;
	protected Container container = null;
	protected Valve[] valves = new Valve[0];
	
	
	public SimplePipeline(Container container) {
		// TODO Auto-generated constructor stub
	}
	
	protected class SimplePipelineValveContext implements ValveContext{

		@Override
		public String getInfo() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void invokeNext(org.apache.catalina.Request arg0,
				org.apache.catalina.Response arg1) throws IOException,
				ServletException {
			// TODO Auto-generated method stub
			
		}
		
	}

	@Override
	public void addValve(Valve arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Valve getBasic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Valve[] getValves() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void invoke(org.apache.catalina.Request arg0,
			org.apache.catalina.Response arg1) throws IOException,
			ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeValve(Valve arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBasic(Valve arg0) {
		// TODO Auto-generated method stub
		
	}
}
