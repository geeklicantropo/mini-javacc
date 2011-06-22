package Tree.stm;

import Tree.ExpList;

abstract public class Stm {
	abstract public ExpList kids();
	abstract public Stm build(ExpList kids);
}

