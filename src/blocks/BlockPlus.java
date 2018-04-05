package blocks;

public class BlockPlus extends Block {

	public BlockPlus(Port[] in) {
		super(in);
	}
	
        @Override
	public Port[] execute() {
		String[] arr = IN[0].getNames().toArray(new String[IN[0].getNames().size()]);
                String[] zabijuPetera = IN[1].getNames().toArray(new String[IN[1].getNames().size()]);
		Port result = new Port(arr[0]);
		result.setValue(arr[0], IN[0].getValue(arr[0]) + IN[1].getValue(zabijuPetera[0]));
                //result.getValue(arr[0]);
		OUT = new Port[]{result};
                //OUT[0].getValue("x");
		return OUT;
		
	}

}
