# QyWxAPI
使用企业微信API发送消息给关注公众号的用户

### 修改
Http1GetToken.java
HttpGet get = new HttpGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=替换成你自己的&corpsecret=替换成你自己的");

Http2SendMsg.java
str = "{\"touser\": \"@all\",\"msgtype\": \"text\",\"agentid\": \"替换成你自己的\",\"text\": {\"content\": \""+content+"\"},\"enable_duplicate_check\": \"1\",\"duplicate_check_interval\": \"3\"}";

### 用法
java -jar wxInfo.jar 欲发送的信息
