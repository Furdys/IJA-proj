package test;

import ija.proj.block.*;

import org.junit.Assert;
import org.junit.After;
import org.junit.Test;


public class PortTest
{
    private Port port;
    
    
    @After
    public void tearDown()
    {
        port = null;
    }   

    @Test
    public void test_constructor()
    {       
       port = new Port("x", Port.inputType);
       Assert.assertEquals(1, port.getNames().size());
       Assert.assertTrue(port.getNames().contains("x"));
       
       String[] names = {"x", "y", "z"};
       port = new Port(names, Port.outputType);
       Assert.assertEquals(3, port.getNames().size());
       Assert.assertArrayEquals(names, port.getNames().toArray());
    }    


    @Test
    public void test_values()
    {
        port = new Port("x", Port.inputType);
        Assert.assertEquals(Double.NaN, port.getValue("x"), 0.02);
        
        Assert.assertTrue(port.setValue("x", 42));
        Assert.assertEquals(42, port.getValue("x"), 0.02);
        
    }
    
    @Test
    public void test_compatibily()
    {
        port = new Port("x", Port.inputType);
        
        Assert.assertTrue(port.compatibile(new Port("x", Port.outputType))); 
        
        Assert.assertFalse(port.compatibile(new Port("x", Port.inputType)));
        Assert.assertFalse(port.compatibile(new Port("y", Port.outputType)));
    }
}
