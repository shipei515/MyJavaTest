package com.test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestScript {

	private static TestScript testScript;
    /**
     * 脚本引擎
     */
    protected ScriptEngine scriptEngine;
    
    public TestScript() {
		initScriptEngine();
	}

    public static TestScript getInstance(){
    	if(testScript == null){
    		testScript = new TestScript();
    	}
    	return testScript;
    }
    /**
     * 初始化脚本引擎，使用groovy引擎。
     */
    protected void initScriptEngine()
    {
        ScriptEngineManager factory = new ScriptEngineManager();
        this.scriptEngine = factory.getEngineByName("groovy");
    }

    public ScriptEngine getScriptEngine()
    {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine scriptEngine = factory.getEngineByName("groovy");
        return scriptEngine;
    }

    public void setScriptEngine(ScriptEngine scriptEngine)
    {
        this.scriptEngine = scriptEngine;
    }
}
