package blocks;



public abstract class Block 
{

	protected Port[] IN; 

	protected Port[] OUT;
	private boolean executed = false;
	
	Block(Port[] in, Port[] out)
	{
		this.IN = in;
                this.OUT = out;
	}
        

	/************ getters and setters ************/

	/**
	 * @return
	 */
	public Port[] getInputPorts() 
	{
		return IN;
	}

	public void setInputPorts(Port[] in) 
	{
		this.IN = in;
	}

	public Port[] getOutputPorts() 
	{
		return OUT;
	}

	public void setOutputPorts(Port[] out) 
	{
		this.OUT = out;
	}
	
	public boolean wasExecuted()
	{
		return this.executed;
	}
	
	public void setExecuted()
	{
		this.executed = true;
	}
        
	public Port[] execute()
	{
		System.err.println("Block.execute: Called non-overriden function");
		return null;
	}

    private Port[] clone(Port[] in) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
	
}
