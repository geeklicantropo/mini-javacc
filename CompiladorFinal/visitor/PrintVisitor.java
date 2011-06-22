package visitor;

import syntaxtree.*;

public class PrintVisitor implements Visitor 
{
  private int ident=0;
	
  public void ident1()
  {
	ident++;
	System.out.print("\n");
	for(int i=0; i<ident; i++) System.out.print("  ");
  }
  
  public void ident2()
  {
	ident--;
  }
	
  // MainClass m;
  // ClassDeclList cl;
  public void visit(Program n) 
  {
	System.out.print("Program");
    n.m.accept(this);
    for ( int i = 0; i < n.cl.size(); i++ )
    {        
        n.cl.elementAt(i).accept(this);
    }
  }
  
  // Identifier i1,i2;
  // Statement s;
  public void visit(MainClass n)
  {
	ident1();	
    System.out.print("MainClass");    
    n.i1.accept(this);    
    n.i2.accept(this);    
    n.s.accept(this);
    ident2();
  }

  // Identifier i;
  // VarDeclList vl;
  // MethodDeclList ml;
  public void visit(ClassDeclSimple n) 
  {
	ident1();
    System.out.print("ClassDeclSimple");
    n.i.accept(this);
    
    for ( int i = 0; i < n.vl.size(); i++ ) 
    {        
        n.vl.elementAt(i).accept(this);        
    }
    for ( int i = 0; i < n.ml.size(); i++ ) 
    {        
        n.ml.elementAt(i).accept(this);
    }
    ident2();
    
  }
 
  // Identifier i;
  // Identifier j;
  // VarDeclList vl;
  // MethodDeclList ml;
  public void visit(ClassDeclExtends n)
  {
	ident1();
    System.out.print("ClassDeclExtends");
    n.i.accept(this);    
    n.j.accept(this);
    
    for ( int i = 0; i < n.vl.size(); i++ ) 
    {        
      n.vl.elementAt(i).accept(this);
      //if ( i+1 < n.vl.size() ) { System.out.println(); }
    }
    for ( int i = 0; i < n.ml.size(); i++ ) 
    {        
       n.ml.elementAt(i).accept(this);
    }
    ident2();
  }

  // Type t;
  // Identifier i;
  public void visit(VarDecl n)
  {
	ident1();
	System.out.print("VarDecl");
    n.t.accept(this);    
    n.i.accept(this);
    ident2();
  }

  // Type t;
  // Identifier i;
  // FormalList fl;
  // VarDeclList vl;
  // StatementList sl;
  // Exp e;
  public void visit(MethodDecl n)
  {
	ident1();
    System.out.print("MethodDecl");
    n.t.accept(this);    
    n.i.accept(this);
    
    for ( int i = 0; i < n.fl.size(); i++ )
    {
        n.fl.elementAt(i).accept(this);
        //if (i+1 < n.fl.size()) { System.out.print(", "); }
    }
    
    for ( int i = 0; i < n.vl.size(); i++ ) 
    {       
      n.vl.elementAt(i).accept(this);      
    }
    
    for ( int i = 0; i < n.sl.size(); i++ ) 
    {        
      n.sl.elementAt(i).accept(this);
      //if ( i < n.sl.size() ) { System.out.println(""); }
    }    
    n.e.accept(this);    
    ident2();
  }

  // Type t;
  // Identifier i;
  public void visit(Formal n) 
  {
	ident1();
	System.out.print("Formal");
    n.t.accept(this);    
    n.i.accept(this);
    ident2();
  }

  public void visit(IntArrayType n) 
  {
	ident1();
    System.out.print("IntArrayType");
    ident2();
  }

  public void visit(BooleanType n)
  {
	ident1();
    System.out.print("BooleanType");
    ident2();
  }

  public void visit(IntegerType n)
  {
	ident1();
    System.out.print("IntegerType");
    ident2();
  }

  // String s;
  public void visit(IdentifierType n) 
  {
	ident1();
    System.out.print("IdentifierType(" + n.s + ")");
    ident2();
  }

  // StatementList sl;
  public void visit(Block n)
  {
	ident1();
    System.out.println("Block");
    for ( int i = 0; i < n.sl.size(); i++ )
    {       
      n.sl.elementAt(i).accept(this);        
    }
    ident2();
  }

