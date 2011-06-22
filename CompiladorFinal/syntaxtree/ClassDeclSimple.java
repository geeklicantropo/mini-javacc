package syntaxtree;
import symbol.SymbolTable;
import symbol.Class;
import visitor.IRVisitor;
import visitor.SymbolTableVisitor2;
import visitor.Visitor;
import visitor.TypeVisitor;

public class ClassDeclSimple extends ClassDecl {
  public Identifier i;
  public VarDeclList vl;  
  public MethodDeclList ml;
 
  public ClassDeclSimple(Identifier ai, VarDeclList avl, MethodDeclList aml) {
    i=ai; vl=avl; ml=aml;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  
  
  public void accept(IRVisitor v) 
  {
      v.visit(this);
  }
  
  public Class accept(SymbolTableVisitor2 v)
  {
	  return v.visit(this);
  }

}
