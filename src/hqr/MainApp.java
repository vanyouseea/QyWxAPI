package hqr;

import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;

import hqr.action.Http1GetToken;
import hqr.action.Http2SendMsg;
import hqr.util.Brower;

public class MainApp {
	private CloseableHttpClient httpclient = null;
	private HttpClientContext httpClientContext = null;
	
	public MainApp(String str) {
		try {
			//open browser
			httpclient = Brower.getCloseableHttpClient();
			//html context
			httpClientContext = Brower.getHttpClientContext();
	
			Http1GetToken h1 = new Http1GetToken(httpclient, httpClientContext);
			h1.execute();
			
			Http2SendMsg h2 = new Http2SendMsg(httpclient, httpClientContext, h1.getToken(), str);
			h2.execute();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		if(args.length==0) {
			new MainApp("通用通知信息");
		}
		else {
			new MainApp(args[0]);
		}
		
	}
}
