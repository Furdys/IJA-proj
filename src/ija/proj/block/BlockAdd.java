/**
 * Backend representation of "Add" block.
 * @brief Package for Block type "Add" from group "Arithmetic"
 * @file BlockAdd.java
 * @author Jiri Furda (xfurda00)
 */

package ija.proj.block;

/**
 * @brief The BlockAdd class is Block multiplying two float numbers
 */
public class BlockAdd extends BlockArithmetic 
{
    @Override
    void executeSpecific() /// @brief executeSpecific adds two float values from input Ports and saves it to output Port
    {
        this.getOutputPort(0).setValue("float", this.getInputPort(0).getValue("float") + this.getInputPort(1).getValue("float"));
    }
}
