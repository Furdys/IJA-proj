package test;

import blocks.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;



public class BlockTest
{
    private Port[] portAin = new Port[2];
    private Block blockA;
    private Port[] portAout;
    
    private Port[] portBin = new Port[2];
    private Block blockB;
    private Port[] portBout;
    
    private Port[] portCin;
    private Block blockC;
    private Port[] portCout;
    
    @Before
    public void setUp()
    {
    	portAin[0] = new Port("x");
    	portAin[1] = new Port("y");
    	portAin[0].setValue("x", 15);
    	portAin[1].setValue("y", 20);
    	
    	portBin[0] = new Port("x");
    	portBin[1] = new Port("b");
    	portBin[0].setValue("x", 5);
    	portBin[1].setValue("b", 10);
    	
       
    }
    
    @Test
    public void testBlockPlus()
    {
    	blockA = new BlockPlus(portAin);
    	portAout = blockA.execute();
    	Assert.assertEquals(35, portAout[0].getValue("x"), 0.02);
    	
    	blockB = new BlockPlus(portBin);
    	portBout = blockB.execute();
    	Assert.assertEquals(15, portBout[0].getValue("x"), 0.02);
    	
    	portCin = new Port[]{portAout[0], portBout[0]};
    	blockC = new BlockPlus(portCin);
    	portCout = blockC.execute();
    	Assert.assertEquals(50, portCout[0].getValue("x"), 0.02);    	
    }
    
    @Test
    public void testBlockSub()
    {
    	blockA = new BlockSub(portAin);
    	portAout = blockA.execute();
    	Assert.assertEquals(-5, portAout[0].getValue("x"), 0.02);
    	
    	blockB = new BlockSub(portBin);
    	portBout = blockB.execute();
    	Assert.assertEquals(-5, portBout[0].getValue("x"), 0.02);
    	
    	portCin = new Port[]{portAout[0], portBout[0]};
    	blockC = new BlockSub(portCin);
    	portCout = blockC.execute();
    	Assert.assertEquals(0, portCout[0].getValue("x"), 0.02);    	
    }
    
    @Test
    public void testBlockMul()
    {
    	blockA = new BlockMul(portAin);
    	portAout = blockA.execute();
    	Assert.assertEquals(300, portAout[0].getValue("x"), 0.02);
    	
    	blockB = new BlockMul(portBin);
    	portBout = blockB.execute();
    	Assert.assertEquals(50, portBout[0].getValue("x"), 0.02);
    	
    	portCin = new Port[]{portAout[0], portBout[0]};
    	blockC = new BlockMul(portCin);
    	portCout = blockC.execute();
    	Assert.assertEquals(15000, portCout[0].getValue("x"), 0.02);    	
    }
    
    @Test
    public void testBlockDiv()
    {
    	blockA = new BlockDiv(portAin);
    	portAout = blockA.execute();
    	Assert.assertEquals(0.75, portAout[0].getValue("x"), 0.02);
    	
    	blockB = new BlockDiv(portBin);
    	portBout = blockB.execute();
    	Assert.assertEquals(0.5, portBout[0].getValue("x"), 0.02);
    	
    	portCin = new Port[]{portAout[0], portBout[0]};
    	blockC = new BlockDiv(portCin);
    	portCout = blockC.execute();
    	Assert.assertEquals(1.5, portCout[0].getValue("x"), 0.02);    	
    }
    
    @Test
    public void mixed01()
    {
    	blockA = new BlockPlus(portAin);
    	portAout = blockA.execute();
    	Assert.assertEquals(35, portAout[0].getValue("x"), 0.02);
    	
    	blockB = new BlockDiv(portBin);
    	portBout = blockB.execute();
    	Assert.assertEquals(0.5, portBout[0].getValue("x"), 0.02);
    	
    	portCin = new Port[]{portAout[0], portBout[0]};
    	blockC = new BlockMul(portCin);
    	portCout = blockC.execute();
    	Assert.assertEquals(17.5, portCout[0].getValue("x"), 0.02);    	
    }
    
    @Test
    public void mixed02()
    {
    	blockA = new BlockMul(portAin);
    	portAout = blockA.execute();
    	Assert.assertEquals(300, portAout[0].getValue("x"), 0.02);
    	
    	blockB = new BlockDiv(portBin);
    	portBout = blockB.execute();
    	Assert.assertEquals(0.5, portBout[0].getValue("x"), 0.02);
    	
    	portCin = new Port[]{portAout[0], portBout[0]};
    	blockC = new BlockMul(portCin);
    	portCout = blockC.execute();
    	Assert.assertEquals(150, portCout[0].getValue("x"), 0.02);    	
    }
    
    @Test
    public void mixed03()
    {
    	blockA = new BlockSub(portAin);
    	portAout = blockA.execute();
    	Assert.assertEquals(-5, portAout[0].getValue("x"), 0.02);
    	
    	blockB = new BlockPlus(portBin);
    	portBout = blockB.execute();
    	Assert.assertEquals(15, portBout[0].getValue("x"), 0.02);
    	
    	portCin = new Port[]{portAout[0], portBout[0]};
    	blockC = new BlockMul(portCin);
    	portCout = blockC.execute();
    	Assert.assertEquals(-75, portCout[0].getValue("x"), 0.02);    	
    }
    
    @Test
    public void mixed04()
    {
    	blockA = new BlockDiv(portAin);
    	portAout = blockA.execute();
    	Assert.assertEquals(0.75, portAout[0].getValue("x"), 0.02);
    	
    	blockB = new BlockDiv(portBin);
    	portBout = blockB.execute();
    	Assert.assertEquals(0.5, portBout[0].getValue("x"), 0.02);
    	
    	portCin = new Port[]{portAout[0], portBout[0]};
    	blockC = new BlockMul(portCin);
    	portCout = blockC.execute();
    	Assert.assertEquals(0.375, portCout[0].getValue("x"), 0.02);    	
    }
    
}
