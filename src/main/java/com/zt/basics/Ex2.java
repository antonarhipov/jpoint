package com.zt.basics;

import com.zt.Util;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * Direct patching of org.arhan.A class:
 * 1) locate A
 * 2) patch foo: setBody, insertBefore, insertAfter
 * 3) invoke foo via reflection
 */
public class Ex2 {

  public static void main(String[] args) throws Exception {
    ClassPool cp = ClassPool.getDefault();

    CtClass ct = cp.get("org.arhan.A");

    CtMethod foo = ct.getMethod("foo", "()V");

    foo.setBody("{ System.out.println(\"Hello Jpoint!\");}");

    Class clazz = ct.toClass();

    Util.invokeViaReflection(clazz, "foo");
  }
}
