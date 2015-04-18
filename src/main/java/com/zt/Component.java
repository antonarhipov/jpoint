package com.zt;

public class Component {
  Bean bean;

  public void go(){
    bean.doSomething();
  }

  public Bean getBean() {
    return bean;
  }

  public void setBean(Bean bean) {
    this.bean = bean;
  }
}
