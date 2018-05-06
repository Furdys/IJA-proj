/**
 * Backend representation of arithmetic blocks group.
 * @brief Package for Block classes from group "Arithmetic"
 * @file BlockArithmetic.java
 * @author Jiri Furda (xfurda00)
 */

package ija.proj.block;


/**
 * @brief The BlockArithmetic class is abstract class containing constructor for Block classes from group "Arithmetic"
 */
public abstract class BlockArithmetic extends Block
{
    public BlockArithmetic()    /// Constructor for Block classes from group "Arithmetic"
    {        
        super(new Port[]{new Port("float", Port.inputType), new Port("float", Port.inputType)}, new Port("float", Port.outputType));
    }    
}
