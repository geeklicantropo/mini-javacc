package syntaxtree;
import visitor.IRVisitor;

import visitor.SymbolTableVisitor2;
import visitor.Visitor;
import visitor.TypeVisitor;

import symbol.Class;

public abstract class ClassDecl
{
  public abstract void accept(Visitor v);
  public abstract Type accept(TypeVisitor v);
  public abstract void accept(IRVisitor v); 
  public abstract Class accept(SymbolTableVisitor2 v);
}
