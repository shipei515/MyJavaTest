package com.test;

import java.math.BigDecimal;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class MyThread extends Thread {

	private int i;
	public MyThread(int i) {
		super();
		this.i = i;
	}
	
	@Override
	public void run() {
		ScriptEngine scriptEngine = TestScript.getInstance().getScriptEngine();
		scriptEngine.put("num", i);
		try {
			BigDecimal result = (BigDecimal)scriptEngine.eval("if(num>50) num + 100-20/10 ");
			System.out.println(result);
//			if(i == result){
////				System.out.println("right");
//			}else{
//				System.out.println("error");
//			}
//			System.out.println("input::" + i + "   output::" + result);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
}
