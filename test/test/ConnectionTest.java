package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import ija.proj.block.*;
import ija.proj.block.arithmetic.*;


public class ConnectionTest {
    private Block blockA;
    private Block blockB;
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown()
    {
        blockA = null;
        blockB = null;
    }

    @Test
    public void test_basicConnection()
    {
       blockA = new BlockAdd(); 
       blockB = new BlockAdd(); 

       blockA.getInputPort(0).setValue("float", 6);
       blockA.getInputPort(1).setValue("float", 12);
       
       blockA.getOutputPort(0).setConnectedPort(blockB.getInputPort(0));
       
       blockB.getInputPort(1).setValue("float", 2);
       
       blockA.execute();
       blockB.execute();
       
       Assert.assertEquals(18, blockA.getOutputPort(0).getValue("float"), 0.02);
       Assert.assertEquals(20, blockB.getOutputPort(0).getValue("float"), 0.02);      
    }   
    
    @Test
    public void test_dependentConnection()
    {
       blockA = new BlockAdd(); 
       blockB = new BlockAdd(); 

       blockA.getInputPort(0).setValue("float", 0);
       blockA.getInputPort(1).setValue("float", 1);
       
       blockA.getOutputPort(0).setConnectedPort(blockB.getInputPort(0));
       
       blockB.getInputPort(1).setValue("float", 2);
       
       Assert.assertFalse(blockB.execute()); 
       Assert.assertEquals(Double.NaN, blockB.getOutputPort(0).getValue("float"), 0.02);
    } 
}
