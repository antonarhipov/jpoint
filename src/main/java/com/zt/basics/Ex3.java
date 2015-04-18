package com.zt.basics;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.expr.ExprEditor;
import javassist.expr.NewExpr;
import com.zt.Util;

/**
 * Direct patching of org.arhan.A class:
 * 1) locate A
 * 2) patch foo: instrument, intercept 'new', or replace all println calls, etc..
 * 3) invoke foo via reflection
 */
public class Ex3 {

  public static void main(String[] args) throws Exception {
    ClassPool cp = ClassPool.getDefault();
    CtClass ct = cp.get("org.arhan.A");
    CtMethod foo = ct.getMethod("foo", "()V");

    foo.instrument(new ExprEditor() {
      @Override
      public void edit(NewExpr e) throws CannotCompileException {
        e.replace("{" +
            "$_ = $proceed($$);" +
            "System.out.println($_);" +
            "}");
      }
    });

    Class clazz = ct.toClass();
    Util.invokeViaReflection(clazz, "foo");
  }
}
