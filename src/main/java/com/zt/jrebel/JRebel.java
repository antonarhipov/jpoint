package com.zt.jrebel;

import java.util.ArrayList;
import java.util.List;

public class JRebel {

  List<Listener> listeners = new ArrayList<Listener>();

  private static JRebel jRebel = new JRebel();

  public static JRebel getInstance() {
    return jRebel;
  }

  public void addListener(Listener listener){
    listeners.add(listener);
  }

  public void notifyListeners(){
    for (Listener listener : listeners) {
      listener.onEvent();
    }
  }

}
