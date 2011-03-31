import java.io.FileNotFoundException;

import syntaxtree.*;
import visitor.*;

public class Main {
   public static void main(String [] args) throws FileNotFoundException {
      try {
         MiniJavaParser parser;
         parser = new MiniJavaParser(new java.io.FileInputStream(args[0]));
		 Program root = parser.program();
         root.accept(new PrettyPrintVisitor());
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
}
