package IR.Tradutor;

import IR.ArvoreIR.*;
import IR.Temporario.*;

public class Ex extends Exp{
	Exp exp;
	
	Ex(Exp e){
		exp = e;
	}
	
	Exp unEx(){
		return exp;
	}
	
	Comando unNx(){}
	
	Comando unCx(Rotulo t, Rotulo f){}
}
