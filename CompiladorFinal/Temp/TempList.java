package Temp;

public class TempList
{
   public Temp head;
   public TempList tail;
   public TempList(Temp h, TempList t) {head=h; tail=t;}
   
   public Temp get(int n) throws Exception
   {
       int i=0;
       Temp h = head;
       TempList t = tail;
       while(i<=n)
       {
         if(h==null)throw new IndexOutOfBoundsException();
         if(i==n)return h;
         else
         {
           h = t.head;
           t = t.tail;
         }
         i++;
       }
       throw new IndexOutOfBoundsException();
     }

}


