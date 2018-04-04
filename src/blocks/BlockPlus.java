package blocks;

public class BlockPlus extends Block {

	BlockPlus(BlockType[] in) {
		super(in);
	}
	
	public BlockType[] execute() {
		double[] tmp = {IN[0].getValues()[0] + IN[1].getValues()[0]};
		BlockType result = new BlockType(tmp, IN[0].getNames());
		OUT = new BlockType[]{result};
		return OUT;
		
	}

}