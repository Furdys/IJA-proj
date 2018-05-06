package ija.proj.block;

public class BlockXor extends BlockLogic
{
    @Override
    void executeSpecific() 
    {
        boolean resultB = BlockLogic.double2bool(getInputPort(0).getValue("bool")) ^ double2bool(getInputPort(1).getValue("bool"));
        double resultD = BlockLogic.bool2double(resultB);        
        
        this.getOutputPort(0).setValue("bool", resultD);
    }
}
