package ex03.pyrmont;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;

import org.apache.jasper.tagplugins.jstl.core.Url;

import ex03.pyrmont.connector.http.Constants;
import ex03.pyrmont.connector.http.HttpRequest;
import ex03.pyrmont.connector.http.HttpRequestFacade;
import ex03.pyrmont.connector.http.HttpResponse;

public class ServletProcessor {

	public void process(HttpRequest request, HttpResponse response){
		String uri = request.getRequestURI();
		String servletName = uri.substring(uri.lastIndexOf("/") + 1);
		URLClassLoader loader = null;
		try{
			URL[] urls = new URL[1];
			URLStreamHandler streamHandler = null;
			File classPath = new File(Constants.WEB_ROOT);
			String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
			urls[0] = new URL(null, repository, streamHandler);
			loader = new URLClassLoader(urls);
			
			Class myClass = null;
			myClass = loader.loadClass(servletName);
			
			
			Servlet servlet = null;
			//生成servlet的实例
			servlet = (Servlet) myClass.newInstance();
			HttpRequestFacade requestFacade = new HttpRequestFacade(request);
			servlet.service(requestFacade, response);
			response.finishResponse();
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}

}
