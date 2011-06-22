package jouette;

import java.util.List;
import symbol.Symbol;
import Frame.*;
import Temp.Label;
import Temp.Temp;
import Tree.ExpList;
import Tree.exp.*;
import Tree.stm.*;

public class FrameJouette extends Frame 
{
	private static final int wordSize = 4;
	private static final Temp FP = new Temp();
	private static final Temp RA = new Temp();
	private static final Temp ZERO = new Temp();
	
	public Temp FP() 
	{
	  return FP;
	}

	public Temp R0() 
	{		
	  return ZERO;
	}

	@Override
	public Temp RV() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Access allocLocal(boolean escapes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exp externalCall(String s, Tree.ExpList args)
	{ 
	  return new CALL(new NAME(new Label(s)), args); 
    }


	@Override
	public String tempMap(Temp t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int wordSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
