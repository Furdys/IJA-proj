package ija.proj.block;

public abstract class BlockArithmetic extends Block
{
    public BlockArithmetic()
    {        
        super(new Port[]{new Port("float", Port.inputType), new Port("float", Port.inputType)}, new Port("float", Port.outputType));
    }    
}
