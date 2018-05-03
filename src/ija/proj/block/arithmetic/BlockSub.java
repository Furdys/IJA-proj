package ija.proj.block.arithmetic;

import ija.proj.block.*;

public class BlockSub extends BlockArithmetic 
{
    void executeSpecific() 
    {
        this.getOutputPort(0).setValue("float", this.getInputPort(0).getValue("float") - this.getInputPort(1).getValue("float"));
    }
}