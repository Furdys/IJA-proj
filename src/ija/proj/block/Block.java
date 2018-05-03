package ija.proj.block;

import java.io.Serializable;

public abstract class Block implements Serializable
{
    // --- Attributes ---
    private Port[] inputPorts; 
    private Port[] outputPorts;
    private boolean executed = false;

    // --- Cunstructors ---
    // Default one
    Block(Port[] in, Port[] out)
    {
        this.inputPorts = in;
        this.outputPorts = out;
        
        // -- Set owner to ports --
        for(Port port : this.inputPorts)
        {
            port.setOwnerBlock(this);
        }
        for(Port port : this.outputPorts)
        {
            port.setOwnerBlock(this);
        }
    }

    // Other possible combinations
    Block(Port in, Port out)
    {
        this(new Port[]{in}, new Port[]{out});
    }

    Block(Port[] in, Port out)
    {
        this(in, new Port[]{out});
    }

    Block(Port in, Port[] out)
    {
        this(new Port[]{in}, out);
    }        

    // --- Methods ---
    /**
     * @brief Does general things when executed and calls block-specific action using executeSpecific()
     * @return true when block was executed, false when block is dependet on not yet executed block
     */
    public boolean execute()
    {
        // --- Check for loops ---  // I think we don't need this anymore, it's in findDependency
        if(this.executed == true)
        {
            System.err.println("Block.execute(): Block was already executed (loop detected)");
            //System.exit(2);        
        }
        
        // --- Check for dependency/missing value ---
        for(Port port : this.getInputPorts())    // For every input port
        {
            for(double value : port.content.values()) // For every value in every input port
            {
                if(Double.isNaN(value)) // If value is not yet calculated (connected block wasn't executed)
                {
                    return false;
                }
            }
        }
        
        this.executeSpecific();

        this.executed = true;
        return true;
    }

     /**
     * @brief Virtual method to be replaced by block implemetation. it contains specific action when block is executed
     */
    void executeSpecific()
    {
        System.err.println("Block.executeSpecific(): Called non-overriden function");
        //System.exit(1);
    }       

    // --- Setters and getters ---
    public Port getInputPort(int index) 
    {
        return this.inputPorts[index];
    }  
    
    public Port getOutputPort(int index) 
    {
        return this.outputPorts[index];
    }   
    
    public Port[] getInputPorts() 
    {
        return this.inputPorts;
    }

    public Port[] getOutputPorts() 
    {
            return this.outputPorts;
    }
    
    public boolean wasExecuted() 
    {
        return this.executed;
    }

    /*  // Do we need those?
    public void setInputPorts(Port[] in) 
    {
            this.inputPorts = in;
    }


    public void setOutputPorts(Port[] out) 
    {
            this.outputPorts = out;
    }	
    */
}
