

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class MainTest {
	public MainTest() {  
		  String str = "!@@USER_VIEW.hasRoleByCodes('LeaveDeptLeader','LeaveEachLeader')";
		  System.out.println(str.replaceAll("@@USER_VIEW.hasRoleByCodes('LeaveDeptLeader','LeaveEachLeader')", "false"));
    }  
	public String[] handleTime(String[] timeGroup, int endTime){
		List<String> result = new ArrayList<>();
		int curStartTime = 0;
		int curEndTime = 0;
		for(String timeStr : timeGroup){
			curStartTime = Integer.parseInt(timeStr.split(",")[0]);
			curEndTime = Integer.parseInt(timeStr.split(",")[1]);
			if(curStartTime >= endTime){
				continue;
			}
			if(curEndTime > endTime){
				curEndTime = endTime;
			}
			result.add(StringUtils.leftPad(curStartTime + "", 2, "0") + "," + StringUtils.leftPad(curEndTime + "", 2, "0"));
		}
		return result.toArray(new String[]{});
	}

    
    private static List<Integer> indexArr = null;
    private static int getRandomOrderNo(int index, int length){
    	if(index == 0 || indexArr == null || indexArr.size() == 0){
    		indexArr = new ArrayList<Integer>();
    		for(int i = 0; i < length; i++){
    			indexArr.add(i + 1);
    		}
    	}
    	int k = 0;
    	if(indexArr.size() > 1){
    		k = new Random().nextInt(indexArr.size() - 1);
    	}
    	return indexArr.remove(k);
    }
    public static boolean checkSecurity(String pwd)  
    {  
    	boolean isMatch = true;
    	String regEx = "([a-zA-Z])";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(pwd);
        isMatch = isMatch && matcher.find();
        System.out.println(isMatch);
    	regEx = "([0-9])";
        pattern = Pattern.compile(regEx);
        matcher = pattern.matcher(pwd);
        isMatch = isMatch && matcher.find();
        System.out.println(isMatch);
    	regEx = "((?=[\\x21-\\x7e]+)[^A-Za-z0-9])";//"[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？%+_]";
        pattern = Pattern.compile(regEx);
        matcher = pattern.matcher(pwd);
        isMatch = isMatch && matcher.find();
        System.out.println(isMatch);
        isMatch = pwd.length() >= 8 && isMatch;
        System.out.println(isMatch);
        return isMatch;
    } 
    /** 
     * @param args 
     * @throws IOException 
     */  
    public static void main(String[] args) throws IOException {
//    	Map<String, String> param = new HashMap<String, String>();
//    	System.out.println(checkSecurity("DDDddd2dddd"));
//    	String str = "!USER_VIEW.hasRoleByCodes(LeaveDeptLeader,LeaveEachLeader)";
////		  System.out.println(str.replaceAll("USER_VIEW.hasRoleByCodes\\(LeaveDeptLeader\\,LeaveEachLeader\\)", "11"));
//		  str = StringUtils.replace(str, "USER_VIEW.hasRoleByCodes(LeaveDeptLeader,LeaveEachLeader)", "11");
//		  System.out.println(str);
//        param.put("a1", "ddddd");
//        param.put("a2", "ddddd");
//        param.put("a3", "ddddd");
//        param.put("a4", "ddddd");
//        param.put("a5", "ddddd");
//        param.put("a6", "ddddd");
//        System.out.println(param);
//        String xx = HttpClientUtils.sendMessage4Post("http://192.168.0.128:8000/framework-designer2.0/services/pdmServer/PdmWebService/getAllPdm", param, null);
//        System.out.println(xx);
//		  int num = Calendar.getInstance().get(Calendar.MONDAY);
//		  Date date = DateUtils.truncate(new Date(), Calendar.MONTH);
//		  System.out.println(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
		  Iterator<Calendar> it = DateUtils.iterator(new Date(), DateUtils.RANGE_WEEK_MONDAY);
		  while(it.hasNext()){
			  System.out.println(DateFormatUtils.format(it.next(), "yyyy-MM-dd HH:mm:ss"));
		  }
		  System.out.println(it);
		  
//        Double d = new Double(111111111.020d);
//java.text.NumberFormat nf = java.text.NumberFormat.getInstance();   
//nf.setGroupingUsed(false); 
//
//        System.out.println(nf.format(d));
//    	for(int i = 0; i < 2000000; i++){
//    		getRandomOrderNo(i, 200000);
//    		if(i % 100000 == 0){
//    			System.out.println(new Date().getTime());
//    		}
//    	}
//    	String str = "d/d/d/d/";
//    	System.out.println(str.indexOf("/"));
//    	String str = "12345678";
//    	System.out.println(str.substring(2));
//    	Map<String, Object> obj = new HashMap<>();
//    	obj.put("a", 2.00);
//    	System.out.println(Double.class.isAssignableFrom(obj.get("a").getClass()));
//    	MainTest mainTest = new MainTest();
//    	System.out.println(mainTest.handleTime(new String[]{"09,12","19,22"}, 20));
    	
//    	Calendar current = Calendar.getInstance();
//    	System.out.println(current.get(Calendar.WEEK_OF_YEAR));
//        Map<String, String> map = new LinkedHashMapSp<>();
//        map.put("abd", "");
//        map.put("bcd", "");
//        map.put("acd", "");
//        map.put("a2cad", "");
//        map.put("ac3ad", "");
//        map.put("ac4da", "");
//        map.put("a6cad", "");
//        map.put("ac7da", "");
//        Set<String> keyset = map.keySet();
//        for(String key : keyset){
//        	System.out.println(key);
//        }
//    	String str = "USER_VIEW.ORGID";
//    	System.out.println(str.split("."));
//    	GetDocContent.getDocContent("D:\\develop\\workspaces\\workspace_jee4.4\\exchangePlatform\\fsgovex\\src\\main\\java\\product\\exchange\\vo\\ExDocSendVO.java");
    }  
}
