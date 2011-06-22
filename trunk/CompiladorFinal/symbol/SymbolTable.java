package symbol;

import java.util.ArrayList;
import java.util.Hashtable;

public class SymbolTable 
{	
  public Hashtable<Symbol, Class> cTable;
  private ArrayList<String> error;
     
  public SymbolTable()
  {
    cTable = new Hashtable<Symbol, Class>();
  }       

   
  public Object get(Symbol key)
  {
    return cTable.get(key);
  }

  //public Set<Symbol> keys()
  //{
  //  return cTable.keySet();
  //}

  public void put(Symbol key, Object value)
  {
    if(cTable.get(key)!=null)
      error.add("Classe "+key.toString()+" ja foi definida no escopo do programa");
    else
      cTable.put(key,(Class)value);
  }       
  /*   
  public void print()
  {
    for (Symbol k : keys())
    {
      System.out.println(k);
      ClassTable cT = cTable.get(k);
      cT.print();
    }
  }
   */
  
  public String toString()
  {
    return cTable.toString();
  }


}
