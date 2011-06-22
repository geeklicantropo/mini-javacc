package translate;

public class Exp
{
   private  Tree.exp.Exp exp;    
   
   public Exp(Tree.exp.Exp e)
   {
     exp=e;
   }
    
   public Tree.exp.Exp unEx()
   {
     return exp;
   }
}
