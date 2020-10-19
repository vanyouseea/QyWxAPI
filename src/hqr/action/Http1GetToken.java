package hqr.action;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import hqr.util.Brower;

public class Http1GetToken {
	private CloseableHttpClient httpclient;
	private HttpClientContext httpClientContext;
	private CloseableHttpResponse cl;
	private String token;
	private boolean status = false;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Http1GetToken(CloseableHttpClient httpclient, HttpClientContext httpClientContext) {
		super();
		this.httpclient = httpclient;
		this.httpClientContext = httpClientContext;
	}
	
	public void execute() throws Exception {
	    HttpGet get = new HttpGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ww13a3890a2c0815e3&corpsecret=l0Po7Af9xblCc1YZhc5atpKGuu_0h5k4jMmOSXXHtos");
	    get.setConfig(Brower.getRequestConfig());
	    
	    this.cl = this.httpclient.execute(get, this.httpClientContext);
	    
	    if(cl.getStatusLine().getStatusCode()==200) {
	    	JSONObject jo = JSON.parseObject(EntityUtils.toString(this.cl.getEntity()));
	    	String errcode = jo.get("errcode").toString();
	    	if("0".equals(errcode)) {
	    		status = true;
	    		this.token = (String)jo.get("access_token");
	    	}
	    }
	    else {
	    	EntityUtils.toString(this.cl.getEntity());
	    }
	}
}
