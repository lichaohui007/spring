package ex02.pyrmont;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Url;

public class ServletProcessor1 {

	public void process(Request request, Response response){
		String uri = request.getUri();
		String servletName = uri.substring(uri.lastIndexOf("/")+1);
		URLClassLoader loader = null;
		
		URL[] urls = new URL[1];
		URLStreamHandler streamHandler = null;
		File classPath = new File(Constants.WEB_ROOT);
		try {
			String repository = (new URL("file", null, classPath.getCanonicalFile() + File.separator)).toString();
			urls[0] = new URL(null, repository, streamHandler);
			//新建classLoader去加载类，告诉加载器库在哪里，  制定类名去加载
			loader = new URLClassLoader(urls);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Class myClass = null;
		try {
			myClass = loader.loadClass(servletName);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Servlet servlet = null;
		try {
			servlet = (Servlet) myClass.newInstance();
			servlet.service((ServletRequest)request, (ServletResponse)response);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
