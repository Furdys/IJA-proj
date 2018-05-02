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
    private Block blockD;
    private Block blockE;
    private Block blockF;
    private Block blockG;
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
        blockC = null;
        blockD = null;
        blockE = null;
        blockF = null;
        blockG = null;
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
        
        Assert.assertSame(blockA, scheme.findNonDependentBlock(blockA)); 
        Assert.assertSame(blockA, scheme.findNonDependentBlock(blockB)); 
        Assert.assertSame(blockA, scheme.findNonDependentBlock(blockC));    
    }    
    
    @Test
    public void test_run()
    {       
        blockA = new BlockAdd();
        blockB = new BlockAdd();
        blockC = new BlockAdd();
        
        scheme = new Scheme();
        scheme.addBlock(blockA);
        scheme.addBlock(blockB);
        scheme.addBlock(blockC);

        blockA.getInputPort(0).setValue("float", 1);
        blockA.getInputPort(1).setValue("float", 2);
        
        blockB.getInputPort(0).setConnectedPort(blockA.getOutputPort(0));
        blockB.getInputPort(1).setValue("float", 3);
        
        blockC.getInputPort(0).setValue("float", 4);
        blockC.getInputPort(1).setConnectedPort(blockB.getOutputPort(0));
        
        scheme.run();
        
        Assert.assertEquals(10, blockC.getOutputPort(0).getValue("float"), 0.02);
    }    
    
    @Test
    public void test_run_advanced()
    {
        blockA = new BlockAdd();
        blockB = new BlockSub();
        blockC = new BlockMul();      
        blockD = new BlockDiv();    
        blockE = new BlockAdd();    
        blockF = new BlockSub();    
        blockG = new BlockMul();    
        
        scheme = new Scheme();
        
        scheme.addBlock(blockD);
        scheme.addBlock(blockF);
        scheme.addBlock(blockG);
        scheme.addBlock(blockB);
        scheme.addBlock(blockE);
        scheme.addBlock(blockC);
        scheme.addBlock(blockA);
        
        
        blockA.getInputPort(0).setConnectedPort(blockB.getOutputPort(0));
        blockA.getInputPort(1).setConnectedPort(blockC.getOutputPort(0));
       
        blockB.getInputPort(0).setConnectedPort(blockD.getOutputPort(0));
        blockB.getInputPort(1).setConnectedPort(blockE.getOutputPort(0));
        
        blockC.getInputPort(0).setConnectedPort(blockF.getOutputPort(0));
        blockC.getInputPort(1).setValue("float", 8);  
        
        blockD.getInputPort(0).setValue("float", 3);  
        blockD.getInputPort(1).setValue("float", 4); 
        
        blockE.getInputPort(0).setValue("float", 5);  
        blockE.getInputPort(1).setConnectedPort(blockG.getOutputPort(0)); 
        
        blockF.getInputPort(0).setValue("float", 6);  
        blockF.getInputPort(1).setValue("float", 7); 
        
        blockG.getInputPort(0).setValue("float", 1);  
        blockG.getInputPort(1).setValue("float", 2);
        
        scheme.run();
        
        Assert.assertEquals(2, blockG.getOutputPort(0).getValue("float"), 0.02);
        Assert.assertEquals(-1, blockF.getOutputPort(0).getValue("float"), 0.02);
        Assert.assertEquals(7, blockE.getOutputPort(0).getValue("float"), 0.02);
        Assert.assertEquals(0.75, blockD.getOutputPort(0).getValue("float"), 0.02);
        Assert.assertEquals(-8, blockC.getOutputPort(0).getValue("float"), 0.02);
        Assert.assertEquals(-6.25, blockB.getOutputPort(0).getValue("float"), 0.02);
        Assert.assertEquals(-14.25, blockA.getOutputPort(0).getValue("float"), 0.02);
    }      
}
