package syntaxtree;
import visitor.IRVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public abstract class Statement 
{
  public abstract void accept(Visitor v);
  public abstract Type accept(TypeVisitor v);
  public abstract Tree.exp.Exp accept(IRVisitor v);
}
