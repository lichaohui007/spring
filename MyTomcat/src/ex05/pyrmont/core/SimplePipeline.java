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
import org.apache.catalina.startup.SetContextPropertiesRule;

public class SimplePipeline implements Pipeline{

	protected Valve basic = null;
	protected Container container = null;
	protected Valve[] valves = new Valve[0];
	
	
	public SimplePipeline(Container container) {
		// TODO Auto-generated constructor stub
	}
	
	protected class SimplePipelineValveContext implements ValveContext{

		protected int stage = 0;
		
		@Override
		public String getInfo() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void invokeNext(org.apache.catalina.Request request,
				org.apache.catalina.Response response) throws IOException,
				ServletException {
			// TODO Auto-generated method stub
			int subscript =  stage;
			stage = stage + 1;
			
			if(subscript < valves.length){
				valves[subscript].invoke(request, response, this);
			}else if((subscript ==  valves.length) && (basic != null)){
				basic.invoke(request, response, this);
			}else{
				throw new ServletException("No valve");
			}
		}
		
	}

	@Override
	public void addValve(Valve valve) {
		// TODO Auto-generated method stub
		if(valve instanceof Contained)
			((Contained)valve).setContainer(this.container);
		
		synchronized (valves) {
			Valve[] results = new Valve[valves.length + 1];
			System.arraycopy(valves, 0, results, 0, valves.length);
			results[valves.length] = valve;
			valves = results;
		}
	}

	@Override
	public Valve getBasic() {
		// TODO Auto-generated method stub
		return basic;
	}

	@Override
	public Valve[] getValves() {
		// TODO Auto-generated method stub
		return valves;
	}

	@Override
	public void invoke(org.apache.catalina.Request request,
			org.apache.catalina.Response response) throws IOException,
			ServletException {
		// TODO Auto-generated method stub
		(new SimplePipelineValveContext()).invokeNext(request, response);
	}

	@Override
	public void removeValve(Valve valve) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBasic(Valve valve) {
		// TODO Auto-generated method stub
		this.basic = valve;
		((Contained)valve).setContainer(container);
	}
	
	
}
