
package visitor;

import java.util.ArrayList;

import syntaxtree.*;
import symbol.*;

public class TypeCheckerVisitor implements TypeVisitor 
{
  private Table table;
  private boolean err;
  private ArrayList<String> error;
        
  public TypeCheckerVisitor(Table t, boolean be, ArrayList<String> e)
  {
    table = t;
    err = be;
    error = e;
  }
        
  public boolean getError()
  {
    return err;     
  }
        
  public Type visit(Program n)
  {
    n.m.accept(this);
    for ( int i = 0; i < n.cl.size(); i++ ) 
    {
      n.cl.elementAt(i).accept(this);
    }
    return null;
  }
        
  
  public Type visit(MainClass n)
  {
    table.beginScope();
    n.i1.accept(this);
    n.i2.accept(this);
    n.s.accept(this);       
    table.endScope();
    return null;
  }
  
  public Type visit(ClassDeclSimple n) 
  {
    table.beginScope();
    n.i.accept(this);
    for(int i = 0; i < n.vl.size(); i++)
    {
      n.vl.elementAt(i).accept(this);
    }
    for(int i = 0; i < n.ml.size(); i++)
    {
      n.ml.elementAt(i).accept(this);
    }
    table.endScope();
    return null;
  }

  public Type visit(ClassDeclExtends n) 
  {
    table.beginScope();
    n.i.accept(this);
    n.j.accept(this);
    for ( int i = 0; i < n.vl.size(); i++ ) 
    {
      n.vl.elementAt(i).accept(this);
    }
    for ( int i = 0; i < n.ml.size(); i++ ) 
    {
      n.ml.elementAt(i).accept(this);
    }       
    table.endScope();
    return null;                        
  }

  public Type visit(VarDecl n)
  {
    Symbol s = Symbol.symbol(n.i.s);
    Type type = (Type)table.get(s);
                 
    if(!(type instanceof IntArrayType) && !(type instanceof IntegerType) && !(type instanceof BooleanType) && (type == null))
    {
      //error.add("TypeChecking error: The type of " + s.toString() + " is undeclared");
      error.add("O tipo da variavel " + s.toString() + " nao foi declarada");
      err = true;                 
    }
    return null;
  }

  public Type visit(MethodDecl n)
  {
    table.beginScope();
    Symbol s = Symbol.symbol(n.i.s);
    n.t.accept(this);
    java.lang.Class<Type> type = (java.lang.Class<Type>)table.get(s);
    n.i.accept(this);
    if(!(n.t instanceof IntArrayType) && !(n.t instanceof IntegerType) && !(n.t instanceof BooleanType) && (type == null))
    {
      //error.add("TypeChecking error: Invalid return type for " + s.toString());
      error.add("Tipo de retorno invalido para " + s.toString());
      err = true;
    }
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
    n.e.accept(this);       
    return null;
  }

  public Type visit(Formal n)
  {
    n.t.accept(this);
    n.i.accept(this);
    Symbol s = Symbol.symbol(n.i.s);
    Type type = (Type)table.get(s);
                        
    if(!(type instanceof IntArrayType) && !(type instanceof IntegerType) && !(type instanceof BooleanType) && (type == null))
    {
      //error.add("TypeChecking error: The type of " + s.toString() + " is undeclared");
      error.add("O tipo da variavel " + s.toString() + " nao foi declarado");
      err = true;                 
    }
      return null;
  }
        
  public Type visit(Block n)
  {
    for ( int i = 0; i < n.sl.size(); i++ ) 
    {
      n.sl.elementAt(i).accept(this);
    }
    return null;
  }

  public Type visit(If n) 
  {
    n.e.accept(this);
    n.s1.accept(this);
    n.s2.accept(this);
    return null;
  }

  public Type visit(While n)
  {
    n.e.accept(this);
    n.s.accept(this);
    return null;
  }

  public Type visit(Print n)
  {
    n.e.accept(this);
    return null;
  }

  public Type visit(Assign n) 
  {
    n.i.accept(this);
    Symbol s = Symbol.symbol(n.i.s);
    Type type = (Type)table.get(s);
                        
    if(!(type instanceof IntArrayType) && !(type instanceof IntegerType) && !(type instanceof BooleanType) && (type == null))
    {
      //error.add("TypeChecking error: The variable " + s.toString() + " is undeclared");
      error.add("A variavel " + s.toString() + " nao foi declarada");
      err = true;                 
    }
    n.e.accept(this);
    return null;
  }

  public Type visit(ArrayAssign n)
  {
    n.i.accept(this);
    Symbol s = Symbol.symbol(n.i.s);
    Type type = (Type)table.get(s);
                        
    if(!(type instanceof IntArrayType) && !(type instanceof IntegerType) && !(type instanceof BooleanType) && (type == null))
    {
      //error.add("TypeChecking error: The variable " + s.toString() + " is undeclared");
      error.add("A variavel " + s.toString() + " nao foi declarada");
      err = true;                 
    }
    n.e1.accept(this);
    n.e2.accept(this);
    return null;
  }

@Override
public Type visit(IntArrayType n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(BooleanType n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(IntegerType n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(IdentifierType n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(And n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(LessThan n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(Plus n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(Minus n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(Times n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(ArrayLookup n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(ArrayLength n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(Call n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(IntegerLiteral n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(True n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(False n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(IdentifierExp n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(This n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(NewArray n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(NewObject n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(Not n) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public Type visit(Identifier n) {
	// TODO Auto-generated method stub
	return null;
}  

}
