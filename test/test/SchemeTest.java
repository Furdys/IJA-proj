package test;

import ija.proj.block.*;
import ija.proj.scheme.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class SchemeTest {
    private Block blockA;
    private Block blockB;
    private Block blockC;
    private Scheme scheme;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
        blockA = null;
        blockB = null;
        scheme = null;
    }

    @Test
    public void test_nondependent_linear()
    {       
        scheme = new Scheme();
        blockA = new BlockAdd();
        blockB = new BlockAdd();
        blockC = new BlockAdd();

        blockA.getInputPort(0).setValue("float", 5);
        blockA.getInputPort(1).setValue("float", 4);
        
        blockB.getInputPort(1).setValue("float", 2);
        blockB.getInputPort(0).setConnectedPort(blockA.getOutputPort(0));
        
        blockC.getInputPort(0).setValue("float", 45);
        blockC.getInputPort(1).setConnectedPort(blockB.getOutputPort(0));
        
        //scheme.findNonDependentBlock(blockB);
        
        Assert.assertSame(blockA, scheme.findNonDependentBlock(blockA)); 
        Assert.assertSame(blockA, scheme.findNonDependentBlock(blockB)); 
        Assert.assertSame(blockA, scheme.findNonDependentBlock(blockC));    
    }    
}
