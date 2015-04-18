package com.zt.basics;

import com.zt.Bean;
import com.zt.Component;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.Method;

/**
 * Demonstrates Javassist proxy support
 *
 */
public class Ex4 {

  public static void main(String... args) throws Exception {
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setSuperclass(Bean.class);

    Class c = proxyFactory.createClass(new MethodFilter() {
      public boolean isHandled(Method m) {
        // use MethodFilter in order
        // not to proxy finalize, equals & hashCode
        return !m.getName().equalsIgnoreCase("finalize");
      }
    });


    Object proxy = c.newInstance();

    ((Proxy) proxy).setHandler(new MethodHandler() {
      public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
        System.out.println("in method handler for proxy");
        return proceed.invoke(self, args);
      }
    });

    Component component = new Component();
    component.setBean((Bean) proxy);
    component.go();

    Object proxy2 = c.newInstance();
    Component component2 = new Component();
    component2.setBean((Bean) proxy2);
    component2.go();
  }
}