  // Exp e;
  // Statement s1,s2;
  public void visit(If n) 
  {
	ident1();
    System.out.print("If");
    n.e.accept(this);    
    n.s1.accept(this);   
    n.s2.accept(this);
    ident2();
  }

  // Exp e;
  // Statement s;
  public void visit(While n) 
  {
	ident1();
    System.out.print("While");
    n.e.accept(this);    
    n.s.accept(this);
    ident2();
  }

  // Exp e;
  public void visit(Print n) 
  {
	ident1();
    System.out.print("Print");
    n.e.accept(this);   
    ident2();
  }
  
  // Identifier i;
  // Exp e;
  public void visit(Assign n) 
  {
	ident1();
	System.out.print("Assign");
    n.i.accept(this);   
    n.e.accept(this);
    ident2();
    
  }

  // Identifier i;
  // Exp e1,e2;
  public void visit(ArrayAssign n)
  {
	ident1();
	System.out.print("ArrayAssign");
    n.i.accept(this);   
    n.e1.accept(this);    
    n.e2.accept(this);
    ident2();
    
  }

  // Exp e1,e2;
  public void visit(And n)
  {
	ident1();
	System.out.print("And");
    n.e1.accept(this);    
    n.e2.accept(this);    
    ident2();
  }

  // Exp e1,e2;
  public void visit(LessThan n) 
  {
	ident1();
    System.out.print("LessThan");
    n.e1.accept(this);
    n.e2.accept(this);
    ident2();
  }

  // Exp e1,e2;
  public void visit(Plus n) 
  {
	ident1();
    System.out.print("Plus");
    n.e1.accept(this);
    n.e2.accept(this);
    ident2();
  }

  // Exp e1,e2;
  public void visit(Minus n)
  {
	ident1();
    System.out.print("Minus");
    n.e1.accept(this);
    n.e2.accept(this);
    ident2();
  }

  // Exp e1,e2;
  public void visit(Times n)
  {
	ident1();
    System.out.print("Times");
    n.e1.accept(this);   
    n.e2.accept(this);  
    ident2();
  }

  // Exp e1,e2;
  public void visit(ArrayLookup n)
  {
	ident1();    
    System.out.print("ArrayLookup");
    n.e1.accept(this);
    n.e2.accept(this);   
    ident2();
  }

  // Exp e;
  public void visit(ArrayLength n)
  {
	ident1();
	System.out.print("ArrayLength");
    n.e.accept(this);    
    ident2();
  }

  // Exp e;
  // Identifier i;
  // ExpList el;
  public void visit(Call n) 
  {
	ident1();
	System.out.print("CALL");
    n.e.accept(this);    
    n.i.accept(this);
    
    for ( int i = 0; i < n.el.size(); i++ ) 
    {
        n.el.elementAt(i).accept(this);
        //if ( i+1 < n.el.size() ) { System.out.print(", "); }
    }
    ident2();
  }

  // int i;
  public void visit(IntegerLiteral n)
  {
	ident1();
    System.out.print("IntegerLiteral(" + n.i + ")");
    ident2();
  }

  public void visit(True n)
  {
	ident1();
	System.out.print("True");
	ident2();
  }

  public void visit(False n)
  {
	ident1();
    System.out.print("False");
    ident2();
  }

  // String s;
  public void visit(IdentifierExp n) 
  {
	ident1();
    System.out.print("IdentifierExp(" + n.s +")");
    ident2();
  }

  public void visit(This n)
  {
	ident1();
    System.out.print("This");
    ident2();
  }

  // Exp e;
  public void visit(NewArray n)
  {
	ident1();
    System.out.print("NewArray");
    n.e.accept(this);    
    ident2();
  }

  // Identifier i;
  public void visit(NewObject n)
  {
	ident1();
    System.out.print("NewObject");
    n.i.accept(this);    
    ident2();
  }

  // Exp e;
  public void visit(Not n) 
  {
	ident1();
    System.out.print("Not");
    n.e.accept(this);
    ident2();
  }

  // String s;
  public void visit(Identifier n)
  {
	ident1();
    System.out.print("Identifier(" + n.s + ")");
    ident2();
  }
}
