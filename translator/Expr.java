package IR.Tradutor;

import IR.ArvoreIR.Comando;
import IR.Temporario.Rotulo;

public abstract class Expr {
	abstract Expr unEx();
	abstract Comando unNx();
	abstract Comando unCx(Rotulo t, Rotulo f);
}
