package ex03.pyrmont;

import ex03.pyrmont.connector.http.HttpRequest;
import ex03.pyrmont.connector.http.HttpResponse;

public class StaticResourcePrecessor {
	public void process(HttpRequest request, HttpResponse response){
		try{
			response.sendStaticResource();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
