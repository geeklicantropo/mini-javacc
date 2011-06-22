package syntaxtree;
import symbol.Class;
import symbol.Symbol;
import visitor.IRVisitor;
import visitor.SymbolTableVisitor2;
import visitor.Visitor;
import visitor.TypeVisitor;

public class MainClass 
{
  public Identifier i1,i2; //i1= nome da classe,  i2= identificador dos parametros de entrada
  public Statement s; //instruções dentro da classe principal

  public MainClass(Identifier ai1, Identifier ai2, Statement as)
  {
    i1=ai1; i2=ai2; s=as;
  }

  public void accept(Visitor v)
  {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) 
  {
    return v.visit(this);
  }
  
  public void accept(IRVisitor v)
  {
      v.visit(this);
  }
  
  public Class accept(SymbolTableVisitor2 v, Symbol id) 
  {
      return new Class(id);
  }


}

