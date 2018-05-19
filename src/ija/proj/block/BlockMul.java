/**
 * Backend representation of "Mul" block.
 * @brief Package for Block type "Mul" from group "Arithmetic"
 * @file BlockMul.java
 * @author Jiri Furda (xfurda00)
 */

package ija.proj.block;


/**
 * @brief The BlockMul class is Block multipling two float numbers
 */
public class BlockMul extends BlockArithmetic 
{
    @Override
    void executeSpecific()  /// @brief executeSpecific mutliplies two float values from input Ports and saves it to output Port
    {
        this.getOutputPort(0).setValue("float", this.getInputPort(0).getValue("float") * this.getInputPort(1).getValue("float"));
    }
}