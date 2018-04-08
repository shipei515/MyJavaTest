import framework.basic.config.ConfigManager;
import framework.common.exception.FrameworkException;
import framework.common.tools.ext.HttpPostUtils;
import framework.common.tools.map.ExtHashMap;


public class PortalTest {

	protected static int MY_LOOP = 1;
	
	public void run(int loop, int threadCount){
		MY_LOOP = loop;
		for(int i = 0; i < threadCount; i++){
			MyTread1 thread = new MyTread1();
			thread.start();
		}
	}
	
	class MyTread1 extends Thread{
		
		private boolean isSave = false;
		@Override
		public void run() {
			for(int i = 0; i < MY_LOOP; i++){
				String sendData = "{\"systemId\":\"SYS-BS-01\",\"recorder\":\"8ab798a8-ae57-11e7-9171-005056a44745\",\"orgName\":\"集团本部\",\"applyer\":\"8ab798a8-ae57-11e7-9171-005056a44745\",\"applyerName\":\"谢锐\",\"appUrl\":\"url\u003dweb%2Fbpm%2FtaskManager%2FtoMobileSign.form%3FtaskId%3D4028805f61df35c30161df5360465fbe\u0026oKey\u003d01\",\"title\":\"testXXAAAv9\",\"orgId\":\"88bb2b2b-ae5d-11e7-9171-005056a44745\",\"enabled\":\"1\",\"taskUserName\":\"谢锐\",\"isSign\":\"0\",\"bussinessType\":\"\",\"taskType\":\"任务协同-回复\",\"instanceId\":\"17c9a540-d681-4827-b35b-4c6307c73624\",\"taskState\":\"1\",\"systemName\":\"协同办公系统\",\"taskUrl\":\"url\u003dweb%2Fbpm%2FtaskManager%2FtoSign.form%3FtaskId%3D4028805f61df35c30161df5360465fbe\u0026oKey\u003d01\",\"recordDate\":\"2018-03-01 10:10:38\",\"applyDate\":\"2018-03-01 10:10:38\",\"taskId\":\"4028805f61df35c30161df5360465fbe\",\"taskUser\":\"8ab798a8-ae57-11e7-9171-005056a44745\"}";
				try {
					String rs = HttpPostUtils.sendPost("http://172.16.20.86:8031/server-portal/api/task/" + (isSave ? "save" : "update"), sendData
							, new ExtHashMap<String, String>().putValue("Authorization", "Bearer " + "37ed48bf-e641-4cf3-9c69-8f9d6ad25559").putValue("Content-Type", "application/json;charset=utf-8"));
					System.out.println(rs);
				} catch (FrameworkException e) {
//					e.printStackTrace();
					System.out.println("error:" + e.message);
				}
				
			}
		}
	}
	
	public static void main(String[] args) {
		PortalTest test = new PortalTest();
		test.run(10, 20);
//		test.run(1, 1);
	}
}
