package blocks;

public abstract class Block {

	protected BlockType[] IN; 
	protected BlockType[] OUT;
	
	Block(BlockType[] in){
		this.IN = in;
	}
	
	
	/**** main for basic testing ****/
	/*public static void main(String args[]){
		
		
	}*/


	/************ getters and setters ************/

	public BlockType[] getIN() {
		return IN;
	}

	public void setIN(BlockType[] in) {
		this.IN = in;
	}

	public BlockType[] getOUT() {
		return OUT;
	}

	public void setOUT(BlockType[] out) {
		this.OUT = out;
	}
	
	
	
}
