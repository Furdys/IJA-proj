package ija.proj.block;

public abstract class BlockArithmetic extends Block
{
    public BlockArithmetic()
    {        
        super(new Port[]{new Port("float"), new Port("float")}, new Port("float"));
    }    
}
