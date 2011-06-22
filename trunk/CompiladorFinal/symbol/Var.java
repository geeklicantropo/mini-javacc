package symbol;

import Frame.Access;
import Frame.InReg;
import Temp.Temp;
import syntaxtree.*;

public class Var
{
  public Type type;
  public Symbol name;    
  public Access access;

  public Var(Type t, Symbol s)
  {
	  type = t;
	  name = s;
	  access = new InReg(new Temp());
  }
	
}
