
public class Block {

	Block(String name){
		this.name = name;
	}
	
	
	private String name;
	private double IN; //change double to type later
	private double OUT; //change double to type later
	
	
	/***** basic operations
	 * create more later 
	 * or let user create his own
	 */
	
	public double add() {
		return this.IN + this.OUT;
	}
	
	public double substract(){
		return IN-OUT;
	}
	
	public double divide(){
		return IN/OUT;
	}
	
	public double multiply(){
		return IN*OUT;
	}

	/************ getters and setters ************/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getIN() {
		return IN;
	}

	public void setIN(double iN) {
		IN = iN;
	}

	public double getOUT() {
		return OUT;
	}

	public void setOUT(double oUT) {
		OUT = oUT;
	}
	
	
	
	
}
