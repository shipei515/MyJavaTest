package product.oa.test;

import java.io.IOException;
import java.nio.charset.Charset;

import framework.common.exception.FrameworkException;
import framework.common.tools.ext.HttpPostUtils;
import framework.common.tools.map.ExtHashMap;

public class WebServiceTest
{

    public static void main(String[] args) throws FrameworkException, IOException
    {

    	//公文引入
//        String resp1 = HttpClientUtils.sendMessage4Post("https://10.0.126.26/services/exchangeServer/ExchangeWebService/replyedDoc", "docReplyObj={\"signer\":\"167\",\"signerName\":\"阮泽邦\",\"dept\":\"0401\",\"sourceTaskId\":\"8a80fe9a5e4c8c43015e4d08019800fe\",\"sourceId\":\"fbb076ce-cf4f-4c9a-82db-dc0de2c84dc8\",\"docReceiveId\":\"34776892-bb6b-464b-a09a-776e28a79a98\",\"recorderId\":\"167\"}&state=2", null);
//        System.out.println(resp1);
    	//4a
//        String resp2 = HttpClientUtils.sendMessage4Post("https://10.0.126.27/services/authServer/AuthWebService/changePassword"
//        		, "loginName=xionghui@chinauniwater.com&password=17b91a3acb97c5b2b4e41a8017db1db3", null);
//        System.out.println(resp2);
    	//bpm
    	String token = "de46044e-9f83-404b-9266-1134b0e00634";
    	String str = "{\"systemId\":\"SYS-BS-01\",\"orgName\":\"中水联科技股份有限公司\",\"applyer\":\"8ab6924b-ae57-11e7-9171-005056a44745\",\"applyerName\":\"杜锋\",\"title\":\"zcv\",\"taskUserName\":\"陈园超\",\"isSign\":\"0\",\"taskState\":\"1\",\"systemName\":\"协同办公系统\",\"taskUrl\":\"http://localhost:8040/web/right/auth/loginSso4Outer.form?url\u003dweb%2Fbpm%2FtaskManager%2FtoSign.form%3FtaskId%3D2c905eb15f29bb53015f29d03070000c\",\"businessType\":\"\",\"applyDate\":\"2017-10-17 18:10:29\",\"taskId\":\"2c905eb15f29bb53015f29d03070000c\",\"taskUser\":\"8ab6924b-ae57-11e7-9171-005056a44745\"}";
        String resp2 = HttpPostUtils.sendPost("http://172.16.20.87:8033/server-portal/api/task/update"
        		, str, new ExtHashMap<String, String>().putValue("Authorization", "Bearer " + token).putValue("Content-Type", "application/json;charset=utf-8"));
        System.out.println(resp2);
        
        System.out.println("Default Charset=" + Charset.defaultCharset());  
        System.out.println("file.encoding=" + System.getProperty("file.encoding"));  
        System.out.println("Default Charset=" + Charset.defaultCharset());  
//        System.out.println("Default Charset in Use=" + getDefaultCharSet());  
      //Word.Application代表COM OLE编程标识，可查询MSDN得到  
/*        ActiveXComponent app = new ActiveXComponent("Word.Application");  
        //设置Word不可见  
        app.setProperty("Visible",false);  
        //调用Application对象的Documents属性，获得Documents对象  
        Dispatch docs = app.getProperty("Documents").toDispatch();  
        Dispatch doc = Dispatch.call(docs,"Open","d:\\\\关于（项目建设部）系统管理员的中水联董字。 (2).doc",new Variant(false),new Variant(true)).getDispatch();  
        Dispatch.call(doc,//要转换的文档  
                     "SaveAS",  
                     "d:/default1.doc", //要保存的word文件名  
                     12  
                     );  
        //关闭打开的Word文件  
        Dispatch.call(doc,  
                      "Close",  
                       false//设置不保存改变  
                       );  
        //关闭Word应用程序  
        app.invoke("Quit",0);  
        System.out.println("完成！"); */ 
    }

}
