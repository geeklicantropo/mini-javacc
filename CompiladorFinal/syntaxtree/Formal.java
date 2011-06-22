package syntaxtree;
import symbol.Var;
import visitor.IRVisitor;
import visitor.SymbolTableVisitor2;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Formal {
  public Type t;
  public Identifier i;
 
  public Formal(Type at, Identifier ai) {
    t=at; i=ai;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  
  public Var accept(SymbolTableVisitor2 v)
  {
	  return v.visit(this);
  }
  
  public Tree.exp.Exp accept(IRVisitor v) 
  {
	 return v.visit(this);
  }
}
