package Canon;

public class BasicBlocks {
  public StmListList blocks;
  public Temp.Label done;

  private StmListList lastBlock;
  private Tree.stm.StmList lastStm;

  private void addStm(Tree.stm.Stm s) {
	lastStm = lastStm.tail = new Tree.stm.StmList(s,null);
  }

  private void doStms(Tree.stm.StmList l) {
      if (l==null) 
	doStms(new Tree.stm.StmList(new Tree.stm.JUMP(done), null));
      else if (l.head instanceof Tree.stm.JUMP 
	      || l.head instanceof Tree.stm.CJUMP) {
	addStm(l.head);
	mkBlocks(l.tail);
      } 
      else if (l.head instanceof Tree.stm.LABEL)
           doStms(new Tree.stm.StmList(new Tree.stm.JUMP(((Tree.stm.LABEL)l.head).label), 
	  			   l));
      else {
	addStm(l.head);
	doStms(l.tail);
      }
  }

  void mkBlocks(Tree.stm.StmList l) {
     if (l==null) return;
     else if (l.head instanceof Tree.stm.LABEL) {
	lastStm = new Tree.stm.StmList(l.head,null);
        if (lastBlock==null)
  	   lastBlock= blocks= new StmListList(lastStm,null);
        else
  	   lastBlock = lastBlock.tail = new StmListList(lastStm,null);
	doStms(l.tail);
     }
     else mkBlocks(new Tree.stm.StmList(new Tree.stm.LABEL(new Temp.Label()), l));
  }
   

  public BasicBlocks(Tree.stm.StmList stms) {
    done = new Temp.Label();
    mkBlocks(stms);
  }
}
