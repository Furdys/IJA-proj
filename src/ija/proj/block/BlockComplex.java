package ija.proj.block;

public abstract class BlockComplex extends Block
{
    public BlockComplex()
    {        
        super(new Port[]{new Port(new String[]{"real", "imaginary"}, Port.inputType), new Port(new String[]{"real", "imaginary"}, Port.inputType)},
                new Port(new String[]{"real", "imaginary"}, Port.outputType));
    }    
}
