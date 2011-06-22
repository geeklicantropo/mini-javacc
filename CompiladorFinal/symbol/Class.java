package symbol;

import java.util.Hashtable;

public class Class
{
  public Symbol id;
  public Symbol extendClass;
  public Hashtable<Symbol, Method> mTable;
  public Hashtable<Symbol, Var> vTable;  
  
  
  public Class(Symbol id)
  {
	this.id = id;
	this.mTable = new Hashtable<Symbol, Method>();
	this.vTable = new Hashtable<Symbol, Var>();
  }
  
  public Object getMethod(Symbol key)
  {
    return mTable.get(key);
  }
  
  public Object getVar(Symbol key)
  {
    return vTable.get(key);
  }

}
