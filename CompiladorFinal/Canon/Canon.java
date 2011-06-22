package Canon;

class MoveCall extends Tree.stm.Stm {
  Tree.exp.TEMP dst;
  Tree.exp.CALL src;
  MoveCall(Tree.exp.TEMP d, Tree.exp.CALL s) {dst=d; src=s;}
  public Tree.ExpList kids() {return src.kids();}
  public Tree.stm.Stm build(Tree.ExpList kids) {
	return new Tree.stm.MOVE(dst, src.build(kids));
  }
}   
  
class ExpCall extends Tree.stm.Stm {
  Tree.exp.CALL call;
  ExpCall(Tree.exp.CALL c) {call=c;}
  public Tree.ExpList kids() {return call.kids();}
  public Tree.stm.Stm build(Tree.ExpList kids) {
	return new Tree.stm.EXP(call.build(kids));
  }
}   
  
class StmExpList {
  Tree.stm.Stm stm;
  Tree.ExpList exps;
  StmExpList(Tree.stm.Stm s, Tree.ExpList e) {stm=s; exps=e;}
}

public class Canon {
  
 static boolean isNop(Tree.stm.Stm a) {
   return a instanceof Tree.stm.EXP
          && ((Tree.stm.EXP)a).exp instanceof Tree.exp.CONST;
 }

 static Tree.stm.Stm seq(Tree.stm.Stm a, Tree.stm.Stm b) {
    if (isNop(a)) return b;
    else if (isNop(b)) return a;
    else return new Tree.stm.SEQ(a,b);
 }

 static boolean commute(Tree.stm.Stm a, Tree.exp.Exp b) {
    return isNop(a)
        || b instanceof Tree.exp.NAME
        || b instanceof Tree.exp.CONST;
 }

 static Tree.stm.Stm do_stm(Tree.stm.SEQ s) { 
	return seq(do_stm(s.left), do_stm(s.right));
 }

 static Tree.stm.Stm do_stm(Tree.stm.MOVE s) { 
	if (s.dst instanceof Tree.exp.TEMP 
	     && s.src instanceof Tree.exp.CALL) 
		return reorder_stm(new MoveCall((Tree.exp.TEMP)s.dst,
						(Tree.exp.CALL)s.src));
	else if (s.dst instanceof Tree.exp.ESEQ)
	    return do_stm(new Tree.stm.SEQ(((Tree.exp.ESEQ)s.dst).stm,
					new Tree.stm.MOVE(((Tree.exp.ESEQ)s.dst).exp,
						  s.src)));
	else return reorder_stm(s);
 }

 static Tree.stm.Stm do_stm(Tree.stm.EXP s) { 
	if (s.exp instanceof Tree.exp.CALL)
	       return reorder_stm(new ExpCall((Tree.exp.CALL)s.exp));
	else return reorder_stm(s);
 }

 static Tree.stm.Stm do_stm(Tree.stm.Stm s) {
     if (s instanceof Tree.stm.SEQ) return do_stm((Tree.stm.SEQ)s);
     else if (s instanceof Tree.stm.MOVE) return do_stm((Tree.stm.MOVE)s);
     else if (s instanceof Tree.stm.EXP) return do_stm((Tree.stm.EXP)s);
     else return reorder_stm(s);
 }

 static Tree.stm.Stm reorder_stm(Tree.stm.Stm s) {
     StmExpList x = reorder(s.kids());
     return seq(x.stm, s.build(x.exps));
 }

 static Tree.exp.ESEQ do_exp(Tree.exp.ESEQ e) {
      Tree.stm.Stm stms = do_stm(e.stm);
      Tree.exp.ESEQ b = do_exp(e.exp);
      return new Tree.exp.ESEQ(seq(stms,b.stm), b.exp);
  }

 static Tree.exp.ESEQ do_exp (Tree.exp.Exp e) {
       if (e instanceof Tree.exp.ESEQ) return do_exp((Tree.exp.ESEQ)e);
       else return reorder_exp(e);
 }
         
 static Tree.exp.ESEQ reorder_exp (Tree.exp.Exp e) {
     StmExpList x = reorder(e.kids());
     return new Tree.exp.ESEQ(x.stm, e.build(x.exps));
 }

 static StmExpList nopNull = new StmExpList(new Tree.stm.EXP(new Tree.exp.CONST(0)),null);

 static StmExpList reorder(Tree.ExpList exps) {
     if (exps==null) return nopNull;
     else {
       Tree.exp.Exp a = exps.head;
       if (a instanceof Tree.exp.CALL) {
         Temp.Temp t = new Temp.Temp();
	 Tree.exp.Exp e = new Tree.exp.ESEQ(new Tree.stm.MOVE(new Tree.exp.TEMP(t), a),
				    new Tree.exp.TEMP(t));
         return reorder(new Tree.ExpList(e, exps.tail));
       } else {
	 Tree.exp.ESEQ aa = do_exp(a);
	 StmExpList bb = reorder(exps.tail);
	 if (commute(bb.stm, aa.exp))
	      return new StmExpList(seq(aa.stm,bb.stm), 
				    new Tree.ExpList(aa.exp,bb.exps));
	 else {
	   Temp.Temp t = new Temp.Temp();
	   return new StmExpList(
			  seq(aa.stm, 
			    seq(new Tree.stm.MOVE(new Tree.exp.TEMP(t),aa.exp),
				 bb.stm)),
			  new Tree.ExpList(new Tree.exp.TEMP(t), bb.exps));
	      }
       }
     }
 }
        
 static Tree.stm.StmList linear(Tree.stm.SEQ s, Tree.stm.StmList l) {
      return linear(s.left,linear(s.right,l));
 }
 static Tree.stm.StmList linear(Tree.stm.Stm s, Tree.stm.StmList l) {
    if (s instanceof Tree.stm.SEQ) return linear((Tree.stm.SEQ)s, l);
    else return new Tree.stm.StmList(s,l);
 }

 static public Tree.stm.StmList linearize(Tree.stm.Stm s) {
    return linear(do_stm(s), null);
 }
}
