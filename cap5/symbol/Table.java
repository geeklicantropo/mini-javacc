package symbol;

import java.util.*;

class Binder 
{
  Object value;
  Symbol key;
  Binder next;

  Binder(Object v, Symbol k, Binder n)
  {
        value=v; key=k; next=n;
  }
}

public class Table {

  private Dictionary dict = new Hashtable();
  private Symbol top;
  private Binder marks;

  public Table(){}

  public Object get(Symbol key) {
        Binder e = (Binder)dict.get(key);
        if (e==null) return null;
        else return e.value;
  }     

  public void put(Symbol key, Object value) {
        dict.put(key, new Binder(value, top, (Binder)dict.get(key)));
        top = key;
  }

  public void beginScope() {marks = new Binder(null,top,marks); top=null;}

  public void endScope() {
        while (top!=null) {
           Binder e = (Binder)dict.get(top);
           if (e.next!=null) dict.put(top,e.next);
           else dict.remove(top);
           top = e.key;
        }
        top=marks.key;
        marks=marks.next;
  }
  
  public Enumeration keys() {return dict.keys();}
}