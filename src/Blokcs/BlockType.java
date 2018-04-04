package Blokcs;

public class BlockType {
	
	private String[] names;
	private double[] values;
	
	public BlockType(double[] values, String[] names){
		this.names = names;
		this.values = values;
	}

	/**** setters and getters ****/
	public String[] getNames() {
		return this.names;
	}

	public double[] getValues() {
		return this.values;
	}
	
	public void setNameValue(double value, String name, int index) {
		this.values[index] = value;
		this.names[index] = name;
	}
	
	
	
	
}
