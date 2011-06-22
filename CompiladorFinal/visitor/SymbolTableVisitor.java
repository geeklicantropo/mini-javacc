package visitor;

import symbol.*;
import syntaxtree.*;

public class SymbolTableVisitor implements Visitor
{
  private Table table;
    
  public Table buildTable(Program program)
  {
    table = new Table();
    visit(program);
    return table;
  }   
    
  public void visit(Program n)
  {
    n.m.accept(this);
    for(int i =0; i< n.cl.size(); i++)
    {
      n.cl.elementAt(i).accept(this);
    }
  }

  public void visit(MainClass n)
  {
    Symbol clas = Symbol.symbol(n.i1.s);
    table.put(clas, clas.getClass());
    clas = Symbol.symbol(n.i2.s);
    table.put(clas, clas.getClass());
    n.s.accept(this);
  }
        
  public void visit(ClassDeclSimple n) 
  {
    Symbol clas = Symbol.symbol(n.i.s);
    table.put(clas, clas.getClass());
    for(int i = 0; i < n.vl.size(); i++)
    {
      n.vl.elementAt(i).accept(this);
    }
    for(int j = 0; j < n.ml.size(); j++)
    {
      n.ml.elementAt(j).accept(this);
    }
  }
        
  public void visit(ClassDeclExtends n) 
  {
    Symbol clas = Symbol.symbol(n.i.s);
    table.put(clas, clas.getClass());
    for(int i = 0; i < n.vl.size(); i++)
    {
      n.vl.elementAt(i).accept(this);
    }
    for(int j = 0; j < n.ml.size(); j++)
    {
      n.ml.elementAt(j).accept(this);
    }
  }
        
  public void visit(VarDecl n)
  {         
    table.put(Symbol.symbol(n.i.s), n.t);           
  }
        
  public void visit(MethodDecl n) 
  {
    Symbol name = Symbol.symbol(n.i.s);
    table.put(name, name.getClass());
    for ( int i = 0; i < n.fl.size(); i++ ) 
    {
      n.fl.elementAt(i).accept(this);
    }
    for ( int i = 0; i < n.vl.size(); i++ ) 
    {
      n.vl.elementAt(i).accept(this);
    }
    for ( int i = 0; i < n.sl.size(); i++ ) 
    {
      n.sl.elementAt(i).accept(this);
    }
  }

  @Override
  public void visit(Formal n) 
  {
    table.put(Symbol.symbol(n.i.s), n.t);
  }

@Override
public void visit(IntArrayType n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(BooleanType n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(IntegerType n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(IdentifierType n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(Block n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(If n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(While n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(Print n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(Assign n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(ArrayAssign n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(And n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(LessThan n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(Plus n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(Minus n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(Times n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(ArrayLookup n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(ArrayLength n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(Call n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(IntegerLiteral n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(True n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(False n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(IdentifierExp n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(This n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(NewArray n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(NewObject n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(Not n) {
	// TODO Auto-generated method stub
	
}

@Override
public void visit(Identifier n) {
	// TODO Auto-generated method stub
	
}        
}
