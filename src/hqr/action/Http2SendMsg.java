package hqr.action;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import hqr.util.Brower;

public class Http2SendMsg {
	private CloseableHttpClient httpclient;
	private HttpClientContext httpClientContext;
	private CloseableHttpResponse cl;
	private String accessToken;
	
	private String str = "";
	
	public Http2SendMsg(CloseableHttpClient httpclient, HttpClientContext httpClientContext,String accessToken,String content) {
		this.httpclient = httpclient;
		this.httpClientContext = httpClientContext;
		this.accessToken = accessToken;
		
		str = "{\"touser\": \"@all\",\"msgtype\": \"text\",\"agentid\": \"1000002\",\"text\": {\"content\": \""+content+"\"},\"enable_duplicate_check\": \"1\",\"duplicate_check_interval\": \"3\"}";
		 System.out.println("json:"+str);
	}
	
	public void execute() throws Exception {
	    HttpPost post = new HttpPost("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="+accessToken);
	    post.setConfig(Brower.getRequestConfig());
	    
		StringEntity json = new StringEntity(str ,ContentType.APPLICATION_JSON);
		System.out.println(json.toString());
		post.setEntity(json);
		
		cl = httpclient.execute(post,httpClientContext);
	    
	    this.cl = this.httpclient.execute(post, this.httpClientContext);
	    
	    if(cl.getStatusLine().getStatusCode()==200) {
	    	JSONObject jo = JSON.parseObject(EntityUtils.toString(this.cl.getEntity()));
	    	String errcode = jo.get("errcode").toString();
	    	if("0".equals(errcode)) {
	    		System.out.println("msg push to user successfully");
	    	}
	    	else {
	    		System.out.println("failed to push the msg"+jo.get("errmsg"));
	    	}
	    }
	    else {
	    	EntityUtils.toString(this.cl.getEntity());
	    }
	    cl.close();
	}
}
