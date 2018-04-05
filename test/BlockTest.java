import blocks.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;



public class BlockTest
{
    private Port[] portAin = new Port[2];
    private Block blockA;
    private Port[] portAout;
    
    @Before
    public void setUp()
    {
       portAin[0] = new Port("x");
       portAin[1] = new Port("y");
       
       portAin[0].setValue("x", 1);
       portAin[1].setValue("y", 2);
       
       
       blockA = new BlockPlus(portAin);
    }
    
    @Test
    public void test01()
    {
       portAout = blockA.execute();
       
       Assert.assertEquals(3, portAout[0].getValue("x"), 0.02);
    }
}
