package ex05.pyrmont.core;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Pipeline;
import org.apache.catalina.Valve;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;

public class SimplePipeline implements Pipeline{

	protected Valve basic = null;
	protected Container container = null;
	protected Valve[] valves = new Valve[0];
	
	
	public SimplePipeline(Container container) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void addValve(Valve valve) {
		// TODO Auto-generated method stub
		if(valve instanceof Contained)
			((Contained)valve).setContainer(this.container);
		synchronized(valves){
			Valve[] results = new Valve[valves.length + 1];
			System.arraycopy(valves, 0, results, 0, valves.length);
			results[valves.length] = valve;//给results赋值
			valves = results;
		}
	}

	@Override
	public Valve getBasic() {
		// TODO Auto-generated method stub
		return basic;
	}

	@Override
	public Container getContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Valve getFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Valve[] getValves() {
		// TODO Auto-generated method stub
		return valves;
	}

	public void invoke(Request request, Response response){
		
	}
	@Override
	public boolean isAsyncSupported() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeValve(Valve arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBasic(Valve valve) {
		// TODO Auto-generated method stub
		this.basic = valve;
		 //((Contained) valve).setContainer(container);
		((Contained)valve).setContainer(container);
	}

	@Override
	public void setContainer(Container container) {
		// TODO Auto-generated method stub
		this.container = container;
	}

	
	protected class SimplePipelineValveContext implements ValveContext{
		
	}
}
