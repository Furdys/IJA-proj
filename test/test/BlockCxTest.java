package test;

import ija.proj.block.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;


public class BlockCxTest
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
    public void test_blockCxAdd()
    {
        
       block = new BlockCxAdd(); 

       block.getInputPort(0).setValue("real", 2);
       block.getInputPort(0).setValue("imaginary", 5);
       
       block.getInputPort(1).setValue("real", 3);
       block.getInputPort(1).setValue("imaginary", -2);
       
       block.execute();
       /*
       Assert.assertEquals(5, block.getOutputPort(0).getValue("real"), 0.02);
       Assert.assertEquals(3, block.getOutputPort(0).getValue("imaginary"), 0.02);*/
    }    
/*
    @Test
    public void test_blockCxSub()
    {
       block = new BlockCxSub(); 

       block.getInputPort(0).setValue("real", 2);
       block.getInputPort(0).setValue("imaginary", 5);
       
       block.getInputPort(1).setValue("real", 3);
       block.getInputPort(1).setValue("imaginary", -2);
       
       block.execute();
       
       Assert.assertEquals(-1, block.getOutputPort(0).getValue("real"), 0.02);
       Assert.assertEquals(7, block.getOutputPort(0).getValue("imaginary"), 0.02);
    }    
    
    @Test
    public void test_blockCxMul()
    {
       block = new BlockCxMul(); 

       block.getInputPort(0).setValue("real", 2);
       block.getInputPort(0).setValue("imaginary", 5);
       
       block.getInputPort(1).setValue("real", 3);
       block.getInputPort(1).setValue("imaginary", -2);
       
       block.execute();
       
       Assert.assertEquals(16, block.getOutputPort(0).getValue("real"), 0.02);
       Assert.assertEquals(11, block.getOutputPort(0).getValue("imaginary"), 0.02);
    }    
    
    @Test
    public void test_blockCxDiv()
    {
       block = new BlockCxDiv(); 

       block.getInputPort(0).setValue("real", 3);
       block.getInputPort(0).setValue("imaginary", 5);
       
       block.getInputPort(1).setValue("real", 2);
       block.getInputPort(1).setValue("imaginary", -1);
       
       block.execute();
       
       Assert.assertEquals(1/5, block.getOutputPort(0).getValue("real"), 0.02);
       Assert.assertEquals(13/5, block.getOutputPort(0).getValue("imaginary"), 0.02);
    }   
*/
}