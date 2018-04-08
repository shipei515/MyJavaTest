import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.parser.TagElement;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class WebTest {
	private String getHtml(String urlString) {
		try {
			StringBuffer html = new StringBuffer();
			java.net.URL url = new java.net.URL(urlString); // 根据 String 表示形式创建
															// URL 对象。
			java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url
					.openConnection();// 返回一个 URLConnection 对象，它表示到 URL
										// 所引用的远程对象的连接。
			java.io.InputStreamReader isr = new java.io.InputStreamReader(
					conn.getInputStream());// 返回从此打开的连接读取的输入流。
			java.io.BufferedReader br = new java.io.BufferedReader(isr);// 创建一个使用默认大小输入缓冲区的缓冲字符输入流。

			String temp;
			while ((temp = br.readLine()) != null) { // 按行读取输出流
				if (!temp.trim().equals("")) {
					html.append(temp).append("\n"); // 读完每行后换行
				}
			}
			br.close(); // 关闭
			isr.close(); // 关闭
			return html.toString(); // 返回此序列中数据的字符串表示形式。
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String sendGET(String url, String param) {
		String result = "";// 访问返回结果
		BufferedReader read = null;// 读取访问结果

		try {
			// 创建url
			URL realurl = new URL(url);
			// 打开连接
			URLConnection connection = realurl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段，获取到cookies等
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			read = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "GBK"));
			String line = "";// 循环读取
			while ((line = read.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (read != null) {// 关闭流
				try {
					read.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}
	
	public String findContent(String xml, String startStr){
		int sIndex = xml.indexOf(startStr);
		String curXml = xml.substring(sIndex + startStr.length() + 1);
		String tagStart = startStr.substring(0, startStr.indexOf(" "));
		String tagEnd = "</" + tagStart.substring(1) + ">";
		List<Integer> ilist = new ArrayList<Integer>();
		List<Integer> indexList = new ArrayList<Integer>();
		int curStartIndex = min(curXml.indexOf(tagStart, sIndex + 1), curXml.indexOf(tagEnd, sIndex + 1), ilist);
		while(curStartIndex > -1){
			indexList.add(curStartIndex);
			curStartIndex = min(curXml.indexOf(tagStart, curStartIndex + 1), curXml.indexOf(tagEnd, curStartIndex + 1), ilist);
		}
		int endIndex = 0;
		int x = 0;
		int i = 0;
		for(Integer num : ilist){
			if(x == 0 && num == 1){
				endIndex = indexList.get(i);
				break;
			}
			i++;
		}
		return curXml.substring(0, endIndex);
	}
	
	public int min(int a1, int a2, List<Integer> ilist){
		if(a1 != -1 && a2 != -1){
			ilist.add(a1 > a2 ? 0 : 1);
			return Math.min(a1, a2);
		}else if(a1 == -1){
			ilist.add(1);
			return a2;
		}else{
			ilist.add(0);
			return a1;
		}
	}
//	public String findTag(String xml, int sIndex, String tagStart, String tagEnd){
//		int startIndex = xml.indexOf(tagStart, sIndex);
//		while(startIndex != -1){
//			int endTag = xml.indexOf(tagEnd, startIndex + tagStart.length());
//			startIndex = xml.indexOf(tagStart, sIndex);
//		}
//	}
	public String handleContent(String content){
		content = content.replaceAll("&nbsp;", " ");
		content = content.replaceAll("<br /><br />", "<br />");
		content = content.replaceAll("<br />", "\r\n");
		return content;
	}

	public static void main(String[] args) throws IOException {
		WebTest test = new WebTest();
//		String rs = test.sendGET(
//				"http://www.258zw.com/html/143663/11870328.html", "");
//		String content = test.findContent(rs, "<div id=\"chapterContent\">");
//		System.out.println(test.handleContent(content));
		String urls = "<li><a href=\"/html/143663/15842867.html\">第993章第九百九十三章 风起东玄</a></li>  <li><a href=\"/html/143663/15842868.html\">第994章第九百九十四章 添油加醋</a></li>  <li><a href=\"/html/143663/15842869.html\">第995章第九百九十五章 故人相遇</a></li>  <li><a href=\"/html/143663/15867089.html\">第996章第九百九十六章 准备</a></li>  <li><a href=\"/html/143663/15869109.html\">第997章第九百九十七章 重现江湖</a></li>  <li><a href=\"/html/143663/15879053.html\">第998章第九百九十八章 好狗不挡道</a></li>  <li><a href=\"/html/143663/15879054.html\">第999章第九百九十九章 以大欺小？</a></li>  <li><a href=\"/html/143663/15879055.html\">第1000章第一千章 这才是以大欺小</a></li>  <li><a href=\"/html/143663/15908781.html\">第1001章第一千零一章 下马威</a></li>  <li><a href=\"/html/143663/15950669.html\">第1002章第一千零二章 巧取豪夺</a></li>  <li><a href=\"/html/143663/15950671.html\">第1003章第一千零三章 宁奇在哪？</a></li>  <li><a href=\"/html/143663/15950673.html\">第1004章第一千零四章 化尸</a></li>  <li><a href=\"/html/143663/15950675.html\">第1005章第一千零五章 故人寻仇</a></li>  <li><a href=\"/html/143663/15950677.html\">第1006章第一千零六章 刀起头落</a></li>  <li><a href=\"/html/143663/15950679.html\">第1007章第一千零七章 威慑</a></li>  <li><a href=\"/html/143663/15950680.html\">第1008章第一千零八章 久违的提示音</a></li>  <li><a href=\"/html/143663/15968469.html\">第1009章第一千零九章 闭关修行</a></li>  <li><a href=\"/html/143663/15969786.html\">第1010章第一千零十章 又遇故人</a></li>  <li><a href=\"/html/143663/15998392.html\">第1011章第一千零十一章 生命值破亿</a></li>  <li><a href=\"/html/143663/16000359.html\">第1012章第一千零十二章 马山豹</a></li>  <li><a href=\"/html/143663/16000590.html\">第1013章第一千零十三章 大搜捕</a></li>  <li><a href=\"/html/143663/16008907.html\">第1014章第一千零十四章 乌金</a></li>  <li><a href=\"/html/143663/16009654.html\">第1015章第一千零十五章 寻人</a></li>  <li><a href=\"/html/143663/16039583.html\">第1016章第一千零十六章 纨绔子弟</a></li>  <li><a href=\"/html/143663/16040480.html\">第1017章第一千零十七章 让我来</a></li>  <li><a href=\"/html/143663/16051326.html\">第1018章第一千零十八章 你现在知道怕？</a></li>  <li><a href=\"/html/143663/16051327.html\">第1019章第一千零十九章 打回原形</a></li>  <li><a href=\"/html/143663/16052080.html\">第1020章第一千零二十章 杀性</a></li>  <li><a href=\"/html/143663/16075372.html\">第1021章第一千零二十一章 法则破百</a></li>  <li><a href=\"/html/143663/16077503.html\">第1022章第一千零二十二章 特殊的禁法</a></li>  <li><a href=\"/html/143663/16085283.html\">第1023章第一千零二十三章 东玄城</a></li>  <li><a href=\"/html/143663/16085751.html\">第1024章第一千零二十四章 东玄城楚家</a></li>  <li><a href=\"/html/143663/16087261.html\">第1025章第一千零二十五章 不断呢？</a></li>  <li><a href=\"/html/143663/16112806.html\">第1026章第一千零二十六章 是我杀的</a></li>  <li><a href=\"/html/143663/16115925.html\">第1027章第一千零二十七章 认祖归宗</a></li>  <li><a href=\"/html/143663/16124031.html\">第1028章第一千零二十八章 公子请留步</a></li>  <li><a href=\"/html/143663/16124034.html\">第1029章第一千零二十九章 混世魔尊</a></li>  <li><a href=\"/html/143663/16125765.html\">第1030章第一千零三十章 人棍</a></li>  <li><a href=\"/html/143663/16154217.html\">第1031章第一千零三十一章 六耳妖王</a></li>  <li><a href=\"/html/143663/16154218.html\">第1032章第一千零三十二章 免费住店</a></li>  <li><a href=\"/html/143663/16156048.html\">第1033章第一千零三十三章 法则龙心</a></li>  <li><a href=\"/html/143663/16160020.html\">第1034章第一千零三十四章 中央大陆</a></li>  <li><a href=\"/html/143663/16160104.html\">第1035章第一千零三十五章 玄武拍卖行</a></li>  <li><a href=\"/html/143663/16194126.html\">第1036章第一千零三十六章 不过如此</a></li>  <li><a href=\"/html/143663/16194128.html\">第1037章第一千零三十七章 前倨后恭</a></li>  <li><a href=\"/html/143663/16194131.html\">第1038章第一千零三十八章 你到底想怎样！</a></li>  <li><a href=\"/html/143663/16197405.html\">第1039章第一千零三十九章 叛徒</a></li>  <li><a href=\"/html/143663/16197407.html\">第1040章第一千零四十章 说和</a></li>  <li><a href=\"/html/143663/16217980.html\">第1041章第一千零四十一章 挖墙角</a></li>  <li><a href=\"/html/143663/16228418.html\">第1042章第一千零四十二章 拍卖会开始</a></li>  <li><a href=\"/html/143663/16228419.html\">第1043章第一千零四十三章 楚狂</a></li>  <li><a href=\"/html/143663/16228420.html\">第1044章第一千零四十四章 杀出个程咬金</a></li>  <li><a href=\"/html/143663/16229241.html\">第1045章第一千零四十五章 再次拒绝</a></li>  <li><a href=\"/html/143663/16249390.html\">第1046章第一千零四十六章 迷糊修士</a></li>  <li><a href=\"/html/143663/16252554.html\">第1047章第一千零四十七章 购买炼神石</a></li>  <li><a href=\"/html/143663/16259753.html\">第1048章第一千零四十八章 泰坦神龙</a></li>  <li><a href=\"/html/143663/16259755.html\">第1049章第一千零四十九章 龙心到手</a></li>  <li><a href=\"/html/143663/16259757.html\">第1050章第一千零五十章 突破，斗丹境中期！</a></li>  <li><a href=\"/html/143663/16283510.html\">第1051章第一千零五十一章 打出去</a></li>  <li><a href=\"/html/143663/16283511.html\">第1052章第一千零五十二章 比斗开始</a></li>  <li><a href=\"/html/143663/16290089.html\">第1053章第一千零五十三章 怕了？</a></li>  <li><a href=\"/html/143663/16290452.html\">第1054章第一千零五十四章 斩杀孟少阴</a></li>  <li><a href=\"/html/143663/16290453.html\">第1055章第一千零五十五章 你们一起上吧</a></li>  <li><a href=\"/html/143663/16320421.html\">第1056章第一千零五十六章 再次突破</a></li>  <li><a href=\"/html/143663/16320422.html\">第1057章第一千零五十七章 斗丹境后期</a></li>  <li><a href=\"/html/143663/16320424.html\">第1058章第一千零五十八章 十成把握</a></li>  <li><a href=\"/html/143663/16320425.html\">第1059章第一千零五十九章 天诛地灭雷</a></li>  <li><a href=\"/html/143663/16323365.html\">第1060章第一千零六十章 孟家祖训第三条</a></li>  <li><a href=\"/html/143663/16350510.html\">第1061章第一千零六十一章 投影降临</a></li>  <li><a href=\"/html/143663/16350511.html\">第1062章第一千零六十二章 屠狗宝刀</a></li>  <li><a href=\"/html/143663/16354979.html\">第1063章第一千零六十三章 孟天林的背景</a></li>  <li><a href=\"/html/143663/16354983.html\">第1064章第一千零六十四章 永生境修士到来</a></li>  <li><a href=\"/html/143663/16354985.html\">第1065章第一千零六十五章 突变</a></li>  <li><a href=\"/html/143663/16391089.html\">第1066章第一千零六十六章 神秘帮手</a></li>  <li><a href=\"/html/143663/16391988.html\">第1067章第一千零六十七章 我记住你的气息了</a></li>  <li><a href=\"/html/143663/16391992.html\">第1068章第一千零六十八章 造化山脉</a></li>  <li><a href=\"/html/143663/16393820.html\">第1069章第一千零六十九章 监狱</a></li>  <li><a href=\"/html/143663/16396112.html\">第1070章第一千零七十章 淫贼</a></li>  <li><a href=\"/html/143663/16429990.html\">第1071章第一千零七十一章 血神真身</a></li>  <li><a href=\"/html/143663/16430141.html\">第1072章第一千零七十二章 大难临头各自飞</a></li>  <li><a href=\"/html/143663/16430142.html\">第1073章第一千零七十三章 坑爹任务</a></li>  <li><a href=\"/html/143663/16430143.html\">第1074章第一千零七十四章 呆萌龙女</a></li>  <li><a href=\"/html/143663/16430902.html\">第1075章第一千零七十五章 现在可以安静了吗？</a></li>  <li><a href=\"/html/143663/16466490.html\">第一千零七十六章 一脸愕然</a></li>  <li><a href=\"/html/143663/16466492.html\">第一千零七十七章 上瘾</a></li>  <li><a href=\"/html/143663/16466493.html\">第一千零七十八章 第三个愿望</a></li>  <li><a href=\"/html/143663/16466495.html\">第一千零七十九章 业火法则</a></li>  <li><a href=\"/html/143663/16466497.html\">第一千零八十章 秉公执法</a></li>  <li><a href=\"/html/143663/16494415.html\">第1081章第一千零八十一章 何苦呢？</a></li>  <li><a href=\"/html/143663/16494740.html\">第1082章第一千零八十二章 龙帝，我们又见面了</a></li>  <li><a href=\"/html/143663/16494741.html\">第1083章第一千零八十三章 父女</a></li>  <li><a href=\"/html/143663/16494742.html\">第1084章第一千零八十四章 雷龙珠</a></li>  <li><a href=\"/html/143663/16494743.html\">第1085章第一千零八十五章 回东玄城</a></li>  <li><a href=\"/html/143663/16526943.html\">第1086章第一千零八十六章 炼器宗师</a></li>  <li><a href=\"/html/143663/16528550.html\">第1087章第一千零八十七章 修行</a></li>  <li><a href=\"/html/143663/16528555.html\">第1088章第一千零八十八章 五法巅峰</a></li>  <li><a href=\"/html/143663/16528560.html\">第1089章第一千零八十九章 影晴宁</a></li>  <li><a href=\"/html/143663/16528565.html\">第1090章第一千零九十章 请宁北玄出来一见</a></li>  <li><a href=\"/html/143663/16561948.html\">第1091章第一千零九十一章 神仙打架</a></li>  <li><a href=\"/html/143663/16561949.html\">第1092章第一千零九十二章 你拜师吧</a></li>  <li><a href=\"/html/143663/16561950.html\">第1093章第一千零九十三章 身外化身</a></li>  <li><a href=\"/html/143663/16561953.html\">第1094章第一千零九十四章 谁是蝼蚁？</a></li>  <li><a href=\"/html/143663/16561954.html\">第1095章第一千零九十五章 给老子滚！</a></li>  <li><a href=\"/html/143663/16605499.html\">第1096章第一千零九十六章 玄武真君</a></li>  <li><a href=\"/html/143663/16605500.html\">第1097章第一千零九十七章 凤九仙</a></li>  <li><a href=\"/html/143663/16605501.html\">第1098章第一千零九十八章 他到底是谁</a></li>  <li><a href=\"/html/143663/16605502.html\">第1099章第一千零九十九章 是福不是祸</a></li>  <li><a href=\"/html/143663/16606659.html\">第1100章第一千一百章 道衍仙皇</a></li>  <li><a href=\"/html/143663/16631359.html\">第1101章第一千一百零一章 回归</a></li>  <li><a href=\"/html/143663/16631366.html\">第1102章第一千一百零二章 有女初长成</a></li>  <li><a href=\"/html/143663/16632604.html\">第1103章第一千一百零三章 拖延时间</a></li>  <li><a href=\"/html/143663/16641057.html\">第1104章第一千一百零四章 戏耍</a></li>  <li><a href=\"/html/143663/16641778.html\">第1105章第一千一百零五章 阴阳法则</a></li>  <li><a href=\"/html/143663/16665471.html\">第1106章第一千一百零六章 不走，就死！</a></li>  <li><a href=\"/html/143663/16665666.html\">第1107章第一千一百零七章 五毒神女</a></li>  <li><a href=\"/html/143663/16674873.html\">第1108章第一千一百零八章 轰击战神殿</a></li>  <li><a href=\"/html/143663/16674874.html\">第1109章第一千一百零九章 你老大算个什么东西</a></li>  <li><a href=\"/html/143663/16675350.html\">第1110章第一千一百一十章 气势如虹</a></li>  <li><a href=\"/html/143663/16705154.html\">第1111章第一千一百一十一章 初阶圣地</a></li>  <li><a href=\"/html/143663/16705156.html\">第1112章第一千一百一十二章 寒天请辞</a></li>  <li><a href=\"/html/143663/16715262.html\">第1113章第一千一百一十三章 他叫宁奇</a></li>  <li><a href=\"/html/143663/16715368.html\">第1114章第一千一百一十四章 留你不得</a></li>  <li><a href=\"/html/143663/16715369.html\">第1115章第一千一百一十五章 报仇</a></li>  <li><a href=\"/html/143663/16747238.html\">第1116章第一千一百一十六章 博古宗的危机</a></li>  <li><a href=\"/html/143663/16747613.html\">第1117章第一千一百一十七章 气吞万里</a></li>  <li><a href=\"/html/143663/16747614.html\">第1118章第一千一百一十八章 回炉重造</a></li>  <li><a href=\"/html/143663/16752874.html\">第1119章第一千一百一十九章 启程</a></li>  <li><a href=\"/html/143663/16752875.html\">第1120章第一千一百二十章 打砸</a></li>  <li><a href=\"/html/143663/16788738.html\">第1121章第一千一百二十一章 送他们上路</a></li>  <li><a href=\"/html/143663/16789151.html\">第1122章第一千一百二十二章 恐怖的眼珠</a></li>  <li><a href=\"/html/143663/16790408.html\">第1123章第一千一百二十三章 闭关千年</a></li>  <li><a href=\"/html/143663/16797378.html\">第1124章第一千一百二十四章 钳天螳螂</a></li>  <li><a href=\"/html/143663/16797912.html\">第1125章第一千一百二十五章 大漠域</a></li>  <li><a href=\"/html/143663/16828692.html\">第1126章第一千一百二十六章 增法丹</a></li>  <li><a href=\"/html/143663/16828696.html\">第1127章第一千一百二十七章 法器之争</a></li>  <li><a href=\"/html/143663/16835205.html\">第1128章第一千一百二十八章 你敢给我下套！</a></li>  <li><a href=\"/html/143663/16835206.html\">第1129章第一千一百二十九章 你又不是我儿子</a></li>  <li><a href=\"/html/143663/16836395.html\">第1130章第一千一百三十章 一招就够</a></li>  <li><a href=\"/html/143663/16868364.html\">第1131章第一千一百三十一章 新任谷主</a></li>  <li><a href=\"/html/143663/16874240.html\">第1132章第一千一百三十二章 炼丹</a></li>  <li><a href=\"/html/143663/16875093.html\">第1133章第一千一百三十三章 再临大漠坊市</a></li>  <li><a href=\"/html/143663/16901073.html\">第1134章第一千一百三十四章 暮秋蝉</a></li>  <li><a href=\"/html/143663/16910935.html\">第1135章第一千一百三十五章 拒绝</a></li>  <li><a href=\"/html/143663/16970971.html\">第1136章第一千一百三十六章 进阶任务</a></li>  <li><a href=\"/html/143663/16972070.html\">第1137章第一千一百三十七章 丹皇</a></li>  <li><a href=\"/html/143663/16981814.html\">第1138章第一千一百三十八章 你敢杀我！？</a></li>  <li><a href=\"/html/143663/17001162.html\">第1139章第一千一百三十九章 姜丹皇</a></li>  <li><a href=\"/html/143663/17004598.html\">第1140章第一千一百四十章 第一轮比试</a></li>  <li><a href=\"/html/143663/17079383.html\">第1141章第一千一百四十一章 成丹</a></li>  <li><a href=\"/html/143663/17079386.html\">第1142章第一千一百四十二章 原来他叫北玄</a></li>  <li><a href=\"/html/143663/17103324.html\">第1143章第一千一百四十三章 生生不息丹</a></li>  <li><a href=\"/html/143663/17104002.html\">第1144章第一千一百四十四章 炼丹成龙</a></li>  <li><a href=\"/html/143663/17104008.html\">第1145章第一千一百四十五章 玄真大圣</a></li>  <li><a href=\"/html/143663/17189546.html\">第1146章第一千一百四十六章 试探</a></li>  <li><a href=\"/html/143663/17190301.html\">第1147章第一千一百四十七章 突破！永生境！</a></li>  <li><a href=\"/html/143663/17190304.html\">第1148章第一千一百四十八章 告密者</a></li>  <li><a href=\"/html/143663/17200209.html\">第1149章第一千一百四十九章 旭日东升</a></li>  <li><a href=\"/html/143663/17200215.html\">第1150章第一千一百五十章 疯了</a></li>  <li><a href=\"/html/143663/17296767.html\">第1151章第一千一百五十一章 青州府</a></li>  <li><a href=\"/html/143663/17296768.html\">第1152章第一千一百五十二章 取而代之</a></li>  <li><a href=\"/html/143663/17321528.html\">第1153章第一千一百五十三章 宫主谕令</a></li>  <li><a href=\"/html/143663/17321531.html\">第1154章第一千一百五十四章 你可以滚了</a></li>  <li><a href=\"/html/143663/17327118.html\">第1155章第一千一百五十五章 这是个误会</a></li>  <li><a href=\"/html/143663/17406842.html\">第1156章第一千一百五十六章 玄幽丹宗</a></li>  <li><a href=\"/html/143663/17433453.html\">第1157章第一千一百五十七章 落井下石</a></li>  <li><a href=\"/html/143663/17433791.html\">第1158章第一千一百五十八章 三天之约</a></li>  <li><a href=\"/html/143663/17448103.html\">第1159章第一千一百五十九章 冤大头？</a></li>  <li><a href=\"/html/143663/17448107.html\">第1160章第一千一百六十章 问罪</a></li>  <li><a href=\"/html/143663/17510238.html\">第1161章第一千一百六十一章 蓝晨松的招揽</a></li>  <li><a href=\"/html/143663/17519010.html\">第1162章第一千一百六十二章 昆仑洞天少洞主</a></li>  <li><a href=\"/html/143663/17539153.html\">第1163章第一千一百六十三章 冥器</a></li>  <li><a href=\"/html/143663/17548833.html\">第1164章第一千一百六十四章 孟天舒</a></li>  <li><a href=\"/html/143663/17549541.html\">第1165章第一千一百六十五章 传承丹药</a></li>  <li><a href=\"/html/143663/17613377.html\">第1166章第一千一百六十六章 成丹</a></li>  <li><a href=\"/html/143663/17619680.html\">第1167章第一千一百六十七章 阎王泪</a></li>  <li><a href=\"/html/143663/17639789.html\">第1168章第一千一百六十八章 我来吧</a></li>  <li><a href=\"/html/143663/17640513.html\">第1169章第一千一百六十九章 夜叉法相</a></li>  <li><a href=\"/html/143663/17642881.html\">第1170章第一千一百七十章 睁眼说瞎话</a></li>  <li><a href=\"/html/143663/17728513.html\">第1171章第一千一百七十一章 完全碾压</a></li>  <li><a href=\"/html/143663/17728517.html\">第1172章第一千一百七十二章 你敢走吗？</a></li>  <li><a href=\"/html/143663/17821345.html\">第1173章第一千一百七十三章 巨灵法相</a></li>  <li><a href=\"/html/143663/17821347.html\">第1174章第一千一百七十四章 三年之约</a></li>  <li><a href=\"/html/143663/17821348.html\">第1175章第一千一百七十五章 男儿一诺千金</a></li>  <li><a href=\"/html/143663/17821350.html\">第1176章第一千一百七十六章 孟天舒的要求</a></li>  <li><a href=\"/html/143663/17821353.html\">第1177章第一千一百七十七章 坑爹的更新</a></li>  <li><a href=\"/html/143663/17821356.html\">第1178章第一千一百七十八章 荒古禁地</a></li>  <li><a href=\"/html/143663/17822050.html\">第1179章第一千一百七十九章 普通人</a></li>  <li><a href=\"/html/143663/17825950.html\">第1180章第一千一百八十章 陨神之地</a></li>  <li><a href=\"/html/143663/17894985.html\">第1181章第一千一百八十一章 朱雀草</a></li>  <li><a href=\"/html/143663/17898582.html\">第1182章第一千一百八十二章 行尸走肉</a></li>  <li><a href=\"/html/143663/17901443.html\">第1183章第一千一百八十三章 仙族尸身</a></li>  <li><a href=\"/html/143663/17911678.html\">第1184章第一千一百八十四章 入城</a></li>  <li><a href=\"/html/143663/17917016.html\">第1185章第一千一百八十五章 他的后代</a></li>  <li><a href=\"/html/143663/17967980.html\">第1186章第一千一百八十六章 神恩</a></li>  <li><a href=\"/html/143663/17970212.html\">第1187章第一千一百八十七章 别跑！</a></li>  <li><a href=\"/html/143663/17972101.html\">第1188章第一千一百八十八章 你无耻！</a></li>  <li><a href=\"/html/143663/17979683.html\">第1189章第一千一百八十九章 因祸得福</a></li>  <li><a href=\"/html/143663/17979913.html\">第1190章第一千一百九十章 十万年的玄耶果</a></li>  <li><a href=\"/html/143663/18032713.html\">第1191章第一千一百九十一章 修为暴增</a></li>";
		List<String> urlArr = new ArrayList<String>();
		int index = urls.indexOf("\"");
		int endIndex = urls.indexOf("\"", index + 1);
		while(index > -1){
			urlArr.add("http://www.258zw.com" + urls.substring(index + 1, endIndex));
			index = urls.indexOf("\"", endIndex + 1);
			endIndex = urls.indexOf("\"", index + 1);
		}
		System.out.println(urlArr);
		String result = "";
		for(String url : urlArr){
			String rs = test.sendGET(
					url, "");
			String content = test.findContent(rs, "<div id=\"chapterContent\">");
			result += test.handleContent(content) + "\r\n";
		}
		FileUtils.writeStringToFile(new File("C:\\Users\\sp\\Desktop\\myfile.txt"), result);
	}

}
