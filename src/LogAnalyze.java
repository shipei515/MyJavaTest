import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;


public class LogAnalyze {

	public static void main(String[] args) {
		String path = "C:\\Users\\sp\\Desktop\\1116.txt";
		try {
			List<String> list = FileUtils.readLines(new File(path), "gbk");
			Map<String, String> idMapTitle = new HashMap<>();
			for(String line : list){ 
				if(StringUtils.isNotBlank(line)){
					if(line.indexOf("type【getById】") != -1){
						String inputId = StringUtils.substringBetween(line, "inputParam【", "】");
						String data = StringUtils.substringBetween(line, "data【", "end】");
						String id = StringUtils.substringBetween(data, "id=\"", "\",");
						if(!"a6890333-a94c-436e-836a-5ccb81d88ed0".equals(id)){
							continue;
						}
						if("a6890333-a94c-436e-836a-5ccb81d88ed0".equals(id)){
							System.out.println(line);
						}
						String title = StringUtils.substringBetween(data, "title=\"", "\",");
						if(!inputId.equals(id)){
//							System.out.println("输入ID为：" + inputId + "的数据查询出来的结果ID不正确，结果ID：" + id);
						}
						if(idMapTitle.containsKey(id)){
							if(!title.equals(idMapTitle.get(id))){
								System.out.println("ID为：" + id + "的数据title字段进行了变更，新：" + title + ",旧:" + idMapTitle.get(id));
							}
						}
						idMapTitle.put(id, title);
					}else if(line.indexOf("type【update】") != -1){
						String data = StringUtils.substringBetween(line, "data【", "end】");
						String id = StringUtils.substringBetween(data, "id=\"", "\",");
						if(!"a6890333-a94c-436e-836a-5ccb81d88ed0".equals(id)){
							continue;
						}
						if("a6890333-a94c-436e-836a-5ccb81d88ed0".equals(id)){
							System.out.println(line);
						}
						String title = StringUtils.substringBetween(data, "title=\"", "\",");
						if(idMapTitle.containsKey(id)){
							if(!title.equals(idMapTitle.get(id))){
								System.out.println("ID为：" + id + "的数据title字段进行了变更，新：" + title + ",旧:" + idMapTitle.get(id));
							}
						}
						idMapTitle.put(id, title);
					
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
