package blocks;

public class BlockPlus extends Block {

	BlockPlus(Port[] in) {
		super(in);
	}
	
        @Override
	public Port[] execute() {
		String[] arr = (String[]) IN[0].getNames().toArray();
		Port result = new Port();
		result.setValue(arr[0], IN[0].content.get(arr[0]) + IN[1].content.get(arr[0]));
		OUT = new Port[]{result};
		return OUT;
		
	}

}
