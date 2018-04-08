package com.test;

public class ScriptThreadTest {

	public static void main(String[] args) {
		for(int i = 0; i < 100; i++){
			new MyThread(i).start();
		}
	}
}
