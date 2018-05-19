/**
 * Backend representation of "And" block.
 * @brief Package for Block type "And" from group "Logic"
 * @file BlockAnd.java
 * @author Jiri Furda (xfurda00)
 */

package ija.proj.block;


/**
 * @brief The BlockAnd class is Block realising logical conjuction of two bool values.
 */
public class BlockAnd extends BlockLogic
{
    @Override
    void executeSpecific()  /// @brief executeSpecific realises logical conjuction of two bool values from input Ports and saves it to output Port
    {
        boolean resultB = BlockLogic.double2bool(getInputPort(0).getValue("bool")) & double2bool(getInputPort(1).getValue("bool"));
        double resultD = BlockLogic.bool2double(resultB);        
        
        this.getOutputPort(0).setValue("bool", resultD);
    }
}
