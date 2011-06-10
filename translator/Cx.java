package IR.Tradutor;

//import IR.ArvoreIR.*;
import IR.Temporario.*;

public class Cx extends Exp{
	Exp e;
	Cx(Exp e){
		this.e = e;
	}
	
	Exp unEx(){
		Temporario r = new Temporario();
		Rotulo t = new Rotulo();
		Rotulo f = new Rotulo();
		
		return new ESEQ(
				new SEQ(new MOVE(new TEMP(r),
						         new CONST(1)),
						new SEQ(unCx(t,f),new SEQ(new LABEL(f),
												  new SEQ(new MOVE(new TEMP(r),new CONST(0)),
														  new LABEL(t))))),
				new TEMP(r);
	}
	
	Comando unCx(Rotulo t, Rotulo f){}
	
	Comando unNx(){
		return new EXP();
	}
}
