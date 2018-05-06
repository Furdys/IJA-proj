package ija.proj.block;

public abstract class BlockLogic extends Block
{
    public BlockLogic()
    {        
        super(new Port[]{new Port("bool", Port.inputType), new Port("bool", Port.inputType)}, new Port("bool", Port.outputType));
    }  
    
    // --- Constants ---
    public static final Double True = 1.0;
    public static final Double False = 0.0;
    
    // --- Class methods ---
    public static boolean double2bool(double value)
    {
        if(value > (BlockLogic.True - BlockLogic.False)/2.0)
            return true;
        else
            return false;
    }
    
    public static  double bool2double(boolean value)
    {
        if(value)
            return BlockLogic.True;
        else
            return BlockLogic.False;
    }
}
