package visitor;

import syntaxtree.*;
import symbol.*;
import symbol.Class;

public class SymbolTableVisitor2
{
	 private SymbolTable sTable;
     
     public SymbolTableVisitor2()
     {
        sTable = new SymbolTable();
     }
     
     SymbolTable getSymbolTable()
     {
       return sTable;
     }
     
     
     public SymbolTable visit(Program n)
     {
        Class cT;
        cT = n.m.accept(this,Symbol.symbol(n.m.i1.toString()));
        sTable.put(Symbol.symbol(n.m.i1.toString()), cT);                       
        for ( int i = 0; i < n.cl.size(); i++ ) 
        {
             cT = n.cl.elementAt(i).accept(this);
             if(n.cl.elementAt(i) instanceof ClassDeclSimple){
                     sTable.put(Symbol.symbol(((ClassDeclSimple)n.cl.elementAt(i)).i.toString()), cT);
             }
             if(n.cl.elementAt(i) instanceof ClassDeclExtends){
                     sTable.put(Symbol.symbol(((ClassDeclExtends)n.cl.elementAt(i)).i.toString()), cT);
             }               
         }
         return sTable;
     }

     public Class visit(ClassDeclSimple n)
     {
        Class cT = new Class(Symbol.symbol(n.i.toString()));
        for (int i = 0; i < n.vl.size(); i++) 
        {
           cT.vTable.put(Symbol.symbol(n.vl.elementAt(i).i.toString()), n.vl.elementAt(i).accept(this));
        }
             
        for (int i = 0; i < n.ml.size(); i++) 
        {
          //cT.methodCurrent=Symbol.symbol(n.ml.elementAt(i).i.s);
          cT.mTable.put(Symbol.symbol(n.ml.elementAt(i).i.toString()), n.ml.elementAt(i).accept(this));
        }
          //cT.methodCurrent=null;
          return cT;
     }

     
     public Class visit(ClassDeclExtends n) {
             Class cT = new Class(Symbol.symbol(n.i.toString()));
             cT.extendClass = Symbol.symbol(n.j.toString());
             for (int i = 0; i < n.vl.size(); i++)
             {
                     cT.vTable.put(Symbol.symbol(n.vl.elementAt(i).i.toString()), n.vl.elementAt(i).accept(this));
             }
             
             for (int i = 0; i < n.ml.size(); i++) {
                     //cT.methodCurrent=Symbol.symbol(n.ml.elementAt(i).i.s);
                     cT.mTable.put(Symbol.symbol(n.ml.elementAt(i).i.toString()), n.ml.elementAt(i).accept(this));
             }
             //cT.methodCurrent=null;
             return cT;
     }

     public Var visit(VarDecl n)
     {
       return new Var(n.t,Symbol.symbol(n.i.toString()));
     }

     
     public Method visit(MethodDecl n) 
     {
        Method mBt = new  Method(Symbol.symbol(n.i.toString()));
        for (int i = 0; i < n.fl.size(); i++) 
        {
          mBt.vTable.put(Symbol.symbol(n.fl.elementAt(i).i.toString()),
                     n.fl.elementAt(i).accept(this));
          //mBt.pList.add(i, Symbol.symbol(n.fl.elementAt(i).t.toString()));
        }
             
        for (int i = 0; i < n.vl.size(); i++) 
        {
          mBt.vTable.put(Symbol.symbol(n.vl.elementAt(i).i.toString()),n.vl.elementAt(i).accept(this));
        }
                                             
        return mBt;
     }

     
     public Var visit(Formal n) 
     {
       return new Var(n.t,Symbol.symbol(n.i.s));
     }

     
     public Symbol visit(IntArrayType n) 
     {
       return Symbol.symbol("int[]");
     }

     public Symbol visit(BooleanType n) 
     {
       return Symbol.symbol("boolean");
     }

     
     public Symbol visit(IntegerType n) 
     {
       return Symbol.symbol("int");
     }

     
     public Symbol visit(IdentifierType n) 
     {
       return Symbol.symbol(n.s);
     }
     
     
     public Symbol visit(Identifier n) 
     {
       return Symbol.symbol(n.s);
     }

}
