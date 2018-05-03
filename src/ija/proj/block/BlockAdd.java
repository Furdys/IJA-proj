package ija.proj.block;

public class BlockAdd extends BlockArithmetic 
{
    @Override
    void executeSpecific() 
    {
        this.getOutputPort(0).setValue("float", this.getInputPort(0).getValue("float") + this.getInputPort(1).getValue("float"));
    }
}
