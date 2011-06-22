package Frame;

import Temp.Temp;
import Tree.exp.Exp;
import Tree.exp.TEMP;

public class InReg extends Access
{
  Temp temp;
  public InReg(Temp t) 
  {
    temp = t;
  }

  public Exp exp(Exp fp)
  {
    return new TEMP(temp);
  }

  public String toString()
  {
    return temp.toString();
  }
}
