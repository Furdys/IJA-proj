package test;

import ija.proj.block.*;
import ija.proj.block.arithmetic.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;


public class BlockTest
{
    private Block block;
    
    @Before
    public void setUp()
    {
    }
 
    @After
    public void tearDown()
    {
        block = null; 
    }   

    @Test
    public void test_blockAdd()
    {
       block = new BlockAdd(); 

       block.getInputPort(0).setValue("float", 4);
       block.getInputPort(1).setValue("float", 8);
       
       block.execute();
       
       Assert.assertEquals(12, block.getOutputPort(0).getValue("float"), 0.02);
       
    }    

    @Test
    public void test_blockSub()
    {
       block = new BlockSub(); 

       block.getInputPort(0).setValue("float", 4);
       block.getInputPort(1).setValue("float", 12);
       
       block.execute();
       
       Assert.assertEquals(-8, block.getOutputPort(0).getValue("float"), 0.02);
    }    
    
    @Test
    public void test_blockMul()
    {
       block = new BlockMul(); 

       block.getInputPort(0).setValue("float", 4);
       block.getInputPort(1).setValue("float", 5);
       
       block.execute();
       
       Assert.assertEquals(20, block.getOutputPort(0).getValue("float"), 0.02);
    }    
    
    @Test
    public void test_blockDiv()
    {
       block = new BlockDiv(); 

       block.getInputPort(0).setValue("float", 2);
       block.getInputPort(1).setValue("float", 10);
       
       block.execute();
       
       Assert.assertEquals(0.2, block.getOutputPort(0).getValue("float"), 0.02);
    }   

    @Test
    public void test_missingInput()
    {
       block = new BlockAdd(); 

       block.getInputPort(0).setValue("float", 2);
       
       Assert.assertFalse(block.execute());
       Assert.assertEquals(Double.NaN, block.getOutputPort(0).getValue("float"), 0.02);
    }   
    
    /*
    @Test
    public void test_inputPorts()
    {
       block = new BlockAdd(null, null); 
       Assert.assertNull(block.getInputPorts());
       
       block.setInputPorts(blockInput);
       Assert.assertArrayEquals(blockInput, block.getInputPorts());
    }   
    
    @Test
    public void test_outputPort()
    {
       block = new BlockAdd(null, null); 
       Assert.assertNull(block.getOutputPorts());
       
       block.setOutputPorts(blockInput);
       Assert.assertArrayEquals(blockInput, block.getOutputPorts());
    }   
    */
}
