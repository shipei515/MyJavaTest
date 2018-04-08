package com.test;
 
import java.io.File;
import java.io.IOException;

import net.sf.jni4net.Bridge;
 
public class JniTest {
 
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    try {
      Bridge.setVerbose(true);
      Bridge.init(new File(
          "C:/jni4net.n.w64.v40-0.8.8.0.dll"));
      Bridge.LoadAndRegisterAssemblyFrom(new File(
          "C:/CSharpLib.j4n.dll"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
//    MainTable m = new MainTable();
//    HelloModel hm = m.GetHelloObject();
//    System.out.println(m.GetHello("test"));
//    System.out.println(hm.getHello());
//    System.out.println(hm.getWorld());
  }
}
