import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class GroovyTest {

	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine scriptEngine = factory.getEngineByName("groovy");
		scriptEngine.put("node", "1,2");
		Object value = scriptEngine.eval("node.size() == 1 && node.indexOf(\"3\")");
		System.out.println(value);
	}
}
