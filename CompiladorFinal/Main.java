import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.FileOutputStream;

import java.util.ArrayList;


import javacc.*;
import jouette.Codegen;
import jouette.FrameJouette;
import syntaxtree.*;
import visitor.*;
import symbol.*;
import symbol.Class;
import translate.Frag;

import Assem.Instr;
import Canon.BasicBlocks;
import Canon.Canon;
import Canon.TraceSchedule;
import Frame.Frame;
import Tree.Print;
import Tree.stm.StmList;

public class Main
{
  public static void main(String [] args) throws FileNotFoundException
  {
    try
    {
      //cap 2,3,4
    	
      
    	
      MiniJavaParser parser; //Declara o parser
      parser=null;
      
      if(args.length == 0)
      {
    	System.out.print("Digite o caminho para o arquivo com o codigo fonte: ");
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        try 
        {
			String filename = buf.readLine();
			parser = new MiniJavaParser(new java.io.FileInputStream(filename));    
		}
        catch (IOException e) 
        {
		    e.printStackTrace();
		}
      }
      else
      {
      parser = new MiniJavaParser(new java.io.FileInputStream(args[0])); //Recebe o código-fonte a ser analisado com argumento da função Main
      }
      
      
      System.out.println("\n\n");
  	  System.out.println("\n ANALISE LEXICA E SINTATICA\n----------------------------\n");	
      
	  Program root = parser.program(); //Faz análise léxica e sintática e retorna a árvore abstrata (cap.2,3 e 4)
      
	  System.out.println("\n Nenhum erro encontrado");
	  
	  
	  System.out.println("\n\n");
	  System.out.println("\n ARVORE SINTATICA ABSTRATA\n---------------------------\n");	  
	  root.accept(new PrintVisitor());
	  
	  System.out.println("\n\n");
	  System.out.println("\n VERIFICACAO DE TIPOS\n------------------------\n");
	  
	  //cap 5
	  boolean err=false;
	  ArrayList<String> error = new ArrayList<String>();
	  SymbolTableVisitor stVisitor = new SymbolTableVisitor();
	  Table symbolTable;
	  symbolTable = stVisitor.buildTable(root); //Constroi tabela de símbolos	  
      TypeCheckerVisitor tcvisitor = new TypeCheckerVisitor(symbolTable, err, error);
      tcvisitor.visit(root);         
      if(!error.isEmpty())            
        for(int i =0; i < error.size(); i++)  
          System.out.println(error.get(i));
      else
    	System.out.println("\n Nenhum erro encontrado");
      
      SymbolTableVisitor2 stVisitor2 = new SymbolTableVisitor2();
      SymbolTable st = stVisitor2.visit(root);
      
      
      //for (Class cl : st.cTable) 
     // {
      
     // }
      
      
      
      //cap 6,7
      
      
      System.out.println("\n\n");
	  System.out.println("\n REPRESENTACAO INTERMEDIARIA\n----------------------------\n");
      
	  Frame frame = new FrameJouette();
      visitor.IRVisitor iRVisitor = new IRVisitor(frame,st);
      iRVisitor.visit(root);
      
      //FileOutputStream o = new FileOutputStream("saida/IRtree.txt");
      //java.io.PrintStream out = new java.io.PrintStream(o);
      
      Print pr = new Print();
      //pr.prExp(iRVisitor.IRTree, 0);
      
      for (Frag frag : iRVisitor.frags) 
      {
    	 if(frag!=null)
    	 {    	   
    	   System.out.println("#\n");
    	   if(frag.body!=null)
    	   pr.prStm(frag.body);    	   
    	 }
      }
      
      //cap 8
      
      ArrayList<StmList> stms = new ArrayList<StmList>();
       for (Frag frag : iRVisitor.frags) 
       {
         stms.add(Canon.linearize(frag.body));
       }
       
       ArrayList<BasicBlocks> blocks = new ArrayList<BasicBlocks>();
       for (StmList stm : stms) 
       {
         blocks.add(new BasicBlocks(stm));
       }
       
       ArrayList<TraceSchedule> traces = new ArrayList<TraceSchedule>();
       for (BasicBlocks bb : blocks) 
       {
         traces.add(new TraceSchedule(bb));
       }
       
       //cap 9
       
       System.out.println("\n\n");
 	   System.out.println("\n EMISSAO DE CODIGO ASSEMBLY\n----------------------------\n");
       
       Codegen cd = new Codegen(frame);
       ArrayList<Instr> instr = new ArrayList<Instr>();
       //instr = codegen(StmList s)
       for (TraceSchedule ts : traces) 
       {
          try
          {
			 instr = cd.codegen(ts.stms);
			 for (Instr i : instr) 
		     {
				System.out.println(i.assem); 
		     }
		  } 
          catch (Exception e) 
          {
		     e.printStackTrace();
		  }
       }
 	   
    }
    catch (ParseException e) 
    {
      System.out.println(e.toString());
    }
  }
}
