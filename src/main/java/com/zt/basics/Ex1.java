package com.zt.basics;

import javassist.ClassPool;
import javassist.CtClass;

import java.lang.reflect.Method;

/**
 * A very basic example - just construct a new class directly and do something with it
 */
public class Ex1 {
  public static void main(String[] args) throws Exception {
    ClassPool cp = ClassPool.getDefault();

    CtClass ct = cp.makeClass("org.arhan.A");

    Class clazz = ct.toClass();

    Method[] declaredMethods = clazz.getMethods();
    for (Method method : declaredMethods) {
      System.out.println("method = " + method);
    }

    ct.writeFile("output");

  }
}
