package ija.proj.block;

public class BlockCxSub extends BlockComplex
{
    void executeSpecific() 
    {
        for(String name : new String[]{"real","imaginary"})
        {
            this.getOutputPort(0).setValue(name,
                this.getInputPort(0).getValue(name) - this.getInputPort(1).getValue(name));
        }
    }
}

