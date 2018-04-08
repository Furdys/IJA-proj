import blocks.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;


public class BlockTest
{
    private Port[] blockInput;
    private Port[] blockOutput;
    private Block block;
    
    
    
    @Before
    public void setUp()
    {
       blockInput = new Port[2];
        
       blockInput[0] = new Port("x");
       blockInput[1] = new Port("y");
       
       blockInput[0].setValue("x", 2);
       blockInput[1].setValue("y", 10);
    }
 
    @After
    public void tearDown()
    {
        blockInput = null;
        blockOutput = null;
        block = null; 
    }   

    @Test
    public void test_blockAdd()
    {
       block = new BlockAdd(blockInput); 
       Assert.assertEquals(false, block.wasExecuted());
       
       blockOutput = block.execute();
       
       Assert.assertEquals(true, block.wasExecuted());
       Assert.assertEquals(12, blockOutput[0].getValue("x"), 0.02);
       
    }    

    @Test
    public void test_blockSub()
    {
       block = new BlockSub(blockInput); 
       Assert.assertEquals(false, block.wasExecuted());
       
       blockOutput = block.execute();
       
       Assert.assertEquals(true, block.wasExecuted());
       Assert.assertEquals(-8, blockOutput[0].getValue("x"), 0.02);
    }    
    
    @Test
    public void test_blockMul()
    {
       block = new BlockMul(blockInput); 
       Assert.assertEquals(false, block.wasExecuted());
        
       blockOutput = block.execute();
       
       Assert.assertEquals(true, block.wasExecuted());
       Assert.assertEquals(20, blockOutput[0].getValue("x"), 0.02);
    }    
    
    @Test
    public void test_blockDiv()
    {
       block = new BlockDiv(blockInput); 
       Assert.assertEquals(false, block.wasExecuted());
        
       blockOutput = block.execute();
       
       Assert.assertEquals(true, block.wasExecuted());
       Assert.assertEquals(0.2, blockOutput[0].getValue("x"), 0.02);
    }   
    
    @Test
    public void test_inputPorts()
    {
       block = new BlockAdd(null); 
       Assert.assertNull(block.getInputPorts());
       
       block.setInputPorts(blockInput);
       Assert.assertArrayEquals(blockInput, block.getInputPorts());
    }   
    
    @Test
    public void test_outputPort()
    {
       block = new BlockAdd(null); 
       Assert.assertNull(block.getOutputPorts());
       
       block.setOutputPorts(blockInput);
       Assert.assertArrayEquals(blockInput, block.getOutputPorts());
    }   
}