package syntaxtree;
import Tree.exp.Exp;
import visitor.IRVisitor;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Block extends Statement {
  public StatementList sl;

  public Block(StatementList asl) {
    sl=asl;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  public Tree.exp.Exp accept(IRVisitor v) 
  {
	 return v.visit(this);
  }
}

