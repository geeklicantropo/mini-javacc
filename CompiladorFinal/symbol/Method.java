package symbol;

import java.util.Hashtable;

public class Method
{
  public Symbol id; 
  public Hashtable<Symbol, Var> vTable;
  
  public Method(Symbol id)
  {
	this.id = id;
	this.vTable = new Hashtable<Symbol, Var>(); 
  }
  
  public Object getVar(Symbol key)
  {
    return vTable.get(key);
  }

}
