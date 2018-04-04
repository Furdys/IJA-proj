package Blokcs;

public class BlockSub extends Block {

	
	BlockSub(BlockType[] in) {
		super(in);
	}

	public BlockType[] executet(){
		double[] tmp = {IN[0].getValues()[0] - IN[1].getValues()[0]};
		BlockType result = new BlockType(tmp, IN[0].getNames());
		OUT = new BlockType[]{result};
		return OUT;
	}
}
