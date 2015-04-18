package com.zt.jrebel;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtNewMethod;

// psvme
// cp
// im
// ctf
// cti
// pc
// f
// jr
public class HowItWorks {
  public static void main(String[] args) throws Exception {
    ClassPool cp = ClassPool.getDefault();
    cp.importPackage("com.zt.jrebel");

    CtClass framework = cp.get("com.zt.Framework");

    framework.addInterface(cp.get("com.zt.jrebel.Listener"));
    framework.addMethod(CtNewMethod.make(
        "public void onEvent(){" +
            "configure();" +
            "}",
        framework
    ));

    CtConstructor[] constructors = framework.getConstructors();
    for (CtConstructor constructor : constructors) {
      constructor.insertAfter(" { " +
          "System.out.println(\"Calling framework constructor!\");" +
          "JRebel.getInstance().addListener($0); " +
          "} ");
    }


    framework.toClass().newInstance();

    JRebel.getInstance().notifyListeners();


  }
}
