package Canon;

public class TraceSchedule {

  public Tree.stm.StmList stms;
  BasicBlocks theBlocks;
  java.util.Dictionary table = new java.util.Hashtable();

  Tree.stm.StmList getLast(Tree.stm.StmList block) {
     Tree.stm.StmList l=block;
     while (l.tail.tail!=null)  l=l.tail;
     return l;
  }

  void trace(Tree.stm.StmList l) {
   for(;;) {
     Tree.stm.LABEL lab = (Tree.stm.LABEL)l.head;
     table.remove(lab.label);
     Tree.stm.StmList last = getLast(l);
     Tree.stm.Stm s = last.tail.head;
     if (s instanceof Tree.stm.JUMP) {
	Tree.stm.JUMP j = (Tree.stm.JUMP)s;
        Tree.stm.StmList target = (Tree.stm.StmList)table.get(j.targets.head);
	if (j.targets.tail==null && target!=null) {
               last.tail=target;
	       l=target;
        }
	else {
	  last.tail.tail=getNext();
	  return;
        }
     }
     else if (s instanceof Tree.stm.CJUMP) {
	Tree.stm.CJUMP j = (Tree.stm.CJUMP)s;
        Tree.stm.StmList t = (Tree.stm.StmList)table.get(j.iftrue);
        Tree.stm.StmList f = (Tree.stm.StmList)table.get(j.iffalse);
        if (f!=null) {
	  last.tail.tail=f; 
	  l=f;
	}
        else if (t!=null) {
	  last.tail.head=new Tree.stm.CJUMP(Tree.stm.CJUMP.notRel(j.relop),
					j.left,j.right,
					j.iffalse,j.iftrue);
	  last.tail.tail=t;
	  l=t;
        }
        else {
	  Temp.Label ff = new Temp.Label();
	  last.tail.head=new Tree.stm.CJUMP(j.relop,j.left,j.right,
					j.iftrue,ff);
	  last.tail.tail=new Tree.stm.StmList(new Tree.stm.LABEL(ff),
		           new Tree.stm.StmList(new Tree.stm.JUMP(j.iffalse),
					    getNext()));
	  return;
        }
     }
     else throw new Error("Bad basic block in TraceSchedule");
    }
  }

  Tree.stm.StmList getNext() {
      if (theBlocks.blocks==null) 
	return new Tree.stm.StmList(new Tree.stm.LABEL(theBlocks.done), null);
      else {
	 Tree.stm.StmList s = theBlocks.blocks.head;
	 Tree.stm.LABEL lab = (Tree.stm.LABEL)s.head;
	 if (table.get(lab.label) != null) {
          trace(s);
	  return s;
         }
         else {
	   theBlocks.blocks = theBlocks.blocks.tail;
           return getNext();
         }
      }
  }

  public TraceSchedule(BasicBlocks b) {
    theBlocks=b;
    for(StmListList l = b.blocks; l!=null; l=l.tail)
       table.put(((Tree.stm.LABEL)l.head.head).label, l.head);
    stms=getNext();
    table=null;
  }        
}


