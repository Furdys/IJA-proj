package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import ija.proj.block.*;
import ija.proj.scheme.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SchemeFileTest
{
    private Block blockA;
    private Block blockB;
    private Block blockC;
    private Block blockD;
    private Scheme scheme;
    
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
        scheme = null;
        
        try
        {
            Files.deleteIfExists(Paths.get("test.scheme"));
        }
        catch (IOException ex)
        {
        }
    }
    
    @Test
    public void test_file()
    {
        blockA = new BlockAdd();
        blockB = new BlockSub();
        blockC = new BlockMul();
        blockD = new BlockDiv();
        
        scheme = new Scheme("Test");
        scheme.addBlock(blockA);
        scheme.addBlock(blockB);
        scheme.addBlock(blockC);
        scheme.addBlock(blockD);

        blockA.getInputPort(0).setConnectedPort(blockB.getOutputPort(0));
        blockA.getInputPort(1).setValue("float", 2);
        
        blockB.getInputPort(0).setConnectedPort(blockC.getOutputPort(0));
        blockB.getInputPort(1).setValue("float", 10);
        
        blockC.getInputPort(0).setValue("float", 3);
        blockC.getInputPort(1).setConnectedPort(blockD.getOutputPort(0));
        
        blockD.getInputPort(0).setValue("float", 10);
        blockD.getInputPort(1).setValue("float", 2);
        
        Assert.assertTrue(SchemeFile.save(scheme, "test.scheme"));
        
        scheme = null;
        scheme = SchemeFile.open("test.scheme");
        
        Assert.assertNotNull(scheme);        
        Assert.assertTrue(scheme.run());
        
        for(Block block : scheme.getBlocks())
        {
            if(block instanceof BlockAdd)
                Assert.assertEquals(7, block.getOutputPort(0).getValue("float"), 0.02);
        } 
    }
}
