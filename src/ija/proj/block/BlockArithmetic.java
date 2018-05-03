package ija.proj.block;

import ija.proj.block.arithmetic.*;

public abstract class BlockArithmetic extends Block
{
    public BlockArithmetic()
    {        
        super(new Port[]{new Port("float", Port.inputType), new Port("float", Port.inputType)}, new Port("float", Port.outputType));
    }    
}
