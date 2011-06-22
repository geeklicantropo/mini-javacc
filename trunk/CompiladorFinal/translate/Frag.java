//Cada Fragmento terá um frame associado e uma lista de comandos
//Stm podem ser as instruções SEQ, MOVE, etc..

package translate;

import Tree.stm.Stm;
import Frame.Frame;

public class Frag
{
   public Frame frame;
   public Stm body;

   public Frag(Frame currentFrame, Stm body) 
   {
      this.frame = currentFrame;
      this.body = body;
   }        
}

