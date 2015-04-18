package com.zt;

public class Util {

  public static Object invokeViaReflection(Class<?> clazz, String method) throws Exception {
      return clazz.getDeclaredMethod(method).invoke(clazz.newInstance());
  }

  public static Object invokeViaReflectionWithParams(Class<?> clazz,
                                                     String method,
                                                     Class[] signature,
                                                     Object[] parameters) throws Exception {
      return clazz.getDeclaredMethod(method, signature)
          .invoke(clazz.newInstance(), parameters);
  }
}
