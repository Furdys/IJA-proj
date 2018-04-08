package blocks;



public abstract class Block 
{

	protected Port[] IN; 

	protected Port[] OUT;
	private boolean executed = false;
	
	Block(Port[] in)
	{
		this.IN = in;
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
	
	
}
