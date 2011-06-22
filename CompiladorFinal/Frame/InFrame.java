package Frame;

import Tree.exp.BINOP;
import Tree.exp.CONST;
import Tree.exp.Exp;
import Tree.exp.MEM;

public class InFrame extends Access 
{
  int offset;
  public InFrame(int o) 
  {
    offset = o;
  }

  public Exp exp(Exp fp)
  {
    return new MEM(new BINOP(BINOP.PLUS, fp, new CONST(offset)));
  }

  public String toString()
  {
    Integer offset = new Integer(this.offset);
    return offset.toString();
  }	
}
