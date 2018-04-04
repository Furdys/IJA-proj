
public class Block {

	private String name;
	private BlockType[] IN; 
	private BlockType[] OUT;
	
	Block(String name, BlockType[] in){
		this.IN = in;
		this.name = name;
	}
	
	
	/**** main for basic testing ****/
	public static void main(String args[]){
		
		
	}

	
	
	/***** basic operations
	 * create more later 
	 * or let user create his own
	 */
	
	public BlockType[] add() {
		double[] tmp = {IN[0].getValues()[0] + IN[1].getValues()[0]};
		BlockType result = new BlockType(tmp, IN[0].getNames());
		OUT = new BlockType[]{result};
		return OUT;
		
	}
	
	public BlockType[] substract(){
		double[] tmp = {IN[0].getValues()[0] - IN[1].getValues()[0]};
		BlockType result = new BlockType(tmp, IN[0].getNames());
		OUT = new BlockType[]{result};
		return OUT;
	}
	
	public BlockType[] divide(){
		double[] tmp = {IN[0].getValues()[0] / IN[1].getValues()[0]};
		BlockType result = new BlockType(tmp, IN[0].getNames());
		OUT = new BlockType[]{result};
		return OUT;
	}
	
	public BlockType[] multiply(){
		double[] tmp = {IN[0].getValues()[0] * IN[1].getValues()[0]};
		BlockType result = new BlockType(tmp, IN[0].getNames());
		OUT = new BlockType[]{result};
		return OUT;
	}

	/************ getters and setters ************/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
