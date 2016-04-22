package ex05.pyrmont.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.HttpRequest;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Valve;
import org.apache.catalina.ValveContext;
import org.apache.catalina.Wrapper;

public class SimpleContextValve implements Valve, Contained{

	protected Container container;
	
	@Override
	public Container getContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setContainer(Container container) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void invoke(Request request, Response response, ValveContext valveContext)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		if(!(request.getRequest() instanceof HttpServletRequest)  || !(response.getResponse() instanceof HttpServletResponse)){
			return;
		}
		HttpServletRequest hreq = (HttpServletRequest) request.getRequest();
		String contextPath = hreq.getContextPath();
		String requestURI = ((HttpRequest)request).getDecodedRequestURI();
		String relativeURI = requestURI.substring(contextPath.length()).toUpperCase();
		
		Context context = (Context) getContainer();
		Wrapper wrapper = null;
		try{
			//????
			wrapper = (Wrapper)context.map(request, true);
			
		}catch(Exception e){
			badRequest(requestURI, (HttpServletResponse)response.getResponse());
		}
		
		if(wrapper != null){
			notFound(requestURI, (HttpServletResponse)response.getResponse());
		}
		
		response.setContext(context);
		wrapper.invoke(request, response);
	}

	
	public void badRequest(String requestURI, HttpServletResponse response){
		try{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, requestURI);;
		}catch(Exception e){
			;
		}
	}
	
	public void notFound(String requestURI, HttpServletResponse response){
		try{
			response.sendError(HttpServletResponse.SC_NOT_FOUND, requestURI);;
		}catch(Exception e){
			
		}
	}
}
