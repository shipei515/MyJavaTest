package com.test;

import java.util.Date;
import java.util.HashMap;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.math.RandomUtils;

public class CmsMain {

	public static void main(String[] args) {

        try {
			String endpoint = "http://172.16.20.41:8080/cms/services/CMSWebService?wsdl";
			Service service = new Service();
			Call call = (Call)service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.setOperationName("contentToCMS");
			//填写你要调用的方法名称
			HashMap<String,String> content=new HashMap<String,String>();
			content.put("operate", "1");
			content.put("columnid", "107");
			content.put("srcid", "121229");
			content.put("title", "【水务建设新闻】文章标题111111111111111111111111111");
			content.put("pic", "http://img.netbian.com/file/20110923/c1297262a18bef8326c6bbb0f88e185a.jpg");
			content.put("source", "水务建设新闻");
			content.put("pubdate", "2017-08-22 00:00:00");
			content.put("author", "张三");
            content.put("content", "测试内容111111111111111111--------------33");
            content.put("HaveWap", "0");
            content.put("IsImageNews", "Y");
            content.put("PutInTopx", "Y");
			String result = (String) call.invoke(new Object[]{content});
			System.out.println("result"+result);
        }catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.toString());
		}
    
	}
}
