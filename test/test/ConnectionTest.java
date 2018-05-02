package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import ija.proj.block.*;


public class ConnectionTest {
    public ConnectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void test_basicConnection()
    {
       Block blockA = new BlockAdd(); 
       Block blockB = new BlockAdd(); 

       blockA.getInputPort(0).setValue("float", 6);
       blockA.getInputPort(1).setValue("float", 12);
       
       blockA.getOutputPort(0).setConnectedPort(blockB.getInputPort(0));
       
       blockB.getInputPort(1).setValue("float", 2);
       
       
       blockA.execute();
       blockB.execute();
       
       Assert.assertEquals(18, blockA.getOutputPort(0).getValue("float"), 0.02);
       Assert.assertEquals(20, blockB.getOutputPort(0).getValue("float"), 0.02);
       
    }    
}
