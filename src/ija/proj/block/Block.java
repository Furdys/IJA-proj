package ija.proj.block;

public abstract class Block 
{
    // --- Attributes ---
    private Port[] inputPorts; 
    private Port[] outputPorts;
    private boolean executed = false;

    // --- Cunstructors ---
    Block(Port[] in, Port[] out)
    {
        this.inputPorts = in;
        this.outputPorts = out;
    }

    Block(Port in, Port out)
    {
        this.inputPorts = new Port[]{in};
        this.outputPorts = new Port[]{out};
    }

    Block(Port[] in, Port out)
    {
        this.inputPorts = in;
        this.outputPorts = new Port[]{out};
    }

    Block(Port in, Port[] out)
    {
        this.inputPorts = new Port[]{in};
        this.outputPorts = out;
    }        

    // --- Methods ---
    public void execute()
    {
        // --- Check for loops ---
        if(this.executed == true)
        {
            System.err.println("Block.execute(): Block was already executed (loop detected)");
            System.exit(2);        
        }
        
        this.executeSpecific();

        this.executed = true;
    }

    void executeSpecific()
    {
        System.err.println("Block.executeSpecific(): Called non-overriden function");
        System.exit(1);
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
    
    /*  // Do we need those?
    public Port[] getInputPorts() 
    {
            return this.inputPorts;
    }

    public void setInputPorts(Port[] in) 
    {
            this.inputPorts = in;
    }

    public Port[] getOutputPorts() 
    {
            return this.outputPorts;
    }

    public void setOutputPorts(Port[] out) 
    {
            this.outputPorts = out;
    }	
    */
}
