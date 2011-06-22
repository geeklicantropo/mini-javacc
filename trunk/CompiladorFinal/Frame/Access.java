package Frame;

import Tree.exp.Exp;

public abstract class Access
{
    public Access()
    {
    }

    public abstract Exp exp(Exp framePtr);
}
