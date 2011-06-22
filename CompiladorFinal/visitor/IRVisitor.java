
package visitor;

import java.util.ArrayList;


import symbol.*;
import symbol.Class;
import syntaxtree.*;
import translate.Frag;
import Frame.Frame;
import Temp.Label;
import Tree.ExpList;
import Tree.exp.*;
import Tree.exp.Exp;
import Tree.stm.*;


public class IRVisitor// implements Visitor 
{
   public Tree.exp.Exp IRTree;
   public ArrayList<Frag> frags;
   public SymbolTable st;
   Frame currentFrame;
   Symbol currentClass;
   Symbol currentMethod; 


   
   public IRVisitor(Frame currentFrame, SymbolTable st)
   {	
	 this.frags = new ArrayList<Frag>();
	 this.currentFrame = currentFrame;
	 this.st = st;
   }
   
   private Exp getVar(Symbol var)
   {
	  Class cl  = (Class)st.get(currentClass);
	  Method mt = (Method)cl.getMethod(currentMethod);
	  Var v = (Var)mt.getVar(var);
	      
	  if (v==null) v = (Var)cl.getVar(var);
		  
	  return v.access.exp(new TEMP(currentFrame.FP()));

   }

   //MainClass m. ClassList cl
   public void visit(Program n)
   {
	  n.m.accept(this);
      for(int i =0; i< n.cl.size(); i++)
      {
    	  if(n.cl.elementAt(i) instanceof ClassDeclSimple)
    	     currentClass = Symbol.symbol(((ClassDeclSimple)n.cl.elementAt(i)).i.toString());
          else
        	 currentClass = Symbol.symbol(((ClassDeclExtends)n.cl.elementAt(i)).i.toString());
    	  
    	  n.cl.elementAt(i).accept(this);
      }

   }
   
   //Identifier i1, i2.  Statement s
   public void visit(MainClass n) 
   {
	   currentClass = Symbol.symbol("main");
	   Stm body = new EXP(n.s.accept(this));
	   frags.add(new Frag(currentFrame, body));
   }
   
   //Identifier i; VarDeclList vl;  MethodDeclList ml;
   public void visit(ClassDeclSimple n)
   {
	   for(int i=0; i<n.ml.size();i++)
	   {
		   currentMethod = Symbol.symbol(n.ml.elementAt(i).i.toString());
		   n.ml.elementAt(i).accept(this);  
	   }
 
   }
   public void visit(ClassDeclExtends n)
   {
	   for(int i=0; i<n.ml.size();i++)
	   {
		   currentMethod = Symbol.symbol(n.ml.elementAt(i).i.toString());
		   n.ml.elementAt(i).accept(this);  
	   }
   }
   
   public Exp visit(VarDecl n)
   {
	   return null;
   }
   
   //Type t Identifier i FormalList fl VarDeclList vl StatementList sl Exp e
   public void visit(MethodDecl n)
   {
	   Stm body = new EXP(new CONST(0));

	   for(int i=0; i<n.sl.size(); i++)
	   {		   
           body = new SEQ(body,new EXP(n.sl.elementAt(i).accept(this)));
       }  
	   frags.add(new Frag(currentFrame, body));
   }
   
   public Exp visit(Formal n)
   {	   
	   return getVar(Symbol.symbol(n.i.toString()));
   }
   
   
   public void visit(IntArrayType n)
   {
	   
   }
   
   public void visit(BooleanType n)
   {
	   
   }
   
   public void visit(IntegerType n)
   {
	   
   }
   
   public void visit(IdentifierType n)
   {
	   
   }
   
   public Exp visit(Block n)
   {
	   Exp stm = new CONST(0);
       for(int i=0; i<n.sl.size(); i++)
       stm = new ESEQ(new SEQ(new EXP(stm), new EXP(n.sl.elementAt(i).accept(this))), new CONST(0));
       return stm;    
   }
   
   
   public Exp visit(If n)
   {
	   Label t = new Label();//then
       Label f = new Label();//else
              
       Exp cond = n.e.accept(this);
       Exp th = n.s1.accept(this);
       Exp el = n.s2.accept(this);

       Exp r = 
         new ESEQ(
          new SEQ(
           new CJUMP(CJUMP.GT,cond,new CONST(0),t,f),
            new SEQ(
             new SEQ(new LABEL(t),new EXP(th)),
              new SEQ(new LABEL(f),new EXP(el))
              )),new CONST(0));
       
       return r;

   }
   
