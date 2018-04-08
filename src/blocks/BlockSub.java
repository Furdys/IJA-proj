package blocks;

public class BlockSub extends Block 
{	
    public BlockSub(Port[] in, Port[] out)
    {
        super(in, out);
    }

    @Override
    public Port[] execute()
    {
        String[] firstOpName = IN[0].getNames().toArray(new String[IN[0].getNames().size()]);
        String[] secondOpName = IN[1].getNames().toArray(new String[IN[1].getNames().size()]);
        Port result = new Port(firstOpName[0]);
        result.setValue(super.OUT[0].getName(), IN[0].getValue(firstOpName[0]) - IN[1].getValue(secondOpName[0]));
        super.setExecuted();
        OUT = new Port[]{result};
        return OUT;
    }
}
