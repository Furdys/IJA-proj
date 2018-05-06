/**
 * Backend representation of "Div" block.
 * @brief Package for Block type "Div" from group "Arithmetic"
 * @file BlockDiv.java
 * @author Jiri Furda (xfurda00)
 */

package ija.proj.block;

public class BlockDiv extends BlockArithmetic 
{
    @Override
    void executeSpecific()  /// @brief executeSpecific divides two float values from input Ports and saves it to output Port
    {
        this.getOutputPort(0).setValue("float", this.getInputPort(0).getValue("float") / this.getInputPort(1).getValue("float"));
    }
}