package IR.Tradutor;

import IR.ArvoreIR.*;
import IR.Temporario.*;

abstract class Nx extends Exp{
	Comando comando;
	
	Nx(Comando c){
		comando = c;
	}
	
	unEx(){
		return null;
	};
	
	unNx(){
		return comando;
	}
	
	unCx(Rotulo t, Rotulo f){
		return comando;
	}
}
