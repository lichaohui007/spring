package ex02.pyrmont;

import java.io.IOException;

public class StaticResourceProcessor {

	public void process(Request request, Response response){
		try {
			response.sendStaticResource();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
