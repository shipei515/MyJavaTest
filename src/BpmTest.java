import java.io.File;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;

import framework.common.exception.FrameworkException;
import framework.common.helpers.JsonHelper;
import framework.common.tools.ext.CryptoUtils;
import framework.common.tools.ext.HttpParamUtil;
import framework.common.tools.ext.HttpPostUtils;


public class BpmTest {
	public Map<String, String> userMap = new HashMap<String, String>();
	public BpmTest() {
		userMap.put("190", "zhouxiaomin");
		userMap.put("191", "dufeng");
		userMap.put("192", "lidazhi");
		userMap.put("193", "chenyuanchao");
		userMap.put("194", "tandixian");
		userMap.put("195", "fengling");
		userMap.put("196", "yuyingting");
		userMap.put("197", "xionghui");
		userMap.put("199", "xiaozhen");
		userMap.put("32", "taojinping");
		userMap.put("43", "fulan");
		userMap.put("56", "huzhizheng");
		userMap.put("64", "lihuasheng");
		userMap.put("66", "xierui");
	}
	public void testCooperative(int times){
		String currentName = "";
		try {
			File file = new File("E:\\tmp\\resource.properties");
			String value = FileUtils.readFileToString(file);
			value = (char)(value.charAt(0) + 1) + "";
			FileUtils.write(file, value);
		for(int i = 0; i < times; i++){
//			try {
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("id", "TMP1514248252138");
//				map.put("mytest1", "autotest" + i);
//				map.put("remark", "autotest" + i);
//				map.put("starterId", "194");
//				String url = "http://localhost:8040/web/cooperativeDocument/saveAndStartData.form";
//				String rs = HttpPostUtils.sendPost(url, HttpParamUtil.getUrlParamsByMap(map));
//				Map<String, Object> rsMap = JsonHelper.jsonToObject(rs, Map.class);
//				String id = MapUtils.getString(rsMap, "id");
//				String taskId = MapUtils.getString(rsMap, "taskId");
//				map = new HashMap<String, Object>();
//				map.put("taskId", taskId);
//				map.put("id", id);
//				map.put("reviewId", "197");
//				map.put("processDefinitionKey", "CooperativeDocumentApprove");
//				url = "http://localhost:8040/web/cooperativeDocument/reviewDataById.form";
//				rs = HttpPostUtils.sendPost(url, HttpParamUtil.getUrlParamsByMap(map));
//				rsMap = JsonHelper.jsonToObject(rs, Map.class);
//				url = "http://localhost:8040/web/cooperativeDocument/replyData.form";
//					Thread1 thread = new Thread1();
//					thread.start();
//			} catch (FrameworkException e) {
//				e.printStackTrace();
//			}
//				login(loginNames[(int)(Math.random() * 10)]);
			currentName = "testXXAAA" + value + i;
		    	runBpm(currentName);
//		    	if(!testBpm(currentName)){
//		    		throw new Exception();
//		    	}
//		    	String bizId = "ea979c8a-c030-41d1-9357-82dd0f221566";
//		    	String name = "testaa1";
//				login(userMap.get("190"));
//				Map<String, String> task = findTask(name);
//				String taskId = task.get("id");
//				bizId = task.get("processInstanceId");
//				replyData(bizId, name, taskId, "exclusiveGateway1505811347074:userTask1505811252319@191");
		}
		} catch (Exception e) {
			try {
				FileUtils.write(new File("E:\\tmp\\error.txt"), currentName + "\r\n", true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	public String[] loginNames = new String[]{"zhouxiaomin","dufeng","lidazhi","chenyuanchao","tandixian","fengling","yuyingting","xionghui","xiaozhen","wuxuwen","wujiangpeng"};
	public class Thread1 extends Thread {
	   public void run() {  
	    try {
			login(loginNames[(int)(Math.random() * 10)]);
		} catch (FrameworkException e) {
			e.printStackTrace();
		}
	   }
	}
	
	public String login(String loginName) throws FrameworkException{
		long threadId = Thread.currentThread().getId();
		System.out.println(threadId + ":::" + loginName);
		CookieManager manager = new CookieManager();
	    CookieHandler.setDefault(manager);
	    Map<String, Object> map = new HashMap<String, Object>();
		map.put("loginName", loginName);
		map.put("password", CryptoUtils.md5(MapUtils.getString(map, "loginName") + "1234@com"));
		String url = "http://localhost:8040/web/right/auth/login.form";
		String rs = HttpPostUtils.sendPost(url, HttpParamUtil.getUrlParamsByMap(map));
		Map<String, Object> rsMap = JsonHelper.jsonToObject(rs, Map.class);
		CookieStore cookieJar = manager.getCookieStore();
		StringBuffer sb = new StringBuffer();
		return sb.toString();
	}
	
	public String runBpm(String name) throws FrameworkException{
		login(userMap.get("66"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "TMP1514248252138");
		map.put("title", name);
		map.put("remark", name);
		map.put("starterId", "66");
		String url = "http://localhost:8040/web/cooperativeDocument/saveAndStartData.form";
		String rs = HttpPostUtils.sendPost(url, HttpParamUtil.getUrlParamsByMap(map));
		Map<String, Object> rsMap = JsonHelper.jsonToObject(rs, Map.class);
		String bizId = MapUtils.getString(rsMap, "id");
		String taskId = MapUtils.getString(rsMap, "taskId");
		map = new HashMap<String, Object>();
		map.put("taskId", taskId);
		map.put("id", bizId);
		map.put("reviewId", "32,43,56,64");
		map.put("processDefinitionKey", "CooperativeDocumentApprove");
		url = "http://localhost:8040/web/cooperativeDocument/reviewDataById.form";
		rs = HttpPostUtils.sendPost(url, HttpParamUtil.getUrlParamsByMap(map));
		rsMap = JsonHelper.jsonToObject(rs, Map.class);
		login(userMap.get("32"));
		Map<String, String> task = findTask(name);
		taskId = task.get("id");
		bizId = task.get("processInstanceId");
		replyData(bizId, name, taskId, "exclusiveGateway1515584987912:parallelGateway1515585249946:userTask1515585337388@66");
		login(userMap.get("43"));
		task = findTask(name);
		taskId = task.get("id");
		bizId = task.get("processInstanceId");
		replyData(bizId, name, taskId, "exclusiveGateway1515584987912:parallelGateway1515585249946:userTask1515585337388@66");
		login(userMap.get("56"));
		task = findTask(name);
		taskId = task.get("id");
		bizId = task.get("processInstanceId");
		replyData(bizId, name, taskId, "exclusiveGateway1515584987912:parallelGateway1515585249946:userTask1515585337388@66");
		login(userMap.get("64"));
		task = findTask(name);
		taskId = task.get("id");
		bizId = task.get("processInstanceId");
		replyData(bizId, name, taskId, "exclusiveGateway1515584987912:parallelGateway1515585249946:userTask1515585337388@66");
		return "";
	}
	
	public boolean testBpm(String name) throws FrameworkException{
		login(userMap.get("190"));
		Map<String, String> task = findTask(name);
		if(task != null){
			return false;
		}
		login(userMap.get("191"));
		task = findTask(name);
		if(task != null){
			return false;
		}
		login(userMap.get("192"));
		task = findTask(name);
		if(task != null){
			return false;
		}
		login(userMap.get("193"));
		task = findTask(name);
		if(task != null){
			return false;
		}
		login(userMap.get("194"));
		task = findTask(name);
		if(task != null){
			return false;
		}
		login(userMap.get("195"));
		task = findTask(name);
		if(task != null){
			return false;
		}
		login(userMap.get("196"));
		task = findTask(name);
		if(task != null){
			return false;
		}
		login(userMap.get("197"));
		task = findTask(name);
		if(task == null){
			return false;
		}
		return true;
	}
	
	public String replyData(String bizId, String name, String taskId, String nextOpperator) throws FrameworkException{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskId", taskId);
		map.put("id", bizId);
		map.put("title", name);
		map.put("nextOperator", nextOpperator);
		String url = "http://localhost:8040/web/cooperativeDocument/replyData.form";
		String rs = HttpPostUtils.sendPost(url, HttpParamUtil.getUrlParamsByMap(map));
		return "success";
	}
	
	public Map<String, String> findTask(String name) throws FrameworkException{
		String url = "http://localhost:8040/web/bpm/taskManager/getTaskList.form";
		String rs = HttpPostUtils.sendPost(url, "");
//	    System.out.println(rs);
		Map<String, Object> rsMap = JsonHelper.jsonToObject(rs, Map.class);
		List<Map<String, String>> taskList = (List)rsMap.get("rows");
		for(Map<String ,String> task : taskList){
			if(MapUtils.getString(task, "name").equals(name)){
				return task;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		BpmTest test = new BpmTest();
		test.testCooperative(10);
	}
}
