package syntaxtree;
import symbol.SymbolTable;
import visitor.IRVisitor;
import visitor.SymbolTableVisitor2;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Program {
  public MainClass m;
  public ClassDeclList cl;

  public Program(MainClass am, ClassDeclList acl) {
    m=am; cl=acl; 
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  
  public SymbolTable accept(SymbolTableVisitor2 v)
  {
	  return v.visit(this);
  }
  
  public void accept(IRVisitor v) 
  {
	 v.visit(this);
  }
}
