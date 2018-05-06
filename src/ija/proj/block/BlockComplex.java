/**
 * Backend representation of complex arithmetic blocks group.
 * @brief Package for Block classes from group "Complex arithmetic"
 * @file BlockComplex.java
 * @author Jiri Furda (xfurda00)
 */

package ija.proj.block;


/**
 * @brief The BlockComplex class is virtual class containing constructor for Block classes from group "Complex arithmetic"
 */
public abstract class BlockComplex extends Block
{
    public BlockComplex()   /// Constructor for Block classes from group "Complex arithmetic"
    {        
        super(new Port[]{new Port(new String[]{"real", "imaginary"}, Port.inputType), new Port(new String[]{"real", "imaginary"}, Port.inputType)},
                new Port(new String[]{"real", "imaginary"}, Port.outputType));
    }    
}