   public Exp visit(While n)
   {
	   Label test = new Label();
       Label done = new Label();
       Label body = new Label();
       
       Exp cond = n.e.accept(this);
       Exp b = n.s.accept(this);
       
       Exp  r = new ESEQ(
         new SEQ(new LABEL(test),
          new SEQ(
           new CJUMP(CJUMP.GT,cond,new CONST(0),body,done),
            new SEQ(new LABEL(body), new SEQ(new EXP(b),new JUMP(test)))
            )
            ), new CONST(0)
       );
               
      return r;

   }
   
   
   public Exp visit(Print n)
   {
	 Tree.exp.Exp e = n.e.accept(this);	 
	 Tree.ExpList el =  new ExpList(e, null);	 
	 return new CALL(new NAME(new Label("_printExp")), el);	 
   }  
   
   
   public Exp visit(Assign n)
   {
	   Exp value = n.e.accept(this);
       Exp addr = getVar(Symbol.symbol(n.i.s));
               
       Exp r = new ESEQ(
                new MOVE(addr,value),
                 new CONST(0));
       return r;
   }
   
   public Exp visit(ArrayAssign n)
   {
	 Exp addr  = n.i.accept(this);
     Exp index = n.e1.accept(this);
     Exp value = n.e2.accept(this);
       
     Exp r = new ESEQ( 
              new MOVE( new MEM(
               new BINOP(BINOP.PLUS,addr,
                new BINOP(BINOP.MUL,index,
                	new CONST(currentFrame.wordSize())))), value), new CONST(0));
       
     return r;
 
   }
   
   
   public Exp visit(And n)
   {       
       return new BINOP(BINOP.MUL,n.e1.accept(this),n.e2.accept(this));
      
   }
   
   public Exp visit(LessThan n)
   {       
     return  new BINOP(BINOP.MINUS, n.e2.accept(this), n.e1.accept(this));
   }
   
   
   public Exp visit(Plus n)
   {
	   Exp op1 = n.e1.accept(this);
       Exp op2 = n.e2.accept(this);
       
       Exp plus = new BINOP(BINOP.PLUS,op1,op2);
       
       return plus;
   
   }
   
   
   public Exp visit(Minus n)
   {
	   Exp op1 = n.e1.accept(this);
       Exp op2 = n.e2.accept(this);
       
       Exp min = new BINOP(BINOP.MINUS,op1,op2);
       
       return min;

   }
   
   
   //Exp e1, Exp e2
   public Exp visit(Times n)
   {
	  return new BINOP(BINOP.MUL, n.e1.accept(this), n.e2.accept(this)); 
   }
   
   public Exp visit(ArrayLookup n)
   {
	   Exp addr = n.e1.accept(this);
       Exp index = n.e2.accept(this);
       
       Exp arraL= new BINOP(BINOP.PLUS,addr,
                       new BINOP(BINOP.MUL,index,new CONST(currentFrame.wordSize())));
       return arraL;

   }
   
   
   public Exp visit(ArrayLength n)
   {
	   Exp array = getVar(Symbol.symbol(((IdentifierExp)n.e).s));
       return array;

   }
   
   
   public Exp visit(Call n)
   {
	  ExpList params = null;
      Exp var;
      for (int i = n.el.size()-1; i >= 0; i--) 
      {
        var = n.el.elementAt(i).accept(this);
        params = new ExpList(var,params);
      }
      var = n.e.accept(this);      
      Symbol s = null;      
      
      if(n.e instanceof IdentifierExp)  s = Symbol.symbol(((IdentifierExp)n.e).s);
      else if(n.e instanceof NewObject) s = Symbol.symbol(((NewObject)n.e).i.s);
      else s = currentClass;
      
      return new CALL(new NAME(new Label(s.toString()+"$"+n.i.toString())),params);	   
        
   }
   
   
   public Exp visit(IntegerLiteral n)
   {	 
	 return new CONST(n.i);   
   }
   
   
   public Exp visit(True n)
   {
	   return new CONST(1);   
   }
   
   public Exp visit(False n)
   {
	  return new CONST(0);	   
   }
   
   public Exp visit(IdentifierExp n)
   {
	  return getVar(Symbol.symbol(n.s));  
   }
   
   public Exp visit(This n)
   {
	   return new MEM(new TEMP(currentFrame.FP()));
   }
   
   
   public Exp visit(NewArray n)
   {
	  Exp index = n.e.accept(this);
	  Exp size = new BINOP(BINOP.MUL, index, new CONST(currentFrame.wordSize()));
	  Tree.ExpList el =  new ExpList(size, null);	 
	  return new CALL(new NAME(new Label("_malloc")), el); 
   }
   
   
   public Exp visit(NewObject n)
   {
	 Exp e = new CONST((2)*currentFrame.wordSize());
	 Tree.ExpList el =  new ExpList(e, null);	 
	 return new CALL(new NAME(new Label("_malloc")), el);	   
		   
   }
   
   public Exp visit(Not n)
   {
	  Exp op = n.e.accept(this);       
      Exp not = new BINOP(BINOP.MINUS,new CONST(0),op);
      return not;
   }
   public Exp visit(Identifier n)
   {	  
	  return getVar(Symbol.symbol(n.s));
   }
}

