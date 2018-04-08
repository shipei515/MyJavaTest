package com.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class GetDocContent {

	public static void getDocContent(String filePath){
		List<String> strList;
		try {
			strList = FileUtils.readLines(new File(filePath));
			StringBuffer sb = new StringBuffer();
			String remark = "";
			
			for(String str : strList){
				String curStr = StringUtils.trim(str);
				if(curStr.startsWith("*") && curStr.length() > 2 && !curStr.startsWith("**/") && !curStr.endsWith("*/")){
					remark = curStr.substring(2);
				}
				if(curStr.startsWith("/**") && curStr.endsWith("*/")){
					remark = curStr.substring(3, curStr.length() - 2).trim();
				}
				if(curStr.startsWith("private")){
					String enName = curStr.substring(curStr.lastIndexOf(" ") + 1, curStr.length() - 1);
					sb.append(StringUtils.rightPad(enName, 40, " ") + "	" + remark + "\r\n");
					remark = "";
				}
			}
			System.out.println(sb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
